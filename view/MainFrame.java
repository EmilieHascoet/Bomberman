package view;

import java.util.Arrays;
import java.util.HashSet;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import model.Bomberman;


public class MainFrame extends JFrame {
    Bomberman bomberman;

    MainFrame(Bomberman bomberman) {
        this.bomberman = bomberman;

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Bomberman");
        this.pack();
        // set size full sreen
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //this.setSize(1000,600);
        this.setResizable(false);
        this.setLocationRelativeTo (null);
        this.setVisible(true);

        initUI();
    }

    private void initUI() {
        // Initialise avec le panneau de démarrage
        setContentPane(new AccueilPanel(this, this.bomberman));
        //setContentPane(new PartiePanel(this, this.bomberman));
    }

    public void changePanel(JPanel panel) {
        setContentPane(panel);
        validate(); // Valide le conteneur après l'ajout du composant
    }

    public static void main(String[] args) {
        // Exécution de l'interface graphique dans le thread de distribution des événements
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Chargez toutes les images au démarrage de l'application
                
                /*// Crée une nouvelle instance de Bomberman
                Bomberman bomberman = new Bomberman();
                // Lance la partie pour le test de l'interface PartiePanel
                bomberman.nouvellePartie(bomberman.parametres);

                MainFrame frame = new MainFrame(bomberman);
                frame.setVisible(true);*/

                // Crée une nouvelle instance de MainFrame
                Bomberman bomberman2 = new Bomberman();
                bomberman2.setParametres(new HashSet<String>(Arrays.asList("Bombe", "Vie", "Vitesse", "Portée")), 3, 1, 1, 1, 15, 10, new String[] { "Images/Personnage/perso1.png", "Images/Personnage/perso2.png"});

                MainFrame frame2 = new MainFrame(bomberman2);
                frame2.setVisible(true);
            }
        });
    }
}