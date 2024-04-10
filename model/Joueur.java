package model;

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

    // Déclarations des associations
    public Touche touche;

    /**
     * Default constructor
     */
    public Joueur(String nom, int positionX, int positionY) {
        this.nom = nom;
        this.vie = Partie.paramPartie.getNbVie();
        this.stockBombe = Partie.paramPartie.getNbBombeInit();
        this.porteeBombe = Partie.paramPartie.getPorteeBombe();
        this.vitesse = Partie.paramPartie.getVitesse();
        this.score = 0;
        this.positionX = positionX;
        this.positionY = positionY;
    }

    // Déclarations des méthodes
    // debug poser bomb sans thread
    public Bombe poseBombe_test() {
        if (stockBombe > 0) {
            Bombe newBombe = new Bombe(this.positionX, this.positionY, 2, porteeBombe, this);
            Carte.map[this.positionY][this.positionX] = newBombe;
            Case caseBombe = (Case) newBombe;
            caseBombe.setJoueur(this);
            stockBombe--;
            newBombe.explose();
            return newBombe;
        }
        return null;
    }

    /**
     * Pose une bombe à l'emplacement du joueur si il lui en reste
     */
    public Bombe poseBombe() {
        // Vérifie s'il reste des bombes en stock.
        if (stockBombe > 0) {
            // Crée une nouvelle bombe à la position actuelle du joueur.
            Bombe newBombe = new Bombe(this.positionX, this.positionY, 2, porteeBombe, this);

            // Place la bombe sur la carte à la position actuelle du joueur.
            Carte.map[this.positionY][this.positionX] = newBombe;

            // Cast la nouvelle bombe en type Case et l'associe au joueur.
            Case caseBombe = (Case) newBombe;
            caseBombe.setJoueur(this);

            // Décrémente le stock de bombes puisqu'une nouvelle bombe a été placée.
            stockBombe--;

            // Crée un nouveau thread pour gérer l'explosion de la bombe après un délai.
            new Thread(() -> {
                try {
                    // Le thread est mis en pause pour le délai défini (2000 ms = 2 secondes ici).
                    Thread.sleep(newBombe.tempsExplosion * 2000); // Modifiez ce délai en fonction des besoins du jeu.

                    // Après le délai, la méthode explose() de la bombe est appelée.
                    newBombe.explose();
                } catch (InterruptedException e) {
                    // Gestion de l'interruption du thread (important pour la gestion d'erreurs).
                    Thread.currentThread().interrupt();
                    System.out.println("Le thread d'explosion a été interrompu");
                }
            }).start(); // Démarre le thread pour l'exécution en parallèle.

            // Retourne la nouvelle bombe.
            return newBombe;
        }
        // Si aucun stock de bombe n'est disponible, retourne null.
        return null;
    }

    /**
     * Moves the player in the specified direction.
     * 
     * @param direction the direction in which the player should move ("haut",
     *                  "bas", "gauche", "droite")
     */
    public void seDeplacer(String direction) {
        // Récupère la case de départ du joueur
        Case caseDepart = (Case) Carte.map[positionY][positionX];
        Case caseArrivee;

        // Selon la direction choisie, détermine la case d'arrivée
        switch (direction) {
            case "haut":
                caseArrivee = (Case) Carte.map[positionY - 1][positionX];
                break;
            case "bas":
                caseArrivee = (Case) Carte.map[positionY + 1][positionX];
                break;
            case "gauche":
                caseArrivee = (Case) Carte.map[positionY][positionX - 1];
                break;
            case "droite":
                caseArrivee = (Case) Carte.map[positionY][positionX + 1];
                break;
            default:
                // Si la direction n'est pas reconnue, le joueur reste sur place
                caseArrivee = caseDepart;
        }

        // Vérifie si le joueur peut se déplacer vers la case d'arrivée
        if (peutSeDeplacer(caseArrivee)) {
            // Si oui, déplace le joueur vers la case d'arrivée
            caseDepart.joueur = null;
            caseArrivee.joueur = this;
            positionX = caseArrivee.positionX;
            positionY = caseArrivee.positionY;

            // Si la case d'arrivée est un bonus, applique l'effet du bonus
            if (caseArrivee.typeImage == "Bonus") {
                Bonus bonus = (Bonus) caseArrivee;
                bonus.estRamassee(this);
            }
        }
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
    public Boolean placerJoueur(int posX, int posY) {
        Case caseDepart = (Case) Carte.map[this.positionY][this.positionX];
        Case caseArrivee = (Case) Carte.map[posY][posX];
        if (caseArrivee.estDestructible) {
            BlocDestructible bloc = (BlocDestructible) caseArrivee;
            bloc.viderCase();
            caseDepart.joueur = null;
            caseArrivee.joueur = this;
            this.positionX = posX;
            this.positionY = posY;
            return true;
        } else if (peutSeDeplacer(caseArrivee)) {
            caseDepart.joueur = null;
            caseArrivee.joueur = this;
            this.positionX = posX;
            this.positionY = posY;
            return true;
        }
        return false;
    }

}