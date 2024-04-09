package model;
import java.text.ParseException;

import javax.swing.JFrame;

public class Main {
    public static JFrame main;
    public static void main(String args[]) throws ParseException {
        //testJoueurRamasseBonus();
        //testJoueurPoseBombe();
        testDestructionBlocDestructible();
    }

    public static void testJoueurRamasseBonus() {
        // Initialisation de la partie
        Bomberman bomberman = new Bomberman();
        // Change la taille par defaut de la carte
        bomberman.parametres.setBoardHeight(10);
        bomberman.parametres.setBoardWidth(10);
        // Cree la partie
        bomberman.nouvellePartie(bomberman.parametres);

        // Ajoute un bonus à droite du joueur
        Carte.map[1][2] = new Bonus(2, 1, "vie");
        System.out.println(Carte.map[1][2]);

        // Recupere le joueur 1
        Joueur j1 = bomberman.partie.joueurs.get(0);
        System.out.println("Avant que le joueur ne se deplace :\n");
        Carte.afficherCarte();
        System.out.println(j1);

        // Deplace le joueur sur le bonus
        j1.seDeplacer("droite");

        // Affiche la carte + le joueur
        System.out.println("Après que le joueur se soit deplace :\n" + j1);
        Carte.afficherCarte();
    }

    public static void testJoueurPoseBombe() {
        // Initialisation de la partie
        Bomberman bomberman = new Bomberman();
        // Change la taille par defaut de la carte
        bomberman.parametres.setBoardHeight(10);
        bomberman.parametres.setBoardWidth(10);
        // Cree la partie
        bomberman.nouvellePartie(bomberman.parametres);

        // Recupere le joueur 1
        Joueur j1 = bomberman.partie.joueurs.get(0);
        System.out.println("Avant que le joueur ne pose une bombe :\n");
        Carte.afficherCarte();
        System.out.println(j1);

        // Pose une bombe
        Bombe bombe1 = j1.poseBombe();
        bombe1.explose();
        System.out.println("Après que le joueur ait pose une bombe :\n" + j1);
        Carte.afficherCarte();
    }

    public static void testDestructionBlocDestructible() {
        // Initialisation de la partie
        Bomberman bomberman = new Bomberman();
        // Change la taille par defaut de la carte
        bomberman.parametres.setBoardHeight(10);
        bomberman.parametres.setBoardWidth(10);
        // Cree la partie
        bomberman.nouvellePartie(bomberman.parametres);

        // Recupere le joueur 1
        Joueur j1 = bomberman.partie.joueurs.get(0);

        // Ajoute un bloc destructible à droite du joueur
        BlocDestructible blocDestructible = new BlocDestructible(2, 1);
        Carte.map[1][2] = blocDestructible;

        System.out.println("Avant que le joueur ne detruise le bloc destructible :\n");
        Carte.afficherCarte();
        System.out.println((Case)blocDestructible);

        // Detruit le bloc destructible
        blocDestructible.destruction(j1);
        System.out.println("Après destruction du bloc destructible :\n" + Carte.map[1][2] + j1);
        Carte.afficherCarte();
    }
}