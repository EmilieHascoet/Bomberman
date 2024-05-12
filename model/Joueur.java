package model;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

import model.Partie.avatar;;

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
    private long lastActionTime = 0;
    public avatar avatar;

    // Déclarations des associations
    public Touche touche;

    /**
     * Default constructor
     */
    public Joueur(String nom, avatar avatar) {
        this.nom = nom;
        initJoueur();
        this.avatar = avatar;
    }

    public void initJoueur() {
        this.vie = Partie.paramPartie.getNbVie();
        this.stockBombe = Partie.paramPartie.getNbBombeInit();
        this.porteeBombe = Partie.paramPartie.getPorteeBombe();
        this.vitesse = Partie.paramPartie.getVitesse();
        this.score = 0;
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
        List<Case> casesModifiees = new ArrayList<Case>();
        String action = touche.determinerActionJoueur(keyString);
        if(action != null) {
            if(action.equals("bombe")) {
                casesModifiees.add(poseBombe());
            } else {
                // Vérifie si le joueur a déjà joué une action dans le délai de sa vitesse
                long currentTime = System.currentTimeMillis();
                if (currentTime - lastActionTime > vitesse * 100) {
                    lastActionTime = currentTime;
                    casesModifiees = seDeplacer(action);
                } else System.out.println("Trop rapide");
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
            Partie.carte[this.positionY][this.positionX] = newBombe;

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
        Case caseDepart = (Case) Partie.carte[this.positionY][this.positionX];
        Case caseArrivee;

        // Selon la direction choisie, détermine la case d'arrivée
        switch (direction) {
            case "haut":
                caseArrivee = (Case) Partie.carte[this.positionY - 1][this.positionX];
                break;
            case "bas":
                caseArrivee = (Case) Partie.carte[this.positionY + 1][this.positionX];
                break;
            case "gauche":
                caseArrivee = (Case) Partie.carte[this.positionY][this.positionX - 1];
                break;
            case "droite":
                caseArrivee = (Case) Partie.carte[this.positionY][this.positionX + 1];
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

    public void setAvatar(avatar avatar) {
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