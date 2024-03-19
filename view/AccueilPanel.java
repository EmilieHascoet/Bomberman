package view;

import java.awt.*;
import java.lang.ModuleLayer.Controller;

import javax.swing.*;

import controller.AccueilController;

public class AccueilPanel extends JPanel {
    //JLabel logo = new JLabel(new ImageIcon("../Images/Blossom-Battles.jpg"));

    private MainFrame frame;

    public AccueilPanel(MainFrame frame) {
        this.frame = frame;
        setLayout(new BorderLayout());


        ///////////////// PANEL DU HAUT ///////////////////
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));

        // Titre
        JLabel title = new JLabel("Bomberman Labyrinth");
        title.setFont(new Font("Serif", Font.BOLD, 24));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        topPanel.add(title);

        // Utilisation de HedgePanel pour créer une haie sous le titre
        JPanel hedgePanel = new JPanel();
        hedgePanel.setLayout(new BoxLayout(hedgePanel, BoxLayout.X_AXIS));
        for (int i = 0; i < 10; i++) {
            HedgePanel hedge = new HedgePanel();
            //hedge.setAlignmentX(Component.CENTER_ALIGNMENT);
            hedgePanel.add(hedge);
        }
        topPanel.add(hedgePanel);

        // Explication du jeu
        //JLabel explanation = new JLabel("<html>Navigate the maze, place bombs to clear paths, and outsmart your opponent.<br>May the cleverest explorer win!</html>");
        //explanation.setAlignmentX(Component.CENTER_ALIGNMENT);
        //topPanel.add(explanation);

        // Ajout du panel du haut au panel principal
        this.add(topPanel, BorderLayout.NORTH);

        ///////////////// PANEL DE GAUCHE ///////////////////
        
        // Boutons de configuration pour les joueurs
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new GridLayout(6,2));


        // Les 5 controles du joueur 1
        JLabel player1Settings = new JLabel("Controles du joueur 1");
        JLabel player1Icon = new JLabel("Icone du joueur 1");

        JLabel player1HautLabel = new JLabel("Aller en haut");
        JButton player1HautButton = new JButton("Fleche Haut");

        JLabel player1BasLabel = new JLabel("Aller en bas");
        JButton player1BasButton = new JButton("Fleche bas");

        JLabel player1GaucheLabel = new JLabel("Aller à gauche");
        JButton player1GaucheButton = new JButton("Fleche gauche");

        JLabel player1DroiteLabel = new JLabel("Aller à droite");
        JButton player1DroiteButton = new JButton("Fleche droite");

        JLabel player1BombeLabel = new JLabel("Placer une bombe");
        JButton player1BombeButton = new JButton("Right Shift");

        leftPanel.add(player1Settings);
        leftPanel.add(player1Icon);
        leftPanel.add(player1HautLabel);
        leftPanel.add(player1HautButton);
        leftPanel.add(player1BasLabel);
        leftPanel.add(player1BasButton);
        leftPanel.add(player1GaucheLabel);
        leftPanel.add(player1GaucheButton);
        leftPanel.add(player1DroiteLabel);
        leftPanel.add(player1DroiteButton);
        leftPanel.add(player1BombeLabel);
        leftPanel.add(player1BombeButton);

        // Ajout du panel de gauche au panel principal
        this.add(leftPanel, BorderLayout.WEST);

        ///////////////// PANEL DE DROITE ///////////////////
        
        // Boutons de configuration pour les joueurs
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayout(6,2));

        // Les 5 controles du joueur 2
        JLabel player2Settings = new JLabel("Controles du joueur 2");
        JLabel player2Icon = new JLabel("Icone du joueur 2");

        JLabel player2HautLabel = new JLabel("Aller en haut");
        JButton player2HautButton = new JButton("z");

        JLabel player2BasLabel = new JLabel("Aller en bas");
        JButton player2BasButton = new JButton("s");

        JLabel player2GaucheLabel = new JLabel("Aller à gauche");
        JButton player2GaucheButton = new JButton("q");

        JLabel player2DroiteLabel = new JLabel("Aller à droite");
        JButton player2DroiteButton = new JButton("d");

        JLabel player2BombeLabel = new JLabel("Placer une bombe");
        JButton player2BombeButton = new JButton("Espace");

        rightPanel.add(player2Settings);
        rightPanel.add(player2Icon);
        rightPanel.add(player2HautLabel);
        rightPanel.add(player2HautButton);
        rightPanel.add(player2BasLabel);
        rightPanel.add(player2BasButton);
        rightPanel.add(player2GaucheLabel);
        rightPanel.add(player2GaucheButton);
        rightPanel.add(player2DroiteLabel);
        rightPanel.add(player2DroiteButton);
        rightPanel.add(player2BombeLabel);
        rightPanel.add(player2BombeButton);

        // Ajout du panel de droite au panel principal
        this.add(rightPanel, BorderLayout.EAST);

        ////////////////// PANEL DU BAS ///////////////////

        JButton playButton = new JButton("Nouvelle Partie");
        AccueilController ctrPlay = new AccueilController();
        playButton.addActionListener(ctrPlay);
        this.add(playButton, BorderLayout.SOUTH);

        // Afficher la fenêtre
        setVisible(true);
    }
    
}
