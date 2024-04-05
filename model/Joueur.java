package model;

/**
 * 
 */
public class Joueur {

    // Déclarations des attributs
    public String nom;
    public int vie;
    public int stockBombe;
    public int porteeBombe;
    public int score;
    public int positionX;
    public int positionY;
    public int vitesse;

    // Déclarations des associations
    public Partie partie;
    public Touche touche;

    /**
     * Default constructor
     */
    public Joueur(String nom, int vie, int vitesse, int stockBombe, int porteeBombe, int positionX, int positionY, Partie partie) {
        this.nom = nom;
        this.vie = vie;
        this.stockBombe = stockBombe;
        this.porteeBombe = porteeBombe;
        this.score = 0;
        this.positionX = positionX;
        this.positionY = positionY;
        this.vitesse = vitesse;
        this.partie = partie;
    }

    // Déclarations des méthodes
    /**
     * Pose une bombe à l'emplacement du joueur si il lui en reste
     */
    public void poserBombe() {
        if (stockBombe > 0) {
            stockBombe--;
            Bombe bombe = new Bombe(this.positionX, this.positionY, 2, porteeBombe, partie);
            partie.carte.map[this.positionX][this.positionY] = bombe;
        }
    }

    /**
     * Vérifie si le joueur peut se déplacer à l'emplacement voulue
     * Puis le déplace si c'est possible
     * Change l'état des cases pour suivre ou est le joueur
     */
    public void seDéplacer(String direction) {
        Case caseDepart = partie.carte.map[positionY][positionX];
        Case caseArrivee;
        switch (direction) {
            case "haut":
                caseArrivee = partie.carte.map[positionY-1][positionX];
                break;
            case "bas":
                caseArrivee = partie.carte.map[positionX+1][positionY];
                break;
            case "gauche":
                caseArrivee = partie.carte.map[positionX][positionY-1];
                break;
            case "droite":
                caseArrivee = partie.carte.map[positionX][positionY+1];
                break;
            default:
                caseArrivee = caseDepart;
        }
        if (peutSeDeplacer(caseArrivee)) {
            caseDepart.joueur = null;
            caseArrivee.joueur = this;
            positionX = caseArrivee.positionX;
            positionY = caseArrivee.positionY;
        }
    }

    /**
     * Vérifie si le joueur peut se déplacer sur la case entree en parametre
     */
    public boolean peutSeDeplacer(Case caseArrivee) {
        return caseArrivee.estTraversable && caseArrivee.joueur == null;
    }
    

    // on fait un override de la méthode toString pour afficher tous les
    // informations current du joueur
    @Override
    public String toString() {
        return ("Joueur : " + nom + "Vie : " + vie + "StockBombe : " + stockBombe + "PorteeBombe : " + porteeBombe
                + "Score : " + score + "Position : " + positionX + positionY + "Vitesse : " + vitesse);
    }

}