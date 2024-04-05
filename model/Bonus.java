package model;

/**
 * 
 */
public class Bonus extends Case {

    // Déclarations des attributs
    public Boolean isMalus;
    public String effet;

    /**
     * Default constructor
     */
    public Bonus(Integer positionX, Integer positionY, Boolean isMalus, String effet, Partie partie) {
        super(true, positionX, positionY, "Bonus", false, partie);
        // 30% de chance que ce soit un malus
        if (Math.random() < 0.3) {
            this.isMalus = true;
        } else {
            this.isMalus = false;
        }
        this.effet = effet;
    }

    // Déclarations des méthodes
    /*
     * Le joueur ramasse le bonus
     * La case devient vide
     */
    public void estRamassee(Joueur joueur) {
        switch (this.effet) {
            case "vie":
                if (this.isMalus) { joueur.vie = joueur.vie - 1; }
                else { joueur.vie = joueur.vie - 1; }
                break;
            case "stockBombe":
                if (this.isMalus) { joueur.stockBombe = joueur.stockBombe - 1; }
                else { joueur.stockBombe = joueur.stockBombe + 1; }
                break;
            case "porteeBombe":
                if (this.isMalus) { joueur.porteeBombe = joueur.porteeBombe - 1; }
                else { joueur.porteeBombe = joueur.porteeBombe + 1; }
                break;
            case "Vitesse":
                if (this.isMalus) { joueur.vitesse = joueur.vitesse - 1; }
                else { joueur.vitesse = joueur.vitesse + 1; }
                break;
            default:
                break;
        }
        joueur.score += super.Points;
        int posX = super.positionX;
        int posY = super.positionY;
        super.partie.carte.map[posY][posX] = new Case(true, posX, posY, "CaseVide", false, super.partie);
    }

    @Override
    public String toString() {
        return ("A bonus at Positon : " + positionX + positionY + "Effet : " + effet + "Malus : " + isMalus);
    }
}