package controller;

import java.awt.event.*;

import javax.swing.JButton;

import view.ChangerToucheJDialog;

public class AccueilControleur implements ActionListener {
    int joueur;
    String ancienneTouche, actionTouche, typeAction;
    JButton boutonChooseTouche;
    
    // Constructeur pour le changement de touche
    public AccueilControleur(int joueur, String ancienneTouche, String actionTouche, JButton boutonChooseTouche) {
        this.joueur = joueur;
        this.ancienneTouche = ancienneTouche;
        this.actionTouche = actionTouche;
        this.boutonChooseTouche = boutonChooseTouche;
        this.typeAction = "boutonTouchePressed";
    }

    public AccueilControleur(String typeAction) {
        this.typeAction = typeAction;
    }

    // Méthode pour gérer les actions des boutons
    public void actionPerformed(ActionEvent e) {
        if(typeAction == "boutonTouchePressed") {
            AfficheJDialogChangerTouche();
        } else if(typeAction == "boutonPlayPressed") {
            boutonPlay();
        } else if(typeAction == "boutonOptionsPressed") {
            boutonOptions();
        }
    }

    public void AfficheJDialogChangerTouche() {
        new ChangerToucheJDialog(joueur, ancienneTouche, actionTouche, boutonChooseTouche);
    }

    /**
     *  Récupère les touches des joueurs et change les touches de bomberman
     *  Puis affiche l'écran pour choisir les paramètres de la partie
     */
    public void boutonPlay() {
        // TODO implement here
    }

    /**
     *  Récupère les touches des joueurs et change les touches de bomberman
     *  Puis Affiche l'écran pour choisir les paramètres de la partie
     */
    public void boutonOptions() {
        // TODO implement here
    }
}