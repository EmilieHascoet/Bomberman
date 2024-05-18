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
    private ImageLoader imageLoader;
    private Image imageCase;
    private typeCaseEnum typeCase;

    public CasePanel(Case caseModel, ImageLoader imageLoader) {
        this.caseModel = caseModel;
        this.typeCase = caseModel.typeCase;
        this.imageLoader = imageLoader;
        loadImage();
    }

    public void setCaseModel(Case caseModel) {
        this.caseModel = caseModel;
        this.typeCase = caseModel.typeCase;
    }
    
    public void loadImage() {
        if(caseModel.joueur != null) {
                this.path = caseModel.joueur.avatar.getPathImage();
        } else if (caseModel.typeCase == typeCaseEnum.Bonus){
            this.path = ((Bonus)caseModel).effet.getPathImage();
        } else {
            this.path = caseModel.typeCase.getPathImage();
        }
        if (this.path == null) {
            this.imageCase = null;
        } else this.imageCase = imageLoader.getImage(this.path);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Set the background color
        g.setColor(new Color(203, 239, 195));
        g.fillRect(0, 0, getWidth(), getHeight());

        // Afficher l'image de la case
        if (this.imageCase != null) {
            g.drawImage(imageCase, 0, 0, getWidth(), getHeight(), this);
        }

        // Afficher la bombe au dessus du joueur
        if(this.typeCase == typeCaseEnum.Bombe) {
            Image imageBombe = imageLoader.getImage("Images/Bloc/Bombe.png");
            g.drawImage(imageBombe, 0, 0, getWidth(), getHeight(), this);
        }

        // Afficher l'explosion au dessus de tout
        if (caseModel.isFire) {
            Image imageFire = imageLoader.getImage("Images/Explosion/cote.png");
            g.drawImage(imageFire, 0, 0, getWidth(), getHeight(), this);
        }
    }
}