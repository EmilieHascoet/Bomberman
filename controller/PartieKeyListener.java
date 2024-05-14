package controller;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import model.Partie;
import model.Touche;

public class PartieKeyListener extends KeyAdapter {
    private Partie partieEnCours;
    public PartieKeyListener(Partie partieEnCours) {
        this.partieEnCours = partieEnCours;
    }
    @Override
    public void keyPressed(KeyEvent e) {
        String keyString = Touche.getKeyText(e.getKeyCode()) != null ? Touche.getKeyText(e.getKeyCode()) : KeyEvent.getKeyText(e.getKeyCode());
        partieEnCours.jouerTouche(keyString);
    }
}
