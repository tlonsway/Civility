import java.awt.*;
import java.util.*;
public class HouseFrame extends Building{
    ArrayList<TempItem> requirements;
    public HouseFrame(int x,int y){
        //requirements = new ArrayList<TempItem>();
        //requirements.add(new TempItem("wood",50));
        //requirments.add(new TempItem("stone",50));
        super("House Frame",x,y,75,75,Color.BLACK,null);
        requirements = new ArrayList<TempItem>();
        requirements.add(new TempItem("wood planks",1));
        //requirements.add(new TempItem("stone brick",50));
        super.setRequired(requirements);
    }
    public void click(){
        
    }
}