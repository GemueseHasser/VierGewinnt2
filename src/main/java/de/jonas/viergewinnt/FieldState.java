package de.jonas.viergewinnt;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.awt.Color;

@NotNull
public enum FieldState {

    @NotNull
    NONE(
        Color.WHITE,
        new Data.InfoFrameText(
            "Es ist unentscheiden!",
            "Okay...",
            "Unentschieden!"
        )
    ),
    @NotNull
    USER(
        Color.YELLOW,
        new Data.InfoFrameText(
            "Du hast gewonnen!",
            "Cool!",
            "Sieg!"
        )
    ),
    @NotNull
    COMPUTER(
        Color.RED,
        new Data.InfoFrameText(
            "Der Computer hat gewonnen!",
            "Schade",
            "Niederlage!"
        )
    );

    @NotNull
    @Getter
    private final Color color;

    @NotNull
    @Getter
    private final Data.InfoFrameText info;

    @NotNull
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
