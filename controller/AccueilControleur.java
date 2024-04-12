package controller;

import java.awt.Component;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import model.Bomberman;
import view.ChangerToucheJDialog;
import view.MainFrame;
import view.ParametresPanel;
import view.PartiePanel;

public class AccueilControleur implements ActionListener {
    int joueur;
    String ancienneTouche, actionTouche, typeAction;
    JButton boutonChooseTouche;
    MainFrame mainFrame;
    Bomberman bomberman;
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
    public AccueilControleur(String typeAction, MainFrame mainFrame, Bomberman bomberman, List<JPanel> touchesJoueursPanels) {
        this.typeAction = typeAction;
        this.mainFrame = mainFrame;
        this.bomberman = bomberman;
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
        new ChangerToucheJDialog(joueur, ancienneTouche, actionTouche, boutonChooseTouche);
    }

    /**
     *  Récupère les touches des joueurs et change les touches de bomberman
     *  Puis affiche l'écran pour jouer
     */
    public void boutonPlay() {
        setTouches();
        mainFrame.changePanel(new PartiePanel(mainFrame, bomberman));
    }

    /**
     *  Récupère les touches des joueurs et change les touches de bomberman
     *  Puis Affiche l'écran pour choisir les paramètres de la partie
     */
    public void boutonOptions() {
        setTouches();
        mainFrame.changePanel(new ParametresPanel(mainFrame));
    }

    public void setTouches() {
        for(int i = 0; i < touchesJoueursPanels.size(); i++) {
            List<String> touches = new ArrayList<>();
            JPanel panel = touchesJoueursPanels.get(i);
            for(Component c : panel.getComponents()) {
                if(c instanceof JButton) {
                    JButton bouton = (JButton) c;
                    String touche = bouton.getText();
                    touches.add(touche);
                }
            }
            bomberman.setTouche(i+1, touches.get(0), touches.get(1), touches.get(2), touches.get(3), touches.get(4));
        }
        System.out.println(bomberman.listeTouche);
    }
}