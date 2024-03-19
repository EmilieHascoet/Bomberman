package model;
import java.util.*;

/**
 * 
 */
public class Partie {

    /**
     * Default constructor
     * Paramètres pour la partie, définis par l'utilisateur dans l'écran
     */
    public Partie(Set<String> listBonus, int nbVieInit, int nbBombeInit, int boardWidth, int boardHeight) {
        // Créer 2 joueurs avec les paramètres + génère la carte (créer une carte)
        /*this.listBonus = listBonus;
        this.nbVieInit = nbVieInit;
        this.nbBombeInit = nbBombeInit;
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;*/
        paramètres = new Paramètres(listBonus, nbVieInit, nbBombeInit, boardWidth, boardHeight);
        genererNouvelleCarte();
    }

    // Déclarations des attributs
    public Integer temps = 0;
    /*public Set<String> listBonus;
    public int boardWidth;
    public int boardHeight;
    public int nbVieInit;
    public int nbBombeInit;*/

    // Déclarations des associations
    public List<Joueur> joueurs;
    public Carte carte;
    public Paramètres paramètres;

    // Déclaration des méthodes
    /**
     * Garde les score des 2 joueurs, reset le temps et génère une nouvelle carte
     */
    public void nouvellePartie() {
    }

    /**
     * Génère une nouvelle carte avec la taille
     */
    public void genererNouvelleCarte() {
        carte = new Carte(, boardHeight);
    }

}