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
    public static Parametres paramPartie;

    /**
     * Default constructor
     * Paramètres pour la partie, définis par l'utilisateur dans l'écran
     */
    public Partie(Parametres parametres, List<Touche> listTouche, List<String> nomJoueur) {
        // Créer 2 joueurs avec les paramètres + génère la carte (créer une carte)
        Partie.paramPartie = parametres;
        this.joueurs = new ArrayList<>();

        Joueur j1 = new Joueur(nomJoueur.get(0), 1, 1);
        j1.touche = listTouche.get(0);
        joueurs.add(j1);
        
        Joueur j2 = new Joueur(nomJoueur.get(1), parametres.getBoardWidth(), parametres.getBoardHeight());
        j2.touche = listTouche.get(1);
        joueurs.add(j2);

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
        carte = new Carte(Partie.paramPartie.getBoardWidth(), Partie.paramPartie.getBoardHeight(), this);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Partie {");
        sb.append("\n  temps: ").append(temps);
        sb.append("\n  joueurs: ").append(joueurs);
        sb.append("\n  carte: ").append(carte);
        sb.append("\n  paramPartie: ").append(paramPartie);
        sb.append("\n}");
        return sb.toString();
    }
}