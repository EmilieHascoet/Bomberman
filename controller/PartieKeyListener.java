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
        // Ajoutez votre logique de jeu ici
        List<Case> caseModifiees = gameBomberman.partie.jouerTouche(e.getKeyCode());
        if (caseModifiees != null) {
            for (Case c : caseModifiees) {
                System.out.println("#PartieKeyListener# Case modifiée : " + "[" + c.positionX + ", " + c.positionY + "]");
                //PartiePanel.casesPlateauPanel[c.positionY][c.positionX].repaint();
                CasePanel caseAModifier = PartiePanel.casesPlateauPanel[c.positionY][c.positionX];
                System.out.println("#PartieKeyListener# Case : " + caseAModifier.caseModel);
                Carte.afficherCarte();
                caseAModifier.setCaseModel(c);
                caseAModifier.loadImage();
                caseAModifier.repaint();
                caseAModifier.revalidate();
            }
        }
    }
}
