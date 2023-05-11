package com.example.fx_sae;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

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

    public void errer(Labyrinthe labyrinthe){
        ArrayList<Cellule> voisins = labyrinthe.getVoisins(this.position);
        Random random = new Random();
        int choix = random.nextInt(voisins.size());

        //todo finir errer en choisissant aléatoirement la direction qui est récupérée et gérer ça dans le labyrinthe
    }

}
