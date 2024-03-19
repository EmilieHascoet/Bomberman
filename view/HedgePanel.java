package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

class HedgePanel extends JPanel {
    private Image hedgeImage;
    private int imageWidth;
    private int imageHeight;

    public HedgePanel() {
        ClassLoader classLoader = getClass().getClassLoader();
        URL imageUrl = classLoader.getResource("Images/hedge.png");
        if (imageUrl != null) {
            ImageIcon icon = new ImageIcon(imageUrl);
            imageWidth = 30; // Largeur souhaitée
            imageHeight = 30; // Hauteur souhaitée
            hedgeImage = icon.getImage().getScaledInstance(imageWidth, imageHeight, Image.SCALE_SMOOTH);
            setPreferredSize(new Dimension(imageWidth, imageHeight));
        } else {
            System.err.println("Image not found");
        }
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