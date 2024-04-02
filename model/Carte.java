package model;
/**
 * 
 */
public class Carte {

    /**
     * Génère la carte avec la taille, les blocs destructibles et les blocs indestructibles
     */
    public Carte(int longueur, int hauteur) {
        int n = longueur + 2;
        int m = hauteur + 2;
        map = new Case[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // La carte est entourée de blocs indestructibles
                if (i == 0 || j == 0 || i == n-1 || j == m-1) {
                    map[i][j] = new Case(false, i, j, "BlocIndestructible", false);
                }
                // Les 2 coins opposés sont vides pour laisser la place aux joueurs
                else if ((i == 1 && j == 1) || (i == 1 && j == 2) || (i == 2 && j == 1) || (i == n-2 && j == m-2) || (i == n-2 && j == m-3) || (i == n-3 && j == m-2)) {
                    map[i][j] = new Case(true, i, j, "CaseVide", false);
                }
                // Les blocs indesctructibles sont placés sur les cases pairs
                else if (i == 0 || j == 0 || i == n-1 || j == m-1 || (i % 2 == 0 && j % 2 == 0)) {
                    map[i][j] = new Case(false, i, j, "BlocIndestructible", false);
                }
                // Les blocs destructibles sont placés aléatoirement
                else if (Math.random() < 0.5) {
                    map[i][j] = new Case(false, i, j, "BlocDestructible", true);
                } else {
                    map[i][j] = new Case(true, i, j, "CaseVide", false);
                }
                
            }
        }
    }

    public void afficherCarte() {
        for (int i = 0; i < map.length ; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j].estTraversable + "\t");
            }
            System.out.println();
        }
    }

    // Déclarations des attributs
    public Case[][] map;
}

