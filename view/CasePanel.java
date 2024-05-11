package view;
import java.awt.Color;
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
    private MainFrame frame;
    int width = Partie.paramPartie.getBoardWidth()+2;
    int height = Partie.paramPartie.getBoardHeight()+2;
    int size;
    Image imageCase;
    String typeImage;

    public CasePanel(Case caseModel, MainFrame frame) {
        this.caseModel = caseModel;
        this.typeImage = caseModel.typeImage;
        this.frame = frame;
        loadImage();
    }

    public void setCaseModel(Case caseModel) {
        this.caseModel = caseModel;
        this.typeImage = caseModel.typeImage;
    }
    
    public void loadImage() {
        ClassLoader classLoader = getClass().getClassLoader();

        if(caseModel.joueur != null) {
            if(caseModel.joueur.avatar  == frame.bomberman.partie.joueurs.get(0).nom) {
                this.path = frame.bomberman.partie.joueurs.get(0).avatar;
            } else {
                this.path = frame.bomberman.partie.joueurs.get(1).avatar;
            }
        } else if(typeImage != "Bonus") {
                this.path = "Images/" + typeImage + ".png";
        } else {
            this.path = "Images/bonus/" + ((Bonus)this.caseModel).effet + ".png";
        }
        
        URL imageUrl = classLoader.getResource(this.path);
    
        if (imageUrl != null) {
            this.imageCase = new ImageIcon(imageUrl).getImage();
        }
        /* Test path
        else if (typeImage != "CaseVide") {
            System.err.println("Image not found + " + this.path);
        } */
        else {
            this.imageCase = null;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Set the background color
        g.setColor(new Color(203, 239, 195));
        g.fillRect(0, 0, getWidth(), getHeight());

        if (this.imageCase != null) {
            g.drawImage(imageCase, 0, 0, getWidth(), getHeight(), this);
        }
        if(this.typeImage.equals("Bombe")) {
            URL imageUrl = getClass().getClassLoader().getResource("Images/bombe.png");
            if (imageUrl != null) {
                Image imageBombe = new ImageIcon(imageUrl).getImage();
                g.drawImage(imageBombe, 0, 0, getWidth(), getHeight(), this);
            }
        }
        if (caseModel.isFire) {
            URL imageUrl = getClass().getClassLoader().getResource("Images/Explosion/cote.png");
            if (imageUrl != null) {
                Image imageFire = new ImageIcon(imageUrl).getImage();
                g.drawImage(imageFire, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }
}