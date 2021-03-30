package de.jonas.viergewinnt;

import de.jonas.VierGewinnt;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Image;

/**
 * Ein {@link FieldState} beschreibt den Status einer bestimmten {@link Data.CircleLocation}.
 */
@NotNull
public enum FieldState {

    /** Ein leerer {@link FieldState} mit seiner Farbe und dem {@link Data.InfoFrameText}. */
    @NotNull
    NONE(
        null,
        new Data.InfoFrameText(
            "Es ist unentscheiden!",
            "Okay...",
            "Unentschieden!"
        )
    ),
    /** Der {@link FieldState} des Nutzers, mit seiner Farbe und dem {@link Data.InfoFrameText}. */
    @NotNull
    USER(
        ImageLoader.getRedChip(),
        new Data.InfoFrameText(
            "Du hast gewonnen!",
            "Cool!",
            "Sieg!"
        )
    ),
    /** Der {@link FieldState} des Computers, mit seiner Farbe und dem {@link Data.InfoFrameText}. */
    @NotNull
    COMPUTER(
        ImageLoader.getYellowChip(),
        new Data.InfoFrameText(
            "Der Computer hat gewonnen!",
            "Schade",
            "Niederlage!"
        )
    );

    //<editor-fold desc="STATIC FIELDS">
    /** Der statische Status des gesamten Spiels. */
    @NotNull
    @Getter
    @Setter
    private static FieldState state;
    //</editor-fold>

    //<editor-fold desc="LOCAL FIELDS">
    /** Die {@link Color Farbe} des jeweiligen {@link FieldState}. */
    @Nullable
    @Getter
    private final Image image;

    /** Die {@link Data.InfoFrameText Info} des jeweiligen {@link FieldState} für das {@link JFrame Fenster}. */
    @NotNull
    @Getter
    private final Data.InfoFrameText info;
    //</editor-fold>


    //<editor-fold desc="CONSTRUCTORS">
    /**
     * Erzeugt mithilfe einer {@link Color Farbe} und einer {@link Data.InfoFrameText Info} einen neuen und vollständig
     * unabhängigen {@link FieldState}.
     *
     * @param image Das {@link Image Bild}, des {@link FieldState}.
     * @param info  Die {@link Data.InfoFrameText Info} des {@link FieldState}.
     */
    FieldState(
        @Nullable final Image image,
        @NotNull final Data.InfoFrameText info
    ) {
        this.image = image;
        this.info = info;
    }
    //</editor-fold>

}
