import java.io.IOException;
import java.util.ArrayList;

public class Main {

    //x correspond a la hauteur
    //y correspond a la droite et la gauche
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Labyrinthe test;
        test = new Labyrinthe(10,10);


        System.out.println(test.GetCellules());
        System.out.println(test.GetCellule(2,2));
        Loup loup = new Loup(test.GetCellule(1,1));
        test.setLoup(loup);
        Mouton mouton = new Mouton(test.GetCellule(8,8));
        test.setMouton(mouton);
        System.out.println(test.toString());

        test.CasserMur(4,0);
        test.PoserCactus(3,3);
        test.PoserMargueurite(2,3);
        test.CasserMur(0,3);
        test.PoserMargueurite(1,4);
        test.GetCellule(3,3).setElement(null);
        System.out.println(test.toString());
        test.sauvegarderLabyrinthe("labyrintheprefait.dat");
        Labyrinthe récup = Labyrinthe.chargerLabyrinthe("labyrintheprefait.dat");
        mouton.getMange();
        mouton.getMange().toString();
        Cellule t = mouton.getCellule();

        ArrayList<Cellule> ar = test.getVoisins(t);
        Cellule b = mouton.errer(ar);
        mouton.
        System.out.println(récup.toString());



        récup.DefinirSortie(9,1);
        System.out.println(récup.toString());
        System.out.println("les voisins sont "+récup.getVoisins(récup.GetCellule(8,1)));
        //System.out.println("les voisins sont"+récup.getVoisins(8,8));
        //System.out.println("les voisins sont"+récup.getVoisins(4,4));




        }
    }

