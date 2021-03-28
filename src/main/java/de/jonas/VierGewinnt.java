package de.jonas;

import de.jonas.viergewinnt.Bot;
import de.jonas.viergewinnt.FieldState;
import de.jonas.viergewinnt.GUI;
import de.jonas.viergewinnt.WinHandler;
import de.jonas.viergewinnt.WinListener;
import org.jetbrains.annotations.NotNull;

import static de.jonas.viergewinnt.FieldState.NONE;
import static de.jonas.viergewinnt.FieldState.USER;

/**
 * Die Haupt- und Main-Klasse des Vier-Gewinnt Spiels. Von hier aus wird das gesamte Spiel initialisiert und gestartet.
 */
public final class VierGewinnt implements WinListener {

    /**
     * Die Main-Methode der Anwendung.
     *
     * @param args .
     */
    public static void main(final String[] args) {
        startGame(USER);
    }

    public static void startGame(@NotNull final FieldState state) {
        // initialize win-handler
        new WinHandler();
        // add new win-listener
        WinHandler.setWinListener(new VierGewinnt());
        // open Graphical-User-Interface
        new GUI();
        // set user is beginning
        FieldState.setState(state);
        // start bot
        new Bot().start();
    }

    @Override
    public void win(@NotNull final FieldState state) {
        FieldState.setState(NONE);
        new GUI(state);
    }

}
