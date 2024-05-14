package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import model.Main;
import model.Partie;

public class FinPartieView extends JDialog {
    private JButton buttonRejouer;
    private JButton buttonMenu;
    private JButton buttonExit;
    private MainFrame frame;

    public FinPartieView(MainFrame frame) {
        super(frame, "Fin de Partie", true);
        this.frame = frame;
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setSize(400, 220);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        int scorePlayer1 = Partie.getJoueurs().get(0).score;
        int scorePlayer2 = Partie.getJoueurs().get(1).score;
        boolean isPlayer1Winner = scorePlayer1 > scorePlayer2;

        setupComponents(gbc, scorePlayer1, scorePlayer2, isPlayer1Winner);

    }

    public void display() {
        setVisible(true);
    }

    public MainFrame getMainFrame() {
        return frame;
    }

    private void setupComponents(GridBagConstraints gbc, int scorePlayer1, int scorePlayer2, boolean isPlayer1Winner) {
        JLabel labelGagnant = new JLabel(isPlayer1Winner ? "Player 1 gagne !" : "Player 2 gagne !");
        labelGagnant.setForeground(isPlayer1Winner ? Color.BLUE : Color.RED);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        add(labelGagnant, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1;
        add(new JLabel("Score Player 1: " + scorePlayer1), gbc);
        gbc.gridx = 2;
        add(new JLabel("Score Player 2: " + scorePlayer2), gbc);

        gbc.gridy = 2;
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        buttonExit = new JButton("Sortir");
        add(buttonExit, gbc);

        buttonRejouer = new JButton("Rejouer");
        gbc.gridx = 1;
        add(buttonRejouer, gbc);

        buttonMenu = new JButton("Accueil");
        gbc.gridx = 2;
        add(buttonMenu, gbc);
    }

    public void setRejouerAction(ActionListener listener) {
        buttonRejouer.addActionListener(listener);
    }

    public void setMenuAction(ActionListener listener) {
        buttonMenu.addActionListener(listener);
    }

    public void setExitAction(ActionListener listener) {
        buttonExit.addActionListener(listener);
    }
}
