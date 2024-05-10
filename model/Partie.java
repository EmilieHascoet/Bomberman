package model;


import view.PartiePanel;
import java.util.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Timer;
import javax.swing.text.View;



/**
 * 
 */
public class Partie {

    // Déclarations des attributs
    private int temps;
    private Timer time;
    private ActionListener timerAction;

    // Déclarations des associations
    public List<Joueur> joueurs;
    public static Parametres paramPartie;

    private PartiePanel observer;
    /**
     * Default constructor
     * Paramètres pour la partie, définis par l'utilisateur dans l'écran
     */
    public Partie(Parametres parametres, List<Touche> listTouche, List<String> nomJoueur) {
        // Créer 2 joueurs avec les paramètres + génère la carte (créer une carte)
        Partie.paramPartie = parametres;
        this.joueurs = new ArrayList<>();

        Joueur j1 = new Joueur(nomJoueur.get(0), 1, 1, "../Personnages/perso1.png");
        j1.touche = listTouche.get(0);
        joueurs.add(j1);
        
        Joueur j2 = new Joueur(nomJoueur.get(1), parametres.getBoardWidth(), parametres.getBoardHeight(), "../Personnages/perso2.png");
        j2.touche = listTouche.get(1);
        joueurs.add(j2);

        timerAction = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                decrementerTemps();
            }
        };
        time = new Timer(1000, timerAction);

        this.temps = 999;
        
        genererNouvelleCarte();
    }


    // Déclaration des méthodes

    public List<Case> jouerTouche(int KeyCode) {
        List<Case> casesModifiees = new ArrayList<Case>();
        for (Joueur joueur : joueurs) {
            List<Case> casesModifieesJoueur = joueur.jouer(KeyCode);
            if (casesModifieesJoueur != null) casesModifiees.addAll(casesModifieesJoueur);
        }
        return casesModifiees;
    }
    /**
     * Rejouer / Garde les scores des 2 joueurs, reset le temps et génère une nouvelle carte
     */
    public void rejouer() {
        joueurs.get(0).reinitialiserJoueur(1, 1);
        joueurs.get(1).reinitialiserJoueur(Partie.paramPartie.getBoardWidth(), Partie.paramPartie.getBoardHeight());
        this.temps = 999;
        genererNouvelleCarte();
    }

    /**
     * Génère une nouvelle carte avec la taille
     */
    public void genererNouvelleCarte() {
        Carte.genererNouvelleCarteCarte(Partie.paramPartie.getBoardWidth(), Partie.paramPartie.getBoardHeight(), this);
    }

    public int getTemps() {
        return this.temps;
    }

    public void demarrerTemps() {
        this.time.start();
    }

    public void arreterTemps() {
        this.time.stop();
    }


    public void incrementerTemps() {
        this.time.setDelay(this.time.getDelay() + 1000);
    }

    public void decrementerTemps() {
        if (this.temps > 0) {
            this.temps--;
            notifyObservers();
        } else {
            arreterTemps();
        }
    }

    public void addObserver(PartiePanel observer) {
        this.observer = observer;
    }

    public void notifyObservers() {
        observer.updateAll(this.temps);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Partie {");
        sb.append("\n  temps: ").append(temps);
        sb.append("\n  joueurs: ").append(joueurs);
        sb.append("\n  paramPartie: ").append(paramPartie);
        sb.append("\n}");
        return sb.toString();
    }
}