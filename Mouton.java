package com.example.fx_sae;

import java.util.HashMap;

public class Mouton extends Animaux {
    private boolean fuite;
     private HashMap<String, Integer> manges;
    public Mouton(Cellule cellule){
        super(cellule);
         this.manges = new HashMap<String,Integer>();
        this.manges.put("Herbe",0);
        this.manges.put("Cactus",0);
        this.manges.put("margeurite",0);
    }

    public boolean isFuite() {
        return fuite;
    }

    public HashMap<String, Integer> getManges() {
        return manges;
    }

    public void manger() {
        int m = (Integer)this.manges.get(this.getPosition().getElement().toString());
        this.manges.put(this.getPosition().getElement().toString(), m + 1);
    }

}
