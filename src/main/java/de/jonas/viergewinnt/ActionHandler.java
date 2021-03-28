package de.jonas.viergewinnt;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionHandler implements ActionListener {

    private final int buttonNumber;

    public ActionHandler(@Range(from = 0, to = Integer.MAX_VALUE) final int buttonNumber) {
        this.buttonNumber = buttonNumber;
    }

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
            WinHandler.getInstance().checkWin();
            FieldState.setState(FieldState.COMPUTER);
            return;
        }
    }
}
