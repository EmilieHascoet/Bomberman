package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

class HedgePanel extends JPanel {
    private Image hedgeImage;
    private int imageWidth;
    private int imageHeight;

    public HedgePanel() {
        ImageIcon icon = new ImageIcon("/Users/emilie/Documents/L3_MIAGE/JAVA/Bomberman/Images/Haie3.png");
        imageWidth = 300; // Largeur souhaitée
        imageHeight = 40; // Hauteur souhaitée
        hedgeImage = icon.getImage().getScaledInstance(imageWidth, imageHeight, Image.SCALE_SMOOTH);
        setPreferredSize(new Dimension(imageWidth, imageHeight));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (hedgeImage != null) {
            int x = (getWidth() - imageWidth) / 2;
            int y = (getHeight() - imageHeight) / 2;
            g.drawImage(hedgeImage, x, y, this);
        }
    }
}