package view;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.nio.Buffer;
import java.util.*;
import java.util.List;
import java.util.function.Supplier;

import javax.imageio.ImageIO;
import javax.swing.*;

import controller.ChoixToucheControleur;
import model.Partie;
import model.Touche;

public class ChoixTouchePanel extends JPanel {
    ClassLoader classLoader = getClass().getClassLoader();

    private MainFrame mainFrame;
    private Partie partieEnCours;
    public Color backgroundColor = new Color(203, 239, 195);
    private int borderSize = 20;

    private JPanel centerPanel, bottomPanel;
    private List<JPanel> listePanelControles = new ArrayList<JPanel>();
    
    /**
     * Constructs a new AccueilPanel with the specified MainFrame and Bomberman objects.
     * 
     * @param frame the MainFrame object that represents the main frame of the application
     * @param bomberman the Bomberman object that represents the game instance
     */
    public ChoixTouchePanel(MainFrame frame, Partie partie) {
        this.mainFrame = frame;
        this.partieEnCours = partie;

        // Configuration de l'interface utilisateur
        configureUI();

        // Configuration du panel principal
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setBackground(backgroundColor);

        // Création des panels
        //createTopPanel();
        createCenterPanel();
        // Jpanel West et East
        for(int i = 0; i < 2; i++) {
            JPanel panelBord = new JPanel();
            panelBord.setLayout(new BoxLayout(panelBord, BoxLayout.Y_AXIS));
            listePanelControles.add(panelBord);
        }
        createPanelControles();
        createBottomPanel();

        // Ajout des panels au panel principal
        //add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(listePanelControles.get(0), BorderLayout.WEST);
        add(listePanelControles.get(1), BorderLayout.EAST);
        add(bottomPanel, BorderLayout.SOUTH);

        // Ajout de la couleur de fond pour tous les panels
        this.setBackground(backgroundColor);
        //topPanel.setBackground(backgroundColor);
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
     * Creates a JPanel that displays a labyrinth.
     * 
     * @return the created JPanel
     */
    private void createCenterPanel() {
        centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        centerPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

        JPanel panelSlider = new JPanel();
        panelSlider.setBackground(backgroundColor);
        // Label pour le slider
        JLabel labelSlider = new JLabel("Nombre de joueurs : ");
        panelSlider.add(labelSlider);

        // Créer un slider pour choisir le nombre de joueur dans la partie
        JSlider slider = new JSlider(JSlider.HORIZONTAL, 2, 4, partieEnCours.nbJoueurs);
        slider.setMajorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setSnapToTicks(true);
        slider.setLabelTable(slider.createStandardLabels(1));
        slider.setOpaque(false);
        slider.addChangeListener(e -> {
            partieEnCours.nbJoueurs = slider.getValue();
            listePanelControles.get(0).removeAll();
            listePanelControles.get(1).removeAll();
            createPanelControles();
            add(listePanelControles.get(0), BorderLayout.WEST);
            add(listePanelControles.get(1), BorderLayout.EAST);
            revalidate();
            repaint();
        });
        panelSlider.add(slider);
        centerPanel.add(panelSlider, BorderLayout.NORTH);

        try {
            BufferedImage image = ImageIO.read(getClass().getResource("/Images/labyrinthe.png"));
            ImageIcon icon = new ImageIcon(image);
            JButton button = new JButton(icon);
            button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
            centerPanel.add(button, BorderLayout.CENTER);
        } catch (Exception e) {
            System.err.println("Error image labyrinthe : " + e.getMessage());
        }
    }

    /**
     * Creates a JPanel that contains buttons for changing the controls of the players.
     * 
     * @return the created JPanel
     */
    private void createPanelControles() {
        // Liste des noms des touches
        ArrayList<String> listeNomsTouches = new ArrayList<String>(Arrays.asList("Aller en haut", "Aller en bas", "Aller à droite", "Aller à gauche", "Placer une fleur"));

        // Boucle sur les joueurs
        for (int i = 0; i < partieEnCours.nbJoueurs; i++) {
            // Récupère les touches du joueur
            Touche touche = partieEnCours.getJoueurs().get(i).touche;
            // Liste des actions associées aux touches
            List<Supplier<String>> actions = Arrays.asList(touche::getHaut, touche::getBas, touche::getDroite, touche::getGauche, touche::getBombe);

            // Crée un nouveau panel pour les controles du joueur
            JPanel panel = new JPanel();
            panel.setPreferredSize(new Dimension(500, 300));
            panel.setBorder(BorderFactory.createEmptyBorder(10, 50, 50, 50));
            panel.setLayout(new GridLayout(6,2, 40, 10));
            panel.setBackground(backgroundColor);
            
            // Crée les labels pour les controles du joueur
            JLabel labelParametres = new JLabel("Contrôles de " + partieEnCours.getJoueurs().get(i).nom);
            
            // Crée l'icone du joueur
            JLabel iconeJoueur = new JLabel();
            try {
                BufferedImage image = ImageIO.read(getClass().getResource("/Images/Personnage/" + partieEnCours.getJoueurs().get(i).avatar + ".png"));
                ImageIcon icon = new ImageIcon(image);
                iconeJoueur.setIcon(icon);
                // Centre l'icône dans le JLabel
                iconeJoueur.setHorizontalAlignment(JLabel.CENTER);
                iconeJoueur.setVerticalAlignment(JLabel.CENTER);
    
                iconeJoueur.addComponentListener(new ComponentAdapter() {
                    @Override
                    public void componentResized(ComponentEvent e) {
                        ImageIcon originalIcon = (ImageIcon) iconeJoueur.getIcon();
                        Image originalImage = originalIcon.getImage();
                
                        // Calculer le rapport d'aspect de l'image
                        double aspectRatio = (double) originalImage.getWidth(null) / originalImage.getHeight(null);
                
                        // Calculer la nouvelle largeur et hauteur tout en conservant le rapport d'aspect
                        int newHeight = iconeJoueur.getHeight();
                        int newWidth = (int) (newHeight * aspectRatio);
    
                        // Redimensionner l'image
                        Image resizedImage = originalImage.getScaledInstance(newWidth, newHeight, java.awt.Image.SCALE_SMOOTH);
                        ImageIcon resizedIcon = new ImageIcon(resizedImage);
                        iconeJoueur.setIcon(resizedIcon);
                    }
                });
            } catch (Exception e) {
                System.err.println("Error image personnage : " + e.getMessage());
            }

            panel.add(labelParametres);
            panel.add(iconeJoueur);

            // Boucle sur les touches du joueur
            for (int j = 0; j < listeNomsTouches.size(); j++) {
                String nomTouche = listeNomsTouches.get(j);
                String actionTouche = actions.get(j).get();

                // Crée les labels et les boutons pour les touches
                JLabel labelTouche = new JLabel(nomTouche);
                JButton boutonTouche = new JButton(actionTouche);

                panel.add(labelTouche);
                panel.add(boutonTouche);

                // Crée le controleur pour le bouton de la touche
                ChoixToucheControleur controleur = new ChoixToucheControleur(i, actionTouche, nomTouche , boutonTouche, partieEnCours);
                boutonTouche.addActionListener(controleur);
            }

            // Ajoute le panel à gauche si pair sinon à droite
            if(i%2 == 0) {
                listePanelControles.get(0).add(panel);
            } else {
                listePanelControles.get(1).add(panel);
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
        JButton nouvellePartieButton = new JButton("Nouvelle Partie");
        ChoixToucheControleur nouvellePartieController = new ChoixToucheControleur("boutonPlayPressed", mainFrame, listePanelControles, partieEnCours);
        nouvellePartieButton.addActionListener(nouvellePartieController);
        nouvellePartieButton.setPreferredSize(new Dimension(nouvellePartieButton.getPreferredSize().width, 40));

        // Création du bouton pour accéder aux options
        JButton optionsButton = new JButton("Options");
        ChoixToucheControleur optionsController = new ChoixToucheControleur("boutonOptionsPressed", mainFrame, listePanelControles, partieEnCours);
        optionsButton.addActionListener(optionsController);
        optionsButton.setPreferredSize(new Dimension(optionsButton.getPreferredSize().width, 40));

        // Ajout des boutons au panel
        bottomPanel.add(nouvellePartieButton);
        bottomPanel.add(optionsButton);
    }
}