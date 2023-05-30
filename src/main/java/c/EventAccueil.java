package c;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import v.Crédits;
import v.Paramètres;
import v.Paramètres;
import v.Préparation;

import java.awt.*;
import java.io.IOException;
import javafx.scene.control.Button;
public class EventAccueil implements EventHandler<Event> {
    private Stage stage;

    public EventAccueil(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void handle(Event event) {

        if (event.getSource().getClass().toString().equals("class javafx.scene.control.Button")) {
            Button bouton = ((Button) event.getSource());
            if (bouton.getText().equals("Lancer")) {
                try {
                    Préparation prep = new Préparation(10, 8);
                    stage.close(); // Fermer la fenêtre
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            } else if (bouton.getText().equals("quitter")) {
                stage.close();
            } else if (bouton.getText().equals("Crédits")) {
                stage.close();
                Crédits creds = new Crédits();
            } else if (bouton.getText().equals("Paramètres")) {
                stage.close();
                Paramètres param = new Paramètres();

            }
        }
    }
}