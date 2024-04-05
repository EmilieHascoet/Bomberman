package model;

/**
 * 
 */
public class Joueur {

    /**
     * Default constructor
     */
    public Joueur(String nom, int vie, int vitesse, int stockBombe, int porteeBombe, int positionX, int positionY) {
        this.nom = nom;
        this.vie = vie;
        this.stockBombe = stockBombe;
        this.porteeBombe = porteeBombe;
        this.score = 0;
        this.positionX = positionX;
        this.positionY = positionY;
        this.vitesse = vitesse;
    }

    // Déclarations des attributs
    public String nom;
    public Integer vie;
    public Integer stockBombe;
    public Integer porteeBombe;
    public Integer score;
    public Integer positionX;
    public Integer positionY;
    public Integer vitesse;

    // Déclarations des associations
    public Partie partie;
    public Touche touche;

    // Déclarations des getters
    public String getNom() {
        return nom;
    }

    public Integer getVie() {
        return vie;
    }

    public Integer getStockBombe() {
        return stockBombe;
    }

    public Integer getPorteeBombe() {
        return porteeBombe;
    }

    public Integer getScore() {
        return score;
    }

    public Integer getPositionX() {
        return positionX;
    }

    public Integer getPositionY() {
        return positionY;
    }

    public Integer getVitesse() {
        return vitesse;
    }

    // Déclarations des setters
    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setVie(Integer vie) {
        this.vie = vie;
    }

    public void setStockBombe(Integer stockBombe) {
        this.stockBombe = stockBombe;
    }

    public void setPorteeBombe(Integer porteeBombe) {
        this.porteeBombe = porteeBombe;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public void setPositionX(Integer positionX) {
        this.positionX = positionX;
    }

    public void setPositionY(Integer positionY) {
        this.positionY = positionY;
    }

    public void setVitesse(Integer Vitesse) {
        this.vitesse = Vitesse;
    }

    // Déclarations des méthodes
    /**
     * 
     */
    public void poserBombe() {
        if (stockBombe > 0) {
            stockBombe--;
            // temps à ajouter et passer en paramêtre
            Bombe bombe = new Bombe(this.positionX, this.positionY, 2, porteeBombe);
            partie.carte.map[this.positionX][this.positionY] = bombe;
        }
    }

    /**
     * 
     */
    public void seDéplacer() {
        // TODO implement here
    }

    // on fait un override de la méthode toString pour afficher tous les
    // informations current du joueur
    @Override
    public String toString() {
        return ("Joueur : " + nom + "Vie : " + vie + "StockBombe : " + stockBombe + "PorteeBombe : " + porteeBombe
                + "Score : " + score + "Position : " + positionX + positionY + "Vitesse : " + vitesse);
    }

}