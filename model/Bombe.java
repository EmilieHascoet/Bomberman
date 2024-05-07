package model;

import java.util.ArrayList;
import java.util.List;

import view.CasePanel;
import view.PartiePanel;

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
                this.explosion();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        timerThread.start();
    }


    public void explosion() {
        List<Case> caseModifiees = new ArrayList<>();
        Thread explosionThread = new Thread(() -> {
            try {
                // Explosion at the bomb's position
                Case thisCase = Carte.map[this.positionY][this.positionX] = new Case(true, this.positionX, this.positionY, "CaseVide", false);
                thisCase.joueur = this.joueur;
                caseModifiees.add(thisCase);
                
                System.out.println("Explosion de la bombe en [" + this.positionX + ", " + this.positionY + "]");
                joueurPoseBombe.stockBombe++;
                Carte.afficherCarte();

                // Calculate the cross shape
                for (int i = 1; i <= portee; i++) {

                    List<Case> cases = new ArrayList<>();
                    // North (y - i)
                    if (this.positionY - i >= 1) {
                        cases.add(Carte.map[this.positionY - i][this.positionX]);
                    }
                    // South (y + i)
                    if (this.positionY + i < Carte.map.length - 1) {
                        cases.add(Carte.map[this.positionY + i][this.positionX]);
                    }
                    // East (x + i)
                    if (this.positionX + i < Carte.map[0].length - 1) {
                        cases.add(Carte.map[this.positionY][this.positionX + i]);
                    }
                    // West (x - i)
                    if (this.positionX - i >= 1) {
                        cases.add(Carte.map[this.positionY][this.positionX - i]);
                    }

                    for (Case caseCourante : cases) {
                        /*CasePanel caseAModifier = PartiePanel.casesPlateauPanel[caseCourante.positionY][caseCourante.positionX];
                        caseAModifier.setCaseModel(caseCourante);
                        caseAModifier.loadImage();
                        caseAModifier.repaint();
                        caseAModifier.revalidate();*/
                        if (caseCourante.joueur != null) {
                            caseCourante.joueur.vie--;
                        } else if (!caseCourante.estTraversable) {
                            if (caseCourante instanceof BlocDestructible)
                            caseModifiees.add(((BlocDestructible) caseCourante).destruction(this.joueurPoseBombe));
                            // else, bloque la propagation dans cette direction
                        }
                        System.out.println("Explosion en [" + caseCourante.positionX + ", " + caseCourante.positionY + "]");
                    }
                    
                    Carte.afficherCarte();
                    // Delay next expansion
                    Thread.sleep(2000);
                }
                /*for(Case c : caseModifiees){
                    CasePanel caseAModifier = PartiePanel.casesPlateauPanel[c.positionY][c.positionX];
                    caseAModifier.setCaseModel(c);
                    caseAModifier.loadImage();
                    caseAModifier.repaint();
                    caseAModifier.revalidate();
                }*/
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        explosionThread.start(); // Start the explosion thread
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