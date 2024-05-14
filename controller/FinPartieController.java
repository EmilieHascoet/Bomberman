package controller;

import view.*;
import model.Partie;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FinPartieController {
    private FinPartieView view;
    private MainFrame mainFrame;

    public FinPartieController(FinPartieView view) {
        this.view = view;
        mainFrame = view.getMainFrame();
        initController();
        view.display();

    }

    private void initController() {
        view.setRejouerAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // rejouer avec les mêmes paramêtres
                view.dispose();
                Partie.lancerNouvellePartie();
                PartiePanel partiePanel = new PartiePanel(mainFrame);
                mainFrame.changePanel(partiePanel);
                partiePanel.requestFocusInWindow();
            }
        });

        view.setMenuAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.dispose();
                // retourner à l'accueil
                mainFrame.changePanel(new AccueilPanel(mainFrame));
            }
        });

        view.setExitAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Fermer la fenêtre
            }
        });
    }
}
