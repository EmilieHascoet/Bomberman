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
    public Integer tempsPropagation = 50;

    // Déclarations des associations
    public Joueur joueurPoseBombe;

    /**
     * Default constructor
     */
    public Bombe(Integer positionX, Integer positionY, Integer tempsExplosion, Integer portee, Joueur joueur, Partie partie) {
        super(false, positionX, positionY, typeCaseEnum.Bombe, false, partie);
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
                this.explosion();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        timerThread.start();
    }


    /**
     * Déclenche l'explosion de la bombe.
     * Cette méthode propage l'explosion aux cases adjacentes selon la portee de la bombe
     * et détruit tous les blocs qui se trouvent sur son chemin.
     */
    public void explosion() {
        List<Case> caseModifiees = new ArrayList<>();
        propagateExplosion(caseModifiees);
        destroyBlocks(caseModifiees);
    }
    
    /**
     * Propage l'explosion de la position de la bombe aux cases adjacentes en forme de croix.
     * L'explosion se propage dans quatre directions : Nord, Sud, Est et Ouest.
     * Chaque explosion propagée met à jour les cellules affectées (les met en feu).
     * La propagation s'arrête dans un certaine direction si la case n'est pas traversable.
     * 
     * @param caseModifiees une liste pour stocker les cases modifiées pendant la propagation de l'explosion
     */
    private void propagateExplosion(List<Case> caseModifiees) {
        try {
            // Propagate explosion from the bomb's position
            Case thisCase = partie.carte[this.positionY][this.positionX] = new Case(true, this.positionX, this.positionY, typeCaseEnum.CaseVide, false, partie);
            thisCase.joueur = this.joueur;
            if (thisCase.joueur != null) thisCase.joueur.perdreVie();
            System.out.println(joueurPoseBombe.isAlive);
            thisCase.isFire = true;
            caseModifiees.add(thisCase);
            this.joueurPoseBombe.stockBombe++;
            Thread.sleep(tempsPropagation);
    
            // Calculate the cross shape
            boolean[] stopDirections = new boolean[4]; // North, South, East, West
    
            for (int i = 1; i <= portee; i++) {
                // Propagate explosion in each direction
                if (!stopDirections[0]) stopDirections[0] = propagateInDirection(this.positionX, this.positionY - i, caseModifiees); // North
                if (!stopDirections[1]) stopDirections[1] = propagateInDirection(this.positionX, this.positionY + i, caseModifiees); // South
                if (!stopDirections[2]) stopDirections[2] = propagateInDirection(this.positionX + i, this.positionY, caseModifiees); // East
                if (!stopDirections[3]) stopDirections[3] = propagateInDirection(this.positionX - i, this.positionY, caseModifiees); // West

                if (stopDirections[0] && stopDirections[1] && stopDirections[2] && stopDirections[3]) break; // Stop if all directions are blocked
    
                // Delay next expansion
                Thread.sleep(tempsPropagation);
            }
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Propage l'explosion de la bombe dans une direction spécifique à partir des coordonnées données.
     * 
     * @param startX La coordonnée X de départ.
     * @param startY La coordonnée Y de départ.
     * @param caseModifiees Une liste pour stocker les cases modifiés pendant la propagation.
     * @return True si la propagation doit s'arrêter dans cette direction, false sinon.
     */
    private boolean propagateInDirection(int startX, int startY, List<Case> caseModifiees) {    
        Case currentCase = partie.carte[startY][startX];
    
        if (currentCase.typeCase.equals("BlocDestructible") || currentCase.estTraversable) {
            caseModifiees.add(currentCase);
            currentCase.isFire = true;
        }
        if (currentCase.joueur != null) currentCase.joueur.perdreVie();
        if (!currentCase.estTraversable) {
            return true;
        }
        return false;
    }
    
    /**
     * Détruit les blocs dans la liste donnée des cases modifiés.
     * Si une case est une instance de BlocDestructible, la méthode de destruction est appelée.
     * Sinon, il met l'indicateur isFire de la case à false.
     *
     * @param caseModifiees la liste des cases modifiés durant la propagation du feu
     */
    private void destroyBlocks(List<Case> caseModifiees) {
        for (Case c : caseModifiees) {
            if (c instanceof BlocDestructible) ((BlocDestructible) c).destruction(this.joueurPoseBombe);
            else c.isFire = false;
        }
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