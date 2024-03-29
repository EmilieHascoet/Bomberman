package model;

import java.util.Set;

public class Parametres {
    private Set<String> listBonus;
    private int nbVie;
    private int nbBombeInit;
    private int boardWidth;
    private int boardHeight;

    public Parametres(Set<String> listBonus, int nbVie, int nbBombeInit, int boardWidth, int boardHeight) { //Declaration du constructeur Parametres
        this.listBonus = listBonus;
        this.nbVie = nbVie;
        this.nbBombeInit = nbBombeInit;
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
    }

    public void setParametres(Set<String> listBonus, int nbVie, int nbBombeInit, int boardWidth, int boardHeight) {
        this.listBonus = listBonus;
        this.nbVie = nbVie;
        this.nbBombeInit = nbBombeInit;
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;;
    }

    public Set<String> getListBonus() {
        return listBonus;
    }

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
}