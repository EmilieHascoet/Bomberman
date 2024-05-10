package view;

import controller.ParametreController;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import model.Bomberman;

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


        Map<String, Integer> mapP = new HashMap<>();
        String[] lab = new String[] { "Nombres de vies", "Vitesse",
        "Nombre de bombes initiales", "Portée de la bombe", "Largeur du plateau", "Hauteur du plateau" };
        mapP.put("Nombres de vies", b.parametres.getNbVie());
        mapP.put("Vitesse", b.parametres.getVitesse());
        mapP.put("Nombre de bombes initiales", b.parametres.getNbBombeInit());
        mapP.put("Portée de la bombe", b.parametres.getPorteeBombe());
        mapP.put("Largeur du plateau", b.parametres.getBoardWidth());
        mapP.put("Hauteur du plateau", b.parametres.getBoardHeight()); 


        // Create checkboxes for each label
        createCheckBoxes(listModel.get(0).toString(), rightPanel,
                b.parametres.getListBonus().toArray(new String[b.parametres.getListBonus().size()]));
        createTextAreasM(listModel.get(1).toString(), rightPanel, mapP, lab);

        createTextAreas(listModel.get(2), rightPanel, new String[] { "Joueur 1"});
        createTextAreas(listModel.get(3), rightPanel, new String[] { "Joueur 2"});

        squarePanel.add(rightPanel);

        // Bottom panel with a button
        JButton retour = new JButton("Retour");
        retour.addActionListener(new ParametreController(retour, this.frame, this, b));
        retour.setFont(new Font("Arial", Font.BOLD, 16));
        retour.setForeground(Color.white);
        retour.setBackground(new Color(51, 153, 255));

        JButton valider = new JButton("Valider");           
        valider.addActionListener(new ParametreController(valider, this.frame, this, b));
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
        JLabel[] labels = new JLabel[list.length];

        for (int j = 0; j < list.length; j++) {
            final int i = j;
            labels[i] = new JLabel(list[i]);
            textAreas[i] = new JTextField(list[i]);
            textAreas[i].setName(list[i]);
            textAreas[i].addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusGained(java.awt.event.FocusEvent evt) {
                    if (textAreas[i].getText().equals(list[i])) {
                        labels[i].setText(list[i]);
                        textAreas[i].setText("");
                        textAreas[i].setForeground(Color.black);
                    }
                }

                public void focusLost(java.awt.event.FocusEvent evt) {
                    if (textAreas[i].getText().equals("")) {
                        textAreas[i].setText(list[i]);
                        textAreas[i].setForeground(new Color(153, 153, 153));
                    }
                }
            });

            textAreas[i].setFont(new Font("Arial", Font.PLAIN, 16));
            textAreas[i].setForeground(new Color(153, 153, 153));
            labels[i].setFont(new Font("Arial", Font.PLAIN, 16));
            labels[i].setForeground(Color.black);
            textAreaPanel.add(labels[i]);
            textAreaPanel.add(textAreas[i]);

        }

        parentPanel.add(textAreaPanel, label);
    }
    private <K, V> void createTextAreasM(String label, JPanel parentPanel, Map<K, V> m, String[] l) {
        JPanel textAreaPanel = new JPanel();
        textAreaPanel.setBackground(Color.white);
        textAreaPanel.setLayout(new BoxLayout(textAreaPanel, BoxLayout.Y_AXIS));

        JTextField[] textAreas = new JTextField[m.size()];
        JLabel[] labels = new JLabel[m.size()];

        for (int j = 0; j < m.size(); j++) {
            final int i = j;
            labels[i] = new JLabel(l[i]);
            textAreas[i] = new JTextField(m.get(l[i]) + "");
            textAreas[i].setName(l[i]);
            textAreas[i].addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusGained(java.awt.event.FocusEvent evt) {
                    if (textAreas[i].getText().equals(m.get(l[i]) +"")) {
                        labels[i].setText(l[i]);
                        textAreas[i].setText("");
                        textAreas[i].setForeground(Color.black);
                    }
                }

                public void focusLost(java.awt.event.FocusEvent evt) {
                    if (textAreas[i].getText().equals("")) {
                        textAreas[i].setText(m.get(l[i]) + "");
                        textAreas[i].setForeground(new Color(153, 153, 153));
                    }
                }
            });

            textAreas[i].setFont(new Font("Arial", Font.PLAIN, 16));
            textAreas[i].setForeground(new Color(153, 153, 153));
            labels[i].setFont(new Font("Arial", Font.PLAIN, 16));
            labels[i].setForeground(Color.black);
            textAreaPanel.add(labels[i]);
            textAreaPanel.add(textAreas[i]);

        }

        parentPanel.add(textAreaPanel, label);
    }

}