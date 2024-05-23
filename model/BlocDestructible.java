package model;

import java.util.Random;
import model.Partie.bonusEnum;

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
        super(false, positionX, positionY, typeCaseEnum.BlocDestructible, true, partie);
    }

    /**
     * 
     */
    public Case destroy(Joueur joueur) {
        Random rand = new Random();
        double random = rand.nextDouble();
        List<bonusEnum> listeBonus = new ArrayList<>(partie.paramPartie.getListBonus());
        if (random < 0.3) {
            Bonus bonus = new Bonus(this.positionX, this.positionY, listeBonus.get(rand.nextInt(listeBonus.size())), partie);
            partie.getCarte()[this.positionY][this.positionX] = bonus;
            joueur.augmenterScore(Points);
            return bonus;
        } else {
            Case caseVide = new Case(true, this.positionX, this.positionY, typeCaseEnum.CaseVide, false, partie);
            partie.getCarte()[this.positionY][this.positionX] = caseVide;
            joueur.augmenterScore(Points);
            return caseVide;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("BlocDestructible {");
        sb.append("\n  positionX: ").append(positionX);
        sb.append("\n  positionY: ").append(positionY);
        sb.append("\n  estTraversable: ").append(estTraversable);
        sb.append("\n  estDestructible: ").append(estDestructible);
        sb.append("\n  typeCase: ").append(typeCase);
        sb.append("\n}");
        return sb.toString();
    }
}