package de.jonas.viergewinnt;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

import static de.jonas.viergewinnt.FieldState.COMPUTER;
import static de.jonas.viergewinnt.FieldState.NONE;
import static de.jonas.viergewinnt.FieldState.USER;

@NotNull
public class WinHandler {

    @NotNull
    @Getter
    private static WinHandler instance;

    @NotNull
    private static WinListener winListener;

    @NotNull
    public WinHandler() {
        instance = this;
    }

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

    private boolean hasWon(@NotNull final FieldState state) {
        for (@NotNull final Data.WinPossibility winPossibility : Data.WIN_POSSIBILITIES) {
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

    private boolean isIndecisive() {
        for (@NotNull final Data.CircleLocation location : Data.CIRCLE_LOCATIONS) {
            if (location.getState().equals(NONE)) {
                return false;
            }
        }
        return true;
    }

    public static void setWinListener(@NotNull final WinListener listener) {
        winListener = listener;
    }

}
