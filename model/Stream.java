package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Stream {
    
    // Sauvegarder la partie dans un fichier (objet partie sérialisé)
    public static void sauvegarderPartie(Partie partie) {
        // Créer un fichier de sauvegarde
        File file = new File("sauvegarde.txt");
        try {
            // Créer un flux de sortie pour écrire dans le fichier
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            // Écrire la partie dans le fichier
            oos.writeObject(partie);
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Récupérer une partie à partir d'un fichier
    public static Partie recuperePartie() {
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
            fis.close();
            // Supprimer le fichier de sauvegarde une fois la partie récupérée
            file.delete();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return partie;
    }

    public static boolean sauvegardeExist() {
        File file = new File("sauvegarde.txt");
        return file.exists();
    }

}
