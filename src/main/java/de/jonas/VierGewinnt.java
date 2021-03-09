package de.jonas;

import de.jonas.viergewinnt.Bot;
import de.jonas.viergewinnt.FieldState;
import de.jonas.viergewinnt.GUI;
import org.jetbrains.annotations.NotNull;

/**
 * Die Haupt- und Main-Klasse des Vier-Gewinnt Spiels. Von hier aus wird das gesamte Spiel initialisiert und gestartet.
 */
public class VierGewinnt {

    /**
     * Die Main-Methode der Anwendung.
     *
     * @param args .
     */
    public static void main(final String[] args) {
        // open Graphical-User-Interface
        new GUI();
        // set user is beginning
        FieldState.setState(FieldState.USER);
        // start bot
        new Bot().start();
    }

    public void win(@NotNull final FieldState state) {
        stopGame();
        new GUI(state);
    }

    private void stopGame() {
        FieldState.setState(FieldState.NONE);
    }

}
