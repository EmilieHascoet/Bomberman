package view;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.util.HashSet;
import java.util.Arrays;

import model.Bomberman;
import model.Touche;


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
                // Créer deux objets contrôle contenant les touches de base
                Bomberman bomberman = new Bomberman();

                MainFrame frame = new MainFrame(bomberman);
                frame.setVisible(true);
            }
        });
    }
}