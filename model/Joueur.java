package model;
/**
 * 
 */
public class Joueur {

    /**
     * Default constructor
     */
    public Joueur(String nom, int vie, int stockBombe, int score, int positionX, int positionY, int Vitesse) {
        this.nom = nom;
        this.vie = vie;
        this.stockBombe = stockBombe;
        this.score = score;
        this.positionX = positionX;
        this.positionY = positionY;
        this.Vitesse = Vitesse;
    }

    // Déclarations des attributs
    public String nom;
    public Integer vie;
    public Integer stockBombe;
    public Integer score;
    public Integer positionX;
    public Integer positionY;
    public Integer Vitesse;


    // Déclarations des associations
    public Partie partie;
    public Contrôle contrôle;
    
    // Déclarations des méthodes
    /**
     * 
     */
    public void poserBombe() {
        // TODO implement here
    }

    /**
     * 
     */
    public void seDéplacer() {
        // TODO implement here
    }

}