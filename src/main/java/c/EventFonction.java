package c;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import m.Labyrinthe;
import javafx.scene.control.Button;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Objects;
import java.util.Optional;

import static java.awt.event.MouseEvent.MOUSE_CLICKED;

public class EventFonction implements EventHandler<Event> {

    //private EvenementsMenu evenementM;
    private javafx.scene.control.TextField tf;
    private Labyrinthe lab;

    public EventFonction(Labyrinthe lab) {



        this.lab = lab;
    }

    @Override
    public void handle(Event event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Saisir du texte");
        dialog.setHeaderText("Veuillez saisir du texte :");
        dialog.setContentText("Texte :");

        Optional<String> result = dialog.showAndWait();
        if(result.isPresent()){
            String fichier = result.get();
            lab.sauvegarde(fichier);
            System.out.println("sauvegarder");
        }else {
            lab.sauvegarde("labyrintheSansNom");
        }

        lab.sauvegarde("bla");
    }

    }

