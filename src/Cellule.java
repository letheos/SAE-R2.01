import java.util.HashMap;

public class Cellule {
    private int identifiant;
    private int x;
    private int y;


    HashMap<String,Boolean> murs = new HashMap<>();

    public Cellule(int identifiant,int x, int y){
        this.x  = x;
        this.y = y;
        this.identifiant = identifiant;
        this.murs.put("N",true);
        this.murs.put("S",true);
        this.murs.put("E",true);
        this.murs.put("O",true);
    }

}
