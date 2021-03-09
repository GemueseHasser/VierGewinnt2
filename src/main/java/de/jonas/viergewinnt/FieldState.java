package de.jonas.viergewinnt;

import lombok.Getter;
import lombok.Setter;
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

    @Getter
    @Setter
    private static FieldState state;

    FieldState(
        @NotNull final Color color
    ) {
        this.color = color;
    }

}
