package model;
/**
 * 
 */
public class BlocDestructible extends Case{

    /**
     * Default constructor
     */
    public BlocDestructible(Boolean estTraversable, Integer positionX, Integer positionY, boolean estDestructible) {
        super(estTraversable, positionX, positionY, "BlocDestructible", estDestructible);
    }

    /**
     * 
     */
    public void Destruction() {
        // TODO implement here
    }

}