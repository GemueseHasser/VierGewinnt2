package de.jonas.viergewinnt;

import lombok.Getter;
import lombok.SneakyThrows;

import javax.imageio.ImageIO;

import java.awt.Image;

/**
 * Der {@link ImageLoader} lädt alle nötigen {@link Image Bilder} für die Anwendung.
 */
public class ImageLoader {

    //<editor-fold desc="CONSTANTS">
    /** Die Instanz-Variable, womit man auf den {@link ImageLoader} zugreifen kann. */
    @Getter
    private static final ImageLoader INSTANCE = new ImageLoader();
    //</editor-fold>


    //<editor-fold desc="STATIC FIELDS">
    /** Der rote Chip des Spiels. */
    @Getter
    private static Image redChip;
    /** Der gelbe Chip des Spiels. */
    @Getter
    private static Image yellowChip;
    //</editor-fold>


    /**
     * Es werden alle Bilder neu geladen.
     */
    @SneakyThrows
    public void loadImages() {
        // load red chip
        final Image redChipOriginal = ImageIO.read(
            getClass().getResource("/redChip.png")
        );
        redChip = redChipOriginal.getScaledInstance(
            Data.CircleLocation.SIZE,
            Data.CircleLocation.SIZE,
            Image.SCALE_SMOOTH
        );
        // load yellow chip
        final Image yellowChipOriginal = ImageIO.read(
            getClass().getResource("/yellowChip.png")
        );
        yellowChip = yellowChipOriginal.getScaledInstance(
            Data.CircleLocation.SIZE,
            Data.CircleLocation.SIZE,
            Image.SCALE_SMOOTH
        );
    }

}
