package view;

import controller.PartieKeyListener;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import model.Bomberman;
import model.Carte;
import model.Partie;

public class PartiePanel extends JPanel {
    MainFrame mainFrame;
    Bomberman gameBomberman;
    public Color backgroundColor = new Color(203, 239, 195);

    // Timer du jeu pour le taux de rafraichissement
    private Timer timer;
    private ActionListener timerAction;
    private int temps = 300;
    private int tauxRafraichissement = 100;
    private int compteur = 0;

    JPanel infoPanel = new JPanel();
    static int infoPanelHeight = 150;
    JPanel north = new JPanel();
    JPanel south = new JPanel();
    JPanel plateauPanel;

    private List<JPanel> lifePanels = new ArrayList<>();
    JLabel chrono;
    List<JLabel> labelName;
    List<JLabel> labelLives;
    List<JLabel> labelBombs;
    List<JLabel> labelScore;
    List<JLabel> labelBonus;
    
    public static CasePanel[][] casesPlateauPanel;

    public PartiePanel(MainFrame frame, Bomberman bomberman) {
        this.mainFrame = frame;
        this.gameBomberman = bomberman;

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(backgroundColor);

        infoPanel = createInfosPanel();
        plateauPanel = createPlateauPanel();
        plateauPanel.setAlignmentX(CENTER_ALIGNMENT);

        // Add the panel to the center of the frame
        this.add(infoPanel);
        this.add(plateauPanel);

        // Add a key listener to the panel
        PartieKeyListener keyListener = new PartieKeyListener(gameBomberman);
        this.addKeyListener(keyListener);

        // Make sure the panel can receive keyboard input
        this.setFocusable(true);
        this.requestFocusInWindow();

        timerAction = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                decrementerTemps();
            }
        };
        timer = new Timer(tauxRafraichissement, timerAction);

        this.timer.start();
    }

    private JPanel createPlateauPanel() {
        // panel de type grille
        int width = Partie.paramPartie.getBoardWidth()+2;
        int height = Partie.paramPartie.getBoardHeight()+2;

        casesPlateauPanel = new CasePanel[height][width];
        
        JPanel plateauPanel = new JPanel();
        plateauPanel.setBackground(new Color(0, 0, 0));
        plateauPanel.setLayout(new BoxLayout(plateauPanel, BoxLayout.Y_AXIS));

        for (int i = 0; i < height; i++) {
            JPanel panel = new JPanel();
            panel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
            for (int j = 0; j < width; j++) {
                CasePanel casePlateauPanel = new CasePanel(Carte.map[i][j], mainFrame);
                panel.add(casePlateauPanel);
                casesPlateauPanel[i][j] = casePlateauPanel;
            }
            plateauPanel.add(panel);
        }

        plateauPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        return plateauPanel;
    }

    private JPanel createInfosPanel() {
        JPanel infoPanel = new JPanel();

        //JLabel to display
        chrono = new JLabel("Temps: " + temps / 60 + " : " + temps % 60);;
        labelName = new ArrayList<>();
        labelLives = new ArrayList<>();
        labelBombs = new ArrayList<>();
        labelScore = new ArrayList<>();
        labelBonus = new ArrayList<>();

        // Create a panel to display information
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setPreferredSize(new Dimension(mainFrame.getWidth(), infoPanelHeight));
        north.setLayout(new BoxLayout(north, BoxLayout.X_AXIS));
        south.setLayout(new GridLayout(2, gameBomberman.partie.joueurs.size() + 1));

        infoPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        north.add(chrono);

        for(int i = 0; i < gameBomberman.partie.joueurs.size(); i++){
            // Create a label to display the number of lives
            labelName.add(new JLabel("Joueur: " + gameBomberman.partie.joueurs.get(i).nom + " "));
            labelBombs.add(new JLabel("Stock de bombes: " + gameBomberman.partie.joueurs.get(i).stockBombe + " "));
            labelScore.add(new JLabel("Score: " + gameBomberman.partie.joueurs.get(i).score + " "));

            // Create a panel to hold the life squares
            JPanel lifePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));


            JLabel lifeLabel = new JLabel("Vies:");
            lifePanel.add(lifeLabel);

            // Add the life squares to the panel
            for(int j = 0; j < gameBomberman.partie.joueurs.get(i).vie; j++){
                JPanel lifeSquare = new JPanel();
                lifeSquare.setPreferredSize(new Dimension(10, 20));
                lifeSquare.setBackground(Color.GREEN);
                lifeSquare.setBorder(javax.swing.BorderFactory.createEmptyBorder(0,10,0,10));
                lifePanel.add(lifeSquare);
            }

            // Add the life panel to the list of life panels
            lifePanels.add(lifePanel); // This line is new

            south.add(labelName.get(i));
            south.add(lifePanels.get(i)); // This line has changed
            south.add(labelBombs.get(i));
            south.add(labelScore.get(i));
        }

        infoPanel.add(north);
        infoPanel.add(south);

        return infoPanel;
    }

    public void updatePlateauPanel() {
        // Update the plateau panel
        for (int i = 0; i < Partie.paramPartie.getBoardHeight()+2; i++) {
            for (int j = 0; j < Partie.paramPartie.getBoardWidth()+2; j++) {
                casesPlateauPanel[i][j].setCaseModel(Carte.map[i][j]);
                casesPlateauPanel[i][j].loadImage();
            }
        }
        plateauPanel.repaint();
        plateauPanel.revalidate();
    }

    public void updateInfosPanel() {
        // Update the information panel
        south.removeAll();
        infoPanel.remove(this.south);
        north.add(chrono);

        for(int i = 0; i < gameBomberman.partie.joueurs.size(); i++){
            south.add(new JLabel("Joueur: " + gameBomberman.partie.joueurs.get(i).nom + " "));
            
            // Update the life squares
            JPanel lifePanel = lifePanels.get(i);
            lifePanel.removeAll();

            JLabel lifeLabel = new JLabel("Vies:");
            lifePanel.add(lifeLabel);
            for(int j = 0; j < gameBomberman.partie.paramPartie.getNbVie(); j++){
                JPanel lifeSquare = new JPanel();
                lifeSquare.setPreferredSize(new Dimension(10, 20));
                lifeSquare.setBorder(javax.swing.BorderFactory.createEmptyBorder(0,10,0,10));

                if(j < gameBomberman.partie.joueurs.get(i).vie){
                    lifeSquare.setBackground(Color.GREEN);
                } else {
                    lifeSquare.setBackground(Color.RED);
                }

                lifePanel.add(lifeSquare);
            }
            south.add(lifePanel);

            south.add(new JLabel("Stock de bombes: " + gameBomberman.partie.joueurs.get(i).stockBombe + " "));
            south.add(new JLabel("Score: " + gameBomberman.partie.joueurs.get(i).score + " "));
        }
        infoPanel.add(south);
    }

    public void decrementerTemps() {
        compteur++;
        updateInfosPanel();
        updatePlateauPanel();
        // Décrémentez le temps seulement lorsque le compteur atteint 10 (c'est-à-dire toutes les secondes)
        if (compteur >= 10) {
            if (temps > 0) {
                temps--;
                chrono.setText("Temps: " + temps / 60 + " : " + temps % 60);
            } else {
                // Arrêtez le timer lorsque le temps est écoulé
                this.timer.stop();
            }
            // Réinitialisez le compteur
            compteur = 0;
        }
    }
}