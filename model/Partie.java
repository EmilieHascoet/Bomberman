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
    public Partie(Parametres parametres, List<Touche> listTouche, List<String> nomJoueur) {
        // Créer 2 joueurs avec les paramètres + génère la carte (créer une carte)
        this.param = parametres;
        this.joueurs = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            Joueur j = new Joueur(nomJoueur.get(i), param.getNbVie(), param.getVitesse(), param.getNbBombeInit(), param.getPorteeBombe(), 0, 0);
            j.touche = listTouche.get(i);
            joueurs.add(j);
        }
        genererNouvelleCarte();
    }

    // Déclarations des attributs
    public Integer temps = 0;

    // Déclarations des associations
    public List<Joueur> joueurs;
    public Carte carte;
    public Parametres param;

    // Déclaration des méthodes
    /**
     * Rejouer / Garde les scores des 2 joueurs, reset le temps et génère une nouvelle carte
     */
    public void rejouer() {
    }

    /**
     * Génère une nouvelle carte avec la taille
     */
    public void genererNouvelleCarte() {
        carte = new Carte(param.getBoardWidth(), param.getBoardHeight());
    }
}