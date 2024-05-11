import javax.swing.SwingUtilities;

import model.Partie;
import view.MainFrame;

/**
 * 
 */
public class Bomberman {
    // Déclaration des attributs
    MainFrame frame;
    
    // Lance la vue de la partie de bomberman  
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Chargez toutes les images au démarrage de l'application

                // Crée une nouvelle instance de MainFrame
                new Partie();

                MainFrame frame = new MainFrame();
                frame.setVisible(true);
            }
        });
    }

}