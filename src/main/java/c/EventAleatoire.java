package c;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import m.Labyrinthe;

public class EventAleatoire implements EventHandler<MouseEvent>  {
    private Labyrinthe lab;

    public EventAleatoire(Labyrinthe lab){
        this.lab = lab;
    }
    @Override
    public void handle(MouseEvent mouseEvent) {
        lab.aleatoire();
        System.out.println(lab.toString());

    }
}
