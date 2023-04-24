import java.lang.reflect.Array;
import java.util.ArrayList;

public class Labyrinthe {
    private int nx;
    private int ny;
    private ArrayList<ArrayList<Cellule>> cellules;

    //x correspond a la hauteur
    //y correspond a la droite et la gauche
    public Labyrinthe(int nx,int ny){
        this.nx= nx;
        this.ny = ny;
        this.cellules = new ArrayList<ArrayList<Cellule>>();
        int identifiant = 0;
        for(int x = 0;x<nx;x++){
            this.cellules.add(new ArrayList<Cellule>());
            for(int y = 0;y<ny;y++){
                this.cellules.get(x).add(new Cellule(identifiant,x,y));
                identifiant ++;
            }
        }

    }

    public ArrayList<ArrayList<Cellule>> GetCellules() {
        return cellules;
    }
    public Cellule GetCellule(int x,int y){
        return this.cellules.get(x).get(y);
    }
    public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("+");
    for (int i = 0; i < this.ny; i++) {
        sb.append("---+");
    }
    sb.append("\n");

    for (int i = 0; i < this.nx; i++) {
        sb.append("|");
        for (int j = 0; j < this.ny; j++) {
            if (this.cellules.get(i).get(j).murs.get("E")) {
                sb.append("   |");
            } else {
                sb.append("    ");
            }
        }
        sb.append("\n");

        sb.append("+");
        for (int j = 0; j < this.ny; j++) {
            if (this.cellules.get(i).get(j).murs.get("S")) {
                sb.append("---+");
            } else {
                sb.append("   +");
            }
        }
        sb.append("\n");
    }
    return sb.toString();
}
public void CasserMur(int x ,int y,String orientation){
        Cellule c = this.cellules.get(x).get(y);
        System.out.println("la cellule en question :"+c);
        if(c.murs.containsKey(orientation) == false){

            return;
        }
        if (orientation.equals("E") && y == this.ny-1){

            return;
        }
        if (orientation.equals("S") && x == this.nx-1){
            return;
        }
        if (orientation.equals("N") && x == 0){
            return;
        }
        if (orientation.equals("O") && y == 0){
            return;
        }
        c.murs.put(orientation,false);
        System.out.println("j'ai pété le mur");
        
        if (orientation.equals("N") && x > 0){
            this.cellules.get(x-1).get(y).murs.put("S",false);
            System.out.println("j'ai cassé le mur de la cellule ");
        }
        else if (orientation.equals("S") && x< this.nx-1) {
            this.cellules.get(x+1).get(y).murs.put("N",false);
            System.out.println("j'ai cassé le mur de la cellule ");
        }
        else if (orientation.equals("E") && x< this.ny-1) {
            this.cellules.get(x).get(y+1).murs.put("O",false);
            System.out.println("j'ai cassé le mur de la cellule ");
        }
        else if (orientation.equals("O") && y>0 ){
            this.cellules.get(x).get(y-1).murs.put("E",false);
            System.out.println("j'ai cassé le mur de la cellule ");
        }

}

}
