package view;

import javax.swing.*;
import java.awt.*;

public class FinPartieView extends JFrame {

    public FinPartieView(int scorePlayer1, int scorePlayer2, int nbBombesUtilisees, boolean isPlayer1Winner) {
        // Configurer le titre de la fenêtre.
        super("Fin de Partie");

        // Configurer l'opération de fermeture par défaut.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Définir la taille de la fenêtre.
        setSize(400, 220);
        // Centrer la fenêtre.
        setLocationRelativeTo(null);

        // Utiliser GridBagLayout pour le placement des composants.
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Définir l'espacement externe des composants.
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;

        // Afficher le gagnant avec une couleur différente.
        JLabel labelGagnant = new JLabel(isPlayer1Winner ? "Player 1 gagne !" : "Player 2 gagne !");
        labelGagnant.setForeground(isPlayer1Winner ? Color.BLUE : Color.RED);
        add(labelGagnant, gbc);

        // Réinitialiser la largeur de la grille pour les éléments suivants.
        gbc.gridwidth = 1;
        gbc.gridx = 1;
        gbc.gridy = 1;

        // Afficher le score du joueur 1.
        JLabel labelScorePlayer1 = new JLabel("Score Player 1: " + scorePlayer1);
        add(labelScorePlayer1, gbc);

        gbc.gridy = 2;

        // Afficher le score du joueur 2.
        JLabel labelScorePlayer2 = new JLabel("Score Player 2: " + scorePlayer2);
        add(labelScorePlayer2, gbc);

        gbc.gridy = 3;

        // Afficher le nombre de bombes utilisées.
        JLabel labelNbBombes = new JLabel("Nb. bombes utilisées: " + nbBombesUtilisees);
        add(labelNbBombes, gbc);

        // Placer les boutons côte à côte sur la même ligne.
        gbc.gridwidth = 1;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Créer le bouton "Exit".
        gbc.gridx = 0;
        JButton buttonExit = new JButton("Sorti");
        add(buttonExit, gbc);

        // Créer le bouton "Rejouer".
        gbc.gridx = 1;
        JButton buttonRejouer = new JButton("Rejouer");
        add(buttonRejouer, gbc);

        // Créer le bouton "Menu".
        gbc.gridx = 2;
        JButton buttonMenu = new JButton("Accueil");
        add(buttonMenu, gbc);

        // Rendre la fenêtre visible.
        setVisible(true);
    }

    public static void main(String[] args) {
        // Création de la fenêtre de fin de partie avec des valeurs de test.
        SwingUtilities.invokeLater(() -> new FinPartieView(3, 5, 10, true));
    }
}
