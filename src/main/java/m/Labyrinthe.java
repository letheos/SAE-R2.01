package m;

import javafx.scene.control.Cell;
import m.*;

import java.awt.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class Labyrinthe implements Serializable {
    private int nx;
    private int ny;
    private Mouton mouton;
    private Loup loup;
    private ArrayList<ArrayList<Cellule>> cellules;

    private Cellule sortie;

    //x correspond à la hauteur
    //y correspond à la droite et la gauche
    public Labyrinthe(int nx, int ny) {
        this.nx = nx;
        this.ny = ny;
        this.cellules = new ArrayList<ArrayList<Cellule>>();
        int identifiant = 0;
        this.sortie = null;

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

    public Labyrinthe() {
        this.nx = 4;
        this.ny = 4;
        this.cellules = new ArrayList<>();
        int identifiant = 0;
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
    public Labyrinthe(Labyrinthe lab){
        this.nx = lab.getNx();
        this.ny = lab.getNy();
        this.cellules = lab.GetCellules();
        this.mouton = lab.getMouton();
        this.loup = lab.getLoup();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        // Dessiner la première ligne avec des barres horizontales en haut
        sb.append("+");
        for (int i = 0; i < ny; i++) {
            sb.append("---+");
        }
        sb.append("\n");

        // Dessiner les lignes du milieu
        for (int x = 0; x < nx; x++) {
            sb.append("|");
            for (int y = 0; y < ny; y++) {
                Cellule cell = cellules.get(x).get(y);
                if (mouton != null && x == mouton.getX() && y == mouton.getY()) {
                    sb.append(" M |");
                } else if (loup != null && x == loup.getX() && y == loup.getY()) {
                    sb.append(" L |");
                } else if (cell.getÉlément() == null) {
                    sb.append("Ter|");
                } else if (cell.getÉlément() instanceof Mur) {
                    sb.append("###|");
                } else if (cell.getÉlément() instanceof Cactus) {
                    sb.append("/*/|");
                } else if (cell.getÉlément() instanceof marguerite) {
                    sb.append("!!!|");
                } else {
                    sb.append("   |");
                }
            }
            sb.append("\n");

            // Dessiner une ligne avec des barres horizontales entre chaque cellule
            sb.append("+");
            for (int i = 0; i < ny; i++) {
                sb.append("---+");
            }
            sb.append("\n");
        }

        return sb.toString();
    }


    public ArrayList<ArrayList<Cellule>> GetCellules() {
        return cellules;
    }

    public Cellule GetCellule(int x, int y) {
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
    public boolean CasserMur(int x /*droite*/, int y/*hauteur*/) {
        if (x == 0 || x == this.nx - 1) {
            System.out.println("ce mur n'est pas destructible il est en bord de carte");
            return false;
        }

        if (y == 0 || y == (this.ny - 1)) {
            System.out.println("ce mur n'est pas destructible il est en bord de carte");
            return false;
        }
        if (this.cellules.get(x).get(y).getÉlément() instanceof Mur) {
            this.cellules.get(x).get(y).setÉlément(new Herbe());
            return true;
        }
        System.out.println("Ceci n'est pas un mur");
        return false;
    }

    public boolean PoserMargueurite(int x, int y) {
        if (this.cellules.get(x).get(y).getÉlément() instanceof Mur) {
            System.out.println("ce n'est pas possible de poser une plante sur un mur");
            return false;
        }
        this.cellules.get(x).get(y).setÉlément(new marguerite());
        return true;
    }

    public boolean PoserCactus(int x, int y) {
        if (this.cellules.get(x).get(y).getÉlément() instanceof Mur) {
            System.out.println("ce n'est pas possible de poser une plante sur un mur");
            return false;
        }
        this.cellules.get(x).get(y).setÉlément(new Cactus());
        return true;
    }

    public boolean PoserHerbe(int x, int y) {
        if (this.cellules.get(x).get(y).getÉlément() instanceof Mur) {
            System.out.println("ce n'est pas possible de poser une plante sur un mur");
            return false;
        }
        this.cellules.get(x).get(y).setÉlément(new Herbe());
        return true;
    }

    //ajout des fonctions Définir Sortie et PoserMur le 29/04/2023 par letheos à 19:56
    public boolean PoserMur(int x, int y) {
        if (this.cellules.get(x).get(y).getÉlément() instanceof Mur) {
            System.out.println("ce n'est pas possible il y'a déja un mur");
            return false;
        }
        this.cellules.get(x).get(y).setÉlément(new Mur());

        return true;
    }

    public boolean DéfinirSortie(int x, int y) {
        if (this.sortie != null) {
            System.out.println("la sortie est déja définie ");
            return false;
        }
        if ((x == 0 && y == 0) || (x == 0 && y == this.ny - 1) || (x == this.nx - 1 && y == 0) || (x == this.nx - 1 && y == this.ny - 1)) {
            System.out.println("un coin n'est pas valable");
            return false;
        }
        if (cellules.get(x).get(y).getÉlément() instanceof Végétal) {
            System.out.println("cette case n'est pas valide");
            return false;
        } else {
            System.out.println("la sortie a été crée ");
            this.cellules.get(x).get(y).setÉlément(new Herbe());
            this.sortie = this.cellules.get(x).get(y);
            return true;
        }
    }


    //x = droite
    //y = hauteur
    //liste de boolean en format [N,S,E,O] pour indiquer si l'animal peut se déplacer dans une direction ou non
    public ArrayList<Cellule> getVoisins(Cellule cellule) {
        ArrayList<Cellule> voisins = new ArrayList<>();
        int x = cellule.getX();
        int y = cellule.getY();
        if (!(this.GetCellule(x - 1, y).getÉlément() instanceof Mur)) {

            voisins.add(this.GetCellule(x - 1, y));
        }
        if (!(this.GetCellule(x + 1, y).getÉlément() instanceof Mur)) {

            voisins.add(this.GetCellule(x + 1, y));
        }
        if (!(this.GetCellule(x, y - 1).getÉlément() instanceof Mur)) {

            voisins.add(this.GetCellule(x, y - 1));
        }
        if (!(this.GetCellule(x, y + 1).getÉlément() instanceof Mur)) {

            voisins.add(this.GetCellule(x, y + 1));

        }
        return voisins;




        /*if (this.cellules.get(cellule.getX() - 1).get(cellule.getY()).getÉlément() instanceof Végétal || this.cellules.get(cellule.getX() - 1).get(cellule.getY()).getÉlément() == null) {
            System.out.println("la cellule au dessus de "+cellule.getX()+","+cellule.getY()+"est  la cellule"+this.GetCellule(cellule.getX()-1, cellule.getY()).getX()+","+this.GetCellule(cellule.getX()-1, cellule.getY()).getY());
            voisins.add(this.cellules.get(cellule.getX() - 1).get(cellule.getY()));
        }
        if (this.cellules.get(cellule.getX() + 1).get(cellule.getY()).getÉlément() instanceof Végétal || this.cellules.get(cellule.getX() + 1).get(cellule.getY()).getÉlément() == null) {
            System.out.println("la cellule en dessous de "+cellule.getX()+","+cellule.getY()+"est  la cellule"+this.GetCellule(cellule.getX()+1, cellule.getY()).getX()+","+this.GetCellule(cellule.getX()+1, cellule.getY()).getY());
            voisins.add(this.cellules.get(cellule.getX() + 1).get(cellule.getY()));
        }
        if (this.cellules.get(cellule.getX()).get(cellule.getY() + 1).getÉlément() instanceof Végétal || this.cellules.get(cellule.getX()).get(cellule.getY() + 1).getÉlément() == null) {
            System.out.println("la cellule a droite de "+cellule.getX()+","+cellule.getY()+"est  la cellule"+this.GetCellule(cellule.getX(), cellule.getY()+1).getX()+","+this.GetCellule(cellule.getX(), cellule.getY()+1).getY());
            voisins.add(this.cellules.get(cellule.getX() + 1).get(cellule.getY()+1));
        }
        if (this.cellules.get(cellule.getX()).get(cellule.getY() - 1).getÉlément() instanceof Végétal ||this.cellules.get(cellule.getX()).get(cellule.getY() - 1).getÉlément() == null ) {
            System.out.println("la cellule a gauche de "+cellule.getX()+","+cellule.getY()+"est  la cellule"+this.GetCellule(cellule.getX(), cellule.getY()-1).getX()+","+this.GetCellule(cellule.getX(), cellule.getY()-1).getY());
            voisins.add(this.cellules.get(cellule.getX()).get(cellule.getY() - 1));
        }
        return voisins;*/
    }

    public void setLoup(Loup loup) {
        this.loup = loup;
    }

    public void setMouton(Mouton mouton) {
        this.mouton = mouton;
    }

    public Mouton getMouton() {
        return this.mouton;
    }

    public Loup getLoup() {
        return loup;
    }

    public int getNx() {
        return this.nx;
    }

    public int getNy() {
        return this.ny;
    }

    public Cellule getSortie() {
        return this.sortie;
    }
    public Labyrinthe getLab(){
        return this;
    }

    public void setSortie(Cellule cellule) {
        this.sortie = cellule;
    }


    public void setNx(int nx) {
        this.nx = nx;
    }

    public void setNy(int ny) {
        this.ny = ny;
    }

    public void sauvegarde(String fichier) {
        StringBuilder sb = new StringBuilder();
        //ArrayList<Object> sauv = new ArrayList<>();
        for (int i = 0; i < cellules.size(); i++) {
            //ArrayList<String> ligne = new ArrayList<>();

            for (int k = 0; k < cellules.get(i).size(); k++) {
                if (mouton != null && cellules.get(i).get(k).getX() == mouton.getX() && cellules.get(i).get(k).getY() == mouton.getY()) {
                    //si les coordonnnées du mouton sont les mêmes que celles de cettes cellules, on le met dans la liste
                    sb.append("m");
                } else if (loup != null && cellules.get(i).get(k).getX() == loup.getX() && cellules.get(i).get(k).getY() == loup.getY()) {
                    //si les coordonnnées du mouton sont les mêmes que celles de cettes cellules, on le met dans la liste
                    sb.append("l");
                } else if (i == 0 || i == cellules.size() - 1) {
                    if (cellules.get(i).get(k).getÉlément() instanceof Herbe) {
                        sb.append("s");
                        //Si l'élément est sur les bord du labyrinthe on vérifie si c'est de l'herbe est donc la sortie
                    } else if (cellules.get(i).get(k).getÉlément() instanceof marguerite) {
                        sb.append("s");
                    } else if (cellules.get(i).get(k).getÉlément() instanceof Cactus) {
                        sb.append("S");
                    } else {
                        sb.append("x");
                    }

                } else if (k == 0 || k == cellules.get(i).size()) {
                    if (cellules.get(i).get(k).getÉlément() instanceof Herbe) {
                        sb.append("S");
                        //Si l'élément est sur les bord du labyrinthe on vérifie si c'est de l'herbe est donc la sortie
                    } else if (cellules.get(i).get(k).getÉlément() instanceof Mur) {
                        sb.append("x");
                        //si l'élément est un mur, on met x dans la liste
                    }

                } else if (cellules.get(i).get(k).getÉlément() instanceof Mur) {
                    sb.append("x");
                    //si l'élément est un mur, on met x dans la liste
                } else if (cellules.get(i).get(k).getÉlément() instanceof Herbe) {
                    sb.append("h");
                    //si l'élément est de l'herbe, on met h dans la liste
                } else if (cellules.get(i).get(k).getÉlément() instanceof Cactus) {
                    sb.append("c");
                    //si l'élément est un cactus, on met c dans la liste
                } else if (cellules.get(i).get(k).getÉlément() instanceof marguerite) {
                    sb.append("f");
                    //si l'élément est une marguerite, on met f dans la liste
                } else if (cellules.get(i).get(k).getÉlément().equals(null)) {
                    sb.append("t");
                    //Si la cellule qu'on traite à la les coordonnées du monton alors on met le mouton

                }

            }
            sb.append("\n");
            //ajout d'un string pour passer la ligne

        }
        try {
            FileWriter fw = new FileWriter(fichier + ".txt", StandardCharsets.US_ASCII);
            //fileWriter permet d'écrire dans un fichier en lui donnant un chemin et un codage,
            //le codage en utf8 est pour éviter d'avoir des caractères chinois
            fw.write(sb.toString());

            fw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }


    }

    public void PlaceAleatoire(int hauteur, int largeur) {
        ArrayList<String> t = new ArrayList<>();
        t.add("m");
        t.add("h");
        t.add("h");
        t.add("h");
        t.add("h");
        t.add("f");
        t.add("f");
        t.add("c");
        t.add("c");
        t.add("h");
        Random random = new Random();
        int caisseCsGo = random.nextInt(t.size());

        if (t.get(caisseCsGo) == "c") {
            this.GetCellule(hauteur,largeur).setÉlément(new Cactus());
        } else if (t.get(caisseCsGo) == "h") {
            this.GetCellule(hauteur,largeur).setÉlément(new Herbe());
        } else if (t.get(caisseCsGo) == "f") {
            this.GetCellule(hauteur,largeur).setÉlément(new marguerite());
        } else {
            this.GetCellule(hauteur,largeur).setÉlément(new Mur());
        }if (this.loup.getX() == hauteur && this.loup.getY() == largeur){
            this.GetCellule(hauteur,largeur).setÉlément(new Herbe());

        }if (this.mouton.getX() == hauteur && this.mouton.getY() == largeur){
            this.GetCellule(hauteur,largeur).setÉlément(new Herbe());

        }
    }

    public void PlaceAleatoire2(int hauteur, int largeur, int probaM, int probaH, int probaF, int probaC) {
        ArrayList<String> t = new ArrayList<>();
        for (int m = 0; m < probaM; m++) {
            t.add("m");
        }
        for (int h = 0; h < probaH; h++) {
            t.add("h");
        }
        for (int f = 0; f < probaF; f++) {
            t.add("f");
        }
        for (int c = 0; c < probaC; c++) {
            t.add("c");
        }
        Random random = new Random();
        int caisseCsGo = random.nextInt(t.size());
        if (t.get(caisseCsGo) == "c") {
            this.PoserCactus(hauteur, largeur);
        } else if (t.get(caisseCsGo) == "h") {
            this.PoserHerbe(hauteur, largeur);
        } else if (t.get(caisseCsGo) == "f") {
            this.PoserMargueurite(hauteur, largeur);
        } else {
            this.PoserMur(hauteur, largeur);
        }
    }

    public void effaceTout() {

        for (int i = 0; i < cellules.size(); i++) {
            for (int j = 0; j < cellules.get(i).size(); j++) {
                this.PoserHerbe(i, j);
            }
        }
        if (this.loup == null) {
            this.setLoup(new Loup(cellules.get(this.nx - 3).get(this.ny - 3)));
        } else {
            this.loup.setPosition(cellules.get(this.getNx() - 3).get(this.getNy() - 3));
        }
        if (this.mouton == null) {
            this.setMouton(new Mouton(cellules.get(1).get(2)));
        } else {
            this.mouton.setPosition(cellules.get(1).get(2));
        }
    }

    public void effaceTout2(int x, int y) {
        setNy(y);
        setNx(x);

        for (int i = 0; i < cellules.size(); i++) {
            for (int j = 0; j < cellules.get(i).size(); j++) {
                this.PoserHerbe(i, j);
            }
        }
        if (this.loup == null) {
            this.setLoup(new Loup(cellules.get(this.nx - 3).get(this.ny - 3)));
        } else {
            this.loup.setPosition(cellules.get(this.getNx() - 3).get(this.getNy() - 3));
        }
        if (this.mouton == null) {
            this.setMouton(new Mouton(cellules.get(1).get(2)));
        } else {
            this.mouton.setPosition(cellules.get(1).get(2));
        }
    }

    public void aleatoire() {
        this.effaceTout();
        for (int i = 1; i < cellules.size() - 1; i++) {
            for (int j = 1; j < cellules.get(i).size() - 1; j++) {
                this.PlaceAleatoire(i, j);
            }
        }

        this.PoserHerbe(1, 1);
        this.setMouton(new Mouton(cellules.get(1).get(1)));
        this.PoserHerbe(this.getNx() - 2, this.getNy() - 2);
        this.setLoup(new Loup(cellules.get(this.getNx() - 3).get(this.getNy() - 2)));
        this.DéfinirSortie(0, 1);
    }

    public String recup(String fichier) {
        StringBuilder recup = new StringBuilder();

        try {
            FileInputStream input = new FileInputStream(fichier);
            //ouvre un flux de lecture vers un fichier donné
            InputStreamReader redar = new InputStreamReader(input);
            //convertit les octets lus en caractères
            BufferedReader bufRead = new BufferedReader(redar);
            //met en mémoire tampon les caractères du flux d'entrée
            String ligne;
            while ((ligne = bufRead.readLine()) != null) {
                recup.append(ligne);
                //on ajoute à une ligne à chaque boucle du while
                recup.append("\n");
                //contenuFichier.add(elt.add(ligne));


                //contenuFichier.add(elt);


            }

            bufRead.close();
        } catch (IOException e) {
            System.out.println("Erreur lors de la lecture du fichier : " + e.getMessage());
        }
        return recup.toString();
    }

    public ArrayList<Object> GetElements() {
        ArrayList<Object> elements = new ArrayList<>();

        ArrayList<ArrayList<Cellule>> cellules = GetCellules(); // Appel à la méthode GetCellules()

        for (ArrayList<Cellule> ligne : cellules) {
            for (Cellule cellule : ligne) {
                Object element = cellule.getÉlément(); // Utilisation de la méthode GetElement()
                elements.add(element);
            }
        }

        return elements;
    }
    public ArrayList<Integer> TrouveSortie(String laby){
        String[] deligne = laby.split("\n");
        ArrayList<Integer> co = new ArrayList<>();
        int hauteur = deligne.length;
        int largueur = deligne[0].length();
        for(int i =0; i < hauteur-1; i++){
            for(int j = 0 ;j< deligne[i].length();j++){
                char c = deligne[i].charAt(j);
                String str = Character.toString(c);
                if(str == "s"){
                    co.add(i);
                    co.add(j);
                    return co;
                }

            }
        }
        return co;
    }

    public Labyrinthe recupToLaby(String laby) {
        String[] deligne = laby.split("\n");

        int hauteur = deligne.length;
        int largueur = deligne[0].length();


        Labyrinthe lab = new Labyrinthe(hauteur, largueur);
        lab.setLoup(new Loup(lab.GetCellule(1, 1)));
        lab.setMouton(new Mouton(lab.GetCellule(lab.nx - 2, lab.ny - 2)));


        for (int h = 0; h < hauteur-1; h++) {
            System.out.println(hauteur-1);
            System.out.println(hauteur);
            System.out.println(largueur);
            System.out.println("\n");

            System.out.println(lab.nx);

            System.out.println(lab.ny);






            int lar = 0;
            for (int j = 0; j < deligne[h].length()-1; j++) {


                if (j == deligne[h].length()) {
                    break;
                }

                char c = deligne[h].charAt(j);
                String str = Character.toString(c);
                if (str.equals("x") && lab.cellules.get(h).get(j).getÉlément() instanceof Mur) {
                    System.out.println("mur");


                } else if (str.equals("x") && !(lab.cellules.get(h).get(j).getÉlément() instanceof Mur)) {
                    lab.PoserMur(j, h);
                } else if (str.equals("f")) {
                    lab.PoserMargueurite(j, h);
                } else if (str.equals("h")) {
                    lab.PoserHerbe(j, h);
                } else if (str.equals("c")) {
                    lab.PoserCactus(j, h);
                } else if (str.equals("T")) {
                    lab.GetCellule(j, h).setÉlément(null);
                } else if (str.equals("l")) {
                    //this.setLoup(new Loup(this.cellules.get(h).get(lar)));
                    lab.PoserHerbe(h, j);
                    lab.getLoup().setPosition(lab.GetCellules().get(h).get(j));
                    //on place sur le labyrinthe un nouveau loup à la position
                } else if (str.equals("m")) {
                    lab.PoserHerbe(h, j);
                    lab.getMouton().setPosition(lab.GetCellules().get(h).get(j));
                    //on place sur le labyrinthe un nouveau Mouton à la position
                } else if (str.equals("s")) {
                    lab.DéfinirSortie(h, j);
                    lab.setSortie(lab.GetCellules().get(h).get(j));
                }

            }


        }
        System.out.println(lab.toString());
        return lab;


    }

}




