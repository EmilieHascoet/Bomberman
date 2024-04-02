package model;
import java.util.*;

/**
 * 
 */
public class Partie {
    public Parametres param;

    public List<Joueur> joueurs;
    public Carte carte;
    public Parametres paramètres;
    /**
     * Default constructor
     * Paramètres pour la partie, définis par l'utilisateur dans l'écran
     */
    public Partie(Parametres parametres) {
        // Créer 2 joueurs avec les paramètres + génère la carte (créer une carte)
        this.param = parametres;
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
        carte = new Carte(param.getLargeur(), param.getHauteur());
    }

}