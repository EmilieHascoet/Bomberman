package model;

import java.util.Set;

public class Parametres {
    // Déclarations des attributs
    private Set<String> listBonus;
    private int nbVie;
    private int vitesse;
    private int nbBombeInit;
    private int porteeBombe;
    private int boardWidth;
    private int boardHeight;

    // Déclaration du constructeur Parametres
    public Parametres(Set<String> listBonus, int nbVie, int vitesse, int nbBombeInit, int porteeBombe, int boardWidth,
            int boardHeight) {
        this.listBonus = listBonus;
        this.nbVie = nbVie;
        this.vitesse = vitesse;
        this.nbBombeInit = nbBombeInit;
        this.porteeBombe = porteeBombe;
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
    }

    // Déclarations des méthodes getters
    public Set<String> getListBonus() {
        return listBonus;
    }

    public int getNbVie() {
        return nbVie;
    }

    public int getVitesse() {
        return vitesse;
    }

    public int getNbBombeInit() {
        return nbBombeInit;
    }

    public int getPorteeBombe() {
        return porteeBombe;
    }

    public int getBoardWidth() {
        return boardWidth;
    }

    public int getBoardHeight() {
        return boardHeight;
    }

    // Déclarations des méthodes setters
    public void setListBonus(Set<String> listBonus) {
        this.listBonus = listBonus;
    }

    public void setNbVie(int nbVie) {
        this.nbVie = nbVie;
    }

    public void setVitesse(int vitesse) {
        this.vitesse = vitesse;
    }

    public void setNbBombeInit(int nbBombeInit) {
        this.nbBombeInit = nbBombeInit;
    }

    public void setPorteeBombe(int porteeBombe) {
        this.porteeBombe = porteeBombe;
    }

    public void setBoardWidth(int boardWidth) {
        this.boardWidth = boardWidth;
    }

    public void setBoardHeight(int boardHeight) {
        this.boardHeight = boardHeight;
    }

    // on overide la méthode toString pour afficher tous les paramètres
    @Override
    public String toString() {
        return ("Les parametres choisi sont :\n Liste des bonus : " + listBonus + "\nNombre de vie : " + nbVie
                + "\nVitesse : " + vitesse + "\nNombre de bombe initiale : " + nbBombeInit + "\nPortée de la bombe : "
                + porteeBombe + "\nLargeur du plateau : " + boardWidth + "\n Hauteur du plateau : " + boardHeight);
    }

}
