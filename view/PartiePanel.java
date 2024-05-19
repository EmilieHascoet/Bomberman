package view;

import controller.FinPartieController;
import controller.PartieKeyListener;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
    private BufferedImage infoPanelBackground;
    private ImageLoader imageLoader = new ImageLoader();
    private int sizeBorderPlateau = 5;

    JPanel infoPanel = new JPanel();
    static int infoPanelHeight = 150;
    JPanel north = new JPanel();
    JPanel south = new JPanel();
    GridBagConstraints gbc = new GridBagConstraints();
    GridBagConstraints squarePan = new GridBagConstraints();
    JPanel plateauPanel;
    Map<String, Integer> vieJoueurs;
    ClassLoader classLoader = getClass().getClassLoader();

    private List<JPanel> lifePanels = new ArrayList<>();
    JLabel chrono;
    List<JLabel> labelName;
    List<JLabel> labelLives;
    
    List<JLabel> labelBombs;
    List<JLabel> labelScore;
    List<JLabel> labelBonus;

    public static CasePanel[][] casesPlateauPanel;

    public PartiePanel(MainFrame frame, Partie partie) {
        this.mainFrame = frame;
        this.partieEnCours = partie;
        this.temps = partie.time;

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

        try {
            infoPanelBackground = ImageIO.read(getClass().getResource("/Images/iPanelBackground.png/"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        vieJoueurs = new HashMap<>() {
            {
                for (Joueur joueur : partieEnCours.getJoueurs()) {
                    put(joueur.nom, joueur.getVie());
                }
            }
        };

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
                (mainFrame.getHeight() - infoPanelHeight) / height);
        JPanel plateauPanel = new JPanel();
        plateauPanel.setOpaque(false);
        plateauPanel.setPreferredSize(
                new Dimension(sizeCase * width + sizeBorderPlateau * 2, sizeCase * height + sizeBorderPlateau * 2));
        plateauPanel.setMaximumSize(plateauPanel.getPreferredSize());
        // plateauPanel.setBackground(new Color(0, 0, 0));
        plateauPanel.setLayout(new BoxLayout(plateauPanel, BoxLayout.Y_AXIS));

        for (int i = 0; i < height; i++) {
            JPanel panel = new JPanel();
            panel.setOpaque(false);
            panel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
            for (int j = 0; j < width; j++) {
                CasePanel casePlateauPanel = new CasePanel(partieEnCours.carte[i][j], imageLoader);
                casePlateauPanel.setPreferredSize(new Dimension(sizeCase, sizeCase));
                panel.add(casePlateauPanel);
                casesPlateauPanel[i][j] = casePlateauPanel;
            }
            plateauPanel.add(panel);
        }

        // Add a black border around the plateau panel
        plateauPanel.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), sizeBorderPlateau));
        return plateauPanel;
    }

    private JPanel createInfosPanel() {
        JPanel infoPanel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image scaledImage = infoPanelBackground.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH);
                if (infoPanelBackground != null) {
                    g.drawImage(infoPanelBackground, 0, 0, this.getWidth(), this.getHeight(), this);
                }
            }
        };
        // Create a panel to display the information

        // JLabel to display
        chrono = new JLabel("Temps: " + temps / 60 + " : " + temps % 60);
        
        labelName = new ArrayList<>();
        labelLives = new ArrayList<>();
        labelBombs = new ArrayList<>();
        labelScore = new ArrayList<>();
        labelBonus = new ArrayList<>();

        // Create a panel to display information
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setPreferredSize(new Dimension(mainFrame.getWidth(), infoPanelHeight));
        north.setLayout(new BoxLayout(north, BoxLayout.X_AXIS));
        south.setLayout(new GridBagLayout());
        south.setOpaque(false);
        gbc.insets = new java.awt.Insets(5, 50, 5, 50);
        infoPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        north.add(chrono);

        for (int i = 0; i < partieEnCours.nbJoueurs; i++) {
            JPanel panel = new JPanel(new GridBagLayout());
            panel.setOpaque(false);
            GridBagConstraints abc = new GridBagConstraints();
            abc.insets = new java.awt.Insets(0, 20, 0, 20);
            // Create a label to display the number of lives
            labelName.add(new JLabel("Joueur: " + partieEnCours.getJoueurs().get(i).nom + " "));
            labelBombs.add(new JLabel("Stock de bombes: " + partieEnCours.getJoueurs().get(i).stockBombe + " "));
            labelScore.add(new JLabel("Score: " + partieEnCours.getJoueurs().get(i).score + " "));
            gbc.gridx = i % 2;
            gbc.gridy = i / 2;
            // Create a panel to hold the life squares
            JPanel lifePanel = new JPanel(new FlowLayout());
            lifePanel.setOpaque(false);
            int sizeLifeSquare = partieEnCours.getJoueurs().get(i).getVie() / 5 + 1;
            int niveau = 0; 
            squarePan.insets = new java.awt.Insets(1, 1, 1, 1);

            JLabel lifeLabel = new JLabel("Vies:");
            lifePanel.add(lifeLabel);

            int col = 0;
            
            // Add the life squares to the panel
            for (int j = 0; j < partieEnCours.getJoueurs().get(i).getVie(); j++) {
                if(j > 0 && j % 5 == 0){
                    col = 0;
                    niveau++;
                }
                squarePan.gridx = col;
                squarePan.gridy = niveau;

                JPanel lifeSquare = new JPanel();
                lifeSquare.setPreferredSize(new Dimension(10/sizeLifeSquare, 20/sizeLifeSquare));
                lifeSquare.setBackground(Color.GREEN);
                lifePanel.add(lifeSquare, squarePan);
                col++;
            }   

                     
            // Add the life panel to the list of life panels

            lifePanels.add(lifePanel);
            abc.gridx = 0; abc.gridy = 0;
            panel.add(labelName.get(i), abc);
            abc.gridx = 1; abc.gridy = 0;
            panel.add(lifePanels.get(i), abc);
            abc.gridx = 2; abc.gridy = 0;
            panel.add(labelBombs.get(i), abc);
            abc.gridx = 3; abc.gridy = 0;
            panel.add(labelScore.get(i), abc);
            abc.gridx = 4; abc.gridy = 0;
            abc.gridheight = 2;
            south.add(panel, gbc);
        }

        infoPanel.add(north);
        infoPanel.add(south);
        gbc.insets = new java.awt.Insets(5, 100, 5, 100);

        return infoPanel;
    }

    public void updatePlateauPanel() {
        // Update the plateau panel
        for (int i = 0; i < partieEnCours.paramPartie.getBoardHeight() + 2; i++) {
            for (int j = 0; j < partieEnCours.paramPartie.getBoardWidth() + 2; j++) {
                casesPlateauPanel[i][j].setCaseModel(partieEnCours.carte[i][j]);
                casesPlateauPanel[i][j].loadImage();
            }
        }
        plateauPanel.repaint();
        plateauPanel.revalidate();
    }

    public void updateInfosPanel() {
        // Update the information panel
        south.removeAll();
        north.add(chrono);
        north.setOpaque(false);

        for(int i = 0; i < partieEnCours.nbJoueurs; i++){
            JPanel panel = new JPanel(new GridBagLayout());
            panel.setOpaque(false);
            GridBagConstraints abc = new GridBagConstraints();
            abc.insets = new java.awt.Insets(0, 10, 0, 10);
            gbc.gridx = i % 2;
            gbc.gridy = i / 2;

            // Update the life squares
            
            JPanel lifePanel = lifePanels.get(i);
            lifePanel.setOpaque(false);
            lifePanel.setLayout(new GridBagLayout());
            lifePanel.removeAll();
            squarePan.gridx = 0;
            squarePan.gridy = 0;
            squarePan.gridheight = 2;
            
            JLabel lifeLabel = new JLabel("Vies: ");
            lifePanel.add(lifeLabel);
            int niveau = 0;
            int sizeLifeSquare = partieEnCours.getJoueurs().get(i).getVie() / 5 + 1;
            squarePan.insets = new java.awt.Insets(1, 1, 1, 1);
            int col = 1;
            squarePan.gridx = col;
            squarePan.gridy = niveau;
            squarePan.gridheight = 1;
          
            for (int j = 0; j < vieJoueurs.get(partieEnCours.getJoueurs().get(i).nom); j++) {
                if(j > 0 && j % 5 == 0){
                    col = 1;
                    niveau++;
                }
                squarePan.gridx = col;
                squarePan.gridy = niveau;
                JPanel lifeSquare = new JPanel();
                lifeSquare.setPreferredSize(new Dimension(10 / sizeLifeSquare, 20 / sizeLifeSquare));
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

            labelName.get(i).setText("Joueur: " + partieEnCours.getJoueurs().get(i).nom + " ");
            labelBombs.get(i).setText("Stock de bombes: " + partieEnCours.getJoueurs().get(i).stockBombe + " ");
            labelScore.get(i).setText("Score: " + partieEnCours.getJoueurs().get(i).score + " ");
            abc.gridx = 0; abc.gridy = 0;
            abc.gridheight = 2;
            String avatarPath = partieEnCours.getJoueurs().get(i).avatar.getPathImage();
            Image img = imageLoader.getImage(avatarPath);
            Image newimg = img.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);
            JLabel avatar = new JLabel(new ImageIcon(newimg));
            panel.add(avatar, abc);
            abc.gridheight = 1;
            abc.gridx = 1; abc.gridy = 0;
            panel.add(labelName.get(i), abc);
            abc.gridx = 2; abc.gridy = 0;
            panel.add(lifePanel, abc);
            abc.gridx = 1; abc.gridy = 1;
            panel.add(labelBombs.get(i), abc);
            abc.gridx = 2; abc.gridy = 1;
            panel.add(labelScore.get(i), abc);

            south.add(panel, gbc);
        }
        infoPanel.add(south);
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