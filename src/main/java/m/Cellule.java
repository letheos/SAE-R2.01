package m;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Cellule implements Serializable {
    private int identifiant;
    private int x;
    private int y;

    private Element élément;




    public Cellule(int identifiant,int x, int y,Element element){
        this.x  = x;
        this.y = y;
        this.identifiant = identifiant;
        this.élément = element;
    }

    public Element getÉlément() {
        return élément;
    }

    public void setÉlément(Element élément) {
        this.élément = élément;
    }
    public void manger(){
        this.élément = null;
    }
    public void repousser(){
        ArrayList<String> elements = new ArrayList<String>();
        elements.add("herbe");
        elements.add("herbe");
        elements.add("cactus");
        elements.add("margeurite");
        Random rand = new Random();
        int indexAleatoire = rand.nextInt(elements.size());
        String element = elements.get(indexAleatoire);
        if (element.equals("herbe")){
            this.élément = new Herbe();
        }
        else if (element.equals("cactus")){
            this.élément = new Cactus();
        }
        else if (element.equals("margeurite")) {
            this.élément = new marguerite();
        }
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public int manathan(Cellule destination){
        return (int)Math.sqrt(Math.pow(destination.getX() - this.x, 2) + Math.pow(destination.getY() - this.y, 2));
    }
}
