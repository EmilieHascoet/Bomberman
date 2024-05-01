package controller;

import view.*;
import model.Bomberman;

public class FinPartieController {
    private FinPartieView view;
    private Bomberman bomber;

    public FinPartieController(FinPartieView view, Bomberman bomber) {
        this.bomber = bomber;
        this.view = view;
        initController();
    }

    private void initController() {
        view.addExitButtonListener(e -> exitApplication());
        view.addReplayButtonListener(e -> replayGame());
        view.addMenuButtonListener(e -> returnToMenu());
    }

    private void exitApplication() {
        System.exit(0);
        view.dispose();
    }

    private void replayGame() {
        bomber.partie.rejouer();

    }

    private void returnToMenu() {
        view.dispose();
        MainFrame.main(null);
    }
}
