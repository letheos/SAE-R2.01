package com.example.fx_sae;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.stage.Stage;
import javafx.scene.control.ScrollBar;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.InputEvent;
import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException, ClassNotFoundException {
        Evenement e = new Evenement();


        Labyrinthe test;
        int nbrLargeur = 10;
        int nbrLongueur = 10;
        //problème si les 2 sont pas égaux
        test = new Labyrinthe(nbrLongueur,nbrLargeur);


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
        test.PoserMargueurite(3,4);
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
        récup.GetCellule(5,5).setÉlément(null);
        //System.out.println("les voisins sont"+récup.getVoisins(8,8));
        //System.out.println("les voisins sont"+récup.getVoisins(4,4));
        Image image = new Image("C:\\document\\cour\\src\\Herbe.Png");
        Image image2 = new Image("C:\\document\\cour\\src\\Mur.Png");
        Image image3 = new Image("C:\\document\\cour\\src\\cactus.jpg");
        Image image4 = new Image("C:\\document\\cour\\src\\Margeurites.jpg");
        Image image5 = new Image ("C:\\document\\cour\\src\\Terre.png");
        Image image6 = new Image("C:\\document\\cour\\src\\Terre.png");
        Image image7 = new Image("C:\\document\\cour\\src\\\\Mouton.png");
        ImageView imageView = new ImageView(image6);


        BackgroundSize backgroundSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        BackgroundImage backgroundImage2 = new BackgroundImage(image2, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        BackgroundImage backgroundImage3 = new BackgroundImage(image3, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        BackgroundImage backgroundImage4 = new BackgroundImage(image4, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        BackgroundImage backgroundImage5 = new BackgroundImage(image5, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);
        Background background2 = new Background(backgroundImage2);
        Background background3 = new Background(backgroundImage3);
        Background background4 = new Background(backgroundImage4);
        Background background5 =  new Background(backgroundImage5);

        GridPane gridPane = new GridPane();
        for (int row = 0; row < récup.getNx(); row++) {
    for (int col = 0; col < récup.getNy(); col++) {
        Button button = new Button();
        button.setPrefSize(50, 50);
        if (récup.GetCellule(col,row).getÉlément() instanceof Mur){
            button.setBackground(background2);
        } else if (récup.GetCellule(col,row).getÉlément() instanceof Cactus) {
            button.setBackground(background3);

        } else if (récup.GetCellule(col,row).getÉlément() instanceof marguerite) {
            button.setBackground(background4);

        } else if (récup.GetCellule(col,row).getÉlément() ==  null) {
            button.setBackground(background5);

        } else {
            button.setBackground(background);

        }
        if(récup.GetCellule(col,row).equals(récup.getMouton().getPosition())){
            System.out.println("la case "+col+1+row+1+"est l'emplacement du mouton");
            //TODO finir l'implémentation de la superposition de l'image du mouton et du loup sur les cases
            /*StackPane stackPane = new StackPane();
            stackPane.getChildren().addAll(button,imageView);
            button.getParent().getChildrenUnmodifiable().add(stackPane);*/
        }



        button.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        ((GridPane) gridPane).add(button, col, row);
    }
}
//
  //      public void handle(MouseEvent v) {
  //          int colIndex = GridPane.getColumnIndex((Node) v.getTarget());
  //          int rowIndex = GridPane.getRowIndex((Node) v.getTarget());
  //          System.out.println("Cellule (" + colIndex + ", " + rowIndex + ") a été cliquée.");
  //      }


        GridPane boutonCote = new GridPane();
        //buton pour placé un mur
        Button placeMur = new Button();
        placeMur.setText("Placer Mur");
        //button pour placer une marguerite
        Button placerMarguerite = new Button();
        placerMarguerite.setText("placer Marguerite");

        //button pour placer un cactus
        Button placerCactus = new Button();
        placerCactus.setText("placer Cactus");
        //button pour place de l'herbe
        Button placerHerbe = new Button();
        placerHerbe.setText("placer Herbe");
        //button pour mettre de la terre(enlever tout)
        Button placerTerre = new Button();
        placerTerre.setText("placer Terre");

        //ajout dans boutonCote des bouton de haut en bas
        boutonCote.add(placerTerre,0,1);
        boutonCote.add(placeMur,0,2);
        boutonCote.add(placerCactus,0,3);
        boutonCote.add(placerHerbe,0,4);
        boutonCote.add(placerMarguerite,0,5);


        placerMarguerite.setOnAction(e);


        int largeur = nbrLongueur*50;
        int hauteur = nbrLargeur*55+150;

        gridPane.setLayoutX(0);
        gridPane.setLayoutY(0);
        boutonCote.setLayoutX(nbrLongueur*55);
        boutonCote.setLayoutY(10);
        Group groupe = new Group();
        groupe.getChildren().add(gridPane);
        groupe.getChildren().add(boutonCote);
        boutonCote.setVgap(20);
        Scene scene = new Scene(groupe, hauteur, largeur);

    stage = new Stage();
    stage.setTitle("jeu wsh");
    stage.setScene(scene);
    stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}