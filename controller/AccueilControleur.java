package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import model.Partie;
import model.Stream;
import view.ChoixTouchePanel;

import view.MainFrame;
import view.PartiePanel;

public class AccueilControleur implements ActionListener {
    private MainFrame mainFrame;
    private String typeAction;
    
    public AccueilControleur(JButton button, MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.typeAction = button.getText();
    }

    // Méthode pour gérer les actions des boutons
    @Override
    public void actionPerformed(ActionEvent e) {
        if(typeAction.equals("Nouvelle Partie")) {
            lancerNouvellePartie();
        } else {
            reprendrePartie();
        }
    }

    private void lancerNouvellePartie() {
        // Lancer une nouvelle partie
        Partie partie = new Partie();
        partie.lancerNouvellePartie();
        ChoixTouchePanel choixTouchePanel = new ChoixTouchePanel(mainFrame, partie);
        mainFrame.changePanel(choixTouchePanel);

    }

    private void reprendrePartie() {
        // Lire un fichier de sauvegarde
        Partie partie = Stream.recuperePartie() ;
        // Reprendre la partie en cours
        PartiePanel partiePanel = new PartiePanel(mainFrame, partie);
        mainFrame.changePanel(partiePanel);
        partiePanel.requestFocusInWindow();
    }
}
