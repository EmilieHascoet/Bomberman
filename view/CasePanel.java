package view;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import model.Bonus;
import model.Case;
import model.Case.typeCaseEnum;

public class CasePanel extends JPanel {
    public Case caseModel;
    private String path;
    Image imageCase;
    typeCaseEnum typeCase;

    public CasePanel(Case caseModel) {
        this.caseModel = caseModel;
        this.typeCase = caseModel.typeCase;
        loadImage();
    }

    public void setCaseModel(Case caseModel) {
        this.caseModel = caseModel;
        this.typeCase = caseModel.typeCase;
    }
    
    public void loadImage() {
        ClassLoader classLoader = getClass().getClassLoader();

        if(caseModel.joueur != null) {
                this.path = caseModel.joueur.avatar.getPathImage();
        } else if (caseModel.typeCase == typeCaseEnum.Bonus){
            this.path = ((Bonus)caseModel).effet.getPathImage();
        } else {
            this.path = caseModel.typeCase.getPathImage() == null ? "" : caseModel.typeCase.getPathImage();
        }
        
        URL imageUrl = classLoader.getResource(this.path);
    
        if (imageUrl != null) {
            this.imageCase = new ImageIcon(imageUrl).getImage();
        }
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

        // Afficher la bombe au dessus du joueur
        if(this.typeCase == typeCaseEnum.Bombe) {
            URL imageUrl = getClass().getClassLoader().getResource(typeCase.getPathImage());
            if (imageUrl != null) {
                Image imageBombe = new ImageIcon(imageUrl).getImage();
                g.drawImage(imageBombe, 0, 0, getWidth(), getHeight(), this);
            }
        }
        // Afficher l'explosion au dessus de tout
        if (caseModel.isFire) {
            URL imageUrl = getClass().getClassLoader().getResource("Images/Explosion/cote.png");
            if (imageUrl != null) {
                Image imageFire = new ImageIcon(imageUrl).getImage();
                g.drawImage(imageFire, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }
}