package model;

import java.util.Set;

public class Parametres {
    private Set<String> listBonus;
    private int nbVie;
    private int nbBombeInit;
    private int boardWidth;
    private int boardHeight;
    private int vitesse;

    public Parametres(Set<String> listBonus, int nbVie, int nbBombeInit, int boardWidth, int boardHeight, int vitesse) { //Declaration du constructeur Parametres
        this.listBonus = listBonus;
        this.nbVie = nbVie;
        this.nbBombeInit = nbBombeInit;
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        this.vitesse = vitesse;
    }

    // Déclarations des méthodes getters
    public int getNbVie() {
        return nbVie;
    }
    public int getNbBombeInit() {
        return nbBombeInit;
    }
    public int getLargeur() {
        return boardWidth;
    }
    public int getHauteur() {
        return boardHeight;
    }
    public int getVitesse() {
        return vitesse;
    }

    // Déclarations des méthodes setters
    public Set<String> getListBonus() {
        return listBonus;
    }
    public void setListBonus(Set<String> listBonus) {
        this.listBonus = listBonus;
    }
    public void setNbVie(int nbVie) {
        this.nbVie = nbVie;
    }
    public void setNbBombeInit(int nbBombeInit) {
        this.nbBombeInit = nbBombeInit;
    }
    public void setLargeur(int boardWidth) {
        this.boardWidth = boardWidth;
    }
    public void setHauteur(int boardHeight) {
        this.boardHeight = boardHeight;
    }
    public void setVitesse(int vitesse) {
        this.vitesse = vitesse;
    }
}