package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JLabel;

import controller.PartieKeyListener;
import model.Bomberman;
import model.Carte;
import model.Case;
import model.Partie;

public class PartiePanel extends JPanel {
    MainFrame mainFrame;
    Bomberman gameBomberman;
    public Color backgroundColor = new Color(203, 239, 195);
    
    JPanel infoPanel = new JPanel();
    JPanel plateauPanel;

    public static CasePanel[][] casesPlateauPanel;

    public PartiePanel(MainFrame frame, Bomberman bomberman) {
        this.mainFrame = frame;
        this.gameBomberman = bomberman;

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(backgroundColor);

        // Add a key listener to the panel
        PartieKeyListener keyListener = new PartieKeyListener(gameBomberman);
        this.addKeyListener(keyListener);

        // Make sure the panel can receive keyboard input
        this.setFocusable(true);
        this.requestFocusInWindow();

        // Create a panel to display information
        infoPanel = createInfosPanel();
        plateauPanel = createPlateauPanel();
        plateauPanel.setAlignmentX(CENTER_ALIGNMENT);

        // Add the panel to the center of the frame
        this.add(infoPanel);
        this.add(plateauPanel);
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
        infoPanel.setLayout(new GridLayout(1, 3));

        // Set the preferred size of the panel
        infoPanel.setPreferredSize(new Dimension(infoPanel.getWidth(), 70));

        infoPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        // Create a label to display the number of lives
        JLabel labelLives = new JLabel("Vies: " + gameBomberman.partie.joueurs.get(0).vie);
        infoPanel.add(labelLives);

        // Create a label to display the number of bombs
        JLabel labelBombs = new JLabel("Bombe: " + gameBomberman.partie.joueurs.get(0).stockBombe);
        infoPanel.add(labelBombs);

        // Create a label to display the number of lives
        JLabel labelScore = new JLabel("Score: " + gameBomberman.partie.joueurs.get(0).score);
        infoPanel.add(labelScore);

        return infoPanel;
    }
}
