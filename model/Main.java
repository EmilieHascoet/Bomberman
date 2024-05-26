package model;

import java.text.ParseException;

public class Main {

    private static Partie partie;
    public static void main(String args[]) throws ParseException {
        // Création de la partie   
        partie = new Partie();
        partie.lancerNouvellePartie();

        testJoueurRamasseBonus();
        testPartieKeyPressed();
        testRejouer();
    }

    // Test de la méthode estRamassee de la classe Bonus
    public static void testJoueurRamasseBonus() {
        System.out.println("Test de la méthode estRamassee de la classe Bonus");
        Joueur j2 = partie.getJoueurs().get(1);
        Bonus bonus = new Bonus(2, 1, Partie.bonusEnum.Vie, partie);
        partie.getCarte()[1][2] = bonus;
        System.out.println("Bonus " + bonus);
        partie.jouerTouche("D");
        System.out.println("Joueur 2 : " + j2);
    }
    
    // Test de la méthode jouer de la classe Joueur
    public static void testPartieKeyPressed() {
        System.out.println("Test de la méthode jouerTouche de la classe Partie");
        Joueur j1 = partie.getJoueurs().get(0);
        partie.jouerTouche(j1.touche.getHaut());
        System.out.println("J1 aller en haut, carte :");
        partie.afficherCarte();
        partie.jouerTouche(j1.touche.getBombe());
        System.out.println("J1 Bombe, carte :");
        partie.afficherCarte();
        partie.jouerTouche(j1.touche.getBas());
        System.out.println("J1 aller en bas, carte :");
        partie.afficherCarte();
    }
    
    // Test de la méthode lancerNouvellePartie de la classe Partie, pour rejouer
    public static void testRejouer() {
        System.out.println("Test de la méthode lancerNouvellePartie de la classe Partie, pour rejouer");
        partie.lancerNouvellePartie();
        System.out.println("Carte : ");
        partie.afficherCarte();
        // afficher joueuers
        for (Joueur j : partie.getJoueurs()) {
            System.out.println(j);
        }
    }
}