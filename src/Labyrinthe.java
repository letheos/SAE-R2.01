import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Labyrinthe implements Serializable {
    private int nx;
    private int ny;
    private ArrayList<ArrayList<Cellule>> cellules;

    //x correspond a la hauteur
    //y correspond a la droite et la gauche
    public Labyrinthe(int nx, int ny) {
    this.nx = nx;
    this.ny = ny;
    this.cellules = new ArrayList<ArrayList<Cellule>>();
    int identifiant = 0;

    // Création des cellules
    for (int x = 0; x < nx; x++) {
        this.cellules.add(new ArrayList<Cellule>());
        for (int y = 0; y < ny; y++) {
            // Vérification si la cellule est sur un bord
            boolean isBorderCell = x == 0 || y == 0 || x == nx - 1 || y == ny - 1;
            if (isBorderCell) {
                // Si la cellule est sur un bord, on la crée comme étant un mur
                this.cellules.get(x).add(new Cellule(identifiant, x, y, new Mur()));
            } else {
                // Sinon, on la crée comme étant de l'herbe
                this.cellules.get(x).add(new Cellule(identifiant, x, y, new Herbe()));
            }
            identifiant++;
        }
    }
}

public String toString() {
    StringBuilder sb = new StringBuilder();

    // Dessiner la première ligne avec des barres horizontales en haut
    sb.append("+");
    for (int i = 0; i < nx; i++) {
        sb.append("---+");
    }
    sb.append("\n");

    // Dessiner les lignes du milieu
    for (int y = 0; y < ny; y++) {
        sb.append("|");
        for (int x = 0; x < nx; x++) {
            Cellule cell = cellules.get(x).get(y);
            if (cell.getÉlément() instanceof Mur) {
                sb.append("###|");
            }
            else if (cell.getÉlément() instanceof Cactus){
                sb.append("/*/|");
            }
            else if (cell.getÉlément() instanceof marguerite){
                sb.append("!!!|");
            }
            else {
                sb.append("   |");
            }
        }
        sb.append("\n");

        // Dessiner une ligne avec des barres horizontales entre chaque cellule
        sb.append("+");
        for (int i = 0; i < nx; i++) {
            sb.append("---+");
        }
        sb.append("\n");
    }

    return sb.toString();
}



    public ArrayList<ArrayList<Cellule>> GetCellules() {
        return cellules;
    }
    public Cellule GetCellule(int x,int y){
        return this.cellules.get(x).get(y);
    }


    //ajout 11:25 le 25/04/2023
    //ajout des deux fonctions le 25/04/2023 à 13:13
public void sauvegarderLabyrinthe(String nomFichier) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomFichier));
        oos.writeObject(this);
        oos.close();
    }

    public static Labyrinthe chargerLabyrinthe(String nomFichier) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomFichier));
        Labyrinthe labyrinthe = (Labyrinthe) ois.readObject();
        ois.close();
        return labyrinthe;
    }


    //ajout des fonctions CasserMur,PoserMargueurite,PoserCactus et PoserHerbe à 14:43 le 28/04/2023 par theos
    public boolean CasserMur(int x /*droite*/, int y/*hauteur*/){
        if (this.cellules.get(x).get(y).getÉlément() instanceof Mur){
            this.cellules.get(x).get(y).setÉlément(new Herbe());
            return true;
        }
        System.out.println("Ceci n'est pas un mur");
        return false;
    }
    public boolean PoserMargueurite(int x,int y){
        if(this.cellules.get(x).get(y).getÉlément() instanceof Mur){
            System.out.println("ce n'est pas possible de poser une plante sur un mur");
            return false;
        }
        this.cellules.get(x).get(y).setÉlément(new marguerite());
        return true;
    }
    public boolean PoserCactus(int x,int y){
        if(this.cellules.get(x).get(y).getÉlément() instanceof Mur){
            System.out.println("ce n'est pas possible de poser une plante sur un mur");
            return false;
        }
        this.cellules.get(x).get(y).setÉlément(new Cactus());
        return true;
    }
    public boolean PoserHerbe(int x,int y){
        if(this.cellules.get(x).get(y).getÉlément() instanceof Mur){
            System.out.println("ce n'est pas possible de poser une plante sur un mur");
            return false;
        }
        this.cellules.get(x).get(y).setÉlément(new Herbe());
        return true;
    }


}
