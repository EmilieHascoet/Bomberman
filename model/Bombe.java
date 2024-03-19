package model;



/**
 * 
 */
public class Bombe extends Case {

    /**
     * Default constructor
     */
    public Bombe(Boolean estTraversable, Integer positionX, Integer positionY, boolean estDestructible, Integer tempsExplosion, Integer portée) {
        super(estTraversable, positionX, positionY, "Bombe", estDestructible);
        this.tempsExplosion = tempsExplosion;
        this.portée = portée;
    }

    // Déclarations des attributs
    public Integer tempsExplosion;
    public Partie partie;
    public Integer portée;

    /**
     * 
     */
    public void explose() {
        // TODO implement here
    }

}