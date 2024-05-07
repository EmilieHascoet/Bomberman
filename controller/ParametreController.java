package controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import model.Bomberman;
import view.AccueilPanel;
import view.MainFrame;

public class ParametreController implements ActionListener {
    private MainFrame fenetre;
    private JPanel p;
    private Bomberman b;
    private String typeAction;

    public ParametreController(JButton bouton, MainFrame frame, JPanel pan, Bomberman bomberman) {
        for (ActionListener al : bouton.getActionListeners()) {
            bouton.removeActionListener(al);
        }
        fenetre = frame;
        b = bomberman;
        typeAction = bouton.getText();
        p = pan;
    }

    public void actionPerformed(ActionEvent e) {
        if (typeAction.equals("Retour")) {
            boutonRetour();
        } else if (typeAction.equals("Valider")) {
            boutonValider();
        }
    }

    public void boutonRetour() {
        // Retourner au menu principal
        fenetre.changePanel(new AccueilPanel(fenetre, b));
    }

    public void boutonValider() {
        Set<String> listBonus = new HashSet<>();
        List<String> errors = new ArrayList<>();
        Map<String, String> list = new java.util.HashMap<>(); // Parametres du joueur 1

        for (Component c : p.getComponents()) { // Jpanel
            for (Component c2 : ((JPanel) c).getComponents()) { // Jpanel et JButton
                if (c2 instanceof JPanel) {
                    for (Component c3 : ((JPanel) c2).getComponents()) {
                        for (Component c4 : ((JPanel) c3).getComponents()) {
                            if (c4 instanceof JCheckBox) {
                                if (((JCheckBox) c4).isSelected()) {
                                    listBonus.add(((JCheckBox) c4).getText());
                                }
                            }
                            if (c4 instanceof JTextField) {
                                String name = ((JTextField) c4).getName();
                                String value = ((JTextField) c4).getText();
                                if (name != null) {
                                    switch (name) {
                                        case "Nombre de vies":
                                            try {
                                                Integer.parseInt(value);
                                            } catch (NumberFormatException ex) {
                                                errors.add("Veuillez entrer un nombre de vies");
                                                break;
                                            }
                                        case "Vitesse":
                                            try {
                                                Integer.parseInt(value);
                                            } catch (NumberFormatException ex) {
                                                errors.add("Veuillez entrer une vitesse");
                                                break;
                                            }
                                        case "Nombre de bombes initiales":
                                            try {
                                                Integer.parseInt(value);
                                            } catch (NumberFormatException ex) {
                                                errors.add("Veuillez entrer un nombre de bombes initiales");
                                                break;
                                            }
                                        case "Portée de la bombe":
                                            try {
                                                Integer.parseInt(value);
                                            } catch (NumberFormatException ex) {
                                                errors.add("Veuillez entrer une portée de bombe");
                                                break;
                                            }
                                        case "Largeur du plateau":
                                            try {
                                                Integer.parseInt(value);
                                            } catch (NumberFormatException ex) {
                                                errors.add("Veuillez entrer une largeur de plateau");
                                                break;
                                            }
                                        case "Hauteur du plateau":
                                            try {
                                                Integer.parseInt(value);
                                            } catch (NumberFormatException ex) {
                                                errors.add("Veuillez entrer une hauteur de plateau");
                                                break;
                                            }
                                        case "Nom du joueur 1":
                                            try {
                                                String.format(value);
                                            } catch (NumberFormatException ex) {
                                                errors.add("Veuillez entrer un nom pour le joueur 1");
                                                break;
                                            }
                                        case "Nom du joueur 2":
                                            try {
                                                String.format(value, "%s");
                                            } catch (NumberFormatException ex) {
                                                errors.add("Veuillez entrer un nom pour le joueur 2");
                                                break;
                                            }
                                        case "Couleur du joueur 1":
                                            try {
                                                String.format(value, "%s");
                                            } catch (NumberFormatException ex) {
                                                errors.add("Veuillez entrer une couleur pour le joueur 1");
                                                break;
                                            }
                                        case "Couleur du joueur 2":
                                            try {
                                                String.format(value, "%s");
                                            } catch (NumberFormatException ex) {
                                                errors.add("Veuillez entrer une couleur pour le joueur 2");
                                                break;
                                            }
                                    }
                                    list.put(((JTextField) c4).getName(), value);
                                }

                            }
                        }
                    }
                }
            }
        }
        if (!errors.isEmpty()) {
            if (listBonus.isEmpty()) {
                errors.add("Veuillez choisir au moins un bonus");
            }
            String errorMessage = String.join("\n", errors);
            JOptionPane.showMessageDialog(null, errorMessage, "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        } else {
            b.setParametres(listBonus, Integer.parseInt(list.get("Nombre de vies")),
                    Integer.parseInt(list.get("Vitesse")), Integer.parseInt(list.get("Nombre de bombes initiales")),
                    Integer.parseInt(list.get("Portée de la bombe")), Integer.parseInt(list.get("Largeur du plateau")),
                    Integer.parseInt(list.get("Hauteur du plateau")));
            b.setNomJoueurs(0, list.get("Nom du joueur 1"));
            b.setNomJoueurs(1, list.get("Nom du joueur 2"));
            fenetre.changePanel(new AccueilPanel(fenetre, b));
        }
    }
}
