package controller;

import model.Partie;
import view.*;

public class FinPartieController {
    private FinPartieView view;

    public FinPartieController(FinPartieView view) {
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
        Partie.lancerNouvellePartie();

    }

    private void returnToMenu() {
        view.dispose();
        MainFrame.main(null);
    }
}
