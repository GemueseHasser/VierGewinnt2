package de.jonas.viergewinnt;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ThreadLocalRandom;

import static de.jonas.viergewinnt.FieldState.COMPUTER;
import static de.jonas.viergewinnt.FieldState.NONE;
import static de.jonas.viergewinnt.FieldState.USER;

@NotNull
public class Bot {

    private static final int TIMER_PERIOD = 100;

    public void start() {
        final Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (!FieldState.getState().equals(COMPUTER)) {
                    return;
                }
                for (@NotNull final Data.WinPossibility possibility : Data.WIN_POSSIBILITIES) {
                    // check if computer can win
                    if (canWin(possibility, COMPUTER) != -1) {
                        // try to win
                        placeChip(canWin(possibility, COMPUTER));
                        FieldState.setState(FieldState.USER);
                        WinHandler.getInstance().checkWin();
                        return;
                    }
                    // check if player can win
                    if (canWin(possibility, USER) != -1) {
                        // try to mess up the users win chance
                        placeChip(canWin(possibility, USER));
                        FieldState.setState(FieldState.USER);
                        WinHandler.getInstance().checkWin();
                        return;
                    }
                }
                // place random chip
                placeRandom();
                FieldState.setState(FieldState.USER);
                WinHandler.getInstance().checkWin();
            }
        }, 0, TIMER_PERIOD);
    }

    @NotNull
    @Range(from = 0, to = Integer.MAX_VALUE)
    private Integer canWin(
        @NotNull final Data.WinPossibility possibility,
        @NotNull final FieldState state
    ) {
        final int[] chance = possibility.getChance();
        for (int i = 0; i < chance.length; i++) {
            if (isEmpty(i, chance, state)) {
                if (!canPlace(chance[i])) continue;
                return chance[i];
            }
        }
        return -1;
    }

    private boolean isEmpty(
        @Range(from = 0, to = 3) final int empty, final int[] chance,
        @NotNull final FieldState state
    ) {
        for (int i = 0; i < chance.length; i++) {
            if (i != empty && !Data.CIRCLE_LOCATIONS[chance[i]].getState().equals(state)) return false;
        }
        return Data.CIRCLE_LOCATIONS[chance[empty]].getState().equals(FieldState.NONE);
    }

    private void placeChip(final int field) {
        Data.CIRCLE_LOCATIONS[field].setState(COMPUTER);
    }

    private void placeRandom() {
        Data.Column column = Data.COLUMNS[getRandom()];
        while (true) {
            for (final int field : column.getColumn()) {
                if (!Data.CIRCLE_LOCATIONS[field].getState().equals(FieldState.NONE)) {
                    continue;
                }
                Data.CIRCLE_LOCATIONS[field].setState(COMPUTER);
                WinHandler.getInstance().checkWin();
                FieldState.setState(FieldState.USER);
                return;
            }
            column = Data.COLUMNS[getRandom()];
        }
    }

    private boolean canPlace(final int field) {
        final Data.Column column = getColumn(field);
        assert column != null;
        for (final int columnField : column.getColumn()) {
            if (!Data.CIRCLE_LOCATIONS[columnField].getState().equals(NONE)) continue;
            return columnField == field;
        }
        return false;
    }

    @Nullable
    private Data.Column getColumn(final int field) {
        for (@NotNull final Data.Column column : Data.COLUMNS) {
            for (final int columnField : column.getColumn()) {
                if (columnField == field) return column;
            }
        }
        return null;
    }

    @Range(from = 0, to = Integer.MAX_VALUE)
    private int getRandom() {
        return ThreadLocalRandom.current().nextInt(0, Data.COLUMNS.length);
    }

}
