import javax.imageio.ImageIO;
import java.io.File;
import java.awt.image.BufferedImage;
import java.io.IOException;
public class IconImage {
    private BufferedImage[] images;
    private String[] icons;
    public IconImage() {
        icons=new String[]{"berry","ice","needle","rawrubber","stick","stone axe","stone pickaxe","stone","wood planks","wood","wooden axe","wooden pickaxe","stone brick"};
        images = new BufferedImage[icons.length];
        for(int i=0;i<icons.length;i++) {
            try {
                System.out.println("file loading string: " + "images/"+icons[i]+".png");
                images[i]=ImageIO.read(new File("images/"+icons[i]+".png"));
            } catch (IOException e) {
                System.out.println("failed to load image of type: " + icons[i]);
                e.printStackTrace();
            }
        }
    }
    public BufferedImage getImage(String type) {
        int in = -1;
        for(int i=0;i<icons.length;i++) {
            if (icons[i].equals(type)) {
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