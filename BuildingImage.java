import javax.imageio.ImageIO;
import java.io.File;
import java.awt.image.BufferedImage;
import java.io.IOException;
public class BuildingImage {
    private BufferedImage[] images;
    private String[] buildings;
    public BuildingImage() {
        buildings=new String[]{"factory","goldmine","center","house"};
        images = new BufferedImage[buildings.length];
        for(int i=0;i<buildings.length;i++) {
            try {
                System.out.println("file loading string: " + "images/"+buildings[i]+".png");
                images[i]=ImageIO.read(new File("images/"+buildings[i]+".png"));
            } catch (IOException e) {
                System.out.println("failed to load image of type: " + buildings[i]);
                e.printStackTrace();
            }
        }
    }
    public BufferedImage getImage(String type) {
        int in = -1;
        for(int i=0;i<buildings.length;i++) {
            if (buildings[i].equals(type)) {
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