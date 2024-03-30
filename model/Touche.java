package model;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

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
    private String haut;
    private String bas;
    private String droite;
    private String gauche;
    private String bombe;

    // Déclarations des associations
    Joueur joueur;

    // Déclarations des méthodes setters
    public void setJoueur(Joueur j) {
        this.joueur = j;
    }
    public void setHaut(String haut) {
        this.haut = haut;
    }
    public void setBas(String bas) {
        this.bas = bas;
    }
    public void setDroite(String droite) {
        this.droite = droite;
    }
    public void setGauche(String gauche) {
        this.gauche = gauche;
    }
    public void setBombe(String bombe) {
        this.bombe = bombe;
    }

    // Déclarations des méthodes getters
    public String getHaut() {
        return haut;
    }
    public String getBas() {
        return bas;
    }
    public String getDroite() {
        return droite;
    }
    public String getGauche() {
        return gauche;
    }
    public String getBombe() {
        return bombe;
    }

    // Créez la map pour la correspondance des touches
    static public Map<Integer, String> keyMap = new HashMap<>();

    static {
        keyMap.put(KeyEvent.VK_SHIFT, "Shift");
        keyMap.put(KeyEvent.VK_CONTROL, "Ctrl");
        keyMap.put(KeyEvent.VK_ALT, "Alt");
        keyMap.put(KeyEvent.VK_META, "Pomme");
        keyMap.put(KeyEvent.VK_SPACE, "Espace");
        keyMap.put(KeyEvent.VK_ENTER, "Entrée");
        keyMap.put(KeyEvent.VK_BACK_SPACE, "Retour arrière");
        keyMap.put(KeyEvent.VK_DELETE, "Supprimer");
        keyMap.put(KeyEvent.VK_UP, "Flèche du haut");
        keyMap.put(KeyEvent.VK_DOWN, "Flèche du bas");
        keyMap.put(KeyEvent.VK_LEFT, "Flèche de gauche");
        keyMap.put(KeyEvent.VK_RIGHT, "Flèche de droite");
        keyMap.put(KeyEvent.VK_TAB, "Tabulation");
        keyMap.put(KeyEvent.VK_ESCAPE, "Esc");
        keyMap.put(KeyEvent.VK_CAPS_LOCK, "MAJ");
        keyMap.put(KeyEvent.VK_NUM_LOCK, "Verr num");
        keyMap.put(KeyEvent.VK_SCROLL_LOCK, "Verrouillage du défilement");
        for (int i = 1; i <= 12; i++) {
            keyMap.put(KeyEvent.VK_F1 + i - 1, "F" + i);
        }
    }
}