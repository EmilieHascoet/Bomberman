package view;

import controller.FinPartieController;
import controller.PartieKeyListener;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.Border;

import model.Joueur;
import model.Partie;

public class PartiePanel extends JPanel {
    MainFrame mainFrame;
    public Partie partieEnCours;
    public Color backgroundColor = new Color(203, 239, 195);

    // Timer du jeu pour le taux de rafraichissement
    private Timer timer;
    private ActionListener timerAction;
    private int temps;
    private int tauxRafraichissement = 100;
    private int compteur = 0;

    private BufferedImage backgroundImage;
    private ImageLoader imageLoader = new ImageLoader();
    private int sizeBorderPlateau = 5;

    JPanel infoPanel = new JPanel();
    static int infoPanelHeight = 140;
    int footerHeight = 35;
    JPanel north = new JPanel();
    JPanel south = new JPanel();
    GridBagConstraints gbc = new GridBagConstraints();
    GridBagConstraints squarePan = new GridBagConstraints();
    JPanel plateauPanel;
    Map<String, Integer> vieJoueurs;
    ClassLoader classLoader = getClass().getClassLoader();

    private List<JPanel> lifePanels = new ArrayList<>();
    JLabel chrono;
    List<JLabel> labelNames;
    List<JLabel> labelLives;
    
    List<JLabel> labelBombs;
    List<JLabel> labelScores;
    List<JLabel> labelBonus;

    public static CasePanel[][] casesPlateauPanel;

    public PartiePanel(MainFrame frame, Partie partie) {
        this.mainFrame = frame;
        this.partieEnCours = partie;
        this.temps = partie.time;

        vieJoueurs = new HashMap<>() {
            {
                for (Joueur joueur : partieEnCours.getJoueurs()) {
                    put(joueur.nom, partieEnCours.paramPartie.getNbVie());
                }
            }
        };

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(backgroundColor);

        infoPanel = createInfosPanel();
        plateauPanel = createPlateauPanel();

        // Add the panel to the center of the frame
        this.add(infoPanel);
        this.add(plateauPanel);

        // Add a key listener to the panel
        PartieKeyListener keyListener = new PartieKeyListener(partieEnCours);
        this.addKeyListener(keyListener);

        // Make sure the panel can receive keyboard input
        this.setFocusable(true);
        this.setFocusTraversalKeysEnabled(false);

        // Create a timer to update the game every 100 milliseconds
        timerAction = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                decrementerTemps();
            }
        };
        timer = new Timer(tauxRafraichissement, timerAction);

        this.timer.start();

        // Load the background image
        try {
            backgroundImage = ImageIO.read(getClass().getResource("/Images/background.png"));
        
            // Créer un noyau de flou
            float[] matrix = {
                1/25f, 1/25f, 1/25f, 1/25f, 1/25f,
                1/25f, 1/25f, 1/25f, 1/25f, 1/25f,
                1/25f, 1/25f, 1/25f, 1/25f, 1/25f,
                1/25f, 1/25f, 1/25f, 1/25f, 1/25f,
                1/25f, 1/25f, 1/25f, 1/25f, 1/25f,
            };
            Kernel blurKernel = new Kernel(4, 4, matrix);
        
            // Créer un ConvolveOp avec le noyau de flou
            ConvolveOp blurOp = new ConvolveOp(blurKernel);
        
            // Appliquer l'opération de flou à l'image
            backgroundImage = blurOp.filter(backgroundImage, null);
        
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Dessiner l'image de fond
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }

    private JPanel createPlateauPanel() {
        // panel de type grille
        int width = partieEnCours.paramPartie.getBoardWidth()+2;
        int height = partieEnCours.paramPartie.getBoardHeight()+2;

        casesPlateauPanel = new CasePanel[height][width];

        int sizeCase = Math.min(mainFrame.getWidth() - sizeBorderPlateau * 2 / width,
                (mainFrame.getHeight() - infoPanelHeight - footerHeight) / height);
        JPanel plateauPanel = new JPanel();
        plateauPanel.setOpaque(false);
        plateauPanel.setPreferredSize(
                new Dimension(sizeCase * width + sizeBorderPlateau * 2, sizeCase * height + sizeBorderPlateau*2 + footerHeight));
        plateauPanel.setMaximumSize(plateauPanel.getPreferredSize());
        // plateauPanel.setBackground(new Color(0, 0, 0));
        plateauPanel.setLayout(new BoxLayout(plateauPanel, BoxLayout.Y_AXIS));

        for (int i = 0; i < height; i++) {
            JPanel panel = new JPanel();
            panel.setOpaque(false);
            panel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
            for (int j = 0; j < width; j++) {
                CasePanel casePlateauPanel = new CasePanel(partieEnCours.getCarte()[i][j], imageLoader);
                casePlateauPanel.setPreferredSize(new Dimension(sizeCase, sizeCase));
                panel.add(casePlateauPanel);
                casesPlateauPanel[i][j] = casePlateauPanel;
            }
            plateauPanel.add(panel);
        }

        // Add a black border around the plateau panel and a empty border outside the black border
       // Create the outer border
        Border outerBorder = BorderFactory.createEmptyBorder(0, 0, footerHeight, 0);
        // Create the inner border
        Border innerBorder = BorderFactory.createLineBorder(new Color(0, 0, 0), sizeBorderPlateau);
        Border compoundBorder = BorderFactory.createCompoundBorder(outerBorder, innerBorder);
        plateauPanel.setBorder(compoundBorder);

        return plateauPanel;
    }

    private JPanel createInfosPanel() {
        // Create a panel to display information
        JPanel infoPanel = new JPanel();
        infoPanel.setOpaque(false);
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setPreferredSize(new Dimension(mainFrame.getWidth(), infoPanelHeight));

        north.setOpaque(false);
        north.setBorder(BorderFactory.createEmptyBorder(5, 5, 0, 5));
        // JLabel to display the time
        chrono = new JLabel("Temps: " + temps / 60 + " : " + temps % 60);
        chrono.setFont(new Font("Arial", Font.BOLD, 20));
        chrono.setForeground(Color.WHITE);
        north.add(chrono);

        south.setLayout(new GridBagLayout());
        south.setOpaque(false);
        gbc.insets = new java.awt.Insets(0, 20, 0, 20);
        
        labelNames = new ArrayList<>();
        labelLives = new ArrayList<>();
        labelBombs = new ArrayList<>();
        labelScores = new ArrayList<>();


        for (int i = 0; i < partieEnCours.nbJoueurs; i++) {
            JPanel panel = new RoundedPanel(15, new Color(100, 150, 50), 2);
            panel.setLayout(new GridBagLayout());
            panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            GridBagConstraints abc = new GridBagConstraints();
            abc.insets = new java.awt.Insets(0, 10, 0, 10);
            gbc.gridx = i;
            gbc.gridy = 0;

            // Create labels to display the player's name, number of bombs, and score
            JLabel labelName = new JLabel(partieEnCours.getJoueurs().get(i).nom);
            JLabel labelBombe = new JLabel("Bombes: " + partieEnCours.getJoueurs().get(i).getStockBombe());
            JLabel labelScore = new JLabel("Score: " + partieEnCours.getJoueurs().get(i).getScore());

            // Set the color of the labels to white
            labelName.setForeground(Color.WHITE);
            labelBombe.setForeground(Color.WHITE);
            labelScore.setForeground(Color.WHITE);

            // Add the labels to the list of labels
            labelNames.add(labelName);
            labelBombs.add(labelBombe);
            labelScores.add(labelScore);

            // Create a panel to hold the life squares
            JPanel lifePanel = new JPanel(new FlowLayout());
            lifePanel.setOpaque(false);
            lifePanel.setLayout(new GridBagLayout());

            // Create a label to display the number of lives
            JLabel lifeLabel = new JLabel("Vies:");
            lifeLabel.setForeground(Color.WHITE);
            lifePanel.add(lifeLabel);

            int niveau = -1;
            int col = 1;
            int sizeLifeSquare = partieEnCours.getJoueurs().get(i).getVie() / 5 + 1;
            squarePan.insets = new java.awt.Insets(1, 1, 1, 1);
            squarePan.gridheight = 1;
            
            // Add the life squares to the panel
            for (int j = 0; j < vieJoueurs.get(partieEnCours.getJoueurs().get(i).nom); j++) {
                if(j % 5 == 0){
                    col = 1;
                    niveau++;
                }
                squarePan.gridx = col;
                squarePan.gridy = niveau;

                JPanel lifeSquare = new JPanel();
                lifeSquare.setPreferredSize(new Dimension(10/sizeLifeSquare, 20/sizeLifeSquare));
                lifeSquare.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 10));
                if (j < partieEnCours.getJoueurs().get(i).getVie()) {
                    lifeSquare.setBackground(Color.GREEN);
                } else {
                    lifeSquare.setBackground(Color.RED);
                }
                lifePanel.add(lifeSquare, squarePan);
                col++;
            }
    
            // Add the life panel to the list of life panels
            lifePanels.add(lifePanel);

            abc.gridx = 0; abc.gridy = 0;
            abc.gridheight = 2;
            String avatarPath = partieEnCours.getJoueurs().get(i).avatar.getPathImage();
            Image img = imageLoader.getImage(avatarPath);
            Image newimg = img.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);
            JLabel avatar = new JLabel(new ImageIcon(newimg));
            panel.add(avatar, abc);
            abc.gridheight = 1;
            abc.gridx = 1; abc.gridy = 0;
            panel.add(labelNames.get(i), abc);
            abc.gridx = 2; abc.gridy = 0;
            panel.add(lifePanel, abc);
            abc.gridx = 1; abc.gridy = 1;
            panel.add(labelBombs.get(i), abc);
            abc.gridx = 2; abc.gridy = 1;
            panel.add(labelScores.get(i), abc);

            south.add(panel, gbc);
        }

        infoPanel.add(north);
        infoPanel.add(south);

        return infoPanel;
    }

    private void updatePlateauPanel() {
        // Update the plateau panel
        for (int i = 0; i < partieEnCours.paramPartie.getBoardHeight() + 2; i++) {
            for (int j = 0; j < partieEnCours.paramPartie.getBoardWidth() + 2; j++) {
                casesPlateauPanel[i][j].setCaseModel(partieEnCours.getCarte()[i][j]);
                casesPlateauPanel[i][j].loadImage();
            }
        }
        plateauPanel.repaint();
        plateauPanel.revalidate();
    }

    private void updateInfosPanel() {
        for (int i = 0; i < partieEnCours.nbJoueurs; i++) {
            JPanel lifePanel = lifePanels.get(i);
            // Remove all the life squares from the panel except the JLabel displaying the number of lives
            JLabel lifeLabel = (JLabel) lifePanel.getComponent(0);
            lifePanel.removeAll();
            lifePanel.add(lifeLabel);

            int niveau = -1;
            int col = 1;
            int sizeLifeSquare = partieEnCours.getJoueurs().get(i).getVie() / 5 + 1;
            squarePan.insets = new java.awt.Insets(1, 1, 1, 1);
            squarePan.gridheight = 1;
            
            // Add the life squares to the panel
            for (int j = 0; j < vieJoueurs.get(partieEnCours.getJoueurs().get(i).nom); j++) {
                if(j % 5 == 0){
                    col = 1;
                    niveau++;
                }
                squarePan.gridx = col;
                squarePan.gridy = niveau;

                JPanel lifeSquare = new JPanel();
                lifeSquare.setPreferredSize(new Dimension(10/sizeLifeSquare, 20/sizeLifeSquare));
                lifeSquare.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 10));
                if (j < partieEnCours.getJoueurs().get(i).getVie()) {
                    lifeSquare.setBackground(Color.GREEN);
                } else {
                    lifeSquare.setBackground(Color.RED);
                }
                lifePanel.add(lifeSquare, squarePan);
                col++;
            }
            
            if(partieEnCours.getJoueurs().get(i).getVie() > vieJoueurs.get(partieEnCours.getJoueurs().get(i).nom)){
                vieJoueurs.put(partieEnCours.getJoueurs().get(i).nom, partieEnCours.getJoueurs().get(i).getVie());
            }
    
            labelBombs.get(i).setText("Bombes: " + partieEnCours.getJoueurs().get(i).getStockBombe() + " ");
            labelScores.get(i).setText("Score: " + partieEnCours.getJoueurs().get(i).getScore() + " ");
        }
    }

    public void decrementerTemps() {
        compteur++;
        updateInfosPanel();
        updatePlateauPanel();
        if (partieEnCours.estTerminee()) {
            this.timer.stop();
            new FinPartieController(new FinPartieView(mainFrame, partieEnCours));
        }
        // Décrémentez le temps seulement lorsque le compteur atteint 10 (c'est-à-dire
        // toutes les secondes)
        if (compteur % (1000 / this.tauxRafraichissement) == 0) {
            if (temps > 0) {
                temps--;
                partieEnCours.time = temps;
                chrono.setText("Temps: " + temps / 60 + " : " + temps % 60);
            } else {
                // Arrêtez le timer lorsque le temps est écoulé
                this.timer.stop();
                new FinPartieController(new FinPartieView(mainFrame, partieEnCours));
            }
        }
    }
}