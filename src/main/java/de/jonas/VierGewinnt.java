package de.jonas;

import de.jonas.viergewinnt.Bot;
import de.jonas.viergewinnt.FieldState;
import de.jonas.viergewinnt.GUI;
import de.jonas.viergewinnt.ImageLoader;
import de.jonas.viergewinnt.WinHandler;
import de.jonas.viergewinnt.WinListener;
import org.jetbrains.annotations.NotNull;

import static de.jonas.viergewinnt.FieldState.NONE;
import static de.jonas.viergewinnt.FieldState.USER;

/**
 * Die Haupt- und Main-Klasse des Vier-Gewinnt Spiels. Von hier aus wird das gesamte Spiel initialisiert und gestartet.
 */
@NotNull
public final class VierGewinnt implements WinListener {

    //<editor-fold desc="setup and start">
    /**
     * Die Main-Methode der Anwendung.
     *
     * @param args .
     */
    public static void main(@NotNull final String[] args) {
        // load images
        ImageLoader.getINSTANCE().loadImages();
        // start game
        startGame(USER);
    }

    /**
     * Startet und initialisiert das Spiel.
     * @param state Der {@link FieldState}, der beginnen soll.
     */
    public static void startGame(@NotNull final FieldState state) {
        final VierGewinnt game = new VierGewinnt();
        // initialize win listener
        WinHandler.getINSTANCE().setWinListener(game);
        // open Graphical-User-Interface
        new GUI();
        // set user is beginning
        FieldState.setState(state);
        // start bot
        new Bot().start();
    }
    //</editor-fold>


    //<editor-fold desc="implementation">
    @Override
    public void win(@NotNull final FieldState state) {
        FieldState.setState(NONE);
        new GUI(state);
    }
    //</editor-fold>

}
