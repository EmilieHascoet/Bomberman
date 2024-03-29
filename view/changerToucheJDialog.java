package view;

import javax.swing.*;

import controller.AccueilController;
import model.Touche;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.*;

public class changerToucheJDialog extends JDialog {

    private JTextField textField;
    Color backgrounfColor = new Color(231, 195, 239);

    public changerToucheJDialog(String joueur, String ancienneTouche, String actionTouche, JButton boutonChooseTouche) {
        // Set the layout manager
        setLayout(new BorderLayout());
        setBackground(backgrounfColor);

        // Create a label for information
        JLabel label = new JLabel("Attention il n'y a pas de différence entre Shift droit et Shift gauche");
        label.setBackground(backgrounfColor);
        label.setOpaque(true);
        add(label, BorderLayout.NORTH);

        // Create a text field for the user input
        textField = new JTextField(ancienneTouche);
        textField.setEditable(false);
        add(textField, BorderLayout.CENTER);
        
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
                    AccueilController ctr = new AccueilController("1", chosenKey, "Haut", boutonChooseTouche);
                    boutonChooseTouche.addActionListener(ctr);
                    dispose(); // Close the dialog
                }
            }
        }, AWTEvent.KEY_EVENT_MASK);

        this.setModal(true); // Bloque l'interaction avec le JFrame principal
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setTitle("Changer la touche pour le joueur " + joueur + " : " + actionTouche);
        this.setSize(500, 80);
        this.setLocationRelativeTo(null); // Centre la fenêtre sur l'écran
        this.setVisible(true);

        
    }
}
