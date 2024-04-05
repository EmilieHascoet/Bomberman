package model;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

import java.util.HashSet;

/**
 * 
 */
public class Bomberman {

    // Déclarations des attributs
    public List<String> nomJoueurs = new ArrayList<>();

    // Déclarations des associations
    public List<Partie> parties = new ArrayList<>();
    public List<Touche> listeTouche = new ArrayList<>();
    public Parametres parametres;

    /**
     * Default constructor
     */
    public Bomberman() {
        setTouche(1, "Fleche du haut", "Fleche du bas", "Fleche de droite", "Fleche de gauche", "Shift");
        setTouche(2, "Z", "S", "D", "Q", "Espace");
        setParametres(new HashSet<String>(Arrays.asList("Bombe", "Vie", "Vitesse", "Portée")), 3, 1, 1, 1, 15, 15);
        setNomJoueurs(0, "Joueur 1");
        setNomJoueurs(1, "Joueur 2");
    }

    // Déclarations des méthodes
    public void setTouche(int joueur, String haut, String bas, String droite, String gauche, String bombe) {
        if (joueur > listeTouche.size()) {
            listeTouche.add(new Touche(haut, bas, droite, gauche, bombe));
        } else {
            listeTouche.get(joueur - 1).setHaut(haut);
            listeTouche.get(joueur - 1).setBas(bas);
            listeTouche.get(joueur - 1).setDroite(droite);
            listeTouche.get(joueur - 1).setGauche(gauche);
            listeTouche.get(joueur - 1).setBombe(bombe);
        }
    }

    public void setParametres(Set<String> listBonus, int nbVie, int vitesse, int nbBombeInit, int porteeBombe,
            int boardWidth, int boardHeight) {
        // Ajout des paramètres pour la partie (définis par l'utilisateur dans l'écran)
        // new
        if (parametres == null) {
            parametres = new Parametres(listBonus, nbVie, vitesse, nbBombeInit, porteeBombe, boardWidth, boardHeight);
        } else {
            parametres.setListBonus(listBonus);
            parametres.setNbVie(nbVie);
            parametres.setNbBombeInit(nbBombeInit);
            parametres.setBoardWidth(boardWidth);
            parametres.setBoardHeight(boardHeight);
        }
    }

    public void setNomJoueurs(int index, String nomJoueur) {
        if(nomJoueurs.size() <= index) {
            nomJoueurs.add(nomJoueur);
        } else {
            nomJoueurs.set(index, nomJoueur);
        }
    }

    /**
     * Prend les paramètres entrés par le joueur et créer une nouvelle partie
     */
    public void nouvellePartie(Parametres parametresPartie) {
        // Créer une nouvelle partie avec les paramètres entrés dans setParametres
        parties.add(new Partie(parametresPartie, listeTouche, nomJoueurs));
    }

    @Override
    public String toString() {
        return ("les joueurs sont : " + nomJoueurs + "les parties sont : " + parties + "les touches sont : "
                + listeTouche + "les parametres sont : " + parametres);
    }
}