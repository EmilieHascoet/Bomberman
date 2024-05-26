package model;

import java.util.Arrays;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import model.Partie.avatar;
import model.Case.typeCaseEnum;;

/**
 * 
 */
public class Joueur implements Serializable {

    // Déclarations des attributs
    public String nom;
    private int vie;
    private int stockBombe;
    private int porteeBombe;
    private int score;
    private int positionX;
    private int positionY;
    private int vitesse;
    private long lastActionTime = 0;
    private boolean alive;
    public avatar avatar;

    // Déclarations des associations
    public Touche touche;
    private Partie partie;

    /**
     * Default constructor
     */
    public Joueur(String nom, avatar avatar, Partie partie) {
        this.nom = nom;
        this.avatar = avatar;
        this.partie = partie;
        initJoueur();
    }

    public void initJoueur() {
        this.vie = partie.paramPartie.getNbVie();
        this.stockBombe = partie.paramPartie.getNbBombeInit();
        this.porteeBombe = partie.paramPartie.getPorteeBombe();
        this.vitesse = partie.paramPartie.getVitesse();
        this.score = 0;
        this.alive = true;
    }

    // Déclarations des méthodes

    /**
     * Place le joueur sur la carte à la position spécifiée.
     */
    public void placerJoueur(int posY, int posX) {
        this.positionY = posY;
        this.positionX = posX;
    }

    /**
     * Joue une touche du joueur, 
     * rien ne se passe si cette touche n'appartient pas aux touches du joueur.
     * 
     * @param keyString La touche à jouer.
     */
    public List<Case> jouer(String keyString) {
        // Si le joueur est mort, il ne peut pas jouer
        if (!alive) {
            return null;
        }
        List<Case> casesModifiees = new ArrayList<Case>();
        String action = touche.determinerActionJoueur(keyString);
        if(action != null) {
            if(action.equals("bombe")) {
                casesModifiees.add(poseBombe());
            } else {
                // Vérifie si le joueur a déjà joué une action dans le délai de sa vitesse
                long currentTime = System.currentTimeMillis();
                if (currentTime - lastActionTime > vitesse * 30) {
                    lastActionTime = currentTime;
                    casesModifiees = seDeplacer(action);
                }
            }
        }
        return casesModifiees;
    }

    /**
     * Pose une bombe à l'emplacement du joueur si il lui en reste
     */
    private Bombe poseBombe() {
        // Vérifie s'il reste des bombes en stock et si le joueur est en vie
        if (stockBombe > 0) {
            // Crée une nouvelle bombe à la position actuelle du joueur.
            Bombe newBombe = new Bombe(this.positionX, this.positionY, 2, porteeBombe, this, partie);

            // Place la bombe sur la carte à la position actuelle du joueur.
            partie.getCarte()[this.positionY][this.positionX] = newBombe;

            // Cast la nouvelle bombe en type Case et l'associe au joueur.
            Case caseBombe = (Case) newBombe;
            caseBombe.setJoueur(this);

            // Décrémente le stock de bombes puisqu'une nouvelle bombe a été placée.
            stockBombe--;

            // Retourne la nouvelle bombe.
            return newBombe;
        }
        // Si aucun stock de bombe n'est disponible, retourne null.
        return null;
    }

    /**
     * Moves the player in the specified direction and returns the modified cases.
     *
     * @param direction the direction in which the player wants to move ("haut", "bas", "gauche", "droite")
     * @return a list of modified cases if the player moved, null otherwise
     */
    private List<Case> seDeplacer(String direction) {
        // Récupère la case de départ du joueur
        Case caseDepart = (Case) partie.getCarte()[this.positionY][this.positionX];
        Case caseArrivee;

        // Selon la direction choisie, détermine la case d'arrivée
        switch (direction) {
            case "haut":
                caseArrivee = (Case) partie.getCarte()[this.positionY - 1][this.positionX];
                break;
            case "bas":
                caseArrivee = (Case) partie.getCarte()[this.positionY + 1][this.positionX];
                break;
            case "gauche":
                caseArrivee = (Case) partie.getCarte()[this.positionY][this.positionX - 1];
                break;
            case "droite":
                caseArrivee = (Case) partie.getCarte()[this.positionY][this.positionX + 1];
                break;
            default:
                // Si la direction n'est pas reconnue, le joueur reste sur place
                caseArrivee = caseDepart;
        }

        if (peutSeDeplacer(caseArrivee)) {
            // Si oui, déplace le joueur vers la case d'arrivée
            caseDepart.joueur = null;
            caseArrivee.joueur = this;
            this.positionX = caseArrivee.positionX;
            this.positionY = caseArrivee.positionY;

            // Si la case d'arrivée est un bonus, applique l'effet du bonus
            if (caseArrivee.typeCase == typeCaseEnum.Bonus) {
                Bonus bonus = (Bonus) caseArrivee;
                bonus.estRamassee();
            }
            // Si case d'arrivée est en feu et case de départ ne l'est pas, le joueur perd une vie
            if (caseArrivee.isFire && !caseDepart.isFire) {
                this.perdreVie();
            }
            // Retourne les cases modifiées car le joueur a bougé
            return Arrays.asList(caseDepart, caseArrivee);
        }
        // Si non, retourne null car le joueur n'a pas bougé
        return null;
    }

    /**
     * Checks if the player can move to the specified destination case.
     *
     * @param caseArrivee The destination case to check.
     * @return true if the destination case is traversable and does not contain
     *         another player, false otherwise.
     */
    private boolean peutSeDeplacer(Case caseArrivee) {
        return caseArrivee.estTraversable && caseArrivee.joueur == null;
    }

    // Getters
    public boolean isAlive() {
        return alive;
    }
    public int getScore() {
        return score;
    }
    public int getVie() {
        return this.vie;
    }
    public int getStockBombe() {
        return this.stockBombe;
    }
    public int getPorteeBombe() {
        return this.porteeBombe;
    }
    public int getVitesse() {
        return this.vitesse;
    }


    // Setters
    public void augmenterScore(int valeur) {
        this.score += valeur;
    }
    // Augmente la vie du joueur de 1.
    public void gagnerVie() {
        this.vie++;
    }
    public void gagnerStockBombe() {
        this.stockBombe++;
    }
    public void gagnerPorteeBombe() {
        this.porteeBombe++;
    }
    public void gagnerVitesse() {
        this.vitesse++;
    }

    /*
     * Le joueur perd une vie. 
     * Si le joueur n'a plus de vie, il est marqué comme mort.
     */
    public void perdreVie() {
        if (this.vie > 0) {
            this.vie--;
            if (this.vie == 0) {
                this.alive = false;
                // Supprime le joueur de la carte
                partie.getCarte()[this.positionY][this.positionX].joueur = null;

            }
        }
    }
    public void perdreStockBombe() {
        this.stockBombe--;
    }
    public void perdrePorteeBombe() {
        this.porteeBombe--;
    }
    public void perdreVitesse() {
        this.vitesse--;
    }

    // on fait un override de la méthode toString pour afficher tous les
    // informations current du joueur
    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("Joueur {");
        sb.append("\n  nom: ").append(nom);
        sb.append("\n  vie: ").append(vie);
        sb.append("\n  stockBombe: ").append(stockBombe);
        sb.append("\n  porteeBombe: ").append(porteeBombe);
        sb.append("\n  score: ").append(score);
        sb.append("\n  positionX: ").append(positionX);
        sb.append("\n  positionY: ").append(positionY);
        sb.append("\n  vitesse: ").append(vitesse);
        sb.append("\n  avatar: ").append(avatar);
        sb.append("\n}");
        return sb.toString();
    }
}