package model;
import java.text.ParseException;

import javax.swing.JFrame;

public class Main {
    public static JFrame main;
    public static void main(String args[]) throws ParseException {
        //main = new FirstPageView();
        Bomberman bomberman = new Bomberman();
        bomberman.nouvellePartie(bomberman.parametres);
        System.out.println(bomberman);
    }
}