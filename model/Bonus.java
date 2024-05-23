package model;

import model.Partie.bonusEnum;

/**
 * 
 */
public class Bonus extends Case {

    // Déclarations des attributs
    private Boolean isMalus;
    private bonusEnum effet;

    /**
     * Default constructor
     */
    public Bonus(Integer positionX, Integer positionY, bonusEnum effet, Partie partie) {
        super(true, positionX, positionY, typeCaseEnum.Bonus, false, partie);
        // 30% de chance que ce soit un malus
        if (Math.random() < 0.3) {
            this.isMalus = true;
            super.Points += (int) (Math.random() * 50);
        } else {
            this.isMalus = false;
        }
        this.effet = effet;
    }

    // Déclarations des méthodes
    /*
     * Le joueur ramasse le bonus, ce qui a pour effet de modifier ses attributs
     * Ajout ou retrait (si malus) de vie, stock de bombe, portée de bombe, vitesse
     * La case devient vide
     */
    public void estRamassee(Joueur joueur) {
        switch (effet) {
            case Vie:
                if (this.isMalus) { joueur.perdreVie(); }
                else { joueur.gagnerVie();; }
                break;
            case Bombe:
                if (this.isMalus) { if(joueur.getStockBombe()>1) joueur.perdreStockBombe(); }
                else { joueur.gagnerStockBombe(); }
                break;
            case Portee:
                if (this.isMalus) { if(joueur.getPorteeBombe()>1) joueur.perdrePorteeBombe();; }
                else { joueur.gagnerPorteeBombe(); }
                break;
            case Vitesse:
                if (this.isMalus) { if(joueur.getVitesse()>1) joueur.perdreVitesse(); }
                else { joueur.gagnerVitesse(); }
                break;
            default:
                break;
        }
        joueur.augmenterScore(Points);
        int posX = super.positionX;
        int posY = super.positionY;
        Case caseVide = partie.getCarte()[posY][posX] = new Case(true, posX, posY, typeCaseEnum.CaseVide, false, partie);
        caseVide.setJoueur(joueur);
    }

    public bonusEnum getEffet() {
        return effet;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Bonus {");
        sb.append("\n  positionX: ").append(positionX);
        sb.append("\n  positionY: ").append(positionY);
        sb.append("\n  isMalus: ").append(isMalus);
        sb.append("\n  effet: ").append(effet);
        sb.append("\n}");
        return sb.toString();
    }
}