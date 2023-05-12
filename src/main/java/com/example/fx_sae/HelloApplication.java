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

import static javafx.geometry.HPos.*;

public class HelloApplication extends Application {

    private Node getButtonxy (GridPane gridPane,int x ,int y){
        for(Node node: gridPane.getChildren()){
            if (GridPane.getColumnIndex(node) == x && GridPane.getRowIndex(node) == y){
                return node;
            }
        }
        return null;
}

    private void déplacement(Labyrinthe récup,GridPane gridPane,int newX,int newY,ImageView imageView,ImageView imageView1,Animaux animal){

    // Vérifier si la nouvelle position est valide
    if (newX >= 1 && newX < récup.getNx()-1 && newY >= 1 && newY < récup.getNy()-1) {

        // Mettre à jour la position du loup
        if (animal instanceof Loup){
            récup.getLoup().setPosition(récup.GetCellule(newX,newY));}
        else {
            récup.getMouton().setPosition(récup.GetCellule(newX,newY));
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

// vérifier si le bouton a été trouvé
if (button2 != null) {
    // définir un nouveau graphique pour le bouton
    if (animal instanceof Mouton){
    button2.setGraphic(imageView);}
    else {

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


        Labyrinthe test;
        //nx correspond a la hauteur et ny a la largeur
        test = new Labyrinthe(15, 27);


        System.out.println(test.GetCellules());
        System.out.println("le labyrinthe est un "+test.getNx()+test.getNy());

        Loup loup = new Loup(test.GetCellule(1, 1));
        test.setLoup(loup);
        //mouton doit etre a x = ny- et y = nx-2 si tout en bas a droite
        Mouton mouton = new Mouton(test.GetCellule(test.getNx()-2, test.getNy()-2));
        System.out.println("ceci est la cellule 0,0" + test.GetCellule(0, 0));
        System.out.println("le mouton est en " + mouton.getX() + "en x et " + mouton.getY() + "en Y sur la cellule " + mouton.getPosition());
        test.setMouton(mouton);
        System.out.println(test.toString());
        test.CasserMur(2, 0);
        test.PoserCactus(1, 1);
        test.PoserMargueurite(2, 1);
        test.CasserMur(0,2 );
        test.PoserMargueurite(1, 2);
        System.out.println(test.toString());
        test.sauvegarderLabyrinthe("labyrintheprefait.dat");
        Labyrinthe récup = Labyrinthe.chargerLabyrinthe("labyrintheprefait.dat");
        System.out.println(récup.toString());
        int babouin_compteur = 0;
        for(int x = 0;x<récup.getNx();x++){
            for (int y=0;y< test.getNy();y++){
                if (récup.DéfinirSortie(x,y) == true){
                    babouin_compteur +=1;
                }
            }
        }
        System.out.println(babouin_compteur);
        récup.DéfinirSortie(1, 0);
        System.out.println(récup.toString());
        System.out.println("les voisins sont " + récup.getVoisins(récup.GetCellule(2, 1)));
        récup.GetCellule(1, 1).setÉlément(null);
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
        imageView1.setMouseTransparent(true);
        imageView1.setPreserveRatio(true);
        Rectangle clip1 = new Rectangle(imageView1.getFitWidth(),imageView1.getFitHeight());
        clip1.setArcWidth(10);
        clip1.setArcHeight(10);
        imageView1.setClip(clip1);
        imageView.setMouseTransparent(true);
        imageView.setPreserveRatio(true);
        Rectangle clip2 = new Rectangle(imageView.getFitWidth(),imageView.getFitHeight());
        clip2.setArcWidth(10);
        clip2.setArcHeight(10);
        imageView.setClip(clip2);

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

        GridPane gridPane = new GridPane();
        for (int row = 0; row < récup.getNx(); row++) {
            for (int col = 0; col < récup.getNy(); col++) {
                Button button = new Button();

                button.setMinSize(50, 50);
                button.setMaxSize(50,50);
                if (récup.GetCellule(row, col).getÉlément() instanceof Mur) {
                    button.setBackground(background2);
                } else if (récup.GetCellule(row ,col).getÉlément() instanceof Cactus) {
                    button.setBackground(background3);

                } else if (récup.GetCellule(row, col).getÉlément() instanceof marguerite) {
                    button.setBackground(background4);

                } else if (récup.GetCellule(row, col).getÉlément() == null) {
                    button.setBackground(background5);

                } else {
                    button.setBackground(background);

                }
                if (récup.GetCellule(row,col).equals(récup.getMouton().getPosition())) {

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
                    button.setOnMouseClicked(event -> {
                        // Obtenir la position actuelle du loup
                    déplacement(récup,gridPane, récup.getMouton().getX()-1, récup.getMouton().getY(),imageView,imageView1,récup.getMouton());
                    //TODO inversion entre le graphic et le visuel , a réparer
                    System.out.println(récup.toString());
});
                } else if (récup.GetCellule(row,col).equals(récup.getLoup().getPosition())) {
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
                    button.setOnMouseClicked(event -> {
                        // Obtenir la position actuelle du loup
                    déplacement(récup,gridPane, récup.getLoup().getX()+1, récup.getLoup().getY(),imageView,imageView1,récup.getLoup());
                    //TODO inversion entre le graphic et le visuel , a réparer
                    System.out.println(récup.toString());
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
        Scene scene = new Scene(gridPane, récup.getNy()*50, récup.getNx()*50);

        stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Attrappe moi si tu peux ");

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }




}



