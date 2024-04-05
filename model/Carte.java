package model;

import java.util.List;
import java.util.ArrayList;
/**
 * 
 */
public class Carte {

    List<Case> cases = new ArrayList<Case>();
    // Déclarations des attributs
    public Case[][] map;
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
                    cases.add(map[i][j]);
                }
                // Les 2 coins opposés sont vides pour laisser la place aux joueurs
                else if ((i == 1 && j == 1) || (i == 1 && j == 2) || (i == 2 && j == 1) || (i == n-2 && j == m-2) || (i == n-2 && j == m-3) || (i == n-3 && j == m-2)) {
                    map[i][j] = new Case(true, i, j, "CaseVide", false);
                    cases.add(map[i][j]);
                }
                // Les blocs indesctructibles sont placés sur les cases pairs
                else if (i == 0 || j == 0 || i == n-1 || j == m-1 || (i % 2 == 0 && j % 2 == 0)) {
                    map[i][j] = new BlocDestructible(i, j);
                }
                // Les blocs destructibles sont placés aléatoirement
                else if (Math.random() < 0.5) {
                    map[i][j] = new Case(false, i, j, "BlocDestructible", true);
                    cases.add(map[i][j]);
                } else {
                    map[i][j] = new Case(true, i, j, "CaseVide", false);
                    cases.add(map[i][j]);
                }
                
            }
        }
    }

    /**
     * Affiche la carte
     */
    @Override
    public String toString() {
        String s = "Carte : \n";
        for (int i = 0; i < map.length ; i++) {
            for (int j = 0; j < map[i].length; j++) {
                s += map[i][j].estTraversable + "\t";
            }
            s += "\n";
        }
        return s;
    }
    public void afficherCarte() {
        for (int i = 0; i < map.length ; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if(map[i][j].estTraversable && !map[i][j].estDestructible){
                    System.out.print("\u001B[34m " + "T" + "\t" + "\u001B[0m");
                }
                else if(!map[i][j].estTraversable && map[i][j].estDestructible){
                    System.out.print("\u001B[32m " + "D" + "\t" + "\u001B[0m");
                }
                else if(!map[i][j].estTraversable && !map[i][j].estDestructible){
                    System.out.print("\u001B[31m " + "M" + "\t" + "\u001B[0m");
                }
                else{
                    System.out.print("V" + "\t");
                }
            }
            System.out.println();
        }
    }
}
}

