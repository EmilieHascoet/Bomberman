package view;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import model.Bonus;
import model.Case;
import model.Partie;

public class CasePanel extends JPanel {
    public Case caseModel;
    private String path;
    MainFrame frame;
    int width = Partie.paramPartie.getBoardWidth()+2;
    int height = Partie.paramPartie.getBoardHeight()+2;
    int size;
    Image imageCase;
    String typeImage;

    public CasePanel(Case caseModel, MainFrame frame) {
        this.caseModel = caseModel;
        this.frame = frame;
        this.size = Math.min(frame.getWidth() / width, frame.getHeight() / height);
        this.typeImage = caseModel.typeImage;
        loadImage();
        setPreferredSize(new Dimension(size, size));
    }

    public void setCaseModel(Case caseModel) {
        this.caseModel = caseModel;
        this.typeImage = caseModel.typeImage;
    }
    
    public void loadImage() {
        ClassLoader classLoader = getClass().getClassLoader();
    
        if(caseModel.joueur != null) {
            if(caseModel.joueur.nom == frame.bomberman.partie.joueurs.get(0).nom) {
                this.path = frame.bomberman.partie.joueurs.get(0).avatar;
            } else {
                this.path = frame.bomberman.partie.joueurs.get(1).avatar;
            }
        } else {
            if(typeImage != "Bonus") {
                this.path = "Images/" + typeImage + ".png";
            } else {
                this.path = "Images/bonus/" + ((Bonus)this.caseModel).effet + ".png";
            }
        }
        
        URL imageUrl = classLoader.getResource(this.path);
    
        if (imageUrl != null) {
            this.imageCase = new ImageIcon(imageUrl).getImage();
        } else if (typeImage != "CaseVide") {
            System.err.println("Image not found");
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (typeImage.equals("CaseVide") && this.caseModel.joueur == null) {
            g.setColor(new Color(203, 239, 195));
            g.fillRect(0, 0, getWidth(), getHeight());
        }
        else if (this.imageCase != null) {
            g.drawImage(imageCase, 0, 0, getWidth(), getHeight(), this);
        }
    }
}