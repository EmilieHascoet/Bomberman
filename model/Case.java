package model;

/**
 * 
 */
public class Case {

    // Déclarations des attributs
    public Boolean estTraversable;
    public Integer positionX;
    public Integer positionY;
    /**
     * Définit le type de case pour savoir quelle image afficher dans la vue
     * Valeurs possibles : Bombe, BlocDestructible, BlocIndestructible, Bonus,
     * CaseVide
     */
    public String typeImage;
    public boolean estDestructible;
    /**
     * Définit les points que rapporte la case, si elle se détruit par un joueur
     */
    public int Points; 

    // Déclarations des associations
    Joueur joueur;

    /**
     * Default constructor
     */
    public Case(Boolean estTraversable, Integer positionX, Integer positionY, String typeImage,
            boolean estDestructible) {
        this.estTraversable = estTraversable;
        this.positionX = positionX;
        this.positionY = positionY;
        this.typeImage = typeImage;
        this.estDestructible = estDestructible;
        // Méthode pour calculer les points automatiquement avec de l'aléatoire
        // this.Points = Points;
    }

    // Déclarations des méthodes

    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }

    @Override
    public String toString() {
        return ("A case at Positon : " + positionX + positionY + "Type : " + typeImage + "Traversable : "
                + estTraversable + "Destructible : " + estDestructible);
    }

}