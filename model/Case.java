package model;

import java.io.Serializable;

import model.Partie.typeCaseEnum;

/**
 * 
 */
public class Case implements Serializable {

    // Déclarations des attributs
    public Boolean estTraversable;
    public Integer positionX;
    public Integer positionY;
    public Boolean isFire;
    public typeCaseEnum typeCase;
    public boolean estDestructible;
        /**
     * Définit les points que rapporte la case, si elle se détruit par un joueur
     */
    public int Points; 

    // Déclarations des relations
    public Partie partie;
    public Joueur joueur;

    /**
     * Default constructor
     */
    public Case(Boolean estTraversable, Integer positionX, Integer positionY, typeCaseEnum typeCase,
            boolean estDestructible, Partie partie) {
        this.estTraversable = estTraversable;
        this.positionX = positionX;
        this.positionY = positionY;
        this.typeCase = typeCase;
        this.estDestructible = estDestructible;
        this.isFire = false;
        // Méthode pour calculer les points automatiquement avec de l'aléatoire
        if (estDestructible) {
            this.Points = (int) (Math.random() * 100);
        } else if (typeCase == typeCaseEnum.Bonus) {
            this.Points = (int) (Math.random() * 50);
        }
        this.partie = partie;
    }

    // Déclarations des méthodes

    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Case {");
        sb.append("\n  positionX: ").append(positionX);
        sb.append("\n  positionY: ").append(positionY);
        sb.append("\n  estTraversable: ").append(estTraversable);
        sb.append("\n  estDestructible: ").append(estDestructible);
        sb.append("\n  typeCase: ").append(typeCase);
        sb.append("\n  Points: ").append(Points);
        sb.append("\n  joueur: ").append(joueur); // Si vous avez un attribut joueur dans la classe Case
        sb.append("\n}");
        return sb.toString();
    }
}