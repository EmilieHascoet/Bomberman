package view;

import javax.swing.*;
import java.awt.*;

public class RoundedPanel extends JPanel {
    private Color backgroundColor;
    private int cornerRadius = 15;
    private int strokeSize;
    private Color strokeColor;

    public RoundedPanel(int radius, Color bgColor, int sSize, Color sColor) {
        super();
        cornerRadius = radius;
        backgroundColor = bgColor;
        strokeSize = sSize;
        strokeColor = sColor;
        setOpaque(false);
    }

    public RoundedPanel(int radius, Color bgColor, int sSize) {
        super();
        cornerRadius = radius;
        backgroundColor = bgColor;
        strokeSize = sSize;
        setOpaque(false);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Dimension arcs = new Dimension(cornerRadius, cornerRadius);
        int width = getWidth();
        int height = getHeight();
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Dessine le panneau arrondi avec une bordure
        if (backgroundColor != null) {
            graphics.setColor(backgroundColor);
        } else {
            graphics.setColor(getBackground());
        }
        graphics.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height); //paint background
        graphics.setColor(getForeground());
        graphics.setColor(strokeColor);
        graphics.setStroke(new BasicStroke(strokeSize)); // Set the stroke size
        graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height); //paint border
    }
}