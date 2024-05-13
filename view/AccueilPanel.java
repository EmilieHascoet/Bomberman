package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.UIManager;

import controller.AccueilControleur;
import model.Partie;

public class AccueilPanel extends JPanel {
    private MainFrame mainFrame;
    Color backgroundColor = new Color(231, 195, 239);
    Color backgroundColorGreen = new Color(203, 239, 195);

    private JPanel topPanel;
    private JPanel centerPanel;
    private JPanel bonusPanel;
    private JPanel blocsPanel;
    private JPanel bottomPanel;

     public AccueilPanel(MainFrame frame) {
        this.mainFrame = frame;

        // Configuration de l'interface utilisateur
        configureUI();

        // Configuration du panel principal
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setBackground(backgroundColor);

        // Création des panels
        createTopPanel();
        blocsPanel = createPanelBord("Bloc");
        createCenterPanel();
        bonusPanel = createPanelBord("Bonus");
        createBottomPanel();

        // Ajout des panels au panel principal
        add(topPanel, BorderLayout.NORTH);
        add(blocsPanel, BorderLayout.WEST);
        add(centerPanel, BorderLayout.CENTER);
        add(bonusPanel, BorderLayout.EAST);
        add(bottomPanel, BorderLayout.SOUTH);

        // Ajout de la couleur de fond pour tous les panels
        this.setBackground(backgroundColor);
        //topPanel.setBackground(backgroundColor);
        centerPanel.setBackground(backgroundColor);
        bottomPanel.setBackground(backgroundColor);

        // Affichage du panel
        setVisible(true);
    }

    private void configureUI() {
        // Définir la nouvelle taille de police
        int newFontSize = 16;

        // Récupérer la police par défaut
        Font defaultFont = UIManager.getDefaults().getFont("Label.font");

        // Créer une nouvelle police avec la nouvelle taille
        Font newFont = defaultFont.deriveFont((float)newFontSize);

        // Définir la nouvelle police comme police par défaut pour tous les composants
        UIManager.put("Label.font", newFont);
        UIManager.put("Button.font", newFont);
        UIManager.put("TextField.font", newFont);
        UIManager.put("TextArea.font", newFont);
    }

    private void createTopPanel() {
        // Création du panel
        topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        topPanel.setBackground(backgroundColorGreen);

        // Création du label
        JLabel label = new JLabel("Bienvenue dans Bomberman");
        label.setFont(new Font("Arial", Font.BOLD, 40));
        label.setAlignmentX(CENTER_ALIGNMENT);

        // Ajout du label au panel
        topPanel.add(label);
    }

    private JPanel createPanelBord(String type) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setPreferredSize(new Dimension(450, 1000));
        panel.setBackground(backgroundColor);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Label explication
        JTextArea label = new JTextArea(type.equals("Bonus") ? "Les bonus permettent de gagner ou perdre des compétences.\nVoici les bonus disponibles dans le jeu :" : "Les blocs sont des éléments du jeu qui peuvent être détruits ou non et non traversables.\nVoici les blocs disponibles dans le jeu :");
        label.setLineWrap(true);
        label.setWrapStyleWord(true);
        label.setEditable(false);
        label.setBackground(backgroundColor);
        panel.add(label);

        // Affiche chaque élément du model présent dans le jeu + description
        for (Enum<?> element : type.equals("Bonus") ? Partie.bonusEnum.values() : Partie.typeCaseEnum.values()) {
            // Création d'un panel pour chaque élément
            JPanel subPanel = new JPanel();
            subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.X_AXIS));
            subPanel.setBackground(backgroundColor);

            // Création d'un label avec image pour le nom de l'élément
            ImageIcon image = new ImageIcon("Images/" + type + "/" + element.toString() + ".png");
            System.out.println("Images/" + type + "/" + element.toString() + ".png");

            // redimensionner l'image
            Image img = image.getImage();
            Image newimg = img.getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH);
            image = new ImageIcon(newimg);

            JLabel labelImage = new JLabel(image);
            subPanel.add(labelImage);

            // Création du label pour la description de l'élément
            JTextArea textArea = new JTextArea(element instanceof Partie.bonusEnum ? ((Partie.bonusEnum) element).getDescription() : ((Partie.typeCaseEnum) element).getDescription());
            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);
            textArea.setEditable(false);
            textArea.setBackground(backgroundColor);
            subPanel.add(textArea);
            panel.add(subPanel);
        }

        return panel;
    }

    private void createCenterPanel() {
        // Création du panel
        centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Affichage d'un leader board
        JTextArea labelLeaderBoard = new JTextArea("Leaderboard");
        labelLeaderBoard.setFont(new Font("Arial", Font.BOLD, 20));
        labelLeaderBoard.setLineWrap(true);
        labelLeaderBoard.setWrapStyleWord(true);
        labelLeaderBoard.setEditable(false);
        labelLeaderBoard.setBackground(backgroundColor);
        centerPanel.add(labelLeaderBoard, BorderLayout.NORTH);
    }

    private void createBottomPanel() {
        bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 2, 20, 0));

        // Création du bouton pour démarrer une nouvelle partie
        JButton nouvellePartieButton = new JButton("Nouvelle Partie");
        AccueilControleur nouvellePartieController = new AccueilControleur(nouvellePartieButton, mainFrame);
        nouvellePartieButton.addActionListener(nouvellePartieController);
        nouvellePartieButton.setPreferredSize(new Dimension(nouvellePartieButton.getPreferredSize().width, 40));

        // Création du bouton pour accéder aux options
        JButton reprendrePartieButton = new JButton("Reprendre la partie");
        AccueilControleur reprendrePartieControleur = new AccueilControleur(reprendrePartieButton, mainFrame);
        reprendrePartieButton.addActionListener(reprendrePartieControleur);
        reprendrePartieButton.setPreferredSize(new Dimension(reprendrePartieButton.getPreferredSize().width, 40));

        // Ajout des boutons au panel
        bottomPanel.add(nouvellePartieButton);
        bottomPanel.add(reprendrePartieButton);
    }
}
