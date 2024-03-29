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

    // Déclarations des associations
    public Partie partie;
    public List<Touche> listeTouche = new ArrayList<>();
    public Parametres parametres;

    // Déclarations des méthodes
    public void setTouche(int num, String haut, String bas, String droite, String gauche, String bombe) {
        if (num > listeTouche.size()) {
            listeTouche.add(new Touche(haut, bas, droite, gauche, bombe));
        } else {
            listeTouche.get(num - 1).setTouches(haut, bas, droite, gauche, bombe);
        }
    }

    public void setParametres(Set<String> listBonus, int nbVie, int nbBombeInit, int boardWidth, int boardHeight) {
        // Ajout des paramètres pour la partie (définis par l'utilisateur dans l'écran) new 
        if(parametres == null){
            parametres = new Parametres(listBonus, nbVie, nbBombeInit, boardWidth, boardHeight);
        }else{
            parametres.setParametres(listBonus, nbVie, nbBombeInit, boardWidth, boardHeight);
        }

    }

    /**
     * Prend les paramètres entrés par le joueur et créer une nouvelle partie
     */
    public void nouvellePartie(Parametres parametres) {
        // Créer une nouvelle partie avec les paramètres entrés dans setParametres
        partie = new Partie(parametres);
    }

}