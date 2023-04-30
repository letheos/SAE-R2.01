import java.io.IOException;

public class Main {

    //x correspond a la hauteur
    //y correspond a la droite et la gauche
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Labyrinthe test;
        test = new Labyrinthe(10,10);


        System.out.println(test.GetCellules());
        System.out.println(test.GetCellule(2,2));
        Loup loup = new Loup(8,8);
        test.setLoup(loup);
        Mouton mouton = new Mouton(1,1);
        test.setMouton(mouton);
        System.out.println(test.toString());
        test.CasserMur(4,0);
        test.PoserCactus(3,3);
        test.PoserMargueurite(2,3);
        test.CasserMur(0,3);
        test.PoserMargueurite(1,4);
        System.out.println(test.toString());
        test.sauvegarderLabyrinthe("labyrintheprefait.dat");
        Labyrinthe récup = Labyrinthe.chargerLabyrinthe("labyrintheprefait.dat");
        System.out.println(récup.toString());
        int babouin_compteur = 0;
        for(int x = 0;x<10;x++){
            for (int y=0;y<10;y++){
                if (récup.DéfinirSortie(x,y) == true){
                    babouin_compteur +=1;
                }
            }
        }
        System.out.println(babouin_compteur);
        récup.DéfinirSortie(9,1);
        System.out.println(récup.toString());
        System.out.println("les voisins sont "+récup.getVoisins(8,1));
        //System.out.println("les voisins sont"+récup.getVoisins(8,8));
        //System.out.println("les voisins sont"+récup.getVoisins(4,4));




        }
    }

