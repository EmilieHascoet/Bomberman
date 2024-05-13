package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Stream {
    
    public static void sauvegarderPartie(Partie partie) {
        // Sauvegarder la partie dans un fichier
        // Créer un fichier de sauvegarde
        File file = new File("sauvegarde.txt");
        try {
            // Créer un flux de sortie pour écrire dans le fichier
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            // Écrire la partie dans le fichier
            oos.writeObject(partie);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Partie lirePartie() {
        // Lire une partie à partir d'un fichier
        Partie partie = null;
        // Ouvrir le fichier de sauvegarde
        File file = new File("sauvegarde.txt");
        try {
            // Créer un flux d'entrée pour lire le fichier
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            // Lire la partie à partir du fichier
            partie = (Partie) ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return partie;
    }

}
