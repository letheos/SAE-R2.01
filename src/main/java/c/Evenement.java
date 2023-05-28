/*package c;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

import m.Labyrinthe;
public class Evenement implements EventHandler {
    private String action;
    private Labyrinthe l;
    @Override
    public void handle(Event event) {
        //Object source;
        if(event.getSource() instanceof Button){
            Button button =(Button) event.getSource();
            if(button.getText() == "aléatoire"){
                this.l.aleatoire();
            } else if (button.getText() == "placer Marguerite") {
                this.action = "2";
                System.out.println(")zejpifpoziefoz");

            }

        }

    }
    public String getAction(){
        return this.action;
    }
    public void Evenenent(){
    }
    public Evenement(Labyrinthe l){
        this.l = l;
    }
}*/
