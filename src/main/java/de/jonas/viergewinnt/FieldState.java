package de.jonas.viergewinnt;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.awt.Color;

public enum FieldState {

    NONE(
        Color.WHITE,
        new Data.InfoFrameText(
            "Es ist unentscheiden!",
            "Okay...",
            "Unentschieden!"
        )
    ),
    USER(
        Color.YELLOW,
        new Data.InfoFrameText(
            "Du hast gewonnen!",
            "Cool!",
            "Sieg!"
        )
    ),
    COMPUTER(
        Color.RED,
        new Data.InfoFrameText(
            "Der Computer hat gewonnen!",
            "Schade",
            "Niederlage!"
        )
    );

    @Getter
    private final Color color;

    @Getter
    private final Data.InfoFrameText info;

    @Getter
    @Setter
    private static FieldState state;

    FieldState(
        @NotNull final Color color,
        @NotNull final Data.InfoFrameText info
    ) {
        this.color = color;
        this.info = info;
    }

}
