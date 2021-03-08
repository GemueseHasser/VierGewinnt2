package de.jonas.viergewinnt;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.awt.Color;

public enum FieldState {

    NONE(
        Color.WHITE
    ),
    USER(
        Color.YELLOW
    ),
    COMPUTER(
        Color.RED
    );

    @Getter
    private final Color color;

    FieldState(
        @NotNull final Color color
    ) {
        this.color = color;
    }

}
