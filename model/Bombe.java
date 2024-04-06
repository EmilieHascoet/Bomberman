package model;

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
    }

    /**
     * 
     */
    public void explose() {
        for (int direction = 0; direction < 4; direction++) { // 0 nord(y-) , 1 est(x+) , 2 sud(y+) , 3 ouest(x-)
            boolean cont = true;
            int p = 1;
            do {
                int positionYtemp = this.positionY;
                int positionXtemp = this.positionX;
                switch (direction) {
                    case 0: // nord => y-1
                        positionYtemp = -p;
                        break;
                    case 1: // est ==> x+1
                        positionXtemp = +p;
                        break;
                    case 2: // sud => y+1
                        positionYtemp = +p;
                        break;
                    case 3: // ouest => x-1
                        positionXtemp = -p;
                        break;
                }
                if (Carte.map[positionYtemp][positionXtemp].joueur != null) { // si c'est un joueur il perd
                                                                                           // un vie et explosion
                                                                                           // continue
                    Carte.map[positionYtemp][positionXtemp].joueur.vie--; // il faut voir et verifier si il
                                                                                       // y a une ou plus de jouers
                                                                                       // d'abord
                }

                else if (!Carte.map[positionYtemp][positionXtemp].estTraversable) {
                    if (Carte.map[positionYtemp][positionXtemp] instanceof BlocDestructible) // si c'est
                                                                                                          // destructible
                        // le blocl est destruit
                        // et explosion continue
                        ((BlocDestructible) Carte.map[positionYtemp][positionXtemp]).destruction(this.joueurPoseBombe); // cast
                                                                                                                 // and
                                                                                                                 // call
                                                                                                                 // the
                                                                                                                 // destruction
                                                                                                                 // method
                    else // si block n'est pas destructible explostion s'arrete
                        cont = false;
                }
                p++;
            } while (cont && p <= portee);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("BlocDestructible {");
        sb.append("\n  positionX: ").append(positionX);
        sb.append("\n  positionY: ").append(positionY);
        sb.append("\n  estTraversable: ").append(estTraversable);
        sb.append("\n  estDestructible: ").append(estDestructible);
        sb.append("\n  typeImage: ").append(typeImage);
        sb.append("\n}");
        return sb.toString();
    }

}