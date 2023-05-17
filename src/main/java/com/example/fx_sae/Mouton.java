package com.example.fx_sae;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.util.HashMap;

public class Mouton extends Animaux {
    private boolean fuite;
     private HashMap<String, Integer> mangés;
    public Mouton(Cellule cellule){
        super(cellule);
         this.mangés = new HashMap<String,Integer>();
        this.mangés.put("Herbe",0);
        this.mangés.put("Cactus",0);
        this.mangés.put("margeurite",0);
    }
    public String mangé(){
        return "le mouton a mangé "+this.mangés.get("Herbe")+" cases d'herbe "+this.mangés.get("Cactus")+" Cactus et "+this.mangés.get("margeurite")+" margeurites";
    }
    public void manger(Cellule position, GridPane gridPane){
        if (position.getÉlément()instanceof Herbe){
            this.mangés.put("Herbe",this.mangés.get("Herbe")+1);
        }
        else if (position.getÉlément()instanceof marguerite){
            this.mangés.put("margeurite",this.mangés.get("margeurite")+1);
        }
        else if (position.getÉlément()instanceof Cactus){
            this.mangés.put("Cactus",this.mangés.get("Cactus")+1);
        }
        else {
        }
        this.getPosition().setÉlément(null);
        Button button2 = null;
        for (Node node : gridPane.getChildren()) {
    if (GridPane.getColumnIndex(node) == this.getY() && GridPane.getRowIndex(node) == this.getX()) {
        if (node instanceof Button) {

            button2 = (Button) node;
            break;
        }
    }
}

// vérifier si le bouton a été trouvé
if (button2 != null) {
    // définir un nouveau graphique pour le bouton

    Image image5 = new Image("C:\\Users\\depla\\OneDrive\\Images\\Terre.png");
    BackgroundSize backgroundSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false);
    BackgroundImage backgroundImage5 = new BackgroundImage(image5, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
    Background background5 = new Background(backgroundImage5);
    button2.setBackground(background5);

    }
}


    }

