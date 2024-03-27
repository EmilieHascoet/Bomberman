package view;

import javax.swing.*;
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
        add(textField, BorderLayout.CENTER);

        // Créez la map pour la correspondance des touches
        Map<Integer, String> keyMap = new HashMap<>();
        keyMap.put(KeyEvent.VK_SHIFT, "Shift");
        keyMap.put(KeyEvent.VK_CONTROL, "Ctrl");
        keyMap.put(KeyEvent.VK_ALT, "Alt");
        keyMap.put(KeyEvent.VK_META, "Pomme");
        keyMap.put(KeyEvent.VK_SPACE, "Espace");
        keyMap.put(KeyEvent.VK_ENTER, "Entrée");
        keyMap.put(KeyEvent.VK_BACK_SPACE, "Retour arrière");
        keyMap.put(KeyEvent.VK_DELETE, "Supprimer");
        keyMap.put(KeyEvent.VK_UP, "Flèche du haut");
        keyMap.put(KeyEvent.VK_DOWN, "Flèche du bas");
        keyMap.put(KeyEvent.VK_LEFT, "Flèche de gauche");
        keyMap.put(KeyEvent.VK_RIGHT, "Flèche de droite");
        keyMap.put(KeyEvent.VK_TAB, "Tabulation");
        keyMap.put(KeyEvent.VK_ESCAPE, "Esc");
        keyMap.put(KeyEvent.VK_CAPS_LOCK, "MAJ");
        keyMap.put(KeyEvent.VK_NUM_LOCK, "Verr num");
        keyMap.put(KeyEvent.VK_SCROLL_LOCK, "Verrouillage du défilement");
        for (int i = 1; i <= 12; i++) {
            keyMap.put(KeyEvent.VK_F1 + i - 1, "F" + i);
        }
        
        // Ajoutez un écouteur d'événements pour intercepter les touches
        Toolkit.getDefaultToolkit().addAWTEventListener(new AWTEventListener() {
            @Override
            public void eventDispatched(AWTEvent event) {
                KeyEvent ke = (KeyEvent) event;
                if (ke.getID() == KeyEvent.KEY_PRESSED && ke.getSource() == textField) {
                    int keyCode = ke.getKeyCode();
                    String keyName = keyMap.get(keyCode);
                    if (keyName != null) {
                        // Affichez le nom de la touche dans le JTextField
                        textField.setText(keyName);
                        // Consommez l'événement pour annuler l'action par défaut
                        ke.consume();
                    } else {
                        // Vider le JTextField si la touche n'est pas mappée
                        textField.setText("");
                    }
                    String chosenKey = textField.getText();
                    boutonChooseTouche.setText(chosenKey); // Change the text of the button
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
