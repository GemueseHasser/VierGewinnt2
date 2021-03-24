package de.jonas.viergewinnt;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

import java.util.ArrayList;
import java.util.List;

public class WinHandler {

    private static final List<WinListener> WIN_LISTENERS = new ArrayList<>();

    @Getter
    private static WinHandler instance;

    public WinHandler() {
        instance = this;
    }

    public void checkWin() {
        if (hasWon(FieldState.USER)) {
            for (final WinListener listener : WIN_LISTENERS) {
                listener.win(FieldState.USER);
            }
            return;
        }
        if (hasWon(FieldState.COMPUTER)) {
            for (final WinListener listener : WIN_LISTENERS) {
                listener.win(FieldState.COMPUTER);
            }
        }
    }

    private boolean hasWon(@NotNull final FieldState state) {
        for (final Data.WinPossibility winPossibility : Data.WIN_POSSIBILITIES) {
            if (isSame(winPossibility.getChance(), state)) {
                return true;
            }
        }
        return false;
    }

    private boolean isSame(
        @Range(from = 0, to = 3) final int[] fields,
        @NotNull final FieldState state
    ) {
        for (final int field : fields) {
            if (!Data.CIRCLE_LOCATIONS[field].getState().equals(state)) {
                return false;
            }
        }
        return true;
    }

    public static void addListener(@NotNull final WinListener listener) {
        WIN_LISTENERS.add(listener);
    }

}
