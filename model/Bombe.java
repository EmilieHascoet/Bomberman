package model;

/**
 * 
 */
public class Bombe extends Case {

    /**
     * Default constructor
     */
    public Bombe(Integer positionX, Integer positionY, Integer tempsExplosion, Integer portée) {
        super(false, positionX, positionY, "Bombe", false);
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

    @Override
    public String toString() {
        return ("A bomb at Positon : " + positionX + positionY + "TempsExplosion : " + tempsExplosion + "avec portée :"
                + portée);
    }

}