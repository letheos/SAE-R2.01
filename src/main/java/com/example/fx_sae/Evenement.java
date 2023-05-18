package com.example.fx_sae;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class Evenement implements EventHandler {
    private String action;
    @Override
    public void handle(Event event) {
        //Object source;
        if(event.getSource() instanceof Button){
            Button button =(Button) event.getSource();
            if(button.getText() == "Placer Mur"){
                this.action = "1";
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
}
