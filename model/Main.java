package model;
import java.text.ParseException;
import java.util.Set; //Pour test
import java.util.HashSet; //Pour test
import java.util.Arrays; //Pour test

import javax.swing.JFrame;

public class Main {
    public static JFrame main;
    public static void main(String args[]) throws ParseException {
        //main = new FirstPageView();
        Bomberman bomberman = new Bomberman();
        Set<String> listBonus = new HashSet<String>(Arrays.asList("Bombe", "Flamme", "Vie", "Vitesse"));
        bomberman.setParametres(listBonus, 3, 1, 15, 15);
        bomberman.nouvellePartie(bomberman.parametres);
        bomberman.partie.nouvellePartie();
        System.out.println(bomberman.partie.carte);
    }
}