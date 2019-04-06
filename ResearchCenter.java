import java.awt.*;
import java.util.*;
public class ResearchCenter extends Building{
    TechTree tree; 
    public ResearchCenter(TechTree t){
        super("Research Center",-100,-100,200,200,new Color(244,96,4),null);
        tree = t;
    }
    public ArrayList<TempItem> getRequired(){
        return tree.getRequired();
    }
    public void click(){
        
    }
}