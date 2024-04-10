package view;

import model.Bomberman;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Font;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JOptionPane;

import controller.RetourController;

import java.awt.Insets;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ParametresPanel extends JPanel {
    private MainFrame frame;
    private JPanel rightPanel;
    private CardLayout cardLayout;
    private Bomberman b;

    public ParametresPanel(MainFrame frame, Bomberman bomberman) {
        this.frame = frame;
        setLayout(new GridBagLayout());
        setBackground(Color.white);
        b = bomberman;

        JPanel squarePanel = new JPanel();
        squarePanel.setLayout(new GridLayout(1, 2));
        squarePanel.setBackground(new Color(240, 240, 240));

        // Left side containing labels
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(Color.white);
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Label panel with titled border
        JPanel labelPanel = new JPanel(new BorderLayout());
        labelPanel.setBorder(BorderFactory.createTitledBorder("Settings"));
        labelPanel.setBackground(Color.white);

        DefaultListModel<String> listModel = new DefaultListModel<>();
        JList<String> list = new JList<>(listModel);
        listModel.addElement("Liste de bonus");
        listModel.addElement("Partie");
        listModel.addElement("Joueur 1");
        listModel.addElement("Joueur 2");

        list.setFont(new Font("Arial", Font.PLAIN, 16));
        list.setForeground(Color.black);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Allow only single selection

        // Add a JScrollPane to the list
        JScrollPane scrollPane = new JScrollPane(list);
        labelPanel.add(scrollPane);

        // Add the label panel to leftPanel
        leftPanel.add(labelPanel);

        // Add action listener to the list selection
        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    String selectedLabel = list.getSelectedValue();
                    cardLayout.show(rightPanel, selectedLabel);
                }
            }
        });

        // Add the leftPanel to the squarePanel
        squarePanel.add(leftPanel);

        // Right side containing checkboxes using CardLayout
        rightPanel = new JPanel();
        cardLayout = new CardLayout();
        rightPanel.setLayout(cardLayout);
        rightPanel.setBackground(Color.white);

        // Create different sets of checkboxes for each label

        // Create checkboxes for each label
        createCheckBoxes(listModel.get(0).toString(), rightPanel,
                b.parametres.getListBonus().toArray(new String[b.parametres.getListBonus().size()]));
        createTextAreas(listModel.get(1).toString(), rightPanel, new String[] {
                "Nombre de vies", "Vitesse", "Nombre de bombes initiales", "Portée de la bombe", "Largeur du plateau",
                "Hauteur du plateau"
        });
        createTextAreas(listModel.get(2), rightPanel, new String[] { "Nom du joueur 1", "Couleur du joueur 1" });
        createTextAreas(listModel.get(3), rightPanel, new String[] { "Nom du joueur 2", "Couleur du joueur 2" });

        squarePanel.add(rightPanel);

        // Bottom panel with a button
        JButton retour = new JButton("Retour");
        retour.addActionListener(new RetourController(retour, this.frame));
        retour.setFont(new Font("Arial", Font.BOLD, 16));
        retour.setForeground(Color.white);
        retour.setBackground(new Color(51, 153, 255));

        JButton valider = new JButton("Valider") {
            {
                Set<String> listBonus = new HashSet<>();
                String [] l = new String[] {"Nombre de vies", "Vitesse", "Nombre de bombes initiales", "Portée de la bombe", "Largeur du plateau", "Hauteur du plateau"};
                Map<String, String> list = new java.util.HashMap<>();
                Map<String, String> list2 = new java.util.HashMap<>();
                
                addActionListener(e -> {
                    // Get the selected checkboxes
                    for (int i = 0; i < b.parametres.getListBonus().size(); i++) {
                        JCheckBox checkBox = (JCheckBox) ((JPanel) rightPanel.getComponent(0)).getComponent(i);
                        if (checkBox.isSelected()) {
                            listBonus.add(checkBox.getText());
                        } else {
                            listBonus.remove(checkBox.getText());
                        }
                    }
                    if (listBonus.isEmpty()) {
                        JOptionPane.showMessageDialog(this, "Veuillez sélectionner au moins un bonus.", "Erreur",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    else{
                        b.parametres.setListBonus(listBonus);
                    }

                    // Get the text areas
                    for (int i = 0; i < l.length; i++) {
                        JTextField textArea = (JTextField) ((JPanel) rightPanel.getComponent(1)).getComponent(i);
                        if (textArea.getText().equals(list.get(textArea.getText().toString()))) {
                            JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs.", "Erreur",
                                    JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        else{
                            switch (i) {
                                case 0:
                                    list.put(l[0], textArea.getText());
                                case 1:
                                    list.put(l[1], textArea.getText());
                                case 2:
                                    list.put(l[2], textArea.getText());
                                case 3:
                                    list.put(l[3], textArea.getText());
                                case 4:
                                    list.put(l[4], textArea.getText());
                                case 5:
                                    list.put(l[5], textArea.getText());
                                    break;
                                default:
                                    break;
                            }
                        }
                    }

                    for (int i = 0; i < 2; i++) {
                        JTextField textArea = (JTextField) ((JPanel) rightPanel.getComponent(2)).getComponent(i);
                        if (textArea.getText().equals(list2.get(textArea.getText().toString()))) {
                            JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs.", "Erreur",
                                    JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        else{
                            switch (i) {
                                case 0:
                                    list2.put("Nom du joueur 1", textArea.getText());
                                case 1:
                                    list2.put("Couleur du joueur 1", textArea.getText());
                                    break;
                                default:
                                    break;
                            }
                        }
                    }

                    for (int i = 0; i < 2; i++) {
                        JTextField textArea = (JTextField) ((JPanel) rightPanel.getComponent(3)).getComponent(i);
                        if (textArea.getText().equals(list2.get(textArea.getText().toString()))) {
                            JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs.", "Erreur",
                                    JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        else{
                            switch (i) {
                                case 0:
                                    list2.put("Nom du joueur 2", textArea.getText());
                                case 1:
                                    list2.put("Couleur du joueur 2", textArea.getText());
                                    break;
                                default:
                                    break;
                            }
                        }
                    }

                    
                    
                    JDialog dialog = new JDialog();
                    dialog.setAlwaysOnTop(true);
                    dialog.setModal(true);
                    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    dialog.setTitle("Paramètres");
                    dialog.setSize(400, 300);
                    dialog.setLocationRelativeTo(null);
                    dialog.add(new JPanel() {
                        {
                            setBackground(Color.white);
                            add(new JTextArea(listBonus.toString().format("Liste de bonus : %s", listBonus) + "\n" + list.toString().format("Partie : %s", list)) {
                                {
                                    setFont(new Font("Arial", Font.PLAIN, 16));
                                    setForeground(Color.black);
                                    setEditable(false);
                                    setBackground(Color.white);
                                    setLineWrap(true);
                                    setWrapStyleWord(true);
                                    
                                }
                            });
                            add(new JButton("OK") {
                                {
                                    addActionListener(e -> dialog.dispose());
                                }
                            });
                        
                        }
                    });
                    dialog.setVisible(true);
                });
            }
        };
        valider.setFont(new Font("Arial", Font.BOLD, 16));
        valider.setForeground(Color.white);
        valider.setBackground(new Color(51, 153, 255));

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.white);
        bottomPanel.add(retour);
        bottomPanel.add(valider);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(50, 50, 50, 50);
        add(squarePanel, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        add(bottomPanel, gbc);
    }

    // Method to create checkboxes for a specific label
    private void createCheckBoxes(String label, JPanel parentPanel, String[] list) {
        JPanel checkBoxPanel = new JPanel(new GridLayout(0, 2)); // 2 colonnes
        checkBoxPanel.setBackground(Color.white);

        for (String item : list) {
            JCheckBox checkBox = new JCheckBox(item);
            checkBox.setFont(new Font("Arial", Font.PLAIN, 16));
            checkBox.setForeground(Color.black);
            checkBoxPanel.add(checkBox);
        }

        parentPanel.add(checkBoxPanel, label);
    }

    private void createTextAreas(String label, JPanel parentPanel, String[] list) {
        JPanel textAreaPanel = new JPanel();
        textAreaPanel.setBackground(Color.white);
        textAreaPanel.setLayout(new BoxLayout(textAreaPanel, BoxLayout.Y_AXIS));

        JTextField[] textAreas = new JTextField[list.length];

        for (int j = 0; j < list.length; j++) {
            final int i = j;
            textAreas[i] = new JTextField(list[i]) {
                {
                    addFocusListener(new java.awt.event.FocusAdapter() {
                        public void focusGained(java.awt.event.FocusEvent evt) {
                            jTextFieldFocusGained(evt);
                        }

                        public void focusLost(java.awt.event.FocusEvent evt) {
                            jTextFieldFocusLost(evt);
                        }
                    });
                }

                private void jTextFieldFocusGained(java.awt.event.FocusEvent evt) {
                    if (getText().equals(list[i])) {
                        setText("");
                        setForeground(Color.black);
                    }
                }

                private void jTextFieldFocusLost(java.awt.event.FocusEvent evt) {
                    if (getText().equals("")) {
                        setText(list[i]);
                        setForeground(new Color(153, 153, 153));
                    }
                    /*
                     * String text = this.getText();
                     * if (!text.matches("\\d*")) { // Vérifie si la chaîne ne contient que des
                     * chiffres
                     * JOptionPane.showMessageDialog(this,
                     * "Veuillez entrer uniquement des chiffres.", "Erreur",
                     * JOptionPane.ERROR_MESSAGE);
                     * }
                     */
                }
            };
            textAreas[i].setFont(new Font("Arial", Font.PLAIN, 16));
            textAreas[i].setForeground(new Color(153, 153, 153));
            textAreaPanel.add(textAreas[i]);
        }

        parentPanel.add(textAreaPanel, label);
    }

}