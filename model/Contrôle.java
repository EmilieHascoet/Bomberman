
import java.util.*;

/**
 * 
 */
public class Contrôle {

    /**
     * Default constructor
     */
    public Contrôle(String haut, String bas, String droite, String gauche) {
        this.haut = haut;
        this.bas = bas;
        this.droite = droite;
        this.gauche = gauche;
    }

    // Déclarations des attributs
    public String haut;
    public String bas;
    public String droite;
    public String gauche;

    // Déclarations des associations
    Joueur joueur;
}