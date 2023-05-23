package com.example.iu;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ToggleButton;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class EventController implements EventHandler{
    Scene scene;
    Stage stage;

    public EventController(Scene s, Stage st){
        this.scene=s;
        this.stage=st;
    }

    public EventController(){

    }

    @Override
    public void handle(Event event) {
        if (event.getSource().getClass().toString().contains("ToggleButton")) {
            ToggleButton sombre = (ToggleButton) event.getSource();
            if (sombre.isSelected()) {
                System.out.println("Activé");
                sombre.setText("Désactiver");
                scene.setFill(Color.BLACK);
                stage.show();
            } else {
                System.out.println("Désactivé");
                sombre.setText("Activer");
                scene.setFill(Color.BLUE);
                stage.show();
            }
        }
    }
}
