import java.io.IOException;

public class Main {

    //x correspond a la hauteur
    //y correspond a la droite et la gauche
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Labyrinthe test;
        test = new Labyrinthe(10,10);
        System.out.println(test.GetCellules());
        System.out.println(test.GetCellule(2,2));

        System.out.println(test.toString());
        test.CasserMur(4,0);
        test.PoserCactus(3,3);
        test.PoserMargueurite(2,3);
        test.CasserMur(0,3);
        test.PoserMargueurite(1,4);
        System.out.println(test.toString());
        //test.sauvegarderLabyrinthe("labyrinthetest.dat");
        //Labyrinthe récup = Labyrinthe.chargerLabyrinthe("labyrinthetest.dat");
        //System.out.println(récup.toString());

    }

}