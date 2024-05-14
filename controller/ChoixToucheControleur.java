package controller;

import java.awt.event.*;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;

import model.Partie;
import view.ChangerToucheJDialog;
import view.MainFrame;
import view.ParametresPanel;
import view.PartiePanel;

public class ChoixToucheControleur implements ActionListener {
    int joueur;
    String ancienneTouche, actionTouche, typeAction;
    JButton boutonChooseTouche;
    MainFrame mainFrame;
    List<JPanel> touchesJoueursPanels;
    Partie partieEnCours;
    
    // Constructeur pour le changement de touche
    public ChoixToucheControleur(int joueur, String ancienneTouche, String actionTouche, JButton boutonChooseTouche, Partie partieEnCours) {
        this.joueur = joueur;
        this.ancienneTouche = ancienneTouche;
        this.actionTouche = actionTouche;
        this.boutonChooseTouche = boutonChooseTouche;
        this.typeAction = "boutonTouchePressed";
        this.partieEnCours = partieEnCours;
    }

    // Constructeur pour les boutons Play et Options
    public ChoixToucheControleur(String typeAction, MainFrame mainFrame, List<JPanel> touchesJoueursPanels, Partie partieEnCours) {
        this.typeAction = typeAction;
        this.mainFrame = mainFrame;
        this.touchesJoueursPanels = touchesJoueursPanels;
        this.partieEnCours = partieEnCours;
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
        new ChangerToucheJDialog(joueur, ancienneTouche, actionTouche, boutonChooseTouche, partieEnCours);
    }

    /**
     *  Lance une nouvelle partie dans le model et affiche l'écran de jeu
     */
    public void boutonPlay() {
        partieEnCours.lancerNouvellePartie();
        PartiePanel partiePanel = new PartiePanel(mainFrame, partieEnCours);
        mainFrame.changePanel(partiePanel);
        partiePanel.requestFocusInWindow();
    }

    /**
     *  Affiche l'écran pour choisir les paramètres de la partie
     */
    public void boutonOptions() {
        mainFrame.changePanel(new ParametresPanel(mainFrame, partieEnCours));
    }
}