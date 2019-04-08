import java.util.*;
public class Map {
    ArrayList<Biome> biomes;
    boolean generated;
    public Map() {
        generated=false;
        biomes = new ArrayList<Biome>();
        long randseed = (int)(Math.random()*100)*(int)(Math.random()*100)*(int)(Math.random()*100);
        Random generator = new Random(randseed);
        for(int x=-40000;x<=40000;x+=3000) {
            for(int y=-40000;y<=40000;y+=3000) {
                int choice=(int)(generator.nextDouble()*6);
                String type="";
                if (choice==0) {
                    type="winter";
                } else if (choice==1) {
                    type="forest";
                } else if (choice==2) {
                    type="desert";
                } else if (choice==3) {
                    type="rocky";
                } else if (choice==4) {
                    type="jungle";
                } else if (choice==5) {
                    type="plains";
                }
                biomes.add(new Biome(type,x,y,randseed));
                try {
                    Thread.sleep(1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        generated=true;
    }
    public Map(String seed) {
        generated=false;
        biomes = new ArrayList<Biome>();
        Random generator = new Random(Long.parseLong(seed));
        for(int x=-40000;x<=40000;x+=3000) {
            for(int y=-40000;y<=40000;y+=3000) {
                int choice=(int)(generator.nextDouble()*6);
                String type="";
                if (choice==0) {
                    type="winter";
                } else if (choice==1) {
                    type="forest";
                } else if (choice==2) {
                    type="desert";
                } else if (choice==3) {
                    type="rocky";
                } else if (choice==4) {
                    type="jungle";
                } else if (choice==5) {
                    type="plains";
                }
                biomes.add(new Biome(type,x,y,Long.parseLong(seed)));
                try {
                    //Thread.sleep(1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        generated=true;
    }
    public ArrayList<Biome> getBiomes() {
        while(!generated) {}
        return biomes;
    }
}