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
    public Partie(Set<String> listBonus, int nbVie, int nbBombeInit, int hauteurCarte, int longueurCarte, String j1Haut, String j1Bas, String j1Gauche, String j1Droite, String j2Haut, String j2Bas, String j2Gauche, String j2Droite, String j1Bombe, String j2Bombe) {
        // Créer 2 joueurs avec les paramètres + génère la carte (créer une carte)
        this.listBonus = listBonus;
        this.hauteurCarte = hauteurCarte;
        this.longueurCarte = longueurCarte;
        genererNouvelleCarte();
    }

    // Déclarations des attributs
    public Integer temps = 0;
    public Set<String> listBonus;
    public int longueurCarte;
    public int hauteurCarte;

    // Déclarations des associations
    List<Joueur> joueurs;
    public Carte carte;

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
        carte = new Carte(longueurCarte, hauteurCarte);
    }

}