package view;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

import model.Case;
import model.Bonus;

public class CasePanel extends JPanel {
    private ImageIcon icon;
    Case caseModel;
    private String path;

    public CasePanel(Case caseModel) {
        this.caseModel = caseModel;
        System.out.println(caseModel);
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

            //JLabel label = new JLabel(icon);
            //this.add(label);
        } else {
            System.err.println("Image not found");
        }
        //bordure noir
        //this.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int size = Math.min(this.getWidth(), this.getHeight());
        if (icon != null) {
            Image image = icon.getImage();
            g.drawImage(image, 0, 0, size, size, this);
        } else {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, size, size);
        }
    }
}