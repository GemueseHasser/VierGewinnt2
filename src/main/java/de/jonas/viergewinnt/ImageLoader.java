package de.jonas.viergewinnt;

import lombok.Getter;
import lombok.SneakyThrows;

import javax.imageio.ImageIO;

import java.awt.Image;

public class ImageLoader {

    @Getter
    private static Image redChip;

    @Getter
    private static Image yellowChip;

    @SneakyThrows
    public ImageLoader() {
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
