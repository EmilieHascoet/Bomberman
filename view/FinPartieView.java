package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import controller.AccueilControleur;
import controller.FinPartieController;
import model.Bomberman;

public class FinPartieView extends JFrame {

    private JButton buttonExit;
    private JButton buttonRejouer;
    private JButton buttonMenu;

    public FinPartieView(int scorePlayer1, int scorePlayer2, int nbBombesUtilisees, boolean isPlayer1Winner) {
        // Configuration initiale...
        super("Fin de Partie");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 220);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Ajout des composants...
        setupComponents(gbc, scorePlayer1, scorePlayer2, nbBombesUtilisees, isPlayer1Winner);

        setVisible(true);
    }

    private void setupComponents(GridBagConstraints gbc, int scorePlayer1, int scorePlayer2, int nbBombesUtilisees,
            boolean isPlayer1Winner) {
        JLabel labelGagnant = new JLabel(isPlayer1Winner ? "Player 1 gagne !" : "Player 2 gagne !");
        labelGagnant.setForeground(isPlayer1Winner ? Color.BLUE : Color.RED);
        add(labelGagnant, gbc);

        gbc.gridwidth = 1;
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(new JLabel("Score Player 1: " + scorePlayer1), gbc);
        gbc.gridy = 2;
        add(new JLabel("Score Player 2: " + scorePlayer2), gbc);
        gbc.gridy = 3;
        add(new JLabel("Nb. bombes utilisées: " + nbBombesUtilisees), gbc);

        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        buttonExit = new JButton("Sortir");
        gbc.gridx = 0;
        add(buttonExit, gbc);

        buttonRejouer = new JButton("Rejouer");
        gbc.gridx = 1;
        add(buttonRejouer, gbc);

        buttonMenu = new JButton("Accueil");
        gbc.gridx = 2;
        add(buttonMenu, gbc);
    }

    public void addExitButtonListener(ActionListener listener) {
        buttonExit.addActionListener(listener);
    }

    public void addReplayButtonListener(ActionListener listener) {
        buttonRejouer.addActionListener(listener);
    }

    public void addMenuButtonListener(ActionListener listener) {
        buttonMenu.addActionListener(listener);
    }

    /*
     * public static void main(String[] args) {
     * // Création de la fenêtre de fin de partie avec des valeurs de test.
     * SwingUtilities.invokeLater(() -> new FinPartieController(new FinPartieView(3,
     * 5, 10, true),
     * new AccueilControleur(null, null, null, null)));
     * 
     * }
     */
}
