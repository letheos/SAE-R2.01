package com.example.fx_sae;

import javafx.scene.control.Cell;
import m.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class Labyrinthe implements Serializable {
    private int nx;
    private int ny;
    private Mouton mouton;
    private Loup loup;
    private ArrayList<ArrayList<Cellule>> cellules;

    private boolean sortie;

    //x correspond à la hauteur
    //y correspond à la droite et la gauche
    public Labyrinthe(int nx, int ny) {
        this.nx = nx;
        this.ny = ny;
        this.cellules = new ArrayList<ArrayList<Cellule>>();
        int identifiant = 0;
        this.sortie = false;

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
    public Labyrinthe(){
        this.nx = 0;
        this.ny = 0;
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
        for (int y = 0; y < ny; y++) {
            sb.append("|");
            for (int x = 0; x < nx; x++) {
                Cellule cell = cellules.get(x).get(y);
                if ((y == mouton.getX() && (x == mouton.getY()))) {
                    sb.append(" M |");
                } else if ((y == loup.getX()) && (x == loup.getY())) {
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
        if (this.sortie == true) {
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
            this.sortie = true;
            return true;
        }
    }

    //x = droite
    //y = hauteur
    //liste de boolean en format [N,S,E,O] pour indiquer si l'animal peut se déplacer dans une direction ou non
    public ArrayList<Cellule> getVoisins(Cellule cellule) {
        ArrayList<Cellule> voisins = new ArrayList<>();

        if (this.cellules.get(cellule.getX() - 1).get(cellule.getY()).getÉlément() instanceof Végétal) {
            voisins.add(this.cellules.get(cellule.getX() - 1).get(cellule.getY()));

        }
        if (this.cellules.get(cellule.getX() + 1).get(cellule.getY()).getÉlément() instanceof Végétal) {
            voisins.add(this.cellules.get(cellule.getX() + 1).get(cellule.getY()));

        }
        if (this.cellules.get(cellule.getX()).get(cellule.getY() + 1).getÉlément() instanceof Végétal) {
            voisins.add(this.cellules.get(cellule.getX() + 1).get(cellule.getY() + 1));
        }
        if (this.cellules.get(cellule.getX()).get(cellule.getY() - 1).getÉlément() instanceof Végétal) {
            voisins.add(this.cellules.get(cellule.getX()).get(cellule.getY() - 1));
        }
        return voisins;
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

    public ArrayList<String> sauvegarde() {
        ArrayList<String> sauv = new ArrayList<String>();
        for (int i = 0; i < cellules.size(); i++) {
            System.out.println(i);
            for (int k = 0; k < cellules.get(i).size(); k++) {
                if (cellules.get(i).get(k).getX() == mouton.getX() && cellules.get(i).get(k).getY() == mouton.getY()) {
                    //si les coordonnnées du mouton sont les mêmes que celles de cettes cellules, on le met dans la liste
                    sauv.add("M");
                } else if (cellules.get(i).get(k).getX() == loup.getX() && cellules.get(i).get(k).getY() == loup.getY()) {
                    //si les coordonnnées du mouton sont les mêmes que celles de cettes cellules, on le met dans la liste
                    sauv.add("L");
                } else if (i == 0 || i == cellules.size()) {
                    if (cellules.get(i).get(k).getÉlément() instanceof Herbe) {
                        sauv.add("S");
                        //Si l'élément est sur les bord du labyrinthe on vérifie si c'est de l'herbe est donc la sortie
                    }

                } else if (k == 0 || k == cellules.get(i).size()) {
                    if (cellules.get(i).get(k).getÉlément() instanceof Herbe) {
                        sauv.add("S");
                        //Si l'élément est sur les bord du labyrinthe on vérifie si c'est de l'herbe est donc la sortie
                    }
                } else if (cellules.get(i).get(k).getÉlément() instanceof Mur) {
                    sauv.add("x");
                    //si l'élément est un mur, on met x dans la liste
                } else if (cellules.get(i).get(k).getÉlément() instanceof Herbe) {
                    sauv.add("h");
                    //si l'élément est de l'herbe, on met h dans la liste
                } else if (cellules.get(i).get(k).getÉlément() instanceof Cactus) {
                    sauv.add("c");
                    //si l'élément est un cactus, on met c dans la liste
                } else if (cellules.get(i).get(k).getÉlément() instanceof marguerite) {
                    sauv.add("F");
                    //si l'élément est une marguerite, on met f dans la liste
                } else if (cellules.get(i).get(k).getÉlément().equals(null)) {
                    sauv.add("T");
                    //Si la cellule qu'on traite à la les coordonnées du monton alors on met le mouton

                }

            }
            sauv.add("\n");
        }
        try {
            FileWriter fw = new FileWriter("laby2.txt");
            fw.write(sauv.toString());
            fw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return sauv;
    }
    public void PlaceAleatoire(int hauteur, int largeur){
        ArrayList<String> t= new ArrayList<>();
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
        int caisseCsGo = random.nextInt(t.size()) ;
        if (t.get(caisseCsGo) == "c"){
            this.PoserCactus(hauteur,largeur);
        } else if (t.get(caisseCsGo) == "h") {
            this.PoserHerbe(hauteur,largeur);
        }else if(t.get(caisseCsGo) == "f"){
            this.PoserMargueurite(hauteur,largeur);
        } else {
            this.PoserMur(hauteur, largeur);
        }
    }
    public void aleatoire(){
        for (int i = 1; i <cellules.size() - 1 ; i++) {
            for (int j = 1; j <cellules.get(i).size() - 1 ; j++) {
                this.PlaceAleatoire(i,j);
            }
        }
        this.PoserHerbe(this.getNx()-1,this.getNy()-1);
        this.PoserHerbe(1,1);
        this.setLoup(new Loup(cellules.get(1).get(1)));
        this.setLoup(new Loup(cellules.get(this.getNx()-1).get(this.getNy()-1)));
    }

    public ArrayList<String> recup(String fichier) {
        ArrayList contenuFichier = new ArrayList<String>();

        try {
            FileInputStream input = new FileInputStream(fichier);
            InputStreamReader redar = new InputStreamReader(input);
            BufferedReader bufRead = new BufferedReader(redar);

            String ligne;
            while ((ligne = bufRead.readLine()) != null) {
                ArrayList<Character> elt = new ArrayList<Character>();
                System.out.println(ligne);

                //contenuFichier.add(elt.add(ligne));
                for (char c : ligne.toCharArray()) {
                    elt.add(c);
                }

                contenuFichier.add(elt);
                System.out.println(elt.getClass());
                System.out.println(contenuFichier.getClass());
            }

            bufRead.close();
        } catch (IOException e) {
            //System.out.println("Erreur lors de la lecture du fichier : " + e.getMessage());
        }

        return contenuFichier;
    }
/*
    public int recupToLaby(ArrayList<ArrayList<Character>> bla) {
        return 1;
        int x = bla.size();
        int y = 0;
        for (Character u:bla.get(1).toCharArray()) {
            y+=1;
        }
        //int y = bla.get(0).;
        Labyrinthe recup = new Labyrinthe(x, y);
        for (int h = 0; h < bla.size(); h++) {
            String c = null;
            int lar = 0;
            for (char l : bla.get(h).toCharArray()) {
                c = Character.toString(l);
                lar+=1;
            }
            if (Objects.equals(c, "x")) {
                recup.PoserMur(h, lar);
            } else if (c.equals("f")) {
                recup.PoserMargueurite(h, lar);
            } else if (c.equals("h")) {
                recup.PoserHerbe(h, lar);
            } else if (c.equals("c")) {
                recup.PoserCactus(h, lar);
            } else if (c.equals("T")) {
                recup.GetCellule(h, lar).setÉlément(null);
            } else if (c.equals("l")) {
                recup.setLoup(new Loup(recup.cellules.get(h).get(lar)));
                //on place sur le labyrinthe un nouveau loup à la position
            } else if (c.equals("M")) {
                recup.setLoup(new Loup(recup.cellules.get(h).get(lar)));
                //on place sur le labyrinthe un nouveau Mouton à la position
            }

        }
        recup.toString();
    }

 */
}



