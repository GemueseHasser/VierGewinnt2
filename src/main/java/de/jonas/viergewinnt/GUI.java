package de.jonas.viergewinnt;

import javax.swing.JFrame;

public class GUI {

    private static final String TITLE = "Vier Gewinnt";
    private static final int WIDTH = 750;
    private static final int HEIGHT = 500;

    public GUI() {
        final JFrame frame = new JFrame(TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(0, 0, WIDTH, HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLayout(null);

        final DrawGui draw = new DrawGui();
        draw.setBounds(0, 0, WIDTH, HEIGHT);
        draw.setVisible(true);

        frame.add(draw);
        frame.setVisible(true);
    }

}
