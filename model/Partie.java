package model;
import java.io.Serializable;
import java.util.*;

import model.Case.typeCaseEnum;

/**
 * 
 */
public class Partie implements Serializable {

    // Déclarations des associations
    // utiliser la méthode getJoueurs pour avoir la liste des joueurs actifs
    private List<Joueur> joueurs;
    public Parametres paramPartie;
    public TreeMap<Integer, List<String>> leaderBoard;

    // Déclarations des attributs
    public Case[][] carte;
    public int nbJoueurs;

    public enum avatar {
        J1, J2, J3, J4;

        public String getPathImage() {
            switch (this) {
                case J1:
                    return "Images/Personnage/J1.png";
                case J2:
                    return "Images/Personnage/J2.png";
                case J3:
                    return "Images/Personnage/J3.png";
                case J4:
                    return "Images/Personnage/J4.png";
                default:
                    return null;
            }
        }
    }

    public enum bonusEnum {
        Bombe, Portee, Vie, Vitesse;

        public String getDescription() {
            switch (this) {
                case Bombe:
                    return "Ajoute ou retire une bombe au stock de bombe du joueur";
                case Portee:
                    return "Ajoute ou retire un de portée à la bombe du joueur";
                case Vie:
                    return "Ajoute ou retire une vie au joueur";
                case Vitesse:
                    return "Augmente ou diminie la vitesse du joueur";
                default:
                    return "Bonus";
            }
        }

        public String getPathImage() {
            switch (this) {
                case Bombe:
                    return "Images/Bonus/Bombe.png";
                case Portee:
                    return "Images/Bonus/Portee.png";
                case Vie:
                    return "Images/Bonus/Vie.png";
                case Vitesse:
                    return "Images/Bonus/Vitesse.png";
                default:
                    return null;
            }
        }
    }

    /**
     * Default constructor
     * Paramètres pour la partie, définis par l'utilisateur dans l'écran
     */
    public Partie() {
        // Créer les paramètres par défaut
        Set<bonusEnum> setBonus = new HashSet<>(Arrays.asList(Partie.bonusEnum.values()));
        paramPartie = new Parametres(setBonus, 3, 3, 1, 2, 21, 15);

        // Créer les touches par défaut
        List<Touche> touchesDefaut = new ArrayList<>();
        touchesDefaut
                .add(new Touche("Fleche du haut", "Fleche du bas", "Fleche de droite", "Fleche de gauche", "Shift"));
        touchesDefaut.add(new Touche("Z", "S", "D", "Q", "Espace"));
        touchesDefaut.add(new Touche("I", "K", "L", "J", "M"));
        touchesDefaut.add(new Touche("8", "5", "6", "4", "0"));

        // Créer 4 joueurs
        this.joueurs = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            this.joueurs.add(new Joueur("Joueur " + (i + 1), avatar.values()[i], this));
        }

        // Associe les touches par défaut aux joueurs
        for (int i = 0; i < 4; i++) {
            this.joueurs.get(i).touche = (touchesDefaut.get(i));
        }

        // Nombre de joueurs par défaut
        nbJoueurs = 2; 

        leaderBoard = new TreeMap<>(Collections.reverseOrder());
    }

    // Déclaration des méthodes

    public List<Joueur> getJoueurs() {
        // retourne la liste des joueurs selon nbJoueurs
        return joueurs.subList(0, nbJoueurs);
    }

    /**
     * Place les joueurs sur la carte et les cases de départ.
     */
    private void placerJoueursDepartCarte() {
        List<List<Integer>> posDepart = getPosDepart();

        // Place les joueurs sur la carte
        for (int i = 0; i < nbJoueurs; i++) {
            joueurs.get(i).placerJoueur(posDepart.get(i).get(0), posDepart.get(i).get(1));
        }
        // Place les joueurs sur les cases
        for (Joueur joueur : getJoueurs()) {
            carte[joueur.positionY][joueur.positionX].setJoueur(joueur);
        }
    }

    public void initJoueur() {
        for (Joueur joueur : getJoueurs()) {
            joueur.initJoueur();
        }
    }

    /**
     * Rejouer / Garde les joueurs et génère une nouvelle carte
     */
    public void lancerNouvellePartie() {
        System.out.println("Lancement d'une nouvelle partie");
        System.out.println("Paramètres de la partie: " + paramPartie);
        // Réinitialise les attributs des joueurs selon les paramètres de partie
        for (Joueur joueur : getJoueurs()) {
            joueur.initJoueur();
        }
        genererNouvelleCarte();
        placerJoueursDepartCarte();
    }

    private List<List<Integer>> getPosDepart() {
        // Position de départ des joueurs sur la carte
        int largeur = paramPartie.getBoardWidth();
        int hauteur = paramPartie.getBoardHeight();

        List<List<Integer>> posDepart;
        posDepart = new ArrayList<>();
        // Position Y, Position X
        posDepart.add(Arrays.asList(hauteur, largeur));
        posDepart.add(Arrays.asList(1, 1));
        posDepart.add(Arrays.asList(hauteur, 1));
        posDepart.add(Arrays.asList(1, largeur));

        return posDepart;
    }

    /**
     * Génère une nouvelle carte avec la taille
     */
    private void genererNouvelleCarte() {
        // Genere la carte avec la hauteur et longueur entree en parametres
        int m = paramPartie.getBoardWidth() + 2;
        int n = paramPartie.getBoardHeight() + 2;
        carte = new Case[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // La carte est entourée de blocs indestructibles
                if (i == 0 || j == 0 || i == n - 1 || j == m - 1) {
                    carte[i][j] = new Case(false, j, i, typeCaseEnum.BlocIndestructible, false, this);
                }
                // Les 4 coins opposés sont vides pour laisser la place aux joueurs
                else if ((i == 1 && (j == 1 || j == 2 || j == m - 3 || j == m - 2))
                        || (i == n - 2 && (j == 1 || j == 2 || j == m - 3 || j == m - 2))
                        || (i == 2 && (j == 1 || j == m - 2)) || (i == n - 3 && (j == 1 || j == m - 2))) {
                    carte[i][j] = new Case(true, j, i, typeCaseEnum.CaseVide, false, this);
                }
                // Les blocs indesctructibles sont placés sur les cases pairs
                else if (i == 0 || j == 0 || i == n - 1 || j == m - 1 || (i % 2 == 0 && j % 2 == 0)) {
                    carte[i][j] = new Case(false, j, i, typeCaseEnum.BlocIndestructible, false, this);
                }
                // Les blocs destructibles sont placés aléatoirement
                else if (Math.random() < 0.5) {
                    carte[i][j] = new BlocDestructible(j, i, this);
                } else {
                    carte[i][j] = new Case(true, j, i, typeCaseEnum.CaseVide, false, this);
                }
            }
        }
    }

    /**
     * Joue la touche spécifiée par le joueur.
     *
     * @param KeyString La touche à jouer.
     * @return La liste des cases modifiées par le joueur.
     */
    public List<Case> jouerTouche(String KeyString) {
        List<Case> casesModifiees = new ArrayList<Case>();
        for (Joueur joueur : getJoueurs()) {
            List<Case> casesModifieesJoueur = joueur.jouer(KeyString);
            if (casesModifieesJoueur != null)
                casesModifiees.addAll(casesModifieesJoueur);
        }
        return casesModifiees;
    }

    /**
     * Vérifie si la partie est terminée.
     *
     * @return true si la partie est terminée, false sinon.
     */
    public boolean estTerminee() {
        // La partie est terminée si il reste un seul joueur en vie
        int nbJoueursEnVie = 0;
        for (Joueur joueur : getJoueurs()) {
            if (joueur.isAlive) nbJoueursEnVie++;
        }
        
        // Sauvegarde les scores si la partie est terminée
        boolean partieTerminee = nbJoueursEnVie <= 1;
        if(partieTerminee) {
            sauvegarderScores();
        }
        return partieTerminee;
    }

    /**
     * Sauvegarde les scores des joueurs dans un fichier.
     */
    public void sauvegarderScores() {
        System.out.println("Partie terminée");
        System.out.println("Scores: ");
        for (Joueur joueur : getJoueurs()) {
            if(leaderBoard.containsKey(joueur.score)) {
                leaderBoard.get(joueur.score).add(joueur.nom);
            } else {
                leaderBoard.put(joueur.score, new ArrayList<>(Arrays.asList(joueur.nom)));
            }
        }
        Stream.sauvegarderScores(leaderBoard);
    }

    public Joueur getGagnant() {
        List<Integer> alive = new ArrayList<>();
        int maxScore = -1;
        int indexMaxScore = -1;

        for (int i = 0; i < getJoueurs().size(); i++) {
            Joueur joueur = getJoueurs().get(i);
            if (joueur.isAlive) {
                alive.add(i);
            }
            if (joueur.score > maxScore) {
                maxScore = joueur.score;
                indexMaxScore = i;
            }
        }

        if (alive.size() == 0) {
            // Aucun joueur n'est vivant, retourner celui avec le score maximum
            return getJoueurs().get(indexMaxScore);
        } else if (alive.size() == 1) {
            // Un seul joueur est vivant, il est le gagnant
            return getJoueurs().get(alive.get(0));
        } else {
            // Plusieurs joueurs sont en vie, trouver le score maximum parmi eux
            Joueur gagnant = getJoueurs().get(alive.get(0));
            for (int index : alive) {
                Joueur current = getJoueurs().get(index);
                if (current.score > gagnant.score) {
                    gagnant = current;
                }
            }
            return gagnant;
        }
    }

    /* pour test */
    public void afficherCarte() {
        System.out.print("   ");
        for (int i = 0; i < carte.length; i++)
            System.out.print(i + "\t");
        System.out.println();
        for (int i = 0; i < carte.length; i++) {
            if (i < 10 && i >= 0)
                System.out.print(i + "  ");
            else
                System.out.print(i + " ");

            for (int j = 0; j < carte[i].length; j++) {
                if (carte[i][j].joueur != null) {
                    System.out.print("\u001B[35m" + "J" + "\t" + "\u001B[0m"); // Violet pour le joueur
                } else if (carte[i][j].typeCase == typeCaseEnum.CaseVide) {
                    System.out.print("\u001B[34m" + "T" + "\t" + "\u001B[0m"); // Bleu pour le terrain
                } else if (carte[i][j].typeCase == typeCaseEnum.BlocDestructible) {
                    System.out.print("\u001B[32m" + "D" + "\t" + "\u001B[0m"); // Vert pour les blocs destructibles
                } else if (carte[i][j].typeCase == typeCaseEnum.BlocIndestructible) {
                    System.out.print("\u001B[31m" + "M" + "\t" + "\u001B[0m"); // Rouge pour les blocs indestructibles
                } else if (carte[i][j].typeCase == typeCaseEnum.Bombe) {
                    System.out.print("\u001B[33m" + "B" + "\t" + "\u001B[0m"); // Jaune pour les bombes
                } else if (carte[i][j].typeCase == typeCaseEnum.Bonus) {
                    System.out.print("\u001B[36m" + "B" + "\t" + "\u001B[0m"); // Cyan pour les bonus
                }
            }
            System.out.println();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Partie {");
        sb.append("\n  joueurs: ").append(joueurs);
        sb.append("\n  paramPartie: ").append(paramPartie);
        sb.append("\n}");
        return sb.toString();
    }
}