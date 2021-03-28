package de.jonas.viergewinnt;

import org.jetbrains.annotations.NotNull;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Font;

public class GUI {

    private static final String TITLE = "Vier Gewinnt";
    private static final int WIDTH = 750;
    public static final int HEIGHT = 500;

    private static final int BUTOON_AMOUNT = 7;

    private static final int WIN_WIDTH = 450;
    private static final int WIN_HEIGHT = 300;

    private static final int WIN_LABEL_X = 0;
    private static final int WIN_LABEL_Y = 10;
    private static final int WIN_LABEL_WIDTH = 450;
    private static final int WIN_LABEL_HEIGHT = 50;

    private static final int WIN_BUTTON_X = 25;
    private static final int WIN_BUTTON_Y = 100;
    private static final int WIN_BUTTON_WIDH = 400;
    private static final int WIN_BUTTON_HEIGHT = 30;

    private static final int FONT_SIZE = 20;
    private static final Font FONT = new Font("Arial", Font.BOLD, FONT_SIZE);

    private final JFrame frame;

    public GUI() {
        frame = new JFrame(TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(0, 0, WIDTH, HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLayout(null);

        final Draw draw = new Draw();
        draw.setBounds(0, 0, WIDTH, HEIGHT);
        draw.setVisible(true);

        placeButtons();

        frame.add(draw);
        frame.setVisible(true);
    }

    public GUI(@NotNull final FieldState state) {
        final String labelText = (state.equals(FieldState.USER)) ? "Du hast gewonnen!" : "Der Computer hat gewonnen!";
        final String buttonText = (state.equals(FieldState.USER)) ? "Cool" : "Schade";
        final String title = (state.equals(FieldState.USER)) ? "Sieg!" : "Niederlage!";
        frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setBounds(0, 0, WIN_WIDTH, WIN_HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLayout(null);

        final JLabel label = new JLabel(labelText, JLabel.CENTER);
        label.setBounds(WIN_LABEL_X, WIN_LABEL_Y, WIN_LABEL_WIDTH, WIN_LABEL_HEIGHT);
        label.setFont(FONT);

        final JButton button = new JButton(buttonText);
        button.setBounds(WIN_BUTTON_X, WIN_BUTTON_Y, WIN_BUTTON_WIDH, WIN_BUTTON_HEIGHT);
        button.setFont(FONT);
        button.addActionListener((actionEvent -> nextGame()));

        frame.add(label);
        frame.add(button);
        frame.setVisible(true);
    }

    private void placeButtons() {
        final JButton[] buttons = new JButton[BUTOON_AMOUNT];

        for (int i = 0; i < BUTOON_AMOUNT; i++) {
            final Data.ButtonData buttonData = Data.BUTTON_DATA[i];
            buttons[i] = new JButton();
            buttons[i].setBounds(
                buttonData.getX(),
                buttonData.getY(),
                buttonData.getWidth(),
                buttonData.getHeight()
            );
            buttons[i].setOpaque(false);
            buttons[i].setContentAreaFilled(false);
            buttons[i].setBorderPainted(false);
            buttons[i].addActionListener(new ActionHandler(i));
            frame.add(buttons[i]);
        }
    }

    private void nextGame() {
        System.exit(0);
    }

}
