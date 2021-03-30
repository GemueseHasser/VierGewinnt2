package de.jonas.viergewinnt;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

import static de.jonas.viergewinnt.FieldState.COMPUTER;
import static de.jonas.viergewinnt.FieldState.NONE;
import static de.jonas.viergewinnt.FieldState.USER;

/**
 * Mithilfe des {@link WinHandler WinHandlers} kann man überprüfen, ob ein bestimmter Nutzer gewonnen hat. Zufalls dies
 * so ist, wird ein {@link WinListener} ausgelöst.
 */
@NotNull
public final class WinHandler {

    //<editor-fold desc="CONSTANTS">
    /**
     * Die Instanz-Variable des {@link WinHandler WinHandlers}, mit der man auf die Instanz zugreifen kann, ohne den
     * {@link WinHandler} jedes Mal neu zu instanziieren.
     */
    @Getter
    private static final WinHandler INSTANCE = new WinHandler();
    //</editor-fold>


    //<editor-fold desc="STATIC FIELDS">
    /** Der {@link WinListener}, der ausgelöst wird, sobald das Spiel beendet wurde. */
    private WinListener winListener;
    //</editor-fold>

    /**
     * Überprüft das Spiel auf einen Sieg oder ein Unentschieden. Es wird zu Gunsten des Users immer zuerst der User auf
     * einen Sieg überprüft, dann der Computer und dann wird das Spiel auf ein Unentschieden geprüft.
     */
    public void checkWin() {
        if (hasWon(USER)) {
            winListener.win(USER);
            return;
        }
        if (hasWon(COMPUTER)) {
            winListener.win(COMPUTER);
            return;
        }
        if (isIndecisive()) {
            winListener.win(NONE);
        }
    }

    /**
     * Setzt den {@link WinListener} des {@link WinHandler} neu.
     *
     * @param listener Der neue {@link WinListener} für den {@link WinHandler}.
     */
    public void setWinListener(@NotNull final WinListener listener) {
        this.winListener = listener;
    }

    /**
     * Prüft, ob ein bestimmter {@link FieldState} gewonnen hat.
     *
     * @param state Der {@link FieldState}, der auf einen Sieg geprüft hat.
     *
     * @return Wenn der gewisse {@link FieldState} gewonnen hat {@code true}, ansonsten {@code false}.
     */
    private boolean hasWon(@NotNull final FieldState state) {
        for (@NotNull final Data.WinPossibility winPossibility : Data.WIN_POSSIBILITIES) {
            if (isSame(winPossibility.getChance(), state)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Prüft, ob mehrere gewisse Felder denselben {@link FieldState} haben.
     *
     * @param fields Die Felder, die überprüft werden.
     * @param state  Der {@link FieldState}, auf den die Felder geprüft werden sollen.
     *
     * @return Wenn die Felder alle denselben {@link FieldState} haben {@code true}, ansonsten {@code false}.
     */
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

    /**
     * Prüft, ob ein Unentschieden zustande gekommen ist.
     *
     * @return Wenn ein Unentschieden zustande gekommen ist, {@code true}, ansonsten {@code false}.
     */
    private boolean isIndecisive() {
        for (@NotNull final Data.CircleLocation location : Data.CIRCLE_LOCATIONS) {
            if (location.getState().equals(NONE)) {
                return false;
            }
        }
        return true;
    }

}
