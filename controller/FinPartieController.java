package controller;

import view.*;

public class FinPartieController {
    private FinPartieView view;
    private AccueilControleur ac;

    public FinPartieController(FinPartieView view, AccueilControleur ac) {
        this.view = view;
        this.ac = ac;
        initController();
    }

    private void initController() {
        view.addExitButtonListener(e -> exitApplication());
        view.addReplayButtonListener(e -> replayGame());
        view.addMenuButtonListener(e -> returnToMenu());
    }

    private void exitApplication() {
        System.exit(0);
    }

    private void replayGame() {
        ac.boutonPlay();
    }

    private void returnToMenu() {
        view.dispose();
        MainFrame.main(null);
    }
}
