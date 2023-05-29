package c;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import m.Labyrinthe;

import java.util.Stack;


public class EvenementsMenu implements EventHandler {


    private String action;
    private Stage stage;
    public EvenementsMenu(){
        action = null;
    }
    public EvenementsMenu(Stage s){
        this.stage = s;
        action = null;
    }

    public String getAction(){
        return this.action;
    }



    public void handle(Event event) {
        System.out.println("le bouton a été cliqué");
        if (event.getSource() instanceof Button){
            Button button = (Button)event.getSource();
            if (button.getText().toString().equals("Définir sortie")){
                this.action = "Définir sortie";
            }
            if(button.getText().toString().equals("PoserHerbe")){
                this.action = "PoserHerbe";
            }
            if(button.getText().toString().equals("PoserMargeurite")){
                this.action = "PoserMargeurite";
            }
            if (button.getText().toString().equals("PoserCactus")){
                this.action = "PoserCactus";
            }
            if (button.getText().toString().equals("PoserMouton")){
                this.action = "PoserMouton";
            }
            if(button.getText().toString().equals("PoserLoup")){
                this.action = "PoserLoup";
            }
            if(button.getText().toString().equals("PoserMur")){
                this.action = "PoserMur";
            }
            if(button.getText().toString().equals("ChargerLabyrinthe")){
                this.action = "ChargerLabyrinthe";
            }
        }



    }

}