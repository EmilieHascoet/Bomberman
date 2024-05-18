import javax.swing.SwingUtilities;

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
                // Crée une nouvelle instance de MainFrame
                MainFrame frame = new MainFrame();
                frame.setVisible(true);
            }
        });
    }

}