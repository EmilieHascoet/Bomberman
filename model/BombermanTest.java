package model;

import java.util.Scanner;
import java.util.HashSet;
import java.util.Arrays;

public class BombermanTest {

    public static void main(String[] args) {

        Bomberman bomberman = new Bomberman();

        bomberman.setTouche(1, "Fleche haut", "Fleche bas", "Fleche droite", "Fleche gauche", "Shift Droit");

        Scanner scanner = new Scanner(System.in);
        String nbVie, nbBombeInit, boardWidth, boardHeight; // Declare the nbVie variable outside of the do-while loop
        do {
            System.out.println("nombre de vie : ");
            nbVie = scanner.nextLine();
        } while (!isNumeric(nbVie));

        do {
            System.out.println("nombre de bomb : ");
            nbBombeInit = scanner.nextLine();
        } while (!isNumeric(nbBombeInit));

        do {
            System.out.println("nombre de boardWidth : ");
            boardWidth = scanner.nextLine();
        } while (!isNumeric(boardWidth));

        do {
            System.out.println("nombre boardHeight : ");
            boardHeight = scanner.nextLine();
        } while (!isNumeric(boardHeight));

        bomberman.setParametres(new HashSet<String>(Arrays.asList("Bombe", "Vie", "Vitesse", "Portée")),
                Integer.parseInt(nbVie), Integer.parseInt(nbBombeInit), Integer.parseInt(boardWidth),
                Integer.parseInt(boardHeight));

    }

    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
