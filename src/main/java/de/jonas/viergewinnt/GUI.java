package de.jonas.viergewinnt;

import org.jetbrains.annotations.NotNull;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Font;

/**
 * In dem {@link GUI Graphical-User-Interface} werden alle grafischen Anzeigen der Anwendung dargestellt bzw.
 * initialisiert.
 */
@NotNull
public final class GUI {

    //<editor-fold desc="CONSTANTS">
    /** Die Höhe des Haupt-Fensters (in dem das Spiel dargestellt wird). */
    public static final int HEIGHT = 500;
    /** Der Titel der Anwendung. */
    @NotNull
    private static final String TITLE = "Vier Gewinnt";
    /** Die Breite des Haupt-Fensters (in dem das Spiel dargestellt wird). */
    private static final int WIDTH = 750;

    /** Die Anzahl an Buttons, die es in dem {@link GUI} gibt (Für jede Spalte einen). */
    private static final int BUTOON_AMOUNT = 7;

    /** Die Breite eines Fensters, welches nach Spiel-Ende geöfnnet wird. */
    private static final int RESULT_WIDTH = 450;
    /** Die Höhe eines Fensters, welches nach Spiel-Ende geöfnnet wird. */
    private static final int RESULT_HEIGHT = 300;

    /** Die X-Koordinate des Schriftzugs des Fensters, welches nach Spiel-Ende geöffnet wird. */
    private static final int RESULT_LABEL_X = 0;
    /** Die Y-Koordinate des Schriftzugs des Fensters, welches nach Spiel-Ende geöffnet wird. */
    private static final int RESULT_LABEL_Y = 10;
    /** Die Breite des Schriftzugs des Fensters, welches nach Spiel-Ende geöffnet wird. */
    private static final int RESULT_LABEL_WIDTH = 450;
    /** Die Höhe des Schriftzugs des Fensters, welches nach Spiel-Ende geöffnet wird. */
    private static final int RESULT_LABEL_HEIGHT = 50;

    /** Die X-Koordinate des Buttons des Fensters, welches nach Spiel-Ende geöffnet wird. */
    private static final int RESULT_BUTTON_X = 25;
    /** Die Y-Koordinate des Buttons des Fensters, welches nach Spiel-Ende geöffnet wird. */
    private static final int RESULT_BUTTON_Y = 100;
    /** Die Breite des Buttons des Fensters, welches nach Spiel-Ende geöffnet wird. */
    private static final int RESULT_BUTTON_WIDH = 400;
    /** Die Höhe des Buttons des Fensters, welches nach Spiel-Ende geöffnet wird. */
    private static final int RESULT_BUTTON_HEIGHT = 30;

    /** Die Schriftgröße der {@link Font Standard-Schriftart), die in dieser Anwendung verwendet wird. */
    private static final int FONT_SIZE = 20;
    /** Die {@link Font Standard-Schriftart}, die in dieser Anwendung verwendet wird. */
    @NotNull
    private static final Font FONT = new Font("Arial", Font.BOLD, FONT_SIZE);
    //</editor-fold>


    //<editor-fold desc="LOCAL FIELDS">
    /** Das jeweilige {@link JFrame Fenster}, welches jeweils instanziiert wird. */
    @NotNull
    private final JFrame frame;
    //</editor-fold>


    //<editor-fold desc="CONSTRUCTORS">
    /**
     * Erzeugt eine neue und vollständig unabhängige Instanz des {@link GUI}. Dieser Konstruktor erzeugt das {@link
     * JFrame Fenster}, für das hauptsächliche Spiel.
     */
    @NotNull
    public GUI() {
        frame = new JFrame(TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(0, 0, WIDTH, HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLayout(null);

        @NotNull
        final Draw draw = new Draw();
        draw.setBounds(0, 0, WIDTH, HEIGHT);
        draw.setVisible(true);

        placeButtons();

        frame.add(draw);
        frame.setVisible(true);
    }

    /**
     * Erzeugt eine neue und vollständig unabhängige Instanz des {@link GUI}. Dieser Konstruktor erzeugt das {@link
     * JFrame Fenster}, für das Spiel-Ende.
     *
     * @param state Der {@link FieldState Status}, der gewonnen hat bzw. unentschieden ist.
     */
    @NotNull
    public GUI(@NotNull final FieldState state) {
        frame = new JFrame(state.getInfo().getTitle());
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setBounds(0, 0, RESULT_WIDTH, RESULT_HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLayout(null);

        @NotNull
        final JLabel label = new JLabel(state.getInfo().getLabel(), JLabel.CENTER);
        label.setBounds(RESULT_LABEL_X, RESULT_LABEL_Y, RESULT_LABEL_WIDTH, RESULT_LABEL_HEIGHT);
        label.setFont(FONT);

        @NotNull
        final JButton button = new JButton(state.getInfo().getButton());
        button.setBounds(RESULT_BUTTON_X, RESULT_BUTTON_Y, RESULT_BUTTON_WIDH, RESULT_BUTTON_HEIGHT);
        button.setFont(FONT);
        button.addActionListener((actionEvent -> nextGame()));

        frame.add(label);
        frame.add(button);
        frame.setVisible(true);
    }
    //</editor-fold>


    /**
     * Platziert die {@link JButton Buttons} in das instanziierte {@link JFrame Fenster}.
     */
    private void placeButtons() {
        @NotNull
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

    /**
     * Diese Methode wird aufgerufen, sobald das Spiel beendet ist und der Nutzer auf den {@link JButton Button}
     * klickt.
     */
    private void nextGame() {
        System.exit(0);
    }

}
