package model;

import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.util.List;

import javax.swing.JFrame;

public class Main {
    public static JFrame main;

    public static void main(String args[]) throws ParseException {
        // TEST OK
        // testJoueurRamasseBonus();
        // testJoueurPoseBombe();
        // testDestructionBlocDestructible();
        // testRejouer();
        // testExplose();
        // testJoueur1KeyPressed();

        testPartieKeyPressed();
    }

    public static void testExplose() {
        // pour tester la méthode explose de la classe Bombe et voir qu'est que ce passe
        // visuellement c'est mieux d'uiliser
        // le méthode poser bombe sans thread et sans délai
        Bomberman bomberman = new Bomberman();
        // Change la taille par defaut de la carte
        bomberman.parametres.setBoardHeight(9);
        bomberman.parametres.setBoardWidth(9);
        bomberman.parametres.setPorteeBombe(3);
        // Cree la partie
        bomberman.nouvellePartie();
        Joueur j1 = bomberman.partie.joueurs.get(0);
        Joueur j2 = bomberman.partie.joueurs.get(1);

        // Place les joueurs
        j1.placerJoueur(3, 5);
        j2.placerJoueur(3, 4);

        System.out.println("Avant que le joueur ne pose une bombe :\n");
        Carte.afficherCarte();
        System.out.println(j1);
        System.out.println(j2);

        // Pose une bombe
        Bombe bombeJ1 = j1.poseBombe();
        List<Case> casesTraversees = bombeJ1.explose();
        System.out.println("Cases traversées par la bombe : " + casesTraversees);
        System.out.println("Après que le joueur ait pose une bombe :\n" + j1);
        Carte.afficherCarte();
        System.out.println(j2);
    }

    public static void testJoueur1KeyPressed() {
        // Initialisation de la partie
        Bomberman bomberman = new Bomberman();
        // Change la taille par defaut de la carte
        bomberman.parametres.setBoardHeight(10);
        bomberman.parametres.setBoardWidth(10);
        // Cree la partie
        bomberman.nouvellePartie();

        // Recupere le joueur 1
        Joueur j1 = bomberman.partie.joueurs.get(0);
        System.out.println("Avant que le joueur ne se deplace :\n");
        Carte.afficherCarte();
        System.out.println(j1);

        // Deplace le joueur à droite
        List<Case> caseModifiees = j1.jouer(39);
        System.out.println("Après que le joueur se soit deplace a droite :\n" + j1);
        Carte.afficherCarte();
        System.out.println("Cases modifiées : " + caseModifiees);

        // Deplace le joueur à gauche
        j1.jouer(37);
        System.out.println("Après que le joueur se soit deplace a gauche :\n" + j1);
        Carte.afficherCarte();

        // Deplace le joueur en bas
        j1.jouer(40);
        System.out.println("Après que le joueur se soit deplace en bas :\n" + j1);
        Carte.afficherCarte();

        // Pose une bombe
        List<Case> caseModifiees2 = j1.jouer(16);
        System.out.println("Après que le joueur ait posé une bombe :\n" + j1);
        Carte.afficherCarte();
        System.out.println("Cases modifiées : " + caseModifiees2);

        // Deplace le joueur en haut
        j1.jouer(38);
        System.out.println("Après que le joueur se soit deplace en haut :\n" + j1);
        Carte.afficherCarte();
    }

    public static void testPartieKeyPressed() {
        // Initialisation de la partie
        Bomberman bomberman = new Bomberman();
        // Change la taille par defaut de la carte
        bomberman.parametres.setBoardHeight(10);
        bomberman.parametres.setBoardWidth(10);
        // Cree la partie
        bomberman.nouvellePartie();

        // Recupere le joueur 1
        Joueur j1 = bomberman.partie.joueurs.get(0);
        System.out.println("Avant que le joueur ne se deplace :\n");
        Carte.afficherCarte();
        System.out.println(j1);

        // Deplace le joueur à droite
        bomberman.partie.jouerTouche(39);
        System.out.println("Après que le joueur se soit deplace a droite :\n" + j1);
        Carte.afficherCarte();

        // pose une bombe
        bomberman.partie.jouerTouche(16);
        System.out.println("Après que le joueur ait posé une bombe :\n" + j1);
        Carte.afficherCarte();

        // Deplace le joueur à gauche
        bomberman.partie.jouerTouche(37);
        System.out.println("Après que le joueur se soit deplace a gauche :\n" + j1);
        Carte.afficherCarte();
    }

    public static void testJoueurRamasseBonus() {
        // Initialisation de la partie
        Bomberman bomberman = new Bomberman();
        // Change la taille par defaut de la carte
        bomberman.parametres.setBoardHeight(10);
        bomberman.parametres.setBoardWidth(10);
        // Cree la partie
        bomberman.nouvellePartie();

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
        bomberman.nouvellePartie();

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
        bomberman.nouvellePartie();

        // Recupere le joueur 1
        Joueur j1 = bomberman.partie.joueurs.get(0);

        // Ajoute un bloc destructible à droite du joueur
        BlocDestructible blocDestructible = new BlocDestructible(2, 1);
        Carte.map[1][2] = blocDestructible;

        System.out.println("Avant que le joueur ne detruise le bloc destructible :\n");
        Carte.afficherCarte();
        System.out.println((Case) blocDestructible);

        // Detruit le bloc destructible
        blocDestructible.destruction(j1);
        System.out.println("Après destruction du bloc destructible :\n" + Carte.map[1][2] + j1);
        Carte.afficherCarte();
    }

    public static void testRejouer() {
        // Initialisation de la partie
        Bomberman bomberman = new Bomberman();
        // Change la taille par defaut de la carte
        bomberman.parametres.setBoardHeight(10);
        bomberman.parametres.setBoardWidth(10);
        // Cree la partie
        bomberman.nouvellePartie();

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

        // Rejoue la partie
        bomberman.partie.rejouer();
        System.out.println("Après que la partie ait été rejouée :\n" + j1);
        Carte.afficherCarte();
    }
}