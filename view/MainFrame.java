package view;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class MainFrame extends JFrame {
    MainFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Bomberman");
        this.pack();
        this.setSize(800,500);
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
        SwingUtilities.invokeLater(() -> {
            
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}