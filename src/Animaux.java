import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Animaux implements Serializable {
    private Cellule position;




    public Animaux(Cellule cellule){
        this.position= cellule;




    }
    public int getX(){
        return this.position.getX();
    }
    public int getY(){
        return this.position.getY();
    }
    public Cellule getPosition(){
        return this.position;
    }
    public Cellule getCellule(){
        return this.position;
    }
    public Cellule errer(ArrayList<Cellule> a){
        Random random = new Random();
        int choix = random.nextInt(a.size());
        return a.get(choix);

    }

}
