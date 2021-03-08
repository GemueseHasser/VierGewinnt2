package de.jonas.viergewinnt;

import javax.swing.JButton;
import javax.swing.JFrame;

public class GUI {

    private static final String TITLE = "Vier Gewinnt";
    private static final int WIDTH = 750;
    private static final int HEIGHT = 500;

    private static final int BUTOON_AMOUNT = 6;

    private final JFrame frame;

    public GUI() {
        frame = new JFrame(TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(0, 0, WIDTH, HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLayout(null);

        final DrawGui draw = new DrawGui();
        draw.setBounds(0, 0, WIDTH, HEIGHT);
        draw.setVisible(true);

        placeButtons();

        frame.add(draw);
        frame.setVisible(true);
    }

    private void placeButtons() {
        final JButton[] buttons = new JButton[BUTOON_AMOUNT];

        final int width = frame.getWidth();
        final int height = frame.getHeight();

        final int buttonWidth = width / BUTOON_AMOUNT;

        for (int i = 0; i < BUTOON_AMOUNT; i++) {
            final int x = i * buttonWidth;
            buttons[i] = new JButton();
            buttons[i].setBounds(x, 0, buttonWidth, height);
            buttons[i].setOpaque(false);
            buttons[i].setContentAreaFilled(false);
            buttons[i].setBorderPainted(false);
            frame.add(buttons[i]);
        }
    }

}
