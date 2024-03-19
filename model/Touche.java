package model;
/**
 * 
 */
public class Touche {

    /**
     * Default constructor
     */
    public Touche(String haut, String bas, String droite, String gauche, String bombe) {
        this.haut = haut;
        this.bas = bas;
        this.droite = droite;
        this.gauche = gauche;
        this.bombe = bombe;
    }

    // Déclarations des attributs
    public String haut;
    public String bas;
    public String droite;
    public String gauche;
    public String bombe;

    // Déclarations des associations
    Joueur joueur;
}