import java.io.Serializable;
import java.rmi.MarshalException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Cellule implements Serializable {
    private int identifiant;
    private int x;
    private int y;

    private Element element;




    public Cellule(int identifiant,int x, int y,Element element){
        this.x  = x;
        this.y = y;
        this.identifiant = identifiant;
        this.element = element;
    }

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }
    public void manger(){
        this.element = null;
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
            this.element = new Herbe();
        }
        else if (element.equals("cactus")){
            this.element = new Cactus();
        }
        else if (element.equals("margeurite")) {
            this.element = new marguerite();
        }
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
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
}
