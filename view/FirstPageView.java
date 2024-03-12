package view;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import java.awt.BorderLayout;
import java.awt.GridLayout;

public class FirstPageView extends JFrame {
    FirstPageView() {
        JPanel panel = new JPanel();
        //JLabel logo = new JLabel(new ImageIcon("../Images/Blossom-Battles.jpg"));
        JLabel text = new JLabel("Bienvenue sur Bomberman !");
        //panel.add(logo);

        //créer deux panel dans le panel principal avec une séparation vertical
        JPanel joueur1 = new JPanel();
        JPanel joueur2 = new JPanel();
        joueur1.setLayout(new GridLayout(1,1));
        joueur2.setLayout(new GridLayout(1,1));
        JLabel j1 = new JLabel("Joueur 1");
        JLabel j2 = new JLabel("Joueur 2");
        joueur1.add(j1);
        joueur2.add(j2);

        panel.add(text, BorderLayout.NORTH);
        panel.add(joueur1, BorderLayout.WEST);
        panel.add(joueur2, BorderLayout.EAST);

        this.add(panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Bomberman");
        this.pack();
        this.setSize(800,500);
        this.setResizable(false);
        this.setLocationRelativeTo (null);
        this.setVisible(true);
    }
}