package m;

import m.Labyrinthe;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.*;

public class Animaux implements Serializable {
    private int portée;
    private Cellule position;




    public Animaux(Cellule cellule){
        this.portée = 4;
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

    public void setPosition(Cellule position) {
        this.position = position;

    }

    public void errer(Animaux animal ,Labyrinthe labyrinthe){
        ArrayList<Cellule> voisins = labyrinthe.getVoisins(this.position);
        if (voisins.size() == 0){
            System.out.println("aucun voisin");
            return;
        }
        System.out.println(voisins);
        ArrayList éléments = new ArrayList();
        for(int x = 0;x< voisins.size();x++){
            éléments.add(voisins.get(x).getÉlément());
        }
        System.out.println(éléments);
        Random random = new Random();
        int choix = random.nextInt(voisins.size());
        Cellule nouvCellule = voisins.get(choix);
        animal.setPosition(nouvCellule);
        //todo finir errer en choisissant aléatoirement la direction qui est récupérée et gérer ça dans le labyrinthe
    }
    /*
    public Boolean vision(Labyrinthe laby) {
        if (this instanceof Loup){
            if (laby.dijkstra(this.getPosition(),4).contains(laby.getMouton())) {
            return true;
    }
            else {
                return false;
            }
        } else if (this instanceof Mouton) {
            if (laby.dijkstra(this.getPosition(),4).contains(laby.getLoup())){
                return true;
            }
            else{
                return false;
            }

        }

        return false;
}

     */


}
