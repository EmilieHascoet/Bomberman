package view;

import javax.swing.*;
import java.awt.*;

public class FinPartieView extends JPanel {

    public FinPartieView(int scorePlayer1, int scorePlayer2, int nbBombesUtilisees, boolean isPlayer1Winner) {
        // Utiliser GridBagLayout pour le placement des composants.
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Définir l'espacement externe des composants.
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;

        // Afficher le gagnant avec une couleur différente.
        JLabel labelGagnant = new JLabel(isPlayer1Winner ? "Player 1 gagne !" : "Player 2 gagne !");
        labelGagnant.setForeground(isPlayer1Winner ? Color.BLUE : Color.RED);
        this.add(labelGagnant, gbc);

        // Réinitialiser la largeur de la grille pour les éléments suivants.
        gbc.gridwidth = 1;
        gbc.gridx = 1;
        gbc.gridy = 1;

        // Afficher le score du joueur 1.
        JLabel labelScorePlayer1 = new JLabel("Score Player 1: " + scorePlayer1);
        this.add(labelScorePlayer1, gbc);

        gbc.gridy = 2;

        // Afficher le score du joueur 2.
        JLabel labelScorePlayer2 = new JLabel("Score Player 2: " + scorePlayer2);
        this.add(labelScorePlayer2, gbc);

        gbc.gridy = 3;

        // Afficher le nombre de bombes utilisées.
        JLabel labelNbBombes = new JLabel("Nb. bombes utilisées: " + nbBombesUtilisees);
        this.add(labelNbBombes, gbc);

        // Placer les boutons côte à côte sur la même ligne.
        gbc.gridwidth = 1;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Créer le bouton "Sortir".
        gbc.gridx = 0;
        JButton buttonExit = new JButton("Sortir");
        this.add(buttonExit, gbc);

        // Créer le bouton "Rejouer".
        gbc.gridx = 1;
        JButton buttonRejouer = new JButton("Rejouer");
        this.add(buttonRejouer, gbc);

        // Créer le bouton "Accueil".
        gbc.gridx = 2;
        JButton buttonMenu = new JButton("Accueil");
        this.add(buttonMenu, gbc);
    }

    // La méthode main pourrait être utilisée pour tester le panel.

}
