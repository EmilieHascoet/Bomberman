package controller;

import model.Partie;
import view.PartiePanel;


public class TimeController {
    private Partie partie;
    private PartiePanel observer;

    public TimeController(Partie partie, PartiePanel observer) {
        this.partie = partie;

        partie.addObserver(observer);

        partie.demarrerTemps();
    }
}
