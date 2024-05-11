package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import model.Partie;


public class MainFrame extends JFrame {

    public MainFrame() {
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
        setContentPane(new AccueilPanel(this));
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
                new Partie();

                MainFrame frame2 = new MainFrame();
                frame2.setVisible(true);
            }
        });
    }
}