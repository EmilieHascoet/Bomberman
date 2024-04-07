package view;

import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JLabel;

import controller.PartieKeyListener;
import model.Bomberman;
import model.Carte;
import model.Partie;

public class PartiePanel extends JPanel {
    MainFrame mainFrame;
    Bomberman gameBomberman;
    public Color backgroundColor = new Color(203, 239, 195);
    
    JPanel infoPanel = new JPanel();
    JPanel plateauPanel;

    public PartiePanel(MainFrame frame, Bomberman bomberman) {
        this.mainFrame = frame;
        this.gameBomberman = bomberman;

        this.setLayout(new BorderLayout());
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
        this.add(infoPanel, BorderLayout.NORTH);
        this.add(plateauPanel, BorderLayout.CENTER);
    }

    private JPanel createPlateauPanel() {
        System.out.println(gameBomberman.partie.carte);
        // panel de type grille
        int width = Partie.paramPartie.getBoardWidth()+2;
        int height = Partie.paramPartie.getBoardHeight()+2;
        
        JPanel plateauPanel = new JPanel();

        plateauPanel.setLayout(new GridLayout(width, height));
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                plateauPanel.add(new CasePanel(Carte.map[i][j]));
            }
        }

        repaint();
        revalidate();

        plateauPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                for (Component component : plateauPanel.getComponents()) {
                    if (component instanceof CasePanel) {
                        ((CasePanel) component).repaint();
                    }
                }
                plateauPanel.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 3));
            }
        });

        return plateauPanel;
    }

    private JPanel createInfosPanel() {
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(1, 3));

        // Set the preferred size of the panel
        infoPanel.setPreferredSize(new Dimension(0, 70));

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
