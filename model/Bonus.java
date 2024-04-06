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
    public Bonus(Integer positionX, Integer positionY, String effet) {
        super(true, positionX, positionY, "Bonus", false);
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
                else { joueur.vie = joueur.vie + 1; }
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
        Case caseVide = Carte.map[posY][posX] = new Case(true, posX, posY, "CaseVide", false);
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