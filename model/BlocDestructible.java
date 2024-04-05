package model;

/**
 * 
 */
public class BlocDestructible extends Case {

    /**
     * Default constructor
     */
    public BlocDestructible(Integer positionX, Integer positionY) {
        super(false, positionX, positionY, "BlocDestructible", true);
    }

    /**
     * 
     */
    public void Destruction() {
        // TODO implement here
    }

    @Override
    public String toString() {
        return ("A destructible block at Positon : " + positionX + positionY);
    }
}