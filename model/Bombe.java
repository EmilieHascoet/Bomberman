package model;

/**
 * 
 */
public class Bombe extends Case {

    // Déclarations des attributs
    public Integer tempsExplosion;
    public Partie partie;
    public Integer portee;

    /**
     * Default constructor
     */
    public Bombe(Integer positionX, Integer positionY, Integer tempsExplosion, Integer portee, Partie partie) {
        super(false, positionX, positionY, "Bombe", false, partie);
        this.tempsExplosion = tempsExplosion;
        this.portee = portee;
    }

    /**
     * 
     */
    public void explose() {
       for(int direction = 0 ; direction<4 ; direction++){  //0 nord(y-) , 1 est(x-) , 2 sud(y+) , 3 ouest(x+)
       boolean cont= true;
       int p=1;
        do{
            switch(direction){
                case 0:
                    if (estJoueur(positionY+p,positionX)){ // si c'est un joueur il perd un vie et explosion continue
                        joeurVie-- // il faut voir et verifier si il y a une ou plus de jouers d'abord   
                    }

                    else if !estTraversable(positionY-p , positionX){ 
                            if (estDestructible(positionY-p,positionX)) // si c'est destructible le blocl est destruit et explosion continue
                                destrurireBlock(positionX-p,positionX);
                            else // si block n'est pas destructible explostion s'arrete
                                cont=false;
                    }
                    break;
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
            }
        p++;
        }while(cont && p<=portee);
       }
    }

    @Override
    public String toString() {
        return ("A bomb at Positon : " + positionX + positionY + "TempsExplosion : " + tempsExplosion + "avec portee :"
                + portee);
    }

}