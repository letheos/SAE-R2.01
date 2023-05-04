import java.util.HashMap;

public class Mouton extends Animaux {
    private boolean fuite;
     private HashMap<String, Integer> mangés;
    public Mouton(Cellule cellule){
        super(cellule);
         this.mangés = new HashMap<String,Integer>();
        this.mangés.put("Herbe",0);
        this.mangés.put("Cactus",0);
        this.mangés.put("margeurite",0);
    }

}
