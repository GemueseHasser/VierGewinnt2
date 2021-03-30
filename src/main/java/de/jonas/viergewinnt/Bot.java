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

/**
 * Ein {@link Bot} setzt seinen Chip automatisch, sobald der {@link FieldState Computer} an der Reihe ist. Zuerst wird
 * überprüft, ob der {@link Bot} seinen Chip so setzten kann, sodass er gewinnt, dann wird überprüft, ob der {@link
 * FieldState User} gewinnen kann und wenn das der Fall ist, wird es (sofern es möglich ist) "versaut". Und wenn der
 * {@link Bot} nichts mithilfe von künstlicher Intelligenz ausüben kann, platziert er den Chip in einer zufälligen
 * Spalte.
 */
@NotNull
public final class Bot {

    //<editor-fold desc="CONSTANTS">
    /** Das Intervall, in dem der {@link Timer} seine Aktion ausführt, um alles für den {@link Bot} neu zu berechnen. */
    private static final int TIMER_PERIOD = 2000;
    //</editor-fold>


    /**
     * <p>Startet den {@link Bot}. Um den {@link Bot} zu starten, wird hauptsächlich ein {@link Timer} gestartet,
     * welcher in einem bestimmten Intervall wiederholend ausgeführt wird und womit der Bot und dessen Aktion
     * kontinuierlich ausgeführt wird.</p>
     *
     * <p>Sollte der {@link FieldState Computer} nicht an der Reihe sein, wird nichts ausgeführt. Wenn doch, wird
     * zuerst überprüft, ob der {@link Bot} in dem nächsten Zug gewinnen kann. Wenn dies nicht der Fall ist, wird
     * überprüft, ob der {@link FieldState User} im nächsten Zug gewinnen kann. Zufalls dies so ist, versucht der {@link
     * Bot} dem {@link FieldState User} diesen Sieg zu "versauen". Und * wenn der {@link Bot} nichts mithilfe von
     * künstlicher Intelligenz ausüben kann, platziert er den Chip in einer * zufälligen Spalte. </p>
     */
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
                        WinHandler.getINSTANCE().checkWin();
                        return;
                    }
                    // check if player can win
                    if (canWin(possibility, USER) != -1) {
                        // try to mess up the users win chance
                        placeChip(canWin(possibility, USER));
                        FieldState.setState(FieldState.USER);
                        WinHandler.getINSTANCE().checkWin();
                        return;
                    }
                }
                // place random chip
                placeRandom();
                FieldState.setState(FieldState.USER);
                WinHandler.getINSTANCE().checkWin();
            }
        }, 0, TIMER_PERIOD);
    }

    /**
     * Es wird überprüft, ob eine bestimmte {@link Data.WinPossibility} für einen bestimmten {@link FieldState} im
     * nächsten Zug zustande kommen kann. Es werden alle Möglichkeiten dieser einen {@link Data.WinPossibility}
     * überprüft.
     *
     * @param possibility Die {@link Data.WinPossibility}, die überprüft wird.
     * @param state       Der {@link FieldState}, auf den die {@link Data.WinPossibility} überprüft wird.
     *
     * @return Das Feld, womit der bestimmte {@link FieldState} im nächsten Zug gewinnen kann. Wenn er nicht gewinnen
     *     kann, wird einfach '-1' returned.
     */
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

    /**
     * Überprüft, ob genau ein bestimmter Chip aus einer {@link Data.WinPossibility} noch frei ist und alle anderen
     * denselben {@link FieldState} sonst besitzen.
     *
     * @param empty  Das Chip-Feld, welches geprüft wird, ob es noch frei ist.
     * @param chance Die {@link Data.WinPossibility}, die auf genau einen bestimmten freien Chip überprüft wird.
     * @param state  Der {@link FieldState}, den alle anderen Chip-Felder haben sollen.
     *
     * @return Ist genau ein bestimmter Chip frei (und alle anderen haben denselben bestimmten {@link FieldState})?
     */
    private boolean isEmpty(
        @Range(from = 0, to = 3) final int empty,
        final int[] chance,
        @NotNull final FieldState state
    ) {
        for (int i = 0; i < chance.length; i++) {
            if (i != empty && !Data.CIRCLE_LOCATIONS[chance[i]].getState().equals(state)) return false;
        }
        return Data.CIRCLE_LOCATIONS[chance[empty]].getState().equals(FieldState.NONE);
    }

    /**
     * Lässt den {@link Bot} in einem bestimmten Feld seinen Chip platzieren.
     *
     * @param field Das bestimmte Feld, in dem der Chip platziert werden soll.
     */
    private void placeChip(final int field) {
        Data.CIRCLE_LOCATIONS[field].setState(COMPUTER);
    }

    /**
     * Lässt den {@link Bot} seinen Chip an einer zufälligen Position platzieren.
     */
    private void placeRandom() {
        Data.Column column = Data.COLUMNS[getRandom()];
        while (true) {
            for (final int field : column.getColumn()) {
                if (!Data.CIRCLE_LOCATIONS[field].getState().equals(FieldState.NONE)) {
                    continue;
                }
                Data.CIRCLE_LOCATIONS[field].setState(COMPUTER);
                return;
            }
            column = Data.COLUMNS[getRandom()];
        }
    }

    /**
     * Es wird überprüft, ob ein bestimmtes Feld noch frei ist bzw. ob darunter auch ein Chip liegt. Somit wird
     * überprüft, ob es möglich ist einen Chip an einem bestimmten Feld zu platzieren.
     *
     * @param field Das Feld, welches überprüft wird.
     *
     * @return Kann der Chip an diesem bestimmten Feld platziert werden?
     */
    private boolean canPlace(final int field) {
        final Data.Column column = getColumn(field);
        assert column != null;
        for (final int columnField : column.getColumn()) {
            if (!Data.CIRCLE_LOCATIONS[columnField].getState().equals(NONE)) continue;
            return columnField == field;
        }
        return false;
    }

    /**
     * Berechnet die {@link Data.Column Spalte} eines bestimmten Feldes.
     *
     * @param field Das Feld, dessen {@link Data.Column Spalte} berechnet werden soll.
     *
     * @return Die Spalte des Feldes.
     */
    @Nullable
    private Data.Column getColumn(final int field) {
        for (@NotNull final Data.Column column : Data.COLUMNS) {
            for (final int columnField : column.getColumn()) {
                if (columnField == field) return column;
            }
        }
        return null;
    }

    /**
     * Gibt eine zufällige {@link Integer Zahl} zwischen 0 und der Anzahl an Spalten zurück.
     *
     * @return Eine zufällige {@link Integer Zahl} zwischen 0 und der Anzahl an Spalten.
     */
    @Range(from = 0, to = Integer.MAX_VALUE)
    private int getRandom() {
        return ThreadLocalRandom.current().nextInt(0, Data.COLUMNS.length);
    }

}
