package com.example.fx_sae;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.stage.Stage;


public class Main extends Application {

    public void start(Stage stage) throws IOException {
        Labyrinthe labyrinthe = new Labyrinthe(3,4);
        System.out.println(labyrinthe.GetCellules());
        System.out.println(labyrinthe.GetCellule(0,1));

        /*Image image = new Image("C:\\Users\\depla\\OneDrive\\Images\\Herbe.Png");
BackgroundSize backgroundSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false);
BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
Background background = new Background(backgroundImage);

        GridPane gridPane = new GridPane();
        for (int row = 20; row < 30; row++) {
    for (int col = 20; col < 30; col++) {
        Button button = new Button();
        button.setPrefSize(50, 50);
        button.setBackground(background);
        button.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        ((GridPane) gridPane).add(button, col, row);
    }
}
    Scene scene = new Scene(gridPane, 400, 400);
    stage = new Stage();
    stage.setScene(scene);
    stage.show();
    }

    //x correspond a la hauteur
    //y correspond a la droite et la gauche
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Labyrinthe test;
        test = new Labyrinthe(10,10);


        System.out.println(test.GetCellules());
        System.out.println(test.GetCellule(2,2));
        Loup loup = new Loup(test.GetCellule(1,1));
        test.setLoup(loup);
        Mouton mouton = new Mouton(test.GetCellule(8,8));
        test.setMouton(mouton);
        System.out.println(test.toString());
        test.CasserMur(4,0);
        test.PoserCactus(3,3);
        test.PoserMargueurite(2,3);
        test.CasserMur(0,3);
        test.PoserMargueurite(1,4);
        System.out.println(test.toString());
        test.sauvegarderLabyrinthe("labyrintheprefait.dat");
        Labyrinthe récup = Labyrinthe.chargerLabyrinthe("labyrintheprefait.dat");
        System.out.println(récup.toString());
        int babouin_compteur = 0;
        for(int x = 0;x<10;x++){
            for (int y=0;y<10;y++){
                if (récup.DéfinirSortie(x,y) == true){
                    babouin_compteur +=1;
                }
            }
        }
        System.out.println(babouin_compteur);
        récup.DéfinirSortie(9,1);
        System.out.println(récup.toString());
        System.out.println("les voisins sont "+récup.getVoisins(récup.GetCellule(8,1)));
        //System.out.println("les voisins sont"+récup.getVoisins(8,8));
        //System.out.println("les voisins sont"+récup.getVoisins(4,4));
        HelloApplication.launch();



        }

    }*/}}

