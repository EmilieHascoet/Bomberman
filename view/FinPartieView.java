package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import model.Partie;

public class FinPartieView extends JDialog {
    private JButton buttonRejouer;
    private JButton buttonMenu;
    private JButton buttonExit;
    private MainFrame frame;
    private Partie partie;

    public FinPartieView(MainFrame frame, Partie partie) {
        super(frame, "Fin de Partie", true);
        this.frame = frame;
        this.partie = partie;
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setSize(500, 460);
        setLocationRelativeTo(null);

        // Configuration du panneau de fond avec l'image
        setContentPane(new JPanel(new GridBagLayout()) {
            private Image backgroundImage = new ImageIcon(getClass().getResource("/Images/finpartie.png")).getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
            }
        });

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        setupComponents(gbc);
    }

    public void display() {
        setVisible(true);
    }

    public MainFrame getMainFrame() {
        return frame;
    }

    public Partie gePartie() {
        return partie;
    }

    private void setupComponents(GridBagConstraints gbc) {
        // Déterminez le joueur avec le score le plus élevé
        int maxScore = partie.getGagnant().getScore();
        int winningIndex = partie.getJoueurs().indexOf(partie.getGagnant());

        // Gagnant en haut
        JLabel labelGagnant = new JLabel("Player " + (winningIndex + 1) + " gagne avec " + maxScore + " points!");
        labelGagnant.setForeground(Color.BLUE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER; // S'étend sur toute la largeur
        gbc.anchor = GridBagConstraints.CENTER;
        add(labelGagnant, gbc);

        // Scores des joueurs, deux lignes avec positionnement à gauche et à droite
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTH;
        int numPlayers = partie.getJoueurs().size();
        for (int i = 0; i < numPlayers; i++) {
            JLabel scoreLabel = new JLabel("Player " + (i + 1) + ": " + partie.getJoueurs().get(i).getScore());
            if (i < 2) { // Joueurs 1 et 2 à gauche
                gbc.gridx = i;
                gbc.gridy = 1;
            } else { // Joueurs 3 et 4 à droite
                gbc.gridx = 2 + i - 2;
                gbc.gridy = 1;
            }
            add(scoreLabel, gbc);
        }

        // Boutons en bas
        gbc.gridx = 0;
        gbc.gridy = 2; // Ajustez cette valeur si nécessaire pour l'espace
        gbc.gridwidth = 1;
        gbc.weighty = 1; // Pousse les boutons en bas
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.SOUTH;

        buttonExit = new JButton("Sortir");
        add(buttonExit, gbc);

        buttonRejouer = new JButton("Rejouer");
        gbc.gridx = 1;
        add(buttonRejouer, gbc);

        buttonMenu = new JButton("Accueil");
        gbc.gridx = 2;
        add(buttonMenu, gbc);
    }

    public void setRejouerAction(ActionListener listener) {
        buttonRejouer.addActionListener(listener);
    }

    public void setMenuAction(ActionListener listener) {
        buttonMenu.addActionListener(listener);
    }

    public void setExitAction(ActionListener listener) {
        buttonExit.addActionListener(listener);
    }
}
