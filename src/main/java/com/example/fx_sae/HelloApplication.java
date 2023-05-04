package com.example.fx_sae;

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

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException, ClassNotFoundException {



        Labyrinthe test;
        test = new Labyrinthe(10,10);


        System.out.println(test.GetCellules());
        System.out.println(test.GetCellule(2,2));
        Loup loup = new Loup(test.GetCellule(1,1));
        test.setLoup(loup);
        Mouton mouton = new Mouton(test.GetCellule(test.getNx()-2, test.getNy()-2));
        test.setMouton(mouton);
        System.out.println(test.toString());
        test.CasserMur(3,0);
        test.PoserCactus(3,3);
        test.PoserMargueurite(2,3);
        test.CasserMur(0,3);
        test.PoserMargueurite(1,4);
        System.out.println(test.toString());
        test.sauvegarderLabyrinthe("labyrintheprefait.dat");
        Labyrinthe récup = Labyrinthe.chargerLabyrinthe("labyrintheprefait.dat");
        System.out.println(récup.toString());
        /*int babouin_compteur = 0;
        for(int x = 0;x<récup.getNx();x++){
            for (int y=0;y< test.getNy();y++){
                if (récup.DéfinirSortie(x,y) == true){
                    babouin_compteur +=1;
                }
            }
        }
        System.out.println(babouin_compteur);*/
        récup.DéfinirSortie(4,1);
        System.out.println(récup.toString());
        System.out.println("les voisins sont "+récup.getVoisins(récup.GetCellule(2,1)));
        //System.out.println("les voisins sont"+récup.getVoisins(8,8));
        //System.out.println("les voisins sont"+récup.getVoisins(4,4));
        Image image = new Image("C:\\Users\\depla\\OneDrive\\Images\\Herbe.Png");
        Image image2 = new Image("C:\\Users\\depla\\OneDrive\\Images\\Mur.Png");
        Image image3 = new Image("C:\\Users\\depla\\OneDrive\\Images\\cactus.jpg");
        Image image4 = new Image("C:\\Users\\depla\\OneDrive\\Images\\Margeurites.jpg");


        BackgroundSize backgroundSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        BackgroundImage backgroundImage2 = new BackgroundImage(image2, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        BackgroundImage backgroundImage3 = new BackgroundImage(image3, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        BackgroundImage backgroundImage4 = new BackgroundImage(image4, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);
        Background background2 = new Background(backgroundImage2);
        Background background3 = new Background(backgroundImage3);
        Background background4 = new Background(backgroundImage4);

        Parent gridPane = new GridPane();
        for (int row = 0; row < récup.getNx(); row++) {
    for (int col = 0; col < récup.getNy(); col++) {
        Button button = new Button();
        button.setPrefSize(50, 50);
        if (récup.GetCellule(row,col).getÉlément() instanceof Mur){
            button.setBackground(background2);
        } else if (récup.GetCellule(row,col).getÉlément() instanceof Cactus) {
            button.setBackground(background3);

        } else if (récup.GetCellule(row,col).getÉlément() instanceof marguerite) {
            button.setBackground(background4);

        }
        else {
            button.setBackground(background);
        }
        button.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        ((GridPane) gridPane).add(button, col, row);
    }
}

        gridPane.setLayoutX(250);
        gridPane.setLayoutY(150);
    Scene scene = new Scene(gridPane, 400, 400);
    stage = new Stage();
    stage.setScene(scene);
    stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}