package model;


import view.PartiePanel;
import java.util.*;



/**
 * 
 */
public class Partie {

    // Déclarations des associations
    public List<Joueur> joueurs;
    public static Parametres paramPartie;

    /**
     * Default constructor
     * Paramètres pour la partie, définis par l'utilisateur dans l'écran
     */
    public Partie(Parametres parametres, List<Touche> listTouche, List<String> nomJoueur) {
        // Créer 2 joueurs avec les paramètres + génère la carte (créer une carte)
        Partie.paramPartie = parametres;
        this.joueurs = new ArrayList<>();

        Joueur j1 = new Joueur(nomJoueur.get(0), parametres.getBoardWidth(), parametres.getBoardHeight());
        j1.touche = listTouche.get(0);
        joueurs.add(j1);
        
        Joueur j2 = new Joueur(nomJoueur.get(1), 1, 1);
        j2.touche = listTouche.get(1);
        joueurs.add(j2);
        
        genererNouvelleCarte();
    }


    // Déclaration des méthodes

    public List<Case> jouerTouche(int KeyCode) {
        List<Case> casesModifiees = new ArrayList<Case>();
        for (Joueur joueur : joueurs) {
            List<Case> casesModifieesJoueur = joueur.jouer(KeyCode);
            if (casesModifieesJoueur != null) casesModifiees.addAll(casesModifieesJoueur);
        }
        return casesModifiees;
    }
    /**
     * Rejouer / Garde les scores des 2 joueurs et génère une nouvelle carte
     */
    public void rejouer() {
        joueurs.get(0).reinitialiserJoueur(1, 1);
        joueurs.get(1).reinitialiserJoueur(Partie.paramPartie.getBoardWidth(), Partie.paramPartie.getBoardHeight());
        genererNouvelleCarte();
    }

    /**
     * Génère une nouvelle carte avec la taille
     */
    public void genererNouvelleCarte() {
        Carte.genererNouvelleCarteCarte(Partie.paramPartie.getBoardWidth(), Partie.paramPartie.getBoardHeight(), this);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Partie {");
        sb.append("\n  joueurs: ").append(joueurs);
        sb.append("\n  paramPartie: ").append(paramPartie);
        sb.append("\n}");
        return sb.toString();
    }
}