package view;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Bonus;
import model.Case;
import model.Main;
import model.Partie;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

public class CasePanel extends JPanel {
    private ImageIcon icon;
    Case caseModel;
    private String path;
    MainFrame frame;
    int width = Partie.paramPartie.getBoardWidth()+2;
    int height = Partie.paramPartie.getBoardHeight()+2;
    int size;
    Image imageCase;

    public CasePanel(Case caseModel, MainFrame frame) {
        this.caseModel = caseModel;
        this.frame = frame;
        this.size = Math.min(frame.getWidth() / width, frame.getHeight() / height);
        ClassLoader classLoader = getClass().getClassLoader();
        switch(this.caseModel.typeImage) {
            case "BlocIndestructible":
                this.path = "Images/blocIndestructible.png";
                break;
            case "BlocDestructible":
                this.path = "Images/blocDestructible.png";
                break;
            case "Bombe":
                this.path = "Images/bombe.png";
                break;
            case "Bonus":
            switch (((Bonus)this.caseModel).effet) {
                case "vie":
                    this.path = "Images/bonusVie.png";
                    break;
            
                default:
                    this.path = "Images/bonus.png";
                    break;
            }
            default:
                this.path = "";
                break;
        }
        
        URL imageUrl = classLoader.getResource(this.path);
        if (imageUrl != null) {
            icon = new ImageIcon(imageUrl);
            imageCase = icon.getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH);
            setPreferredSize(new Dimension(size, size));
        } else {
            System.err.println("Image not found");
        }
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (icon != null) {
            int x = (getWidth() - size) / 2;
            int y = (getHeight() - size) / 2;
            g.drawImage(imageCase, x, y, this);

        }
    }
}