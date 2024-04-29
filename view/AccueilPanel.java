package view;

import java.awt.*;
import java.net.URL;
import java.util.*;
import java.util.List;
import java.util.function.Supplier;

import javax.swing.*;

import controller.AccueilControleur;
import model.Bomberman;
import model.Touche;

public class AccueilPanel extends JPanel {
    ClassLoader classLoader = getClass().getClassLoader();
    URL imageLabyUrl = classLoader.getResource("Images/labyrinthe.png");
    URL imageLogoUrl = classLoader.getResource("Images/Blossom-Battles.jpg");

    private MainFrame mainFrame;
    private Bomberman gameBomberman;
    public Color backgroundColor = new Color(203, 239, 195);
    int borderSize = 20;

    JPanel topPanel, centerPanel, bottomPanel;
    List<JPanel> listePanelControles = new ArrayList<JPanel>();
    
    /**
     * Constructs a new AccueilPanel with the specified MainFrame and Bomberman objects.
     * 
     * @param frame the MainFrame object that represents the main frame of the application
     * @param bomberman the Bomberman object that represents the game instance
     */
    public AccueilPanel(MainFrame frame, Bomberman bomberman) {
        this.mainFrame = frame;
        this.gameBomberman = bomberman;

        // Configuration de l'interface utilisateur
        configureUI();

        // Configuration du panel principal
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setBackground(backgroundColor);

        // Création des panels
        createTopPanel();
        createCenterPanel();
        createPanelControles();
        createBottomPanel();

        // Ajout des panels au panel principal
        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(listePanelControles.get(0), BorderLayout.WEST);
        add(listePanelControles.get(1), BorderLayout.EAST);
        add(bottomPanel, BorderLayout.SOUTH);

        // Ajout de la couleur de fond pour tous les panels
        this.setBackground(backgroundColor);
        topPanel.setBackground(backgroundColor);
        centerPanel.setBackground(backgroundColor);
        listePanelControles.get(0).setBackground(backgroundColor);
        listePanelControles.get(1).setBackground(backgroundColor);
        bottomPanel.setBackground(backgroundColor);

        // Affichage du panel
        setVisible(true);
    }

    /**
     * Configures the user interface by setting a new font size for all components.
     * The new font size is applied to labels, buttons, text fields, and text areas.
     */
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

    /**
     * Crée un JPanel contenant le titre et les règles du jeu.
     * @return JPanel : le panel contenant le titre et les règles du jeu
     */
    private void createTopPanel() {
        topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));

        // Titre
        JLabel label = new JLabel("Bomberman Labyrinth");
        label.setFont(new Font("Serif", Font.BOLD, 30));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setForeground(Color.RED);
        topPanel.add(label);

        // Ajout d'un espace entre les deux labels
        topPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // texte explicatif du jeu
        String rules = "<html>Le jeu de Bomberman est une bataille stratégique entre deux joueurs. Chaque joueur contrôle un personnage dans un labyrinthe.<br>"
        + "Le but ? Éliminer l'adversaire à l'aide de fleurs explosives.<br>"
        + "Voici comment cela fonctionne :<br>"
        + "<ul>"
        + "<li> Explorez le labyrinthe : Le terrain de jeu est un labyrinthe rempli de haies. Votre mission est de naviguer à travers ce labyrinthe pour trouver votre adversaire.</li>"
        + "<li> Utilisez des fleurs explosives : Vous avez la capacité de placer des fleurs explosives. Ces fleurs peuvent détruire les haies, créant ainsi de nouveaux chemins à travers le labyrinthe.</li>"
        + "<li> Éliminez votre adversaire : Votre objectif ultime est d'utiliser vos fleurs explosives pour éliminer l'adversaire. Soyez stratégique et rapide pour gagner la partie !</li>"
        + "</ul>"
        + "Le premier joueur à éliminer son adversaire remporte la partie. Bonne chance et amusez-vous bien !</html>";
        JLabel label2 = new JLabel(rules);
        label2.setFont(new Font("Serif", Font.BOLD, 17));

        // Créer un nouveau JPanel avec FlowLayout aligné à gauche
        JPanel textPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        textPanel.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 0));
        textPanel.setBackground(backgroundColor);
        textPanel.add(label2);

        topPanel.add(textPanel);
        // Logo
        /*if (imageLogoUrl != null) {

            Image image = (new ImageIcon(imageLogoUrl)).getImage();
            Image resizedImage = image.getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH); // Ajustez la largeur et la hauteur comme vous le souhaitez
            ImageIcon icon = new ImageIcon(resizedImage);

            JLabel logo = new JLabel(icon);
            panel.add(logo);
        } else {
            System.err.println("Image not found Blossom-Battles");
        }*/
    }

    
    /**
     * Creates a JPanel that displays a labyrinth.
     * 
     * @return the created JPanel
     */
    private void createCenterPanel() {
        centerPanel  = new JPanel(new BorderLayout());
        centerPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createEmptyBorder(50, 0, 50, 0),
            BorderFactory.createLineBorder(Color.BLACK, 3)
        ));

        if (imageLabyUrl != null) {
            ImageIcon icon = new ImageIcon(imageLabyUrl);
            JButton button = new JButton(icon);
            button.setHorizontalTextPosition(JButton.CENTER);
            button.setVerticalTextPosition(JButton.CENTER);
            centerPanel.add(button, BorderLayout.CENTER);
        } else {
            System.err.println("Image not found Labyrinthe");
        }
    }

    /**
     * Creates a panel to display the controls for the players.
     * The panel contains labels and buttons for each player's controls.
     * The controls include actions such as moving up, moving down, moving left, moving right, and placing a bomb.
     * The controls are retrieved from the gameBomberman object's listeTouche attribute.
     * The panel is added to the main panel of the AccueilPanel.
     */
    private void createPanelControles() {
        // Liste des noms des touches
        ArrayList<String> listeNomsTouches = new ArrayList<String>(Arrays.asList("Aller en haut", "Aller en bas", "Aller à gauche", "Aller à droite", "Placer une fleur"));

        // Boucle sur les deux joueurs
        for (int i = 0; i < 2; i++) {
            // Récupère les touches du joueur
            Touche touche = this.gameBomberman.listeTouche.get(i);
            // Liste des actions associées aux touches
            List<Supplier<String>> actions = Arrays.asList(touche::getHaut, touche::getBas, touche::getDroite, touche::getGauche, touche::getBombe);

            // Crée un nouveau panel pour les controles du joueur
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(6,2, 50, 20));
            panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
            listePanelControles.add(panel);
            
            // Crée les labels pour les controles du joueur
            JLabel labelParametres = new JLabel("Contrôles de " + gameBomberman.nomJoueurs.get(i));
            JLabel iconeJoueur = new JLabel("Icône du joueur " + (i+1));
            panel.add(labelParametres);
            panel.add(iconeJoueur);

            // Boucle sur les touches du joueur
            for (int j = 0; j < 5; j++) {
                String nomTouche = listeNomsTouches.get(j);
                String actionTouche = actions.get(j).get();

                // Crée les labels et les boutons pour les touches
                JLabel labelTouche = new JLabel(nomTouche);
                JButton boutonTouche = new JButton(actionTouche);

                panel.add(labelTouche);
                panel.add(boutonTouche);

                // Crée le controleur pour le bouton de la touche
                AccueilControleur controleur = new AccueilControleur(i+1, actionTouche, nomTouche , boutonTouche);
                boutonTouche.addActionListener(controleur);
            }
        }
    }

    /**
     * Creates a JPanel that contains buttons for starting a new game and accessing options.
     * 
     * @return the created JPanel
     */
    private void createBottomPanel() {
        bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 2, borderSize, 0));

        // Création du bouton pour démarrer une nouvelle partie
        JButton newGameButton = new JButton("Nouvelle Partie");
        AccueilControleur newGameController = new AccueilControleur("boutonPlayPressed", mainFrame, gameBomberman, listePanelControles);
        newGameButton.addActionListener(newGameController);
        newGameButton.setPreferredSize(new Dimension(newGameButton.getPreferredSize().width, 40));

        // Création du bouton pour accéder aux options
        JButton optionsButton = new JButton("Options");
        AccueilControleur optionsController = new AccueilControleur("boutonOptionsPressed", mainFrame, gameBomberman, listePanelControles);
        optionsButton.addActionListener(optionsController);
        optionsButton.setPreferredSize(new Dimension(optionsButton.getPreferredSize().width, 40));

        // Ajout des boutons au panel
        bottomPanel.add(newGameButton);
        bottomPanel.add(optionsButton);
    }
}