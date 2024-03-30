package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import controller.AccueilController;
import model.Touche;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.*;

public class changerToucheJDialog extends JDialog {

    private JTextField textField;
    Color backgrounfColor = new Color(231, 195, 239);

    public changerToucheJDialog(int joueur, String ancienneTouche, String actionTouche, JButton boutonChooseTouche) {
        // Create a panel to add padding
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPanel.setBackground(backgrounfColor);

        // Create a label for information
        JLabel label = new JLabel("Appuyez sur la touche souhaitée");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        contentPanel.add(label, BorderLayout.NORTH);

        JLabel label2 = new JLabel("Attention il n'y a pas de différence entre Shift droit et Shift gauche");
        label2.setHorizontalAlignment(SwingConstants.CENTER);
        contentPanel.add(label2, BorderLayout.CENTER);

        // Create a text field for the user input
        textField = new JTextField(ancienneTouche);
        textField.setEditable(false);
        textField.setPreferredSize(new Dimension(textField.getPreferredSize().width, 40));
        contentPanel.add(textField, BorderLayout.SOUTH);
        
        // Ajoutez un écouteur d'événements pour intercepter les touches
        Toolkit.getDefaultToolkit().addAWTEventListener(new AWTEventListener() {
            @Override
            public void eventDispatched(AWTEvent event) {
                KeyEvent ke = (KeyEvent) event;
                if (ke.getID() == KeyEvent.KEY_PRESSED && ke.getSource() == textField) {
                    int keyCode = ke.getKeyCode();
                    String chosenKey = Touche.keyMap.get(keyCode);
                    if (chosenKey == null) {
                        chosenKey = KeyEvent.getKeyText(keyCode);
                    }
                    // Consommez l'événement pour annuler l'action par défaut
                    ke.consume();
                    boutonChooseTouche.setText(chosenKey); // Change the text of the button

                    // Remove all action listeners from the button
                    for (ActionListener al : boutonChooseTouche.getActionListeners()) {
                        boutonChooseTouche.removeActionListener(al);
                    }

                    // Ajout du nouveau controller
                    AccueilController ctr = new AccueilController(joueur, chosenKey, "Haut", boutonChooseTouche);
                    boutonChooseTouche.addActionListener(ctr);
                    dispose(); // Close the dialog
                }
            }
        }, AWTEvent.KEY_EVENT_MASK);

        this.add(contentPanel);

        this.setModal(true); // Bloque l'interaction avec le JFrame principal
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setTitle("Changer la touche pour le joueur " + joueur + " : " + actionTouche);
        this.setSize(500, 140);
        this.setLocationRelativeTo(null); // Centre la fenêtre sur l'écran
        this.setVisible(true);

        
    }
}
