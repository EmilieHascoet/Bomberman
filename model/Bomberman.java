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

    /**
     * Default constructor
     */
    public Bomberman() {

    }

    // Déclarations des attributs
    public List<String> nomJoueurs = new ArrayList<>(Arrays.asList("Joueur 1", "Joueur 2"));

    // Déclarations des associations
    public Partie partie;
    public List<Touche> listeTouche = new ArrayList<>();
    public Parametres parametres;

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

    public void setParametres(Set<String> listBonus, int nbVie, int nbBombeInit, int boardWidth, int boardHeight) {
        // Ajout des paramètres pour la partie (définis par l'utilisateur dans l'écran) new 
        if (parametres == null){
            parametres = new Parametres(listBonus, nbVie, nbBombeInit, boardWidth, boardHeight, 1);
        } else{
            parametres.setListBonus(listBonus);
            parametres.setNbVie(nbVie);
            parametres.setNbBombeInit(nbBombeInit);
            parametres.setLargeur(boardWidth);
            parametres.setHauteur(boardHeight);
        }
    }

    /**
     * Prend les paramètres entrés par le joueur et créer une nouvelle partie
     */
    public void nouvellePartie(Parametres parametresPartie) {
        // Créer une nouvelle partie avec les paramètres entrés dans setParametres
        partie = new Partie(parametresPartie, listeTouche, nomJoueurs);
    }
}