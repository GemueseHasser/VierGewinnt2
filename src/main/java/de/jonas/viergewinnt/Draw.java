package de.jonas.viergewinnt;

import org.jetbrains.annotations.NotNull;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

/**
 * Mithilfe dieser Klasse, wird das Spiel ständig neu auf das {@link JFrame Fenster} gezeichnet, sodass sich die
 * einzelnen Felder updaten, sobald sich dessen {@link FieldState} ändert.
 */
@NotNull
public final class Draw extends JLabel {

    //<editor-fold desc="CONSTANTS">
    /** Der Abstand von dem oberen Rand des {@link JFrame Fensters}, ab dem gezeichnet wird. */
    public static final int MARGIN_TOP = 50;
    /** Der Abstand von links und rechts des {@link JFrame Fensters}, ab dem gezeichnet wird. */
    public static final int MARGIN_LEFT_AND_RIGHT = 20;
    /** Der Abstand, der auf der rechten Seite hinzugefügt wird. */
    private static final int MARGIN_RIGHT_ADDITION = 10;
    //</editor-fold>


    //<editor-fold desc="implementation">
    @Override
    protected void paintComponent(@NotNull final Graphics g) {
        super.paintComponent(g);

        @NotNull
        final Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // draw background
        g.setColor(Color.BLUE);
        g.fillRect(
            MARGIN_LEFT_AND_RIGHT,
            MARGIN_TOP,
            this.getWidth() - ((2 * MARGIN_LEFT_AND_RIGHT) + MARGIN_RIGHT_ADDITION),
            this.getHeight() - MARGIN_TOP
        );

        // draw chips
        for (@NotNull final Data.CircleLocation loc : Data.CIRCLE_LOCATIONS) {
            g.setColor(loc.getState().getColor());
            g.fillOval(loc.getX(), loc.getY(), loc.getSize(), loc.getSize());
        }

        repaint();
    }
    //</editor-fold>
}
