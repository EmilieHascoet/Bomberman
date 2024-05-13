package model;

import model.Partie.bonusEnum;

/**
 * 
 */
public class Bonus extends Case {

    // Déclarations des attributs
    public Boolean isMalus;
    public bonusEnum effet;

    /**
     * Default constructor
     */
    public Bonus(Integer positionX, Integer positionY, bonusEnum effet, Partie partie) {
        super(true, positionX, positionY, "Bonus", false, partie);
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
                if (this.isMalus) { joueur.vie--; }
                else { joueur.vie++; }
                break;
            case Bombe:
                if (this.isMalus) { if(joueur.stockBombe>1) joueur.stockBombe--; }
                else { joueur.stockBombe++; }
                break;
            case Portee:
                if (this.isMalus) { if(joueur.porteeBombe>1) joueur.porteeBombe--; }
                else { joueur.porteeBombe++; }
                break;
            case Vitesse:
                if (this.isMalus) { joueur.vitesse--; }
                else { joueur.vitesse++; }
                break;
            default:
                break;
        }
        joueur.score += super.Points;
        int posX = super.positionX;
        int posY = super.positionY;
        Case caseVide = partie.carte[posY][posX] = new Case(true, posX, posY, "CaseVide", false, partie);
        caseVide.setJoueur(joueur);
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