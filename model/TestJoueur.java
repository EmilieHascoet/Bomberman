package model;
import java.text.ParseException;

import javax.swing.JFrame;

public class TestJoueur {
    public static JFrame main;
    public static void main(String args[]) throws ParseException {
        //main = new FirstPageView();
        Bomberman bomberman = new Bomberman();
        bomberman.parametres.setBoardHeight(10);
        bomberman.parametres.setBoardWidth(10);
        bomberman.nouvellePartie(bomberman.parametres);
        System.out.println(Carte.map[1][1]);

        Joueur j1 = bomberman.partie.joueurs.get(0);
        Joueur j2 = bomberman.partie.joueurs.get(1);
        j1.seDeplacer("droite");
        j1.poserBombe();
        j1.seDeplacer("gauche");
        j1.seDeplacer("bas");
        System.out.println(bomberman.partie.carte);
    }
}
