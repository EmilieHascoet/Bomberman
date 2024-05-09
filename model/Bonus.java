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
        switch (this.effet) {
            case "vie":
                if (this.isMalus) { joueur.vie--; }
                else { joueur.vie++; }
                break;
            case "stockBombe":
                if (this.isMalus) { if(joueur.stockBombe>1) joueur.stockBombe--; }
                else { joueur.stockBombe++; }
                break;
            case "porteeBombe":
                if (this.isMalus) { if(joueur.porteeBombe>1) joueur.porteeBombe--; }
                else { joueur.porteeBombe++; }
                break;
            case "vitesse":
                if (this.isMalus) { joueur.vitesse--; }
                else { joueur.vitesse++; }
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