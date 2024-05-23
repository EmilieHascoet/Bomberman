package model;

import java.io.Serializable;

/**
 * 
 */
public class Case implements Serializable {

    public enum typeCaseEnum {
        BlocDestructible, BlocIndestructible, CaseVide, Bombe, Bonus;

        public String getDescription() {
            switch (this) {
                case BlocDestructible:
                    return "Haie à fleurs : destructible";
                case BlocIndestructible:
                    return "Haie sans fleurs : indestructible";
                case Bombe:
                    return "Fleur explosive : indestructible";
                default:
                    return null;
            }
        }

        public String getPathImage() {
            switch (this) {
                case BlocDestructible:
                    return "Images/Bloc/BlocDestructible.png";
                case BlocIndestructible:
                    return "Images/Bloc/BlocIndestructible.png";
                case Bombe:
                    return "Images/Bloc/Bombe.png";
                default:
                    return null;
            }
        }
    }

    // Déclarations des attributs
    protected Boolean estTraversable;
    protected Integer positionX;
    protected Integer positionY;
    public Boolean isFire;
    protected typeCaseEnum typeCase;
    protected boolean estDestructible;
    // Définit les points que rapporte la case, si elle se détruit par un joueur
    protected int Points; 

    // Déclarations des relations
    protected Partie partie;
    protected Joueur joueur;

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

    public Joueur getJoueur() {
        return joueur;
    }

    public typeCaseEnum getTypeCase() {
        return typeCase;
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