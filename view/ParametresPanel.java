package view;

import controller.ParametreController;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import model.Partie;

public class ParametresPanel extends JPanel {
    private final MainFrame frame;
    private JPanel rightPanel;
    private CardLayout cardLayout;
    private Partie partieEnCours;

    public ParametresPanel(MainFrame frame, Partie partie) {
        this.frame = frame;
        this.partieEnCours = partie;
        setLayout(new GridBagLayout());
        setBackground(frame.mainColor);

        JPanel squarePanel = new JPanel();
        squarePanel.setLayout(new GridLayout(1, 2));
        squarePanel.setBackground(new Color(240, 240, 240));

        // Left side containing labels
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(frame.mainColor);
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Label panel with titled border
        JPanel labelPanel = new JPanel(new BorderLayout());
        labelPanel.setBorder(BorderFactory.createTitledBorder("Settings"));
        labelPanel.setBackground(frame.mainColor);

        DefaultListModel<String> listModel = new DefaultListModel<>();
        JList<String> list = new JList<>(listModel);
        listModel.addElement("Liste de bonus");
        listModel.addElement("Partie");
        for (int i = 1; i <= partieEnCours.getNbJoueurs(); i++) {
            listModel.addElement("Joueur " + i);
        }

        list.setFont(new Font("Arial", Font.PLAIN, 16));
        list.setForeground(Color.black);
        list.setBackground(frame.mainColor);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Allow only single selection

        // Add a JScrollPane to the list
        JScrollPane scrollPane = new JScrollPane(list);
        labelPanel.add(scrollPane);

        // Add the label panel to leftPanel
        leftPanel.add(labelPanel);

        // Add action listener to the list selection
        list.addListSelectionListener((ListSelectionEvent e) -> {
            if (!e.getValueIsAdjusting()) {
                String selectedLabel = list.getSelectedValue();
                cardLayout.show(rightPanel, selectedLabel);
            }
        });

        // Add the leftPanel to the squarePanel
        squarePanel.add(leftPanel);

        // Right side containing checkboxes using CardLayout
        rightPanel = new JPanel();
        cardLayout = new CardLayout();
        rightPanel.setLayout(cardLayout);
        rightPanel.setBackground(frame.mainColor);

        Map<String, Integer> mapP = new HashMap<>();
        String[] lab = new String[] { "Nombres de vies", "Vitesse",
                "Nombre de bombes initiales", "Portée de la bombe", "Largeur du plateau", "Hauteur du plateau" };
        mapP.put("Nombres de vies", partieEnCours.paramPartie.getNbVie());
        mapP.put("Vitesse", partieEnCours.paramPartie.getVitesse());
        mapP.put("Nombre de bombes initiales", partieEnCours.paramPartie.getNbBombeInit());
        mapP.put("Portée de la bombe", partieEnCours.paramPartie.getPorteeBombe());
        mapP.put("Largeur du plateau", partieEnCours.paramPartie.getBoardWidth());
        mapP.put("Hauteur du plateau", partieEnCours.paramPartie.getBoardHeight());

        // Create checkboxes for each label
        createCheckBoxes(listModel.get(0), rightPanel,
                Arrays.stream(Partie.bonusEnum.values()).map(Enum::toString).toArray(String[]::new));
        createTextAreasM(listModel.get(1), rightPanel, mapP, lab);

        // Create text areas for each player
        for (int i = 1; i <= partieEnCours.getNbJoueurs(); i++) {
            createTextAreas(listModel.get(i + 1), rightPanel, new String[] { "Joueur " + i });
        }

        squarePanel.add(rightPanel);

        // Bottom panel with a button
        JButton retour = new JButton("Retour");
        retour.addActionListener(new ParametreController(retour, this.frame, this, partieEnCours));
        retour.setFont(new Font("Arial", Font.BOLD, 16));
        retour.setForeground(Color.white);
        retour.setBackground(new Color(51, 153, 255));

        JButton valider = new JButton("Valider");
        valider.addActionListener(new ParametreController(valider, this.frame, this, partieEnCours));
        valider.setFont(new Font("Arial", Font.BOLD, 16));
        valider.setForeground(Color.white);
        valider.setBackground(new Color(51, 153, 255));

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(frame.mainColor);
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
        checkBoxPanel.setBackground(frame.mainColor);

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
        textAreaPanel.setBackground(frame.mainColor);
        textAreaPanel.setLayout(new BoxLayout(textAreaPanel, BoxLayout.Y_AXIS));

        JTextField[] textAreas = new JTextField[list.length];
        JLabel[] labels = new JLabel[list.length];

        for (int j = 0; j < list.length; j++) {
            final int i = j;
            labels[i] = new JLabel(list[i]);
            textAreas[i] = new JTextField(list[i]);
            textAreas[i].setName(list[i]);
            textAreas[i].addFocusListener(new java.awt.event.FocusAdapter() {
                @Override
                public void focusGained(java.awt.event.FocusEvent evt) {
                    if (textAreas[i].getText().equals(list[i])) {
                        labels[i].setText(list[i]);
                        textAreas[i].setText("");
                        textAreas[i].setForeground(Color.black);
                    }
                }

                @Override
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
            labels[i].setAlignmentX(LEFT_ALIGNMENT);
            textAreaPanel.add(labels[i]);
            textAreaPanel.add(textAreas[i]);

        }

        JLabel avatarLabel = new JLabel("Avatar");
        avatarLabel.setText("Avatar");
        avatarLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        avatarLabel.setAlignmentX(LEFT_ALIGNMENT);
        textAreaPanel.add(avatarLabel);

        JPanel avatar = new JPanel(); // Avatar panel
        avatar.setLayout(new FlowLayout());
        avatar.setBorder(BorderFactory.createLineBorder(Color.black, 2));

        JPanel imagePanel = new JPanel(); // Image panel
        imagePanel.setName(list[0]);
        imagePanel.setLayout(new FlowLayout());

        // Load images from the JAR file and display them
        try {
            ClassLoader cl = getClass().getClassLoader();
            Enumeration<URL> urls = cl.getResources("Images/Personnage");

            List<JToggleButton> toggleButtons = new java.util.ArrayList<>();

            // Parcourir les URL
            while (urls.hasMoreElements()) {
                URL url = urls.nextElement();
                URLConnection conn = url.openConnection();

                // Si la connexion est un JAR
                if (conn instanceof JarURLConnection) {
                    JarURLConnection jarConn = (JarURLConnection) conn;
                    JarFile jarFile = jarConn.getJarFile();
                    Enumeration<JarEntry> entries = jarFile.entries();

                    // Parcourir les entrées
                    while (entries.hasMoreElements()) {
                        JarEntry entry = entries.nextElement();

                        // Si l'entrée est dans le répertoire
                        if (entry.getName().startsWith(jarConn.getEntryName())) {
                            String filename = entry.getName();
                            InputStream stream = cl.getResourceAsStream(filename);
                            BufferedImage image = ImageIO.read(stream);
                            if (image == null) {
                                continue;
                            }
                            ImageIcon icon = new ImageIcon(
                                    image.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH));

                            // Create a toggle button with the image icon
                            JToggleButton toggleButton = new JToggleButton();
                            toggleButton.setIcon(icon);
                            toggleButton.setSelectedIcon(icon);

                            // Récupérer le nom du fichier sans l'extension
                            String fileNameWithoutExt = filename.substring(filename.lastIndexOf("/") + 1,
                                    filename.lastIndexOf("."));
                            toggleButton.setName(fileNameWithoutExt);

                            toggleButton.addItemListener((ItemEvent e) -> {
                                if (e.getStateChange() == ItemEvent.SELECTED) {
                                    for (JToggleButton tb : toggleButtons) {
                                        if (tb != toggleButton) {
                                            tb.setSelected(false);
                                        }
                                    }
                                }
                            });

                            imagePanel.add(toggleButton);
                            imagePanel.revalidate(); // Re-layout the panel
                            imagePanel.repaint(); // Repaint the panel

                            toggleButtons.add(toggleButton); // Add the toggle button to the image panel
                        }
                    }
                } else {
                    // Si la connexion est un répertoire
                    File dir = new File(url.toURI());

                    // Parcourir les fichiers du répertoire
                    for (File file : dir.listFiles()) {
                        // Lire l'image
                        BufferedImage image = ImageIO.read(file);
                        if (image == null) {
                            continue;
                        }
                        ImageIcon icon = new ImageIcon(image.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH));

                        // Create a toggle button with the image icon
                        JToggleButton toggleButton = new JToggleButton();
                        toggleButton.setIcon(icon);
                        toggleButton.setSelectedIcon(icon);

                        // Récupérer le nom du fichier sans l'extension
                        String fileName = file.getName().substring(0, file.getName().lastIndexOf("."));
                        toggleButton.setName(fileName);

                        toggleButton.addItemListener((ItemEvent e) -> {
                            if (e.getStateChange() == ItemEvent.SELECTED) {
                                for (JToggleButton tb : toggleButtons) {
                                    if (tb != toggleButton) {
                                        tb.setSelected(false);
                                    }
                                }
                            }
                        });

                        imagePanel.add(toggleButton);
                        imagePanel.revalidate(); // Re-layout the panel
                        imagePanel.repaint(); // Repaint the panel

                        toggleButtons.add(toggleButton); // Add the toggle button to the image panel
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();

        }

        avatar.add(imagePanel); // Add the image panel to the avatar panel
        textAreaPanel.add(avatar); //
        parentPanel.add(textAreaPanel, label);
        avatar.add(imagePanel);
        textAreaPanel.add(avatar);

        parentPanel.add(textAreaPanel, label);
    }

    private <K, V> void createTextAreasM(String label, JPanel parentPanel, Map<K, V> m, String[] l) {
        JPanel textAreaPanel = new JPanel();
        textAreaPanel.setBackground(frame.mainColor);
        textAreaPanel.setLayout(new BoxLayout(textAreaPanel, BoxLayout.Y_AXIS));

        JTextField[] textAreas = new JTextField[m.size()];
        JLabel[] labels = new JLabel[m.size()];

        for (int j = 0; j < m.size(); j++) {
            final int i = j;
            labels[i] = new JLabel(l[i]);
            textAreas[i] = new JTextField(m.get(l[i]) + "");
            textAreas[i].setName(l[i]);
            textAreas[i].addFocusListener(new java.awt.event.FocusAdapter() {
                @Override
                public void focusGained(java.awt.event.FocusEvent evt) {
                    if (textAreas[i].getText().equals(m.get(l[i]) + "")) {
                        labels[i].setText(l[i]);
                        textAreas[i].setText("");
                        textAreas[i].setForeground(Color.black);
                    }
                }

                @Override
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