import java.util.HashMap;

public class Mouton extends Animaux {
    private boolean fuite;
     private HashMap<String, Integer> mange;
    public Mouton(Cellule cellule){
        super(cellule);
        this.mange = new HashMap<String,Integer>();

    }

    public boolean isFuite() {
        return fuite;
    }

    public HashMap<String, Integer> getMange() {
        return mange;
    }
    //loink 04/05/23
    public void manger(){
        int m = this.mange.get(this.getCellule().getElement().toString());
        mange.put(this.getCellule().getElement().toString(),m+1);
    }

}
