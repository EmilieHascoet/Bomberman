package model;
import java.util.*;

/**
 * 
 */
public class Partie {

    // Déclarations des attributs
    public Integer temps = 0;

    // Déclarations des associations
    public List<Joueur> joueurs;
    public Carte carte;
    public Parametres param;

    /**
     * Default constructor
     * Paramètres pour la partie, définis par l'utilisateur dans l'écran
     */
    public Partie(Parametres parametres, List<Touche> listTouche, List<String> nomJoueur) {
        // Créer 2 joueurs avec les paramètres + génère la carte (créer une carte)
        this.param = parametres;
        this.joueurs = new ArrayList<>();
        for (int i = 0; i < nomJoueur.size(); i++) {
            Joueur j = new Joueur(nomJoueur.get(i), param.getNbVie(), param.getVitesse(), param.getNbBombeInit(), param.getPorteeBombe(), 0, 0);
            j.touche = listTouche.get(i);
            joueurs.add(j);
        }
        this.joueurs.get(0).positionX = 1;
        this.joueurs.get(0).positionY = 1;
        this.joueurs.get(1).positionX = param.getBoardWidth();
        this.joueurs.get(1).positionY = param.getBoardHeight();
        genererNouvelleCarte();
    }


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
        carte = new Carte(param.getBoardWidth(), param.getBoardHeight(), this);
    }
}