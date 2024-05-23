package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

public class Stream {
    private static final String DATA_DIR = "./data";
    private static final String SAVE_GAME_FILE = DATA_DIR + "/saveGame.txt";
    private static final String LEADERBOARD_FILE = DATA_DIR + "/leaderboard.txt";

    static {
        // Créer le répertoire de données s'il n'existe pas déjà
        new File(DATA_DIR).mkdirs();
    }
    
    // Sauvegarder la partie dans un fichier (objet partie sérialisé)
    public static void sauvegarderPartie(Partie partie) {
        // Créer un fichier de sauvegarde
        File file = new File(SAVE_GAME_FILE);
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
        File file = new File(SAVE_GAME_FILE);
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

    public static boolean sauvegardePartieExist() {
        File file = new File(SAVE_GAME_FILE);
        return file.exists();
    }

    public static TreeMap<Integer, List<String>> recupereScores() {
        TreeMap<Integer, List<String>> scores = new TreeMap<>();
        File file = new File(LEADERBOARD_FILE);
        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            scores = (TreeMap<Integer, List<String>>) ois.readObject();
            ois.close();
            fis.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return scores;
    }

    public static void sauvegarderScores(TreeMap<Integer, List<String>> Newscores) {
        TreeMap<Integer, List<String>> allScore = new TreeMap<>(Collections.reverseOrder());
        // Récupérer les scores déjà existants
        allScore.putAll(recupereScores());
        // Ajouter les nouveaux scores
        allScore.putAll(Newscores);
        File file = new File(LEADERBOARD_FILE);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(allScore);
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}