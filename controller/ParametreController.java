package controller;

import model.Bomberman;
import view.MainFrame;
import view.AccueilPanel;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class ParametreController implements ActionListener {
    private JButton bouton;
    private MainFrame fenetre;
    private JPanel p;
    private Bomberman b;
    private String typeAction;

    public ParametreController(JButton bouton, MainFrame frame, JPanel pan, Bomberman bomberman) {
        this.bouton = bouton;
        for(ActionListener al : bouton.getActionListeners()){
            bouton.removeActionListener(al);
        }
        this.bouton.addActionListener(this);
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
        String[] l = new String[] { "Nombre de vies", "Vitesse", "Nombre de bombes initiales", "Portée de la bombe",
                "Largeur du plateau", "Hauteur du plateau" };
        Map<String, String> list = new java.util.HashMap<>(); //Parametres du joueur 1
        Map<String, String> list2 = new java.util.HashMap<>();

            for (Component c : p.getComponents()) { // Jpanel
                // System.out.println("Voici le niveau 1 " + c.getClass());
                for (Component c2 : ((JPanel) c).getComponents()) { // Jpanel et JButton
                    // System.out.println("Voici le niveau 2 ////" + c2.getClass());
                    if (c2 instanceof JPanel) {
                        for (Component c3 : ((JPanel) c2).getComponents()) {
                            // System.out.println("Voici le niveau 3 ////////" + c3.getClass());
                            for (Component c4 : ((JPanel) c3).getComponents()) {
                                // System.out.println("Voici le niveau 4 //////////" + c4.getClass());
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
                                                    Integer.parseInt(((JTextField) c4).getText());
                                                } catch (NumberFormatException ex) {
                                                    errors.add("Veuillez entrer une vitesse");
                                                break;
                                                }
                                            case "Nombre de bombes initiales":
                                            try {
                                                Integer.parseInt(((JTextField) c4).getText());
                                            } catch (NumberFormatException ex) {
                                                errors.add("Veuillez entrer un nombre de bombes initiales");
                                                break;
                                            }
                                            case "Portée de la bombe":
                                            try{
                                                Integer.parseInt(((JTextField) c4).getText());
                                            } catch (NumberFormatException ex) {
                                                errors.add("Veuillez entrer une portée de bombe");
                                                break;
                                            }
                                            case "Largeur du plateau":
                                            try{
                                                Integer.parseInt(((JTextField) c4).getText());
                                            } catch (NumberFormatException ex) {
                                                errors.add("Veuillez entrer une largeur de plateau");
                                                break;
                                            }
                                            case "Hauteur du plateau":
                                            try{
                                                Integer.parseInt(((JTextField) c4).getText());
                                            } catch (NumberFormatException ex) {
                                                errors.add("Veuillez entrer une hauteur de plateau");
                                                break;
                                            }

                                        }
                                        list.put(((JTextField) c4).getName(), ((JTextField) c4).getText());
                                        System.out.println(((JTextField) c4).getText());

                                    }

                                }
                            }
                        }
                    }
                }
            }
            if(!errors.isEmpty()){
                System.out.println("Erreur");
                String errorMessage = String.join("\n", errors);
                JOptionPane.showMessageDialog(null, errorMessage, "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }else{
            b.setParametres(listBonus, Integer.parseInt(list.get("Nombre de vies")),
                    Integer.parseInt(list.get("Vitesse")), Integer.parseInt(list.get("Nombre de bombes initiales")),
                    Integer.parseInt(list.get("Portée de la bombe")), Integer.parseInt(list.get("Largeur du plateau")),
                    Integer.parseInt(list.get("Hauteur du plateau")));
        }
    }

}
