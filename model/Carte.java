package model;

import java.util.List;
import java.util.ArrayList;

/**
 * 
 */
public class Carte {
    // Déclarations des attributs
    public Case[][] map;
    public Partie partie;

    /**
     * Génère la carte avec la taille, les blocs destructibles et les blocs
     * indestructibles
     */
    public Carte(int longueur, int hauteur, Partie partie) {
        this.partie = partie;
        // Genere la carte avec la hauteur et longueur en tree en parametres
        int m = longueur + 2;
        int n = hauteur + 2;
        map = new Case[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // La carte est entourée de blocs indestructibles
                if (i == 0 || j == 0 || i == n - 1 || j == m - 1) {
                    map[i][j] = new Case(false, i, j, "BlocIndestructible", false, partie);
                }
                // Les 2 coins opposés sont vides pour laisser la place aux joueurs
                else if ((i == 1 && j == 1) || (i == 1 && j == 2) || (i == 2 && j == 1) || (i == n - 2 && j == m - 2)
                        || (i == n - 2 && j == m - 3) || (i == n - 3 && j == m - 2)) {
                    map[i][j] = new Case(true, i, j, "CaseVide", false, partie);
                }
                // Les blocs indesctructibles sont placés sur les cases pairs
                else if (i == 0 || j == 0 || i == n - 1 || j == m - 1 || (i % 2 == 0 && j % 2 == 0)) {
                    map[i][j] = new BlocDestructible(i, j, partie);
                }
                // Les blocs destructibles sont placés aléatoirement
                else if (Math.random() < 0.5) {
                    map[i][j] = new BlocDestructible(i, j, partie);
                } else {
                    map[i][j] = new Case(true, i, j, "CaseVide", false, partie);
                }

            }
        }
        // place les joueurs sur les cases
        map[1][1].setJoueur(partie.joueurs.get(0));
        map[n - 2][m - 2].setJoueur(partie.joueurs.get(1));
    }

    /**
     * Affiche la carte
     */
    @Override
    public String toString() {
        String s = "Carte : \n";
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j].joueur != null) {
                    s += map[i][j].joueur.nom + "\t";
                } else {
                    s += map[i][j].estTraversable + "\t";
                }
            }
            s += "\n";
        }
        return s;
    }

    /* pour test */
    public void afficherCarte() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j].estTraversable && !map[i][j].estDestructible) {
                    System.out.print("\u001B[34m " + "T" + "\t" + "\u001B[0m");
                } else if (!map[i][j].estTraversable && map[i][j].estDestructible) {
                    System.out.print("\u001B[32m " + "D" + "\t" + "\u001B[0m");
                } else if (!map[i][j].estTraversable && !map[i][j].estDestructible) {
                    System.out.print("\u001B[31m " + "M" + "\t" + "\u001B[0m");
                } else {
                    System.out.print("V" + "\t");
                }
            }
            System.out.println();
        }
    }
}
