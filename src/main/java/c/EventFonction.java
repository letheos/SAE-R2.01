package c;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import m.Labyrinthe;
import javafx.scene.control.Button;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Objects;

import static java.awt.event.MouseEvent.MOUSE_CLICKED;

public class EventFonction implements EventHandler<Event> {
    private Stage stage;
    //private EvenementsMenu evenementM;
    private javafx.scene.control.TextField tf;
    private Labyrinthe lab;

    public EventFonction(Stage s, TextField t, Labyrinthe lab) {
        this.stage = s;
        this.tf = t;
        //this.evenementM = e;
        this.lab = lab;
    }

    @Override
    public void handle(Event event) {
        System.out.println("une banane");
        if (event.getSource() instanceof Button) {
            Button but = (Button) event.getSource();

            if (but.getText().contains("ChargerLabyrinthe")) {


                String racineProjet = System.getProperty("user.dir");
                File debut = new File(racineProjet);
                FileChooser explorateur = new FileChooser();

                explorateur.setInitialDirectory(debut);
                FileChooser.ExtensionFilter filtreText = new FileChooser.ExtensionFilter("Text", "*.txt");
                explorateur.getExtensionFilters().add(filtreText);
                File dossierSelec = explorateur.showOpenDialog(stage);
                if (dossierSelec != null) {
                    tf.setText(dossierSelec.getAbsolutePath());
                    System.out.println(dossierSelec.getAbsolutePath());
                    String recette = lab.recup(dossierSelec.getAbsolutePath());
                    lab.recupToLaby(recette);
                   System.out.println(lab.toString());
                }


            }
        }
    }
}
