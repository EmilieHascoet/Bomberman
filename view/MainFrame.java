package view;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import model.Bomberman;
import model.Touche;

public class MainFrame extends JFrame {
    MainFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Bomberman");
        this.pack();
        // set size full sreen
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //this.setSize(800,500);
        this.setResizable(false);
        this.setLocationRelativeTo (null);
        this.setVisible(true);

        initUI();
    }

    private void initUI() {
        // Initialise avec le panneau de démarrage
        setContentPane(new AccueilPanel(this));
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
                bomberman.setTouche(1, "Fleche haut", "Fleche bas", "Fleche droite", "Fleche gauche", "Shift Droit");
                bomberman.setTouche(2, "z", "s", "d", "q", "Espace");
                bomberman.setParametres(new HashSet<String>(Arrays.asList("Bombe", "Vie", "Vitesse", "Portée")), 3, 3, 15, 15);

                MainFrame frame = new MainFrame();
                frame.setVisible(true);
            }
        });
    }
}