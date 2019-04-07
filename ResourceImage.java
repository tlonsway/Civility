import javax.imageio.ImageIO;
import java.io.File;
import java.awt.image.BufferedImage;
import java.io.IOException;
public class ResourceImage {
    private BufferedImage[] images;
    private String[] resources;
    public ResourceImage() {
        resources=new String[]{"tree","rock","bush","cactus","crystal","glacier","pinetree","rubbertree"};
        images = new BufferedImage[resources.length];
        for(int i=0;i<resources.length;i++) {
            try {
                System.out.println("file loading string: " + "images/"+resources[i]+".png");
                images[i]=ImageIO.read(new File("images/"+resources[i]+".png"));
            } catch (IOException e) {
                System.out.println("failed to load image of type: " + resources[i]);
                e.printStackTrace();
            }
        }
    }
    public BufferedImage getImage(String type) {
        int in = -1;
        for(int i=0;i<resources.length;i++) {
            if (resources[i].equals(type)) {
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