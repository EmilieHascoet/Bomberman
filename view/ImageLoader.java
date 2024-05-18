package view;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

public class ImageLoader {
    private Map<String, Image> images;
    private ClassLoader classLoader;

    public ImageLoader() {
        images = new HashMap<>();
        classLoader = getClass().getClassLoader();
    }

    public Image getImage(String path) {
        if (!images.containsKey(path)) {
            try {
                Image image = ImageIO.read(classLoader.getResource(path));
                if (image != null) {
                    images.put(path, image);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return images.get(path);
    }
}
