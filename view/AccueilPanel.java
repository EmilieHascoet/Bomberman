package view;

import java.awt.*;
import java.net.URL;
import java.util.*;
import java.util.List;
import java.util.function.Supplier;

import javax.swing.*;

import controller.AccueilController;
import model.Bomberman;
import model.Touche;

public class AccueilPanel extends JPanel {
    //JLabel logo = new JLabel(new ImageIcon("../Images/Blossom-Battles.jpg"));

    private MainFrame frame;
    private Bomberman bomberman;

    public AccueilPanel(MainFrame frame, Bomberman bomberman) {
        this.frame = frame;
        this.bomberman = bomberman;
        setLayout(new BorderLayout());
        // Bordure pour le panel de 10 pixels
        int borderSize = 20;
        setBorder(BorderFactory.createEmptyBorder(borderSize, borderSize, borderSize, borderSize));

        Color backgroundColor = new Color(203, 239, 195);

        // ajout de la couleur de fond
        this.setBackground(backgroundColor);

        ///////////////// PANEL DU HAUT ///////////////////

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));

        // Titre
        JLabel title = new JLabel("Bomberman Labyrinth");
        title.setFont(new Font("Serif", Font.BOLD, 30));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setForeground(Color.RED);
        topPanel.add(title);

        /*// Utilisation de HedgePanel pour créer une haie sous le titre
        JPanel hedgePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        hedgePanel.setBackground(backgroundColor);
        for (int i = 0; i < 10; i++) {
            HedgePanel hedge = new HedgePanel();
            hedgePanel.add(hedge);
        }
        topPanel.add(hedgePanel);*/

        // Explication du jeu
        //JLabel explanation = new JLabel("<html>Navigate the maze, place bombs to clear paths, and outsmart your opponent.<br>May the cleverest explorer win!</html>");
        //explanation.setAlignmentX(Component.CENTER_ALIGNMENT);
        //topPanel.add(explanation);

        /////////////// Panel du centre ///////////////

        // Affichage du labyrinthe au centre
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createEmptyBorder(60, 0, 40, 0),
            BorderFactory.createLineBorder(Color.BLACK, 3)
        ));

        ClassLoader classLoader = getClass().getClassLoader();
        URL imageUrl = classLoader.getResource("Images/labyrinthe.png");
        if (imageUrl != null) {
            ImageIcon icon = new ImageIcon(imageUrl);
            JButton button = new JButton(icon);
            centerPanel.add(button, BorderLayout.CENTER);
        } else {
            System.err.println("Image not found Labyrinthe");
        }


        ///////////////// PANEL DE GAUCHE ET DE DROITE ///////////////////
        
        // Boutons de configuration pour les joueurs

        ArrayList<String> listNomTouche = new ArrayList<String>(Arrays.asList("Aller en haut", "Aller en bas", "Aller à gauche", "Aller à droite", "Placer une bombe"));

        for (int i = 0; i < 2; i++) {
            Touche touche = this.bomberman.listeTouche.get(i);
            List<Supplier<String>> actions = Arrays.asList(touche::getHaut, touche::getBas, touche::getGauche, touche::getDroite, touche::getBombe);

            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(6,2, 50, 20));
            panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
            panel.setBackground(backgroundColor);
            
            JLabel SettingsLabel = new JLabel("Controles du joueur " + (i+1));
            JLabel playerIcon = new JLabel("Icone du joueur " + (i+1));
            panel.add(SettingsLabel);
            panel.add(playerIcon);

            for (int j = 0; j < 5; j++) {
                String nomTouche = listNomTouche.get(j);
                String actionTouche = actions.get(j).get();

                JLabel toucheLabel = new JLabel(nomTouche);
                JButton ToucheButton = new JButton(actionTouche);
    
                panel.add(toucheLabel);
                panel.add(ToucheButton);
    
                AccueilController ctr = new AccueilController(i+1, actionTouche, nomTouche , ToucheButton);
                ToucheButton.addActionListener(ctr);
            }

            if(i == 0){
                this.add(panel, BorderLayout.WEST);
            } else {
                this.add(panel, BorderLayout.EAST);
            }
        }

        ////////////////// PANEL DU BAS ///////////////////

        // Boutons pour jouer et accéder aux options
        JPanel bottomPanel = new JPanel();

        bottomPanel.setLayout(new GridLayout(1, 2, borderSize, 0));
        bottomPanel.setBackground(backgroundColor);

        JButton playButton = new JButton("Nouvelle Partie");
        AccueilController ctrPlay = new AccueilController("boutonPlayPressed");
        playButton.addActionListener(ctrPlay);
        playButton.setPreferredSize(new Dimension(playButton.getPreferredSize().width, 40));

        JButton optionsButton = new JButton("Options");
        AccueilController ctrOptions = new AccueilController("boutonOptionsPressed");
        optionsButton.addActionListener(ctrOptions);
        optionsButton.setPreferredSize(new Dimension(optionsButton.getPreferredSize().width, 40));

        bottomPanel.add(playButton);
        bottomPanel.add(optionsButton);

        /////////////// Ajout des panels au panel principal ///////////////

        this.add(topPanel, BorderLayout.NORTH);
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);

        // Afficher la fenêtre
        setVisible(true);

        // Ajout de la couleur de fond pour tous les panels
        this.setBackground(backgroundColor);
        topPanel.setBackground(backgroundColor);
        centerPanel.setBackground(backgroundColor);
    }
}
