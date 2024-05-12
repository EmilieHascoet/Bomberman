package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import controller.AccueilControleur;
import model.Partie;
import model.Touche;

import java.awt.*;
import java.awt.event.*;

public class ChangerToucheJDialog extends JDialog {

    private JTextField textField;
    Color backgrounfColor = new Color(231, 195, 239);

    public ChangerToucheJDialog(int joueur, String ancienneTouche, String actionTouche, JButton boutonChooseTouche) {
        // Create a panel to add padding
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPanel.setBackground(backgrounfColor);

        // Texte pour information
        JLabel label = new JLabel("Appuyez sur la touche souhaitée");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        contentPanel.add(label, BorderLayout.NORTH);

        // Texte pour information 2
        JLabel label2 = new JLabel("Attention il n'y a pas de différence entre Shift droit et Shift gauche");
        label2.setHorizontalAlignment(SwingConstants.CENTER);
        contentPanel.add(label2, BorderLayout.CENTER);

        // Create a text field for the user input
        textField = new JTextField(ancienneTouche);
        textField.setEditable(false);
        textField.setPreferredSize(new Dimension(textField.getPreferredSize().width, 40));
        contentPanel.add(textField, BorderLayout.SOUTH);

        // Désactive les actions par défaut des touches (exemple tabulation)
        this.setFocusTraversalKeysEnabled(false);
        
        this.add(contentPanel);
        this.setModal(true); // Bloque l'interaction avec le JFrame principal
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setTitle("Changer la touche pour le joueur " + joueur + " : " + actionTouche);
        this.setSize(500, 140);
        this.setLocationRelativeTo(null); // Centre la fenêtre sur l'écran
        this.setVisible(true);  

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
                    boutonChooseTouche.setText(chosenKey); // Change le texte du bouton

                    // Remove all action listeners from the button
                    for (ActionListener al : boutonChooseTouche.getActionListeners()) {
                        boutonChooseTouche.removeActionListener(al);
                    }

                    // Ajout du nouveau controller
                    AccueilControleur ctr = new AccueilControleur(joueur, chosenKey, "Haut", boutonChooseTouche);
                    boutonChooseTouche.addActionListener(ctr);

                    // Mettre à jour le modèle Touche du Joueur
                    System.out.println("Joueur: " + joueur + " Action: " + actionTouche + " Touche: " + chosenKey);
                    switch (actionTouche) {
                        case "Aller en haut":
                            Partie.getJoueurs().get(joueur).touche.setHaut(chosenKey);
                            break;
                        case "Aller en bas":
                            Partie.getJoueurs().get(joueur).touche.setBas(chosenKey);
                            break;
                        case "Aller à droite":
                            Partie.getJoueurs().get(joueur).touche.setDroite(chosenKey);
                            break;
                        case "Aller à gauche":
                            Partie.getJoueurs().get(joueur).touche.setGauche(chosenKey);
                            break;
                        case "Placer une fleur":
                            Partie.getJoueurs().get(joueur).touche.setBombe(chosenKey);
                            break;
                    }
                    dispose(); // Ferme la fenetre (JDialog)
                }
            }
        }, AWTEvent.KEY_EVENT_MASK);
    }
}