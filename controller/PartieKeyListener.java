package controller;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import model.Bomberman;

public class PartieKeyListener extends KeyAdapter {
    private Bomberman gameBomberman;

    public PartieKeyListener(Bomberman gameBomberman) {
        this.gameBomberman = gameBomberman;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // Ajoutez votre logique de jeu ici
        System.out.println("Key pressed: " + e.getKeyChar());
    }
}
