package controller;

import java.awt.event.*;
import javax.swing.JButton;
import view.MainFrame;

public class RetourController implements ActionListener{
    private JButton boutonRetour;
    private MainFrame fenetre;

    public RetourController(JButton boutonRetour, MainFrame frame) {
        this.boutonRetour = boutonRetour;
        this.boutonRetour.addActionListener(this);
        fenetre = frame;
    }

    public void actionPerformed(ActionEvent e) {
        // TODO implement here
        fenetre.dispose();
    }

    
    
}
