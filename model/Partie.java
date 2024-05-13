package model;

import java.util.*;

/**
 * 
 */
public class Partie {

    // Déclarations des associations
    // utiliser la méthode getJoueurs pour avoir la liste des joueurs actifs
    private static List<Joueur> joueurs;
    public static Parametres paramPartie;

    // Déclarations des attributs
    public static Case[][] carte;
    public static int nbJoueurs;

    public enum avatar {
        J1, J2, J3, J4
    }

    public enum bonusEnum {
        Bombe, Portee, Vie, Vitesse
    }

    /**
     * Default constructor
     * Paramètres pour la partie, définis par l'utilisateur dans l'écran
     */
    public Partie() {
        // Créer les paramètres par défaut
        Set<bonusEnum> setBonus = new HashSet<>(Arrays.asList(Partie.bonusEnum.values()));
        Partie.paramPartie = new Parametres(setBonus, 3, 3, 1, 2, 20, 15);

        // Créer les touches par défaut
        List<Touche> touchesDefaut = new ArrayList<>();
        touchesDefaut.add(new Touche("Fleche du haut", "Fleche du bas", "Fleche de droite", "Fleche de gauche", "Shift"));
        touchesDefaut.add(new Touche("Z", "S", "D", "Q", "Espace"));
        touchesDefaut.add(new Touche("I", "K", "L", "J", "M"));
        touchesDefaut.add(new Touche("8", "5", "6", "4", "0"));

        // Créer 4 joueurs
        Partie.joueurs = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Partie.joueurs.add(new Joueur("Joueur " + (i + 1), avatar.values()[i]));
        }

        // Associe les touches par défaut aux joueurs
        for (int i = 0; i < 4; i++) {
            Partie.joueurs.get(i).touche = (touchesDefaut.get(i));
        }

        // Nombre de joueurs par défaut
        nbJoueurs = 2;
    }

    // Déclaration des méthodes

    public static List<Joueur> getJoueurs() {
        // retourne la liste des joueurs selon nbJoueurs
        return joueurs.subList(0, nbJoueurs);
    }

    /**
     * Place les joueurs sur la carte et les cases de départ.
     */
    private static void placerJoueursDepartCarte() {
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

    /**
     * Rejouer / Garde les joueurs et génère une nouvelle carte
     */
    public static void lancerNouvellePartie() {
        System.out.println("Lancement d'une nouvelle partie");
        System.out.println("Paramètres de la partie: " + paramPartie);
        // Réinitialise les attributs des joueurs selon les paramètres de partie
        for (Joueur joueur : getJoueurs()) {
            joueur.initJoueur();
        }
        genererNouvelleCarte();
        placerJoueursDepartCarte();
    }

    private static List<List<Integer>> getPosDepart() {
        // Position de départ des joueurs sur la carte
        int largeur = paramPartie.getBoardWidth();
        int hauteur = paramPartie.getBoardHeight();

        List<List<Integer>> posDepart;posDepart = new ArrayList<>();
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
    private static void genererNouvelleCarte() {
        // Genere la carte avec la hauteur et longueur entree en parametres
        int m = paramPartie.getBoardWidth() + 2;
        int n = paramPartie.getBoardHeight() + 2;
        carte = new Case[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // La carte est entourée de blocs indestructibles
                if (i == 0 || j == 0 || i == n - 1 || j == m - 1) {
                    carte[i][j] = new Case(false, j, i, "BlocIndestructible", false);
                }
                // Les 2 coins opposés sont vides pour laisser la place aux joueurs
                else if ((i == 1 && j == 1) || (i == 1 && j == 2) || (i == 2 && j == 1) || (i == n - 2 && j == m - 2)
                        || (i == n - 2 && j == m - 3) || (i == n - 3 && j == m - 2)) {
                    carte[i][j] = new Case(true, j, i, "CaseVide", false);
                }
                // Les blocs indesctructibles sont placés sur les cases pairs
                else if (i == 0 || j == 0 || i == n - 1 || j == m - 1 || (i % 2 == 0 && j % 2 == 0)) {
                    carte[i][j] = new Case(false, j, i, "BlocIndestructible", false);
                }
                // Les blocs destructibles sont placés aléatoirement
                else if (Math.random() < 0.5) {
                    carte[i][j] = new BlocDestructible(j, i);
                } else {
                    carte[i][j] = new Case(true, j, i, "CaseVide", false);
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
    public static List<Case> jouerTouche(String KeyString) {
        List<Case> casesModifiees = new ArrayList<Case>();
        for (Joueur joueur : getJoueurs()) {
            List<Case> casesModifieesJoueur = joueur.jouer(KeyString);
            if (casesModifieesJoueur != null) casesModifiees.addAll(casesModifieesJoueur);
        }
        return casesModifiees;
    }

    /* pour test */
    public static void afficherCarte() {
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
                } else if (carte[i][j].typeImage.equals("CaseVide")) {
                    System.out.print("\u001B[34m" + "T" + "\t" + "\u001B[0m"); // Bleu pour le terrain
                } else if (carte[i][j].typeImage.equals("BlocDestructible")) {
                    System.out.print("\u001B[32m" + "D" + "\t" + "\u001B[0m"); // Vert pour les blocs destructibles
                } else if (carte[i][j].typeImage.equals("BlocIndestructible")) {
                    System.out.print("\u001B[31m" + "M" + "\t" + "\u001B[0m"); // Rouge pour les blocs indestructibles
                } else if (carte[i][j].typeImage.equals("Bombe")) {
                    System.out.print("\u001B[33m" + "B" + "\t" + "\u001B[0m"); // Jaune pour les bombes
                } else if (carte[i][j].typeImage.equals("Bonus")) {
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