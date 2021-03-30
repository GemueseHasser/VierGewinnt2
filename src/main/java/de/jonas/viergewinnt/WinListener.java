package de.jonas.viergewinnt;

import org.jetbrains.annotations.NotNull;

/**
 * Der {@link WinListener} wird ausgelöst, sobald das Spiel durch einen Sieg oder ein Unentschieden beendet wird.
 */
@NotNull
public interface WinListener {

    /**
     * Sobald das Spiel durch einen Sieg oder ein Unentschieden beendet wird, wird diese Methode ausgelöst.
     *
     * @param state Der {@link FieldState}, der gewonnen hat, bzw. wodurch das Spiel beendet wurde.
     */
    void win(@NotNull FieldState state);

}
