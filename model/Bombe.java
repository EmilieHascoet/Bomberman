package model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 */
public class Bombe extends Case {

    // Déclarations des attributs
    public Integer tempsExplosion;
    public Integer portee;
    public Joueur joueurPoseBombe;

    /**
     * Default constructor
     */
    public Bombe(Integer positionX, Integer positionY, Integer tempsExplosion, Integer portee, Joueur joueur) {
        super(false, positionX, positionY, "Bombe", false);
        this.tempsExplosion = tempsExplosion;
        this.portee = portee;
        this.joueurPoseBombe = joueur;

        // Lancer le timer pour l'explosion
        lancerTimer();
    }

    // Méthode pour lancer le timer de l'explosion
    private void lancerTimer() {
        Thread timerThread = new Thread(() -> {
            try {
                Thread.sleep(this.tempsExplosion * 1000);
                this.explose();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        timerThread.start();
    }


    /**
     * Triggers the explosion of the bomb and returns a list of the traversed cases.
     * The explosion can occur in five directions: north, east, south, west, and the bomb itself.
     * The method checks for players and destructible blocks in the traversed cases and applies the necessary actions.
     * 
     * @return A list of the cases traversed by the explosion.
     */
    public List<Case> explose() {
        List<Case> casesTraversees = new ArrayList<>();

        for (int direction = 0; direction < 5; direction++) {
            boolean cont = true;
            int p = 1;

            do {
                int positionYtemp = this.positionY;
                int positionXtemp = this.positionX;
                switch (direction) {
                    case 0: // nord => y-1
                        positionYtemp -= p;
                        break;
                    case 1: // est ==> x+1
                        positionXtemp += p;
                        break;
                    case 2: // sud => y+1
                        positionYtemp += p;
                        break;
                    case 3: // ouest => x-1
                        positionXtemp -= p;
                        break;
                    case 4:
                        cont = false; // bombe elle même une fois
                        break;
                    default:
                        System.err.println("il y a un grave probléme (fonction explose, on est dans default)");
                        System.exit(1);
                }

                Case caseCourante = Carte.map[positionYtemp][positionXtemp];
                casesTraversees.add(caseCourante);

                if (caseCourante.joueur != null) {
                    caseCourante.joueur.vie--;
                } else if (!caseCourante.estTraversable) {
                    if (caseCourante instanceof BlocDestructible)
                        ((BlocDestructible) caseCourante).destruction(this.joueurPoseBombe);
                    cont = false;
                }
                p++;
            } while (cont && p <= portee);
        }

        // Supprime la bombe de la carte
        Joueur joueur = super.joueur;
        Case thisCase = Carte.map[this.positionY][this.positionX] = new Case(true, this.positionX, this.positionY, "CaseVide", false);
        thisCase.joueur = joueur;

        return casesTraversees;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("BOMB {");
        sb.append("\n  positionX: ").append(positionX);
        sb.append("\n  positionY: ").append(positionY);
        sb.append("\n  TempsExplotion: ").append(tempsExplosion);
        sb.append("\n  portée de bomb: ").append(portee);
        sb.append("\n}");
        return sb.toString();
    }

}