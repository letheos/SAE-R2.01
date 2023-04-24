public class Main {

    //x correspond a la hauteur
    //y correspond a la droite et la gauche
    public static void main(String[] args){
        Labyrinthe test;
        test = new Labyrinthe(5,5);
        System.out.println(test.GetCellules());
        System.out.println(test.GetCellule(2,2));
        test.CasserMur(2,2,"O");
        System.out.println(test.toString());

    }

}