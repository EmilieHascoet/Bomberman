package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.UIManager;

import controller.AccueilControleur;
import model.Case.typeCaseEnum;
import model.Partie;
import model.Partie.bonusEnum;
import model.Stream;

public class AccueilPanel extends JPanel {
    private MainFrame mainFrame;
    Color backgroundColor = new Color(231, 195, 239);
    Color backgroundColorGreen = new Color(203, 239, 195);

    private JPanel topPanel;
    private JPanel centerPanel;
    private JPanel bonusPanel;
    private JPanel blocsPanel;
    private JPanel bottomPanel;
    private ImageLoader imageLoader;

     public AccueilPanel(MainFrame frame) {
        this.mainFrame = frame;
        imageLoader = new ImageLoader();

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
        topPanel = new RoundedPanel(20, backgroundColorGreen, 5);
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    
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
        for (Enum<?> element : type.equals("Bonus") ? Partie.bonusEnum.values() : typeCaseEnum.values()) {
            String description = element instanceof Partie.bonusEnum ? ((Partie.bonusEnum) element).getDescription() : ((typeCaseEnum) element).getDescription();
            if(description != null) {
                // Récupération du chemin de l'image
                String pathImage = element instanceof Partie.bonusEnum ? ((Partie.bonusEnum) element).getPathImage() : ((typeCaseEnum) element).getPathImage();

                // Création d'un panel pour chaque élément
                JPanel subPanel = new JPanel();
                subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.X_AXIS));
                subPanel.setBackground(backgroundColor);

                // redimensionner l'image
                Image img = imageLoader.getImage(pathImage);
                Image newimg = img.getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH);
                ImageIcon image = new ImageIcon(newimg);

                // Création d'un label avec image pour le nom de l'élément
                JLabel labelImage = new JLabel(image);
                // Espace entre l'image et le texte
                labelImage.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));
                subPanel.add(labelImage);

                // Création du label pour la description de l'élément
                JTextArea textArea = new JTextArea(description);
                textArea.setLineWrap(true);
                textArea.setWrapStyleWord(true);
                textArea.setEditable(false);
                textArea.setBackground(backgroundColor);

                textArea.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
                subPanel.add(textArea);
                panel.add(subPanel);
            }
        }

        return panel;
    }

    private void createCenterPanel() {
        // Création du panel extérieur
        centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Création du panel
        JPanel innerPanel = new RoundedPanel(30, backgroundColorGreen, 10, Color.YELLOW);
        innerPanel.setLayout(new BorderLayout());
        innerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel label = new JLabel("Meilleurs scores :");
        label.setFont(new Font("Courier New", Font.BOLD, 20));
        label.setAlignmentX(CENTER_ALIGNMENT);
        innerPanel.add(label, BorderLayout.NORTH);

        // Affichage d'un leader board
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setOpaque(false);
        innerPanel.add(textArea, BorderLayout.CENTER);

        // Récupération des scores
        Map<Integer, List<String>> topScores = new TreeMap<>(Collections.reverseOrder());
        topScores.putAll(Stream.recupereScores());

        topScores = topScores.entrySet()
            .stream()
            .limit(10)
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> a, LinkedHashMap::new));

        topScores.forEach((score, noms) -> {
            for(String nom : noms) {
                textArea.append("\n" + nom + " : " + score);
            }
        });

        centerPanel.add(innerPanel);
    }

    private void createBottomPanel() {
        bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 2, 20, 0));

        // Création du bouton pour démarrer une nouvelle partie
        JButton nouvellePartieButton = new JButton("Nouvelle Partie");
        AccueilControleur nouvellePartieController = new AccueilControleur(nouvellePartieButton, mainFrame);
        nouvellePartieButton.addActionListener(nouvellePartieController);
        nouvellePartieButton.setPreferredSize(new Dimension(nouvellePartieButton.getPreferredSize().width, 40));

        // Création du bouton pour reprendre une partie
        JButton reprendrePartieButton = new JButton("Reprendre la partie");
        AccueilControleur reprendrePartieControleur = new AccueilControleur(reprendrePartieButton, mainFrame);
        reprendrePartieButton.addActionListener(reprendrePartieControleur);
        reprendrePartieButton.setPreferredSize(new Dimension(reprendrePartieButton.getPreferredSize().width, 40));

        if(!Stream.sauvegardePartieExist()) {
            reprendrePartieButton.setEnabled(false);
        }

        // Ajout des boutons au panel
        bottomPanel.add(nouvellePartieButton);
        bottomPanel.add(reprendrePartieButton);
    }
}
