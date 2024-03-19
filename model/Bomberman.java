package model;
/**
 * 
 */
public class Bomberman {
    public int boardWidth;
    public int boardHeight;
    public int numPlayers;
    public int numBombs;
    public int bombRange;
    public Bonus[] listBonus;
    
    /**
     * Default constructor
     */
    public Bomberman(int boardWidth, int boardHeight, int numPlayers, int numBombs, int bombRange, Bonus[] bonus) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        this.numPlayers = numPlayers;
        this.numBombs = numBombs;
        this.bombRange = bombRange;
        this.listBonus = bonus;
    }

    // Déclarations des méthodes
    /**
     *
     */
    public void nouvellePartie(Set<String> paramètres) {
        // TODO implement here
    }

    public static void main(String[] args) {
        Bonus[] bonus = new Bonus[3];
        bonus[0] = new Bonus(true, 1, 1, false, false, "Boum");
        bonus[1] = new Bonus(true, 1, 1, false, true, "Boum");
        bonus[2] = new Bonus(true, 1, 1, false, false, "Boum");
        Settings settings = new Settings(10, 10, 2, 2, 2, bonus);
    
        System.out.println("Voici les paramètres de la partie : " +
            "Taille du plateau : " + settings.boardWidth + "x" + settings.boardHeight + " " +
            "Nombre de joueurs : " + settings.numPlayers + " " +
            "Nombre de bombes : " + settings.numBombs + " " +
            "Portée des bombes : " + settings.bombRange + " " +
            "Liste des bonus : ");
    
        for (int i = 0; i < settings.listBonus.length; i++) {
            System.out.println("Bonus " + i + " : " + "Nom du bonus" + " : " + settings.listBonus[i].effet + " | " + "Bonus destructible" + " : " + settings.listBonus[i].estDestructible);
        }
    }

}