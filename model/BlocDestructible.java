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
    public BlocDestructible(Integer positionX, Integer positionY) {
        super(false, positionX, positionY, "BlocDestructible", true);
    }

    /**
     * 
     */
    public void destruction(Joueur joueur) {
        Random rand = new Random();
        double random = rand.nextDouble();
        List<String> listeBonus = new ArrayList<String>();
        listeBonus.addAll(Partie.paramPartie.getListBonus());
        if (random < 0.3) {
            Bonus bonus = new Bonus(this.positionX, this.positionY, listeBonus.get(rand.nextInt(listeBonus.size())));
            Carte.map[this.positionY][this.positionX] = bonus;
        } else {
            Case caseVide = new Case(true, this.positionX, this.positionY, "CaseVide", false);
            Carte.map[this.positionY][this.positionX] = caseVide;
        }
        joueur.score += super.Points;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("BlocDestructible {");
        sb.append("\n  positionX: ").append(positionX);
        sb.append("\n  positionY: ").append(positionY);
        sb.append("\n  estTraversable: ").append(estTraversable);
        sb.append("\n  estDestructible: ").append(estDestructible);
        sb.append("\n  typeImage: ").append(typeImage);
        sb.append("\n}");
        return sb.toString();
    }
}