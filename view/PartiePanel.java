package view;

import controller.PartieKeyListener;
import controller.TimeController;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.Bomberman;
import model.Carte;
import model.Partie;

public class PartiePanel extends JPanel {
    MainFrame mainFrame;
    Bomberman gameBomberman;
    public Color backgroundColor = new Color(203, 239, 195);

    
    JPanel infoPanel = new JPanel();
    JPanel north = new JPanel();
    JPanel south = new JPanel();
    JPanel plateauPanel;
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

        // Add a key listener to the panel
        PartieKeyListener keyListener = new PartieKeyListener(gameBomberman);
        this.addKeyListener(keyListener);

        //JLabel to display
        chrono = new JLabel("Temps: " + gameBomberman.partie.getTemps());
        labelName = new ArrayList<>();
        labelLives = new ArrayList<>();
        labelBombs = new ArrayList<>();
        labelScore = new ArrayList<>();
        labelBonus = new ArrayList<>();

        // Make sure the panel can receive keyboard input
        this.setFocusable(true);
        this.requestFocusInWindow();

        // Create a panel to display information
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        north.setPreferredSize(new Dimension(infoPanel.getWidth(), 70));
        south.setPreferredSize(new Dimension(infoPanel.getWidth(), 70));
        north.setLayout(new BoxLayout(north, BoxLayout.X_AXIS));
        south.setLayout(new GridLayout(2, gameBomberman.partie.joueurs.size() + 1));
        
        infoPanel = createInfosPanel();
        plateauPanel = createPlateauPanel();
        plateauPanel.setAlignmentX(CENTER_ALIGNMENT);
        TimeController timeController = new TimeController(gameBomberman.partie, this);

        gameBomberman.partie.demarrerTemps();

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
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));

        infoPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        north.add(chrono);

        for(int i = 0; i < gameBomberman.partie.joueurs.size(); i++){
            // Create a label to display the number of lives
            labelName.add(new JLabel("Joueur: " + gameBomberman.partie.joueurs.get(i).nom + " "));
            labelLives.add(new JLabel("Vies: " + gameBomberman.partie.joueurs.get(i).vie + " "));
            labelBombs.add(new JLabel("Bombes: " + gameBomberman.partie.joueurs.get(i).stockBombe + " "));
            labelScore.add(new JLabel("Score: " + gameBomberman.partie.joueurs.get(i).score + " "));
            //labelBombs.add(new JLabel("Bonus: " + gameBomberman.partie.joueurs.get(i).listBonus + " "));
            south.add(labelName.get(i));
            south.add(labelLives.get(i));
            south.add(labelBombs.get(i));
            south.add(labelScore.get(i));
        }
        infoPanel.add(north);
        infoPanel.add(south);

        return infoPanel;
    }

    public void updateInfosPanel() {
        // Update the information panel
        south.removeAll();
        north.setPreferredSize(new Dimension(infoPanel.getWidth(), 70));
        south.setPreferredSize(new Dimension(infoPanel.getWidth(), 70));
        infoPanel.remove(this.south);
        north.add(chrono);

        for(int i = 0; i < gameBomberman.partie.joueurs.size(); i++){
            south.add(new JLabel("Joueur: " + gameBomberman.partie.joueurs.get(i).nom + " "));
            south.add(new JLabel("Vies: " + gameBomberman.partie.joueurs.get(i).vie + " "));
            south.add(new JLabel("Bombes: " + gameBomberman.partie.joueurs.get(i).stockBombe + " "));
            south.add(new JLabel("Score: " + gameBomberman.partie.joueurs.get(i).score + " "));
        }
        infoPanel.add(south);
    }

    public void updateAll(int temps){
        chrono.setText("Temps: " + temps);
        gameBomberman.partie.joueurs.get(0).augmenterScore(1);
        updateInfosPanel();
        plateauPanel.repaint();
    }    
}
