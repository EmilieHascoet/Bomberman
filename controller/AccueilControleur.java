package controller;

import java.awt.event.*;

import javax.swing.JButton;

import model.Bomberman;
import view.ChangerToucheJDialog;
import view.MainFrame;
import view.PartiePanel;

public class AccueilControleur implements ActionListener {
    int joueur;
    String ancienneTouche, actionTouche, typeAction;
    JButton boutonChooseTouche;
    MainFrame mainFrame;
    Bomberman bomberman;
    
    // Constructeur pour le changement de touche
    public AccueilControleur(int joueur, String ancienneTouche, String actionTouche, JButton boutonChooseTouche) {
        this.joueur = joueur;
        this.ancienneTouche = ancienneTouche;
        this.actionTouche = actionTouche;
        this.boutonChooseTouche = boutonChooseTouche;
        this.typeAction = "boutonTouchePressed";
    }

    public AccueilControleur(String typeAction, MainFrame mainFrame, Bomberman bomberman) {
        this.typeAction = typeAction;
        this.mainFrame = mainFrame;
        this.bomberman = bomberman;
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
        mainFrame.changePanel(new PartiePanel(mainFrame, bomberman));
    }

    /**
     *  Récupère les touches des joueurs et change les touches de bomberman
     *  Puis Affiche l'écran pour choisir les paramètres de la partie
     */
    public void boutonOptions() {
        // TODO implement here
    }
}