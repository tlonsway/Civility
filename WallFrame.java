import java.awt.*;
import java.util.*;
public class WallFrame extends Building{
    ArrayList<TempItem> requirements;
    public WallFrame(int x,int y){
        //requirements = new ArrayList<TempItem>();
        //requirements.add(new TempItem("wood",50));
        //requirments.add(new TempItem("stone",50));
        super("Wall Frame",x,y,25,25,Color.BLACK,null);
        requirements = new ArrayList<TempItem>();
        requirements.add(new TempItem("stone",8));
        //requirements.add(new TempItem("stone brick",50));
        super.setRequired(requirements);
    }
    public void click(){
        
    }
}