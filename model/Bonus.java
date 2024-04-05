package model;

/**
 * 
 */
public class Bonus extends Case {

    /**
     * Default constructor
     */
    public Bonus(Integer positionX, Integer positionY, Boolean isMalus, String effet) {
        super(true, positionX, positionY, "Bonus", false);
        // 30% de chance que ce soit un malus
        if (Math.random() < 0.3) {
            this.isMalus = true;
        } else {
            this.isMalus = false;
        }
        this.effet = effet;
    }

    // Déclarations des attributs
    public Boolean isMalus;
    public String effet;

    // Déclarations des méthodes
    /*
     * Le joueur ramasse le bonus
     */
    public void estRamassee(Joueur joueur) {
        switch (this.effet) {
            case "vie":
                if (this.isMalus) {
                    joueur.setVie(joueur.getVie() - 1);
                } else {
                    joueur.setVie(joueur.getVie() + 1);
                }
                break;
            case "stockBombe":
                if (this.isMalus) {
                    joueur.setStockBombe(joueur.getStockBombe() - 1);
                } else {
                    joueur.setStockBombe(joueur.getStockBombe() + 1);
                }
                break;
            case "porteeBombe":
                if (this.isMalus) {
                    joueur.setPorteeBombe(joueur.getPorteeBombe() - 1);
                } else {
                    joueur.setPorteeBombe(joueur.getPorteeBombe() + 1);
                }
                break;
            case "Vitesse":
                if (this.isMalus) {
                    joueur.setVitesse(joueur.getVitesse() - 1);
                } else {
                    joueur.setVitesse(joueur.getVitesse() + 1);
                }
                break;
            default:
                break;
        }
        joueur.setScore(joueur.getScore() + super.Points);
    }

    @Override
    public String toString() {
        return ("A bonus at Positon : " + positionX + positionY + "Effet : " + effet + "Malus : " + isMalus);
    }
}