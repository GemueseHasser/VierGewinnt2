package de.jonas.viergewinnt;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Mithilfe des {@link ActionHandler ActionHandlers} wird das anklicken einer Spalte verarbeitet, sodass man als Nutzer
 * Chips setzen kann.
 */
@NotNull
public final class ActionHandler implements ActionListener {

    //<editor-fold desc="LOCAL FIELDS">
    /** Die Nummer des {@link JButton Buttons}, welcher angeklickt wurde (von links). */
    private final int buttonNumber;
    //</editor-fold>


    //<editor-fold desc="CONSTRUCTORS">
    /**
     * Erzeugt einen neuen {@link ActionHandler}. Mithilfe dieses Konstuktors, wird die Nummer des {@link JButton
     * Buttons}, welcher angeklickt wurde (von links) deklariert.
     *
     * @param buttonNumber Die Nummer des {@link JButton Buttons}, welcher angeklickt wurde (von links).
     */
    public ActionHandler(@Range(from = 0, to = Integer.MAX_VALUE) final int buttonNumber) {
        this.buttonNumber = buttonNumber;
    }
    //</editor-fold>


    //<editor-fold desc="implementation">
    @Override
    public void actionPerformed(@NotNull final ActionEvent actionEvent) {
        if (!FieldState.getState().equals(FieldState.USER)) {
            return;
        }
        final Data.Column column = Data.COLUMNS[buttonNumber];
        for (final int field : column.getColumn()) {
            if (!Data.CIRCLE_LOCATIONS[field].getState().equals(FieldState.NONE)) {
                continue;
            }
            Data.CIRCLE_LOCATIONS[field].setState(FieldState.USER);
            FieldState.setState(FieldState.COMPUTER);
            return;
        }
    }
    //</editor-fold>
}
