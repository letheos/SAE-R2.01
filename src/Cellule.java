import java.io.Serializable;
import java.util.HashMap;

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
}
