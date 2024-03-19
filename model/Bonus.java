package model;
/**
 * 
 */
public class Bonus extends Case {

    /**
     * Default constructor
     */
    public Bonus(Boolean estTraversable, Integer positionX, Integer positionY, boolean estDestructible, Boolean isMalus, String effet) {
        super(estTraversable, positionX, positionY, "Bonus", estDestructible);
        this.isMalus = isMalus;
        this.effet = effet;
    }

    // Déclarations des attributs
    public Boolean isMalus;
    public String effet;

    // Déclarations des méthodes
    public void estRamassee() {
        // TODO implement here
    }

}