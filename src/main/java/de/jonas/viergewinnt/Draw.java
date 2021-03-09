package de.jonas.viergewinnt;

import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class Draw extends JLabel {

    public static final int MARGIN_TOP = 50;
    public static final int MARGIN_LEFT_AND_RIGHT = 20;

    private static final int MARGIN_RIGHT_ADDITION = 10;

    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);

        final Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g.setColor(Color.BLUE);
        g.fillRect(
            MARGIN_LEFT_AND_RIGHT,
            MARGIN_TOP,
            this.getWidth() - ((2 * MARGIN_LEFT_AND_RIGHT) + MARGIN_RIGHT_ADDITION),
            this.getHeight() - MARGIN_TOP
        );

        for (final Data.CircleLocation loc : Data.CIRCLE_LOCATIONS) {
            g.setColor(loc.getState().getColor());
            g.fillOval(loc.getX(), loc.getY(), loc.getSize(), loc.getSize());
        }

        repaint();
    }
}
