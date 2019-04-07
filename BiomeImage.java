import javax.imageio.ImageIO;
import java.io.File;
import java.awt.image.BufferedImage;
import java.io.IOException;
public class BiomeImage {
    private BufferedImage[] images;
    private String[] biomes;
    public BiomeImage() {
        biomes=new String[]{"winter","desert","forest","rocky","jungle","plains"};
        images = new BufferedImage[biomes.length];
        for(int i=0;i<biomes.length;i++) {
            try {
                System.out.println("file loading string: " + "images/"+biomes[i]+" texture.png");
                images[i]=ImageIO.read(new File("images/"+biomes[i]+" texture.png"));
            } catch (IOException e) {
                System.out.println("failed to load image of type: " + biomes[i]);
                e.printStackTrace();
            }
        }
    }
    public BufferedImage getImage(String type) {
        int in = -1;
        for(int i=0;i<biomes.length;i++) {
            if (biomes[i].equals(type)) {
                in=i;
                break;
            }
        }
        if (in==-1) {
            return null;
        }
        return images[in];
    }
}