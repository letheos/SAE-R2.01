import java.io.IOException;

public class Main {

    //x correspond a la hauteur
    //y correspond a la droite et la gauche
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Labyrinthe test;
        test = new Labyrinthe(5,5);
        System.out.println(test.GetCellules());
        System.out.println(test.GetCellule(2,2));
        test.CasserMur(2,2,"O");
        test.GetCellule(0,0).murs.put("E",true);
        System.out.println(test.toString());

        test.sauvegarderLabyrinthe("labyrinthetest.dat");
        Labyrinthe récup = Labyrinthe.chargerLabyrinthe("labyrinthetest.dat");
        System.out.println(récup.toString());
    }

}