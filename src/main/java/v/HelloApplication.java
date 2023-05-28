package v;
import c.EvenementsMenu;
import c.EventGridPane;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.awt.*;
import java.awt.event.*;

import javafx.scene.control.Cell;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import m.Labyrinthe;
import m.Loup;
import m.Mouton;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ComboBox;
import javafx.collections.ObservableList;
import javax.swing.text.Position;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.EventObject;

import m.Labyrinthe;
import m.Végétal;
import m.Animaux;
import m.*;

import static javafx.geometry.HPos.*;

public class HelloApplication extends Application {

    private Node getButtonxy(GridPane gridPane, int x, int y) {
        for (Node button : gridPane.getChildren()) {
            if (GridPane.getColumnIndex(button) == x && GridPane.getRowIndex(button) == y) {
                return button;
            }
        }
        return null;
    }

    private void déplacement(Labyrinthe récup, GridPane gridPane, int newX, int newY, ImageView imageView, ImageView imageView1, Animaux animal) {

        // Vérifier si la nouvelle position est valide
        if (newX >= 1 && newX < récup.getNx() - 1 && newY >= 1 && newY < récup.getNy() - 1) {
            int oldX;
            int oldY;
            // Mettre à jour la position du loup
            if (animal instanceof Loup) {
                oldX = récup.getLoup().getX();
                oldY = récup.getLoup().getY();
                récup.getLoup().setPosition(récup.GetCellule(newX, newY));

            } else {
                oldX = récup.getMouton().getX();
                oldY = récup.getMouton().getY();
                récup.getMouton().setPosition(récup.GetCellule(newX, newY));
                récup.getMouton().manger(récup.getMouton().getPosition(), gridPane);
            }
            // Enlever l'ancienne image de la case actuelle
            Button button2 = null;
            for (Node node : gridPane.getChildren()) {
                if (GridPane.getColumnIndex(node) == newY && GridPane.getRowIndex(node) == newX) {
                    if (node instanceof Button) {
                        button2 = (Button) node;
                        break;
                    }
                }
            }

            Button button = null;
            for (Node node : gridPane.getChildren()) {
                if (GridPane.getColumnIndex(node) == oldY && GridPane.getRowIndex(node) == oldX) {
                    if (node instanceof Button) {
                        button = (Button) node;
                        break;

                    }
                }
            }
// vérifier si le bouton a été trouvé
            if (button2 != null) {
                // définir un nouveau graphique pour le bouton
                if (animal instanceof Mouton) {
                    button.setGraphic(null);
                    button2.setGraphic(imageView);
                } else {
                    button.setGraphic(null);
                    button2.setGraphic(imageView1);
                }
            }


            // Ajouter la nouvelle image à la nouvelle case
        /*Pane newOverlayPane = new Pane();

        newOverlayPane.setStyle("-fx-background-color: transparent;");
        imageView1.setMouseTransparent(true);
        imageView1.setPreserveRatio(true);
        Rectangle clip1 = new Rectangle(imageView1.getFitWidth(), imageView1.getFitHeight());
        clip1.setArcHeight(10);
        clip1.setArcWidth(10);
        imageView1.setClip(clip1);

        newOverlayPane.getChildren().add(imageView1);
        ((GridPane) gridPane).add(newOverlayPane, newX, newY);

        GridPane.setValignment(newOverlayPane, VPos.CENTER);
*/
        }//TODO optimiser le code , dans les faits il marche mais pas de manière optimale

    }

    @Override
    public void start(Stage stage) throws IOException, ClassNotFoundException {
        Préparation prep = new Préparation();
        //TODO finir d'implémenter la page de préparation en tant que page complète et relier aux autres pages
    }



    public static void main(String[] args) {
        launch();
    }
}



