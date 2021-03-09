package de.jonas.viergewinnt;

import de.jonas.VierGewinnt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

public class WinHandler {

    public void checkWin() {
        if (hasWon(FieldState.USER)) {
            new VierGewinnt().win(FieldState.USER);
            return;
        }
        if (hasWon(FieldState.COMPUTER)) {
            new VierGewinnt().win(FieldState.COMPUTER);
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

}
