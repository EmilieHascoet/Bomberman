package model;
import java.text.ParseException;

import javax.swing.JFrame;

public class Main {
    public static JFrame main;
    public static void main(String args[]) throws ParseException {
        testJoueurRamasseBonus();
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
        System.out.println("Avant que le joueur ne se deplace :\n" + bomberman.partie.carte);
        System.out.println(j1);

        // Deplace le joueur sur le bonus
        j1.seDeplacer("droite");
        System.out.println("Après bonus :\n" +j1);

        // Affiche la carte + le joueur
        System.out.println("Après que le joueur se soit deplace :\n" + bomberman.partie.carte + j1);
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
        System.out.println("Avant que le joueur ne pose une bombe :\n" + bomberman.partie.carte);
        System.out.println(j1);

        // Pose une bombe
        j1.poseBombe();
        System.out.println("Après que le joueur ait pose une bombe :\n" + bomberman.partie.carte + j1);
    }
}