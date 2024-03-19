package model;

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
    public Contrôle contrôle;

    // Déclarations des méthodes
    /**
     * Prend les paramètres entrés par le joueur et créer une nouvelle partie
     */
    public void nouvellePartie( Set<String> listBonus, int nbVie, int nbBombeInit, int boardWidth, int boardHeight) {
        partie = new Partie(listBonus, nbVie, nbBombeInit, boardWidth, boardHeight);
    }

}