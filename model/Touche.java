package model;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 */
public class Touche {

    // Déclarations des attributs
    private String haut;
    private String bas;
    private String droite;
    private String gauche;
    private String bombe;

    // Déclarations des associations
    Joueur joueur;

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
        keyMap.put(KeyEvent.VK_UP, "Fleche du haut");
        keyMap.put(KeyEvent.VK_DOWN, "Fleche du bas");
        keyMap.put(KeyEvent.VK_LEFT, "Fleche de gauche");
        keyMap.put(KeyEvent.VK_RIGHT, "Fleche de droite");
        keyMap.put(KeyEvent.VK_TAB, "Tabulation");
        keyMap.put(KeyEvent.VK_ESCAPE, "Esc");
        keyMap.put(KeyEvent.VK_CAPS_LOCK, "MAJ");
        keyMap.put(KeyEvent.VK_NUM_LOCK, "Verr num");
        keyMap.put(KeyEvent.VK_SCROLL_LOCK, "Verrouillage du défilement");
        for (int i = 1; i <= 12; i++) {
            keyMap.put(KeyEvent.VK_F1 + i - 1, "F" + i);
        }
    }

    public static String getKeyText(int keyCode) {
        return keyMap.get(keyCode);
    }

    public String determinerActionJoueur(int keyCode) {
        String keyTextString = getKeyText(keyCode) != null ? getKeyText(keyCode) : KeyEvent.getKeyText(keyCode);
        if (keyTextString.equals(this.haut))
            return "haut";
        else if (keyTextString.equals(this.bas))
            return "bas";
        else if (keyTextString.equals(this.droite))
            return "droite";
        else if (keyTextString.equals(this.gauche))
            return "gauche";
        else if (keyTextString.equals(this.bombe))
            return "bombe";
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Touche {");
        sb.append("\n  haut: ").append(haut);
        sb.append("\n  bas: ").append(bas);
        sb.append("\n  droite: ").append(droite);
        sb.append("\n  gauche: ").append(gauche);
        sb.append("\n  bombe: ").append(bombe);
        sb.append("\n}");
        return sb.toString();
    }
}