package model;

import java.util.Random;
import java.util.List;
import java.util.ArrayList;

/**
 * 
 */
public class BlocDestructible extends Case {

    /**
     * Default constructor
     */
    public BlocDestructible(Integer positionX, Integer positionY, Partie partie) {
        super(false, positionX, positionY, "BlocDestructible", true, partie);
    }

    /**
     * 
     */
    public void destruction() {
        Random rand = new Random();
        double random = rand.nextDouble();
        List<String> listeBonus = new ArrayList<String>();
        listeBonus.addAll(this.partie.param.getListBonus());
        if (random < 0.3) {
            Bonus bonus = new Bonus(this.positionX, this.positionY, true, listeBonus.get(rand.nextInt(listeBonus.size())), this.partie);
            this.partie.carte.map[this.positionX][this.positionY] = bonus;
        } else {
            this.partie.carte.map[this.positionX][this.positionY].estTraversable = true; 
            this.partie.carte.map[this.positionX][this.positionY].estDestructible = false;
            this.partie.carte.map[this.positionX][this.positionY].typeImage = "CaseVide";
        }        
    }

    @Override
    public String toString() {
        return ("A destructible block at Positon : " + positionX + positionY);
    }
}