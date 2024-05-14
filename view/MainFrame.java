package view;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.Partie;
import model.Stream;


public class MainFrame extends JFrame {

    private JPanel panelActif;

    public MainFrame() {
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setTitle("Bomberman");
        this.pack();
        // set size full sreen
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //this.setSize(1000,600);
        this.setResizable(false);
        this.setLocationRelativeTo (null);
        this.setVisible(true);

        initUI();

        /*
         * Gestion de la fermeture de l'application
         * On demande à l'utilisateur s'il veut sauvegarder la partie en cours
         * Si l'utilisateur clique sur "Oui", on sauvegarde la partie et on ferme l'application
         */
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Vérifier si le panneau actif est une partie en cours non terminée
                if (panelActif instanceof PartiePanel && ((PartiePanel) panelActif).partieEnCours.estTerminee() == false){
                    // Récupère la partie en cours
                    Partie partie = ((PartiePanel) panelActif).partieEnCours;
                    int response = JOptionPane.showConfirmDialog(MainFrame.this, "Voulez-vous sauvegarder la partie en cours ?", "Confirmation", JOptionPane.YES_NO_CANCEL_OPTION);
                    // Si l'utilisateur clique sur "Oui", on sauvegarde la partie et on ferme l'application
                    if (response == JOptionPane.YES_OPTION) {
                        Stream.sauvegarderPartie(partie);
                        System.exit(0);
                    } else if (response == JOptionPane.NO_OPTION) {
                        System.exit(0);
                    }
                } 
                // Si le panneau actif n'est pas une partie en cours fermer l'application
                else {
                    System.exit(0);
                }
            }
        });
    }

    private void initUI() {
        // Initialise avec le panneau de démarrage
        setContentPane(new AccueilPanel(this));
    }

    public void changePanel(JPanel panel) {
        panelActif = panel;
        setContentPane(panel);
        validate(); // Valide le conteneur après l'ajout du composant
    }
}