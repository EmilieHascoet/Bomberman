package model;
import java.text.ParseException;

import javax.swing.JFrame;

public class Main {
    public static JFrame main;
    public static void main(String args[]) throws ParseException {
        //main = new FirstPageView();
        Carte carte = new Carte(10, 10);
        // voir le resultat en console
        System.out.println("carte.map.length : " + carte.map.length);
        System.out.println("Carte générée : ");
        for (int i = 0; i < carte.map.length ; i++) {
            for (int j = 0; j < carte.map[i].length; j++) {
                System.out.print(carte.map[i][j].estTraversable + "\t");
            }
            System.out.println();
        }
    }
}
