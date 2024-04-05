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
    public Touche touche;

    /**
     * Default constructor
     */
    public Joueur(String nom, int positionX, int positionY) {
        this.nom = nom;
        this.vie = Partie.paramPartie.getNbVie();
        this.stockBombe = Partie.paramPartie.getNbBombeInit();
        this.porteeBombe = Partie.paramPartie.getPorteeBombe();
        this.vitesse = Partie.paramPartie.getVitesse();
        this.score = 0;
        this.positionX = positionX;
        this.positionY = positionY;
    }

    // Déclarations des méthodes
    /**
     * Pose une bombe à l'emplacement du joueur si il lui en reste
     */
    public void poserBombe() {
        if (stockBombe > 0) {
            Bombe newBombe = new Bombe(this.positionX, this.positionY, 2, porteeBombe);
            Carte.map[this.positionY][this.positionX] = newBombe;
            Case caseBombe = (Case) newBombe;
            caseBombe.setJoueur(this);
            stockBombe--;
        }
    }

    
    /**
     * Moves the player in the specified direction.
     * 
     * @param direction the direction in which the player should move ("haut", "bas", "gauche", "droite")
     */
    public void seDeplacer(String direction) {
        // Récupère la case de départ du joueur
        Case caseDepart = (Case) Carte.map[positionY][positionX];
        Case caseArrivee;
    
        // Selon la direction choisie, détermine la case d'arrivée
        switch (direction) {
            case "haut":
                caseArrivee = (Case) Carte.map[positionY-1][positionX];
                break;
            case "bas":
                caseArrivee = (Case) Carte.map[positionY+1][positionX];
                break;
            case "gauche":
                caseArrivee = (Case) Carte.map[positionY][positionX-1];
                break;
            case "droite":
                caseArrivee = (Case) Carte.map[positionY][positionX+1];
                break;
            default:
                // Si la direction n'est pas reconnue, le joueur reste sur place
                caseArrivee = caseDepart;
        }
    
        // Vérifie si le joueur peut se déplacer vers la case d'arrivée
        if (peutSeDeplacer(caseArrivee)) {
            // Si oui, déplace le joueur vers la case d'arrivée
            caseDepart.joueur = null;
            caseArrivee.joueur = this;
            positionX = caseArrivee.positionX;
            positionY = caseArrivee.positionY;
    
            // Si la case d'arrivée est un bonus, applique l'effet du bonus
            if (caseArrivee.typeImage == "Bonus") {
                Bonus bonus = (Bonus) caseArrivee;
                bonus.estRamassee(this);
            }
        }
    }


    /**
     * Checks if the player can move to the specified destination case.
     *
     * @param caseArrivee The destination case to check.
     * @return true if the destination case is traversable and does not contain another player, false otherwise.
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