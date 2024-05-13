package controller;

import view.*;
import model.Partie;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FinPartieController {
    private FinPartieView view;

    public FinPartieController(FinPartieView view) {
        this.view = view;
        initController();
    }

    private void initController() {
        view.setRejouerAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // rejouer avec les mêmes paramêtres
                Partie.lancerNouvellePartie();
            }
        });

        view.setMenuAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // retourner à l'accueil
                MainFrame.main(null);
            }
        });

        view.setExitAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.dispose(); // Fermer la fenêtre
            }
        });
    }
}
