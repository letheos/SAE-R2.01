package com.example.fx_sae;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.awt.event.*;
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

import javax.swing.text.Position;
import java.io.IOException;
import java.util.EventObject;

public class HelloApplication extends Application {

    private Node getButtonxy (GridPane gridPane,int x ,int y){
        for(Node node: gridPane.getChildren()){
            if (GridPane.getColumnIndex(node) == x && GridPane.getRowIndex(node) == y){
                return node;
            }
        }
        return null;
}



    @Override
    public void start(Stage stage) throws IOException, ClassNotFoundException {


        Labyrinthe test;
        test = new Labyrinthe(10, 10);


        System.out.println(test.GetCellules());
        System.out.println(test.GetCellule(2, 2));
        Loup loup = new Loup(test.GetCellule(1, 1));
        test.setLoup(loup);
        Mouton mouton = new Mouton(test.GetCellule(test.getNx() - 2, test.getNy() - 2));
        test.setMouton(mouton);
        System.out.println(test.toString());
        test.CasserMur(3, 0);
        test.PoserCactus(3, 3);
        test.PoserMargueurite(2, 3);
        test.CasserMur(0, 3);
        test.PoserMargueurite(1, 4);
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
        récup.DéfinirSortie(4, 1);
        System.out.println(récup.toString());
        System.out.println("les voisins sont " + récup.getVoisins(récup.GetCellule(2, 1)));
        récup.GetCellule(5, 5).setÉlément(null);
        //System.out.println("les voisins sont"+récup.getVoisins(8,8));
        //System.out.println("les voisins sont"+récup.getVoisins(4,4));
        Image image = new Image("C:\\Users\\depla\\OneDrive\\Images\\Herbe.Png");
        Image image2 = new Image("C:\\Users\\depla\\OneDrive\\Images\\Mur.Png");
        Image image3 = new Image("C:\\Users\\depla\\OneDrive\\Images\\cactus.jpg");
        Image image4 = new Image("C:\\Users\\depla\\OneDrive\\Images\\Margeurites.jpg");
        Image image5 = new Image("C:\\Users\\depla\\OneDrive\\Images\\Terre.png");
        Image image6 = new Image("C:\\\\Users\\\\depla\\\\OneDrive\\\\Images\\\\Terre.png");
        Image image7 = new Image("C:\\Users\\depla\\OneDrive\\Images\\Mouton.png");
        Image image8 = new Image("C:\\Users\\depla\\OneDrive\\Images\\Loup.png");
        ImageView imageView = new ImageView(image7);
        imageView.setFitHeight(30);
        imageView.setFitWidth(30);
        ImageView imageView1 = new ImageView(image8);
        imageView1.setFitHeight(30);
        imageView1.setFitWidth(50);
        StackPane stackPane = new StackPane();
        StackPane stackPane1 = new StackPane();


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
        Background background5 = new Background(backgroundImage5);

        Parent gridPane = new GridPane();
        for (int row = 0; row < récup.getNy(); row++) {
            for (int col = 0; col < récup.getNx(); col++) {
                Button button = new Button();
                button.setPrefSize(50, 50);
                if (récup.GetCellule(col, row).getÉlément() instanceof Mur) {
                    button.setBackground(background2);
                } else if (récup.GetCellule(col, row).getÉlément() instanceof Cactus) {
                    button.setBackground(background3);

                } else if (récup.GetCellule(col, row).getÉlément() instanceof marguerite) {
                    button.setBackground(background4);

                } else if (récup.GetCellule(col, row).getÉlément() == null) {
                    button.setBackground(background5);

                } else {
                    button.setBackground(background);

                }
                if (récup.GetCellule(col, row).equals(récup.getMouton().getPosition())) {

                    //stackPane.getChildren().addAll(button,imageView);
                    Pane overlayPane = new Pane();
                    overlayPane.setStyle("-fx-background-color: transparent;");
                    imageView.setMouseTransparent(true);
                    imageView.setPreserveRatio(true);
                    Rectangle clip = new Rectangle(imageView.getFitWidth(), imageView.getFitHeight());
                    clip.setArcHeight(10);
                    clip.setArcWidth(10);
                    imageView1.setClip(clip);
                    overlayPane.getChildren().add(imageView);
                    button.setGraphic(overlayPane);
                    //TODO finir l'implémentation du loup et du mouton au déplacement

                } else if (récup.GetCellule(col, row).equals(récup.getLoup().getPosition())) {

                    Pane overlayPane = new Pane();
                    overlayPane.setStyle("-fx-background-color: transparent;");
                    imageView1.setMouseTransparent(true);
                    imageView1.setPreserveRatio(true);
                    Rectangle clip = new Rectangle(imageView1.getFitWidth(), imageView1.getFitHeight());
                    clip.setArcHeight(10);
                    clip.setArcWidth(10);
                    imageView1.setClip(clip);
                    overlayPane.getChildren().add(imageView1);
                    button.setGraphic(overlayPane);
                    button.setOnAction(event -> {
    // Obtenir la position actuelle du loup
    Cellule loupPosition = (Cellule) récup.getLoup().getPosition();
    int x = ((Cellule) loupPosition).getX();
    int y = ((Cellule) loupPosition).getY();

    // Calculer la position de la case en dessous
    int newX = x;
    int newY = y + 1;

    // Vérifier si la nouvelle position est valide
    if (newX >= 0 && newX < récup.getNx() && newY >= 0 && newY < récup.getNy()) {
        // Mettre à jour la position du loup
        récup.getLoup().setPosition(récup.GetCellule(newX,newY));

        // Enlever l'ancienne image de la case actuelle
        ((Pane)button.getGraphic()).getChildren().clear();

        // Ajouter la nouvelle image à la nouvelle case
        Pane newOverlayPane = new Pane();

        newOverlayPane.setStyle("-fx-background-color: transparent;");
        imageView1.setMouseTransparent(true);
        imageView1.setPreserveRatio(true);
        Rectangle clip1 = new Rectangle(imageView1.getFitWidth(), imageView1.getFitHeight());
        clip1.setArcHeight(10);
        clip1.setArcWidth(10);
        imageView1.setClip(clip);

        newOverlayPane.getChildren().add(imageView1);
        ((GridPane) gridPane).add(newOverlayPane, newX, newY);
        GridPane.setValignment(newOverlayPane, VPos.CENTER);

    }
});


                }
                button.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

                ((GridPane) gridPane).add(button, col, row);
            }
        }



        gridPane.setLayoutX(0);
        gridPane.setLayoutY(0);

        gridPane.setOnMouseClicked(new EventHandler<MouseEvent>() {
    public void handle(MouseEvent event) {
        int colIndex = GridPane.getColumnIndex((Node) event.getTarget());
        int rowIndex = GridPane.getRowIndex((Node) event.getTarget());
        System.out.println("Cellule (" + colIndex + ", " + rowIndex + ") a été cliquée.");
    }
});
        Scene scene = new Scene(gridPane, 400, 400);

        stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Grass Man");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }



}



