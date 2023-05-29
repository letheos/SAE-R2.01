package c;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import m.Labyrinthe;


public class EvenementsMenu implements EventHandler {


    private String action;

    public EvenementsMenu(){
        action = null;
    }

    public String getAction(){
        return this.action;
    }
    public void setAction(String effet){
        if (effet == "null"){
            this.action = null;
        }
        else {
            this.action = effet;
        }
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
        }



    }

}
