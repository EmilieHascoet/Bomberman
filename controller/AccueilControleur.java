package controller;

import java.awt.event.*;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;
import model.Partie;
import view.MainFrame;
import view.ParametresPanel;
import view.PartiePanel;
import view.changerToucheJDialog;

public class AccueilControleur implements ActionListener {
    int joueur;
    String ancienneTouche, actionTouche, typeAction;
    JButton boutonChooseTouche;
    MainFrame mainFrame;
    List<JPanel> touchesJoueursPanels;
    
    // Constructeur pour le changement de touche
    public AccueilControleur(int joueur, String ancienneTouche, String actionTouche, JButton boutonChooseTouche) {
        this.joueur = joueur;
        this.ancienneTouche = ancienneTouche;
        this.actionTouche = actionTouche;
        this.boutonChooseTouche = boutonChooseTouche;
        this.typeAction = "boutonTouchePressed";
    }

    // Constructeur pour les boutons Play et Options
    public AccueilControleur(String typeAction, MainFrame mainFrame, List<JPanel> touchesJoueursPanels) {
        this.typeAction = typeAction;
        this.mainFrame = mainFrame;
        this.touchesJoueursPanels = touchesJoueursPanels;
    }

    // Méthode pour gérer les actions des boutons
    public void actionPerformed(ActionEvent e) {
        if(typeAction == "boutonTouchePressed") {
            boutonTouche();
        } else if(typeAction == "boutonPlayPressed") {
            boutonPlay();
        } else if(typeAction == "boutonOptionsPressed") {
            boutonOptions();
        }
    }

    /**
     *  Ouvre une fenêtre pour changer la touche du joueur
     */
    public void boutonTouche() {
        new changerToucheJDialog(joueur, ancienneTouche, actionTouche, boutonChooseTouche);
    }

    /**
     *  Lance une nouvelle partie dans le model et affiche l'écran de jeu
     */
    public void boutonPlay() {
        Partie.lancerNouvellePartie();
        PartiePanel partiePanel = new PartiePanel(mainFrame);
        mainFrame.changePanel(partiePanel);
        partiePanel.requestFocusInWindow();
    }

    /**
     *  Affiche l'écran pour choisir les paramètres de la partie
     */
    public void boutonOptions() {
        mainFrame.changePanel(new ParametresPanel(this.mainFrame));
    }
}