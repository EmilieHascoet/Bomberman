package model;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 */
public class Joueur {

    // Déclarations des attributs
    public String nom;
    public int vie;
    public int stockBombe;
    public int porteeBombe;
    public int score;
    public int positionX;
    public int positionY;
    public int vitesse;
    public String avatar;

    // Déclarations des associations
    public Touche touche;

    /**
     * Default constructor
     */
    public Joueur(String nom, int positionX, int positionY, String avatar) {
        this.nom = nom;
        this.vie = Partie.paramPartie.getNbVie();
        this.stockBombe = Partie.paramPartie.getNbBombeInit();
        this.porteeBombe = Partie.paramPartie.getPorteeBombe();
        this.vitesse = Partie.paramPartie.getVitesse();
        this.score = 0;
        this.positionX = positionX;
        this.positionY = positionY;
        this.avatar = avatar;
    }

    // Déclarations des méthodes

    /**
     * Performs an action based on the given KeyCode and returns a list of modified cases.
     * 
     * @param KeyCode the key code representing the action to be performed
     * @return a list of modified cases
     */
    public List<Case> jouer(int KeyCode) {
        List<Case> casesModifiees = new ArrayList<Case>();
        String action = touche.determinerActionJoueur(KeyCode);
        if(action != null) {
            if(action == "bombe") {
                casesModifiees.add(poseBombe());
            } else {
                casesModifiees = seDeplacer(action);
            }
        }
        return casesModifiees;
    }

    /**
     * Pose une bombe à l'emplacement du joueur si il lui en reste
     */
    public Bombe poseBombe() {
        // Vérifie s'il reste des bombes en stock.
        if (stockBombe > 0) {
            // Crée une nouvelle bombe à la position actuelle du joueur.
            Bombe newBombe = new Bombe(this.positionX, this.positionY, 3, porteeBombe, this);

            // Place la bombe sur la carte à la position actuelle du joueur.
            Carte.map[this.positionY][this.positionX] = newBombe;

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
    public List<Case> seDeplacer(String direction) {
        // Récupère la case de départ du joueur
        Case caseDepart = (Case) Carte.map[this.positionY][this.positionX];
        Case caseArrivee;

        // Selon la direction choisie, détermine la case d'arrivée
        switch (direction) {
            case "haut":
                caseArrivee = (Case) Carte.map[this.positionY - 1][this.positionX];
                break;
            case "bas":
                caseArrivee = (Case) Carte.map[this.positionY + 1][this.positionX];
                break;
            case "gauche":
                caseArrivee = (Case) Carte.map[this.positionY][this.positionX - 1];
                break;
            case "droite":
                caseArrivee = (Case) Carte.map[this.positionY][this.positionX + 1];
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
            if (caseArrivee.typeImage == "Bonus") {
                Bonus bonus = (Bonus) caseArrivee;
                bonus.estRamassee(this);
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
    public boolean peutSeDeplacer(Case caseArrivee) {
        return caseArrivee.estTraversable && caseArrivee.joueur == null;
    }

    public void reinitialiserJoueur(int posX, int posY) {
        this.vie = Partie.paramPartie.getNbVie();
        this.stockBombe = Partie.paramPartie.getNbBombeInit();
        this.porteeBombe = Partie.paramPartie.getPorteeBombe();
        this.vitesse = Partie.paramPartie.getVitesse();
        this.score = 0;
        this.positionX = posX;
        this.positionY = posY;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
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

    /**
     * Place le joueur sur la carte à la position spécifiée.
     * 
     * @param posX La position en abscisse de la case sur laquelle placer le joueur.
     * @param posY La position en ordonnée de la case sur laquelle placer le joueur.
     * @return true si le joueur a été placé avec succès, false sinon.
     *         les cas false sont :
     *         - si la case d'arrivée est un bloc indestructible
     *         - si il y a déjà un joueur sur la case d'arrivée
     */
    public void placerJoueur(int posX, int posY) {
        Case caseDepart = (Case) Carte.map[this.positionY][this.positionX];
        Case caseArrivee = (Case) Carte.map[posY][posX];
        if (!peutSeDeplacer(caseArrivee)) {
            BlocDestructible bloc = (BlocDestructible) caseArrivee;
            bloc.viderCase();
        }
        caseDepart.joueur = null;
        caseArrivee.joueur = this;
        this.positionX = posX;
        this.positionY = posY;
    }

    /**
     * Augmente le score du joueur de la valeur spécifiée.
     * 
     * @param valeur La valeur à ajouter au score du joueur.
     */
    public void augmenterScore(int valeur) {
        this.score += valeur;
    }

    /**
     * Diminue la vie du joueur de 1.
     */

    public void perdreVie() {
        if (this.vie > 0) {
            this.vie--;
        }
    }



}