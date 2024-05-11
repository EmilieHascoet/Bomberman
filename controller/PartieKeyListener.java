package controller;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

import model.Bomberman;
import model.Carte;
import model.Case;
import view.CasePanel;
import view.PartiePanel;

public class PartieKeyListener extends KeyAdapter {
    private Bomberman gameBomberman;

    public PartieKeyListener(Bomberman gameBomberman) {
        this.gameBomberman = gameBomberman;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        gameBomberman.partie.jouerTouche(e.getKeyCode());
    }
}
