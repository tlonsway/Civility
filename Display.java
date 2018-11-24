import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.awt.geom.*;
import javafx.geometry.*;
public class Display extends JComponent {
    ArrayList<Building> buildings;
    ArrayList<Resource> resources;
    ArrayList<Biome> biomes;
    double center_x;
    double center_y;
    int width;
    int height; 
    boolean w,a,s,d,i=false;
    Inventory in;
    Player player;
    AIThread aithread;
    ArrayList<Item> craftableItems;
    String view;
    JFrame frame;
    public Display(int w, int h, Inventory inventory,Player p, AIThread at, JFrame f,ArrayList<Item> CI) {
        buildings = new ArrayList<Building>();
        resources = new ArrayList<Resource>();
        biomes = new ArrayList<Biome>();
        center_x=0;
        center_y=0;
        width=w;
        height=h;
        in=inventory;
        player = p;
        aithread=at;
        view = "world";
        frame=f;
        craftableItems = CI;
    }
    public void update() {
        BoundingBox screen = new BoundingBox(center_x,center_y,width,height);
        if (player.getHealth()<=0) {
            view="dead";
        }
        for(Building b : buildings) {
            if (b.getType().equals("goldmine")) {
                in.addGold(.005);
            }
        }
        for(Biome b : biomes) {
            for (int ri=0;ri<b.getResources().size();ri++) {
                Resource r = b.getResource(ri);
                if (r.getHealth()<=0) {
                    b.removeResource(ri);
                    ri++;
                    continue;
                }
                if (r.getType().equals("cactus")&&r.getBoundingBox().intersects(new BoundingBox(890+center_x,490+center_y,60,60))) {
                    player.doDamage(.05);
                }
            }
        }
        int moveamt = 5;
        if (a) {
            boolean check = false;
            for (Building b : buildings) {
                if (b.getBoundingBox().intersects(new BoundingBox(900-moveamt+center_x,500+center_y,40,40))) {
                    check = true;
                }
            }
            for(Biome b : biomes) {
                if (b.getBoundingBox().intersects(screen)) {
                    resources=b.getResources();
                    for (Resource r : resources) {
                        if (r.getBoundingBox().intersects(new BoundingBox(900-moveamt+center_x,500+center_y,40,40))) {
                            check = true;
                        }
                    }
                }
            }
            if (!check)
                center_x-=moveamt;
        }
        if (d) {
            boolean check = false;
            for (Building b : buildings) {
                if (b.getBoundingBox().intersects(new BoundingBox(900+moveamt+center_x,500+center_y,40,40))) {
                    check = true;
                }
            }
            for(Biome b : biomes) {
                if (b.getBoundingBox().intersects(screen)) {
                    resources=b.getResources();
                    for (Resource r : resources) {
                        if (r.getBoundingBox().intersects(new BoundingBox(900+moveamt+center_x,500+center_y,40,40))) {
                            check = true;
                        }
                    }
                }
            }
            if (!check)
                center_x+=moveamt;
        }
        if (w) {
            boolean check = false;
            for (Building b : buildings) {
                if (b.getBoundingBox().intersects(new BoundingBox(900+center_x,500-moveamt+center_y,40,40))) {
                    check = true;
                }
            }
            for(Biome b : biomes) {
                if (b.getBoundingBox().intersects(screen)) {
                    resources=b.getResources();
                    for (Resource r : resources) {
                        if (r.getBoundingBox().intersects(new BoundingBox(900+center_x,500-moveamt+center_y,40,40))) {
                            check = true;
                        }
                    }
                }
            }
            if (!check)
                center_y-=moveamt;
        }
        if (s) {
            boolean check = false;
            for (Building b : buildings) {
                if (b.getBoundingBox().intersects(new BoundingBox(900+center_x,500+moveamt+center_y,40,40))) {
                    check = true;
                }
            }
            for(Biome b : biomes) {
                if (b.getBoundingBox().intersects(screen)) {
                    resources=b.getResources();
                    for (Resource r : resources) {
                        if (r.getBoundingBox().intersects(new BoundingBox(900+center_x,500+moveamt+center_y,40,40))) {
                            check = true;
                        }
                    }
                }
            }
            if (!check)
                center_y+=moveamt;
        }
    }
    public void draw() {
        this.repaint();
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(view == "inventory"){
            Font f = new Font("Courier New",Font.PLAIN,60);
            g.setFont(f);
            g.setColor(Color.BLACK);
            g.drawString("Inventory",52,52);
            g.setColor(Color.WHITE);
            g.drawString("Inventory",50,50);
            g.setColor(Color.GRAY);
            g.fillRect(100,100,1600,800);
            g.setColor(Color.BLACK);
            g.drawRect(100,100,1600,800);
            int x = 150;
            int y = 150;
            f = new Font("Courier New",Font.PLAIN,15);
            g.setFont(f);
            ArrayList<Item> Items = player.getInventory();
            for(Item i: player.getInventory()){
                g.setColor(Color.WHITE);
                g.fillRect(i.getBX(),i.getBY(),i.getBWidth(),i.getBHeight());
                g.setColor(Color.BLACK);
                g.drawRect(i.getBX(),i.getBY(),i.getBWidth(),i.getBHeight());
                g.drawString(i.getType(),i.getBX()+5,i.getBY()+20);
                g.drawString("x"+i.getQuantity(),i.getBX()+15,i.getBY()+40);
            }
            Point mp = MouseInfo.getPointerInfo().getLocation();
            Point loc = frame.getLocationOnScreen();
            g.setColor(Color.WHITE);
            g.fillOval((int)mp.getX()-(int)loc.getX()-5,(int)mp.getY()-(int)loc.getY()-5,10,10); 
            g.setColor(Color.BLACK);
            g.drawOval((int)mp.getX()-(int)loc.getX()-5,(int)mp.getY()-(int)loc.getY()-5,10,10);
            g.setColor(Color.GRAY);
            g.fillRect(595,820,610,70);
            g.setColor(Color.BLACK);
            g.drawRect(595,820,610,70);
            Item[] hotbar = player.getHotbar();
            f = new Font("Courier New",Font.PLAIN,15);
            g.setFont(f);
            for(int i = 0; i < 10; i++){
                if(player.getHotBarItemSelected() == i){
                    g.setColor(Color.BLACK);
                }
                else{
                    g.setColor(Color.WHITE);
                }
                g.fillRect(605+i*60,830,50,50);
                if(player.getHotBarItemSelected() == i){
                    g.setColor(Color.WHITE);
                }
                else{
                    g.setColor(Color.BLACK);
                }
                if(hotbar[i] != null){
                    g.drawRect(605+i*60,830,50,50);
                    g.drawString(hotbar[i].getType(),610+i*60,850);
                    g.drawString("x" + hotbar[i].getQuantity(),610+i*60,865);
                }
            }
        }
        else if(view == "world"){
            BoundingBox screen = new BoundingBox(center_x,center_y,width,height);
            for(Biome b : biomes) {
                if (b.getBoundingBox().intersects(screen)) {
                    g.setColor(b.getColor());
                    g.fillRect(-(int)center_x+b.getX(),-(int)center_y+b.getY(),b.getWidth(),b.getHeight());
                }
            }
            for(Biome b : biomes) {
                if (b.getBoundingBox().intersects(screen)) {
                    resources=b.getResources();
                    for (int ri=0;ri<resources.size();ri++) {
                        Resource r = resources.get(ri);
                        if (r.getBoundingBox().intersects(screen)) {
                            g.setColor(r.getColor());
                            g.fillRect((int)(r.getX()-center_x),(int)(r.getY()-center_y),(int)(r.getWidth()),(int)(r.getHeight()));
                            g.setColor(Color.BLACK);
                            g.drawRect((int)(r.getX()-center_x),(int)(r.getY()-center_y),(int)(r.getWidth()),(int)(r.getHeight()));
                            Font f = new Font("Courier New",Font.PLAIN,18);
                            g.setFont(f);
                            g.drawString(r.getType(), (int)(r.getX()-center_x), (int)(r.getY()-center_y+r.getHeight()+20));
                            if (r.getHealth()<r.getMax_Health()) {
                                g.setColor(Color.BLACK);
                                g.drawRect((int)(r.getX()-center_x)-1,(int)(r.getY()-center_y)-16,(int)r.getWidth()+1,11);
                                g.setColor(Color.RED);
                                g.fillRect((int)(r.getX()-center_x),(int)(r.getY()-center_y)-15,(int)r.getWidth(),10);
                                g.setColor(Color.GREEN);
                                g.fillRect((int)(r.getX()-center_x),(int)(r.getY()-center_y)-15,(int)(r.getHealth()/r.getMax_Health()*r.getWidth()),10);
                            }
                        }
                    }
                }
            }
            for (Building b : buildings) {
                if (b.getBoundingBox().intersects(screen)) {
                    g.setColor(b.getColor());
                    g.fillRect((int)(b.getX()-center_x),(int)(b.getY()-center_y),(int)(b.getWidth()),(int)(b.getHeight()));
                    g.setColor(Color.BLACK);
                    g.drawRect((int)(b.getX()-center_x),(int)(b.getY()-center_y),(int)(b.getWidth()),(int)(b.getHeight()));
                    Font f = new Font("Courier New",Font.PLAIN,15);
                    g.setFont(f);
                    String btitle="";
                    if (b.getType().equals("house")) {
                        btitle+=((House)(b)).getOwner()+"'s ";
                    }
                    g.drawString(btitle+b.getType(), (int)(b.getX()-center_x), (int)(b.getY()-center_y+b.getHeight()+20));
                }
            }
            for(AI a : aithread.getBots()) {
                if (a.getBoundingBox().intersects(screen)) {
                    g.setColor(a.getColor());
                    g.fillRect((int)(a.getX()-center_x),(int)(a.getY()-center_y),(int)(a.getWidth()),(int)(a.getHeight()));
                    g.setColor(Color.BLACK);
                    g.drawRect((int)(a.getX()-center_x),(int)(a.getY()-center_y),(int)(a.getWidth()),(int)(a.getHeight()));
                    Font f = new Font("Courier New",Font.PLAIN,18);
                    g.setFont(f);
                    g.drawString(a.getName(),(int)(a.getX()-center_x),(int)(a.getY()-center_y+a.getHeight()+20));
                }                
            }
            g.setColor(Color.BLACK);
            Font f = new Font("Courier New", Font.BOLD, 30);
            g.setFont(f);
            g.drawString("Gold: "+(int)(in.getGold()),40,40);
            g.drawString("center_x: " + center_x + "   center_y: " + center_y,300,40);
            g.setColor(player.getColor());
            g.fillOval(900,500,40,40);
            g.setColor(Color.BLACK);
            g.drawOval(900,500,40,40);
            Point mp = MouseInfo.getPointerInfo().getLocation();
            Point loc = frame.getLocationOnScreen();
            int[] fistCords = player.getFistCords((int)mp.getX()-(int)loc.getX(),(int)mp.getY()-(int)loc.getY());
            g.setColor(Color.WHITE);
            g.fillOval((int)mp.getX()-(int)loc.getX()-5,(int)mp.getY()-(int)loc.getY()-5,10,10);
            g.setColor(Color.BLACK);
            g.drawOval((int)mp.getX()-(int)loc.getX()-5,(int)mp.getY()-(int)loc.getY()-5,10,10);
            g.setColor(player.getFistColor());
            g.fillOval(fistCords[0],fistCords[1],10,10);
            g.fillOval(fistCords[2],fistCords[3],10,10);
            g.setColor(Color.BLACK);
            g.drawOval(fistCords[0],fistCords[1],10,10);
            g.drawOval(fistCords[2],fistCords[3],10,10);
            g.drawRect(899,479,41,11);
            g.setColor(Color.RED);
            g.fillRect(900,480,40,10);
            g.setColor(Color.GREEN);
            g.fillRect(900,480,(int)(player.getHealth()/100*40),10);
            g.setColor(Color.GRAY);
            g.fillRect(595,820,610,70);
            g.setColor(Color.BLACK);
            g.drawRect(595,820,610,70);
            Item[] hotbar = player.getHotbar();
            f = new Font("Courier New",Font.PLAIN,15);
            g.setFont(f);
            for(int i = 0; i < 10; i++){
                if(player.getHotBarItemSelected() == i){
                    g.setColor(Color.BLACK);
                }
                else{
                    g.setColor(Color.WHITE);
                }
                g.fillRect(605+i*60,830,50,50);
                if(player.getHotBarItemSelected() == i){
                    g.setColor(Color.WHITE);
                }
                else{
                    g.setColor(Color.BLACK);
                }
                if(hotbar[i] != null){
                    g.drawRect(605+i*60,830,50,50);
                    g.drawString(hotbar[i].getType(),610+i*60,850);
                    g.drawString("x" + hotbar[i].getQuantity(),610+i*60,865);
                }
            }
        }
        else if(view.equals("crafting")){
            Font f = new Font("Courier New",Font.PLAIN,60);
            g.setFont(f);
            g.setColor(Color.BLACK);
            g.drawString("Crafting",52,52);
            g.setColor(Color.WHITE);
            g.drawString("Crafting",50,50);
            g.setColor(Color.GRAY);
            g.fillRect(100,100,1600,800);
            g.setColor(Color.BLACK);
            g.drawRect(100,100,1600,800);
            f = new Font("Courier New",Font.PLAIN,15);
            g.setFont(f);
            for(Item e: craftableItems){
                g.setColor(Color.WHITE);
                g.fillRect(e.getCX(),e.getCY(),100,50);
                g.setColor(Color.BLACK);
                g.drawRect(e.getCX(),e.getCY(),100,50);
                g.drawString(e.getType(),e.getCX()+5,e.getCY()+15);
                for(int a = 0; a < e.getItemsRequired().size();a++){
                    g.drawString(e.getItemsRequired().get(a)+": x"+e.getNumOfItem().get(a),e.getCX()+5,e.getCY()+a*5+10+25);
                }
            }
            Point mp = MouseInfo.getPointerInfo().getLocation();
            Point loc = frame.getLocationOnScreen();
            g.setColor(Color.WHITE);
            g.fillOval((int)mp.getX()-(int)loc.getX()-5,(int)mp.getY()-(int)loc.getY()-5,10,10); 
            g.setColor(Color.BLACK);
            g.drawOval((int)mp.getX()-(int)loc.getX()-5,(int)mp.getY()-(int)loc.getY()-5,10,10);
        } else if (view.equals("dead")) {
            g.setColor(Color.RED);
            g.fillRect(0,0,1900,1000);
            Font f = new Font("Courier New",Font.BOLD,100);
            g.setFont(f);
            g.setColor(Color.BLACK);
            g.drawString("YOU DIED",650,400);
        }   
    }
    public void addBuilding(Building b) {
        buildings.add(b);
        if (b.getType().equals("house")) {
            House h = (House)(b);
            Human bot = new Human((int)(b.getX()),(int)(b.getY())+150,this,h);
            aithread.addBot(bot);
            h.setOwner(bot.getName());
            (new Thread(bot)).start();
        }
    }
    public void addResource(Resource r) {
        resources.add(r);
    }
    public void setCenterX(double x) {
        center_x=x;
    }
    public void setCenterY(double y) {
        center_y=y;
    }
    public double getCenterX() {
        return center_x;
    }
    public double getCenterY() {
        return center_y;
    }
    public void aPress() {
        a=true;
    }
    public void aRelease() {
        a=false;
    }
    public void dPress() {
        d=true;
    }
    public void dRelease() {
        d=false;
    }
    public void wPress() {
        w=true;
    }
    public void wRelease() {
        w=false;
    }
    public void sPress() {
        s=true;
    }
    public void sRelease() {
        s=false;
    }
    public void cPress(){
        if(view != "crafting"){
            view = "crafting";
        }
        else{
            view = "world";
        }
    }
    public void onePress(){
        player.setHotBarItemSelected(0);
    }
    public void twoPress(){
        player.setHotBarItemSelected(1);
    }
    public void threePress(){
        player.setHotBarItemSelected(2);
    }
    public void fourPress(){
        player.setHotBarItemSelected(3);
    }
    public void fivePress(){
        player.setHotBarItemSelected(4);
    }
    public void sixPress(){
        player.setHotBarItemSelected(5);
    }
    public void sevenPress(){
        player.setHotBarItemSelected(6);
    }
    public void eightPress(){
        player.setHotBarItemSelected(7);
    }
    public void ninePress(){
        player.setHotBarItemSelected(8);
    }
    public void zeroPress(){
        player.setHotBarItemSelected(9);
    }
    public void iPress(){
        if(view != "inventory"){
            view = "inventory";
        }
        else{
            view = "world";
        }
    }
    public void inventoryClick(int x,int y){
        for(Item e: player.getInventory()){
            if(new BoundingBox(e.getBX(),e.getBY(),e.getBWidth(),e.getBHeight()).intersects(new BoundingBox(x,y,1,1))){
                player.setHotBarItem(e);
            }
        }
    }
    public ArrayList<Building> getBuildings() {
        return buildings;
    }
    public ArrayList<Resource> getResources() {
        return resources;
    }
    public void mouseClick(double x, double y) {
        BoundingBox screen = new BoundingBox(center_x,center_y,width,height);
        //System.out.println("Mouse clicked at x:" + x + " and y:" + y);
        Resource objectHit = null;
        Building buildinghit = null;
        Point mp = MouseInfo.getPointerInfo().getLocation();
        Point loc = frame.getLocationOnScreen();
        int[] fistcords = player.getFistCords((int)mp.getX()-(int)loc.getX(),(int)mp.getY()-(int)loc.getY());
        int fistx=fistcords[2];
        int fisty=fistcords[3];
        if (player.getFists().getAnimate().isRightArm()) {
            fistx=fistcords[0];
            fisty=fistcords[1];
        }
        for(Biome b : biomes) {
            if (b.getBoundingBox().intersects(screen)) {
                resources=b.getResources();
                for (Resource r : resources) {
                    if (r.getBoundingBox().intersects(new BoundingBox(fistx+center_x,fisty+center_y,10,10))) {
                        objectHit = r;
                        //System.out.println("Punched object: " + r.getType());
                        //System.out.println("Display: " + r.getYield().getQuantity());
                        player.addItem(r.getYield());
                        //System.out.println("adding quantity of type " + r.getType() + " amount: " + r.getYield().getQuantity());
                    }
                }
            }
        }
        for (Building b : buildings) {
            if (b.getBoundingBox().intersects(new BoundingBox(fistx+center_x,fisty+center_y,10,10))) {
                buildinghit = b;
                //System.out.println("Punched building: " + b.getType());
            }
        }
        player.punch(objectHit,(int)x,(int)y);
    }
    public void craftingClick(int x,int y){
        System.out.print("craftingMouseClick()");
        for(Item i: craftableItems){
            if(hasResources(i) && new BoundingBox(i.getCX(),i.getCY(),100,50).intersects(new BoundingBox(x,y,1,1))){
                player.craft(i);
            }
        }
    }
    public boolean hasResources(Item i){
        for(int a = 0; a < i.getItemsRequired().size();a++){
            boolean contin = false;
            for(Item e: player.getInventory()){
                if(e.getType() == i.getItemsRequired().get(a) && e.getQuantity() >= i.getNumOfItem().get(a)){
                    contin = true;
                }
            }
            if(!contin){
                return false;
            }
        }
        return true;
    }
    public void addBiome(Biome b) {
        biomes.add(b);
        ArrayList<Resource> biomeResources = b.getResources();
        for(Resource r : biomeResources) {
            resources.add(r);
        }
    }
}