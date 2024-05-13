package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

import controller.AccueilControleur;
import controller.ChoixToucheControleur;
import model.Partie;

public class AccueilPanel extends JPanel {
    private MainFrame mainFrame;
    Color backgroundColor = new Color(231, 195, 239);

    private JPanel topPanel;
    private JPanel centerPanel;
    private JPanel bottomPanel;

     public AccueilPanel(MainFrame frame) {
        this.mainFrame = frame;

        // Configuration de l'interface utilisateur
        configureUI();

        // Configuration du panel principal
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setBackground(backgroundColor);

        // Création des panels
        createTopPanel();
        createCenterPanel();
        createBottomPanel();

        // Ajout des panels au panel principal
        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        // Ajout de la couleur de fond pour tous les panels
        this.setBackground(backgroundColor);
        //topPanel.setBackground(backgroundColor);
        centerPanel.setBackground(backgroundColor);
        bottomPanel.setBackground(backgroundColor);

        // Affichage du panel
        setVisible(true);
    }

    private void configureUI() {
        // Configuration de l'interface utilisateur
        /*UIManager.put("OptionPane.background", backgroundColor);
        UIManager.put("Panel.background", backgroundColor);
        UIManager.put("OptionPane.messageForeground", Color.BLACK);
        UIManager.put("Button.background", Color.WHITE);
        UIManager.put("Button.foreground", Color.BLACK);
        UIManager.put("Button.border", BorderFactory.createEmptyBorder(10, 10, 10, 10));
        UIManager.put("Button.font", new Font("Arial", Font.PLAIN, 20));
        UIManager.put("Label.font", new Font("Arial", Font.PLAIN, 20));
        UIManager.put("TextField.font", new Font("Arial", Font.PLAIN, 20));
        UIManager.put("ComboBox.font", new Font("Arial", Font.PLAIN, 20));
        UIManager.put("CheckBox.font", new Font("Arial", Font.PLAIN, 20));
        UIManager.put("RadioButton.font", new Font("Arial", Font.PLAIN, 20));
        UIManager.put("TextArea.font", new Font("Arial", Font.PLAIN, 20));
        UIManager.put("List.font", new Font("Arial", Font.PLAIN, 20));
        UIManager.put("Table.font", new Font("Arial", Font.PLAIN, 20));
        UIManager.put("TableHeader.font", new Font("Arial", Font.PLAIN, 20));
        UIManager.put("MenuBar.font", new Font("Arial", Font.PLAIN, 20));
        UIManager.put("Menu.font", new Font("Arial", Font.PLAIN, 20));
        UIManager.put("MenuItem.font", new Font("Arial", Font.PLAIN, 20));
        UIManager.put("ToolTip.font", new Font("Arial", Font.PLAIN, 20));*/
    }

    private void createTopPanel() {
        // Création du panel
        topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Création du label
        JLabel label = new JLabel("Bienvenue dans Bomberman");
        label.setFont(new Font("Arial", Font.BOLD, 40));
        label.setAlignmentX(CENTER_ALIGNMENT);

        // Ajout du label au panel
        topPanel.add(label);
    }

    private void createCenterPanel() {
        // Création du panel
        centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Création du label
        JLabel label = new JLabel("Veuillez choisir une option :");
        label.setAlignmentX(CENTER_ALIGNMENT);

        // Ajout du label au panel
        centerPanel.add(label);
    }

    private void createBottomPanel() {
        bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 2, 20, 0));

        // Création du bouton pour démarrer une nouvelle partie
        JButton nouvellePartieButton = new JButton("Nouvelle Partie");
        AccueilControleur nouvellePartieController = new AccueilControleur(nouvellePartieButton, mainFrame);
        nouvellePartieButton.addActionListener(nouvellePartieController);
        nouvellePartieButton.setPreferredSize(new Dimension(nouvellePartieButton.getPreferredSize().width, 40));

        // Création du bouton pour accéder aux options
        JButton reprendrePartieButton = new JButton("Reprendre la partie");
        AccueilControleur reprendrePartieControleur = new AccueilControleur(reprendrePartieButton, mainFrame);
        reprendrePartieButton.addActionListener(reprendrePartieControleur);
        reprendrePartieButton.setPreferredSize(new Dimension(reprendrePartieButton.getPreferredSize().width, 40));

        // Ajout des boutons au panel
        bottomPanel.add(nouvellePartieButton);
        bottomPanel.add(reprendrePartieButton);
    }
}
