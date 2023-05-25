package com.example.fx_sae;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.awt.*;
import java.awt.event.*;

import javafx.scene.control.Cell;
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

import javax.swing.text.Position;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.EventObject;

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
            //TODO corriger le code , quand un animal passe sur une case déja visitée il ne peux plus s'afficher dessus
            //TODO modifier pour corriger le déplacement du mouton , quand il marche sur une case de terre il n'apparait pas

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
        test = new Labyrinthe(10, 10);


        System.out.println(test.GetCellules());
        System.out.println("le labyrinthe est un " + test.getNx() + test.getNy());

        Loup loup = new Loup(test.GetCellule(1, 1));
        test.setLoup(loup);
        //mouton doit etre a x = ny- et y = nx-2 si tout en bas a droite
        Mouton mouton = new Mouton(test.GetCellule(test.getNx() - 2, test.getNy() - 2));
        System.out.println("ceci est la cellule 0,0" + test.GetCellule(0, 0));
        System.out.println("le mouton est en " + mouton.getX() + "en x et " + mouton.getY() + "en Y sur la cellule " + mouton.getPosition());
        test.setMouton(mouton);
        System.out.println(test.toString());
        test.CasserMur(2, 0);
        test.PoserCactus(1, 1);
        test.PoserMargueurite(2, 1);
        test.CasserMur(0, 2);
        test.PoserMargueurite(1, 2);
        System.out.println(test.toString());
        test.sauvegarderLabyrinthe("labyrintheprefait.dat");
        Labyrinthe récup = Labyrinthe.chargerLabyrinthe("labyrintheprefait.dat");
        System.out.println(récup.toString());
        int babouin_compteur = 0;
        for (int x = 0; x < récup.getNx(); x++) {
            for (int y = 0; y < test.getNy(); y++) {
                if (récup.DéfinirSortie(x, y) == true) {
                    babouin_compteur += 1;
                }
            }
        }
        System.out.println(babouin_compteur);
        récup.DéfinirSortie(1, 0);
        System.out.println(récup.toString());

        récup.GetCellule(1, 1).setÉlément(null);
        //System.out.println("les voisins sont"+récup.getVoisins(8,8));
        //System.out.println("les voisins sont"+récup.getVoisins(4,4));
        String  racineProjet = System.getProperty("user.dir");
        String cheminHerbe = racineProjet+"\\src\\images\\Herbe.png";
        String cheminMur = racineProjet+"\\src\\images\\Mur.png";
        String chemincactus = racineProjet+"\\src\\images\\cactus.jpg";
        String cheminMargeurite = racineProjet+"\\src\\images\\Margeurites.jpg";
        String cheminTerre = racineProjet+"\\src\\images\\Terre.png";
        String cheminMouton = racineProjet+"\\src\\images\\Mouton.png";
        String cheminLoup = racineProjet+"\\src\\images\\Loup.png";
        Image image = new Image(cheminHerbe);
        Image image2 = new Image(cheminMur);
        Image image3 = new Image(chemincactus);
        Image image4 = new Image(cheminMargeurite);
        Image image5 = new Image(cheminTerre);
        Image image7 = new Image(cheminMouton);
        Image image8 = new Image(cheminLoup);
        ImageView imageView = new ImageView(image7);
        imageView.setFitHeight(30);
        imageView.setFitWidth(30);
        ImageView imageView1 = new ImageView(image8);
        imageView1.setFitHeight(30);
        imageView1.setFitWidth(50);
        imageView1.setMouseTransparent(true);
        imageView1.setPreserveRatio(true);
        Rectangle clip1 = new Rectangle(imageView1.getFitWidth(), imageView1.getFitHeight());
        clip1.setArcWidth(10);
        clip1.setArcHeight(10);
        imageView1.setClip(clip1);
        imageView.setMouseTransparent(true);
        imageView.setPreserveRatio(true);
        Rectangle clip2 = new Rectangle(imageView.getFitWidth(), imageView.getFitHeight());
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
                button.setMaxSize(50, 50);
                if (récup.GetCellule(row, col).getÉlément() instanceof Mur) {
                    button.setBackground(background2);
                } else if (récup.GetCellule(row, col).getÉlément() instanceof Cactus) {
                    button.setBackground(background3);

                } else if (récup.GetCellule(row, col).getÉlément() instanceof marguerite) {
                    button.setBackground(background4);

                } else if (récup.GetCellule(row, col).getÉlément() == null) {
                    button.setBackground(background5);

                } else {
                    button.setBackground(background);

                }
                if (récup.GetCellule(row, col).equals(récup.getMouton().getPosition())) {

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
                        récup.getMouton().errer(récup.getMouton(),récup);
                        déplacement(récup, gridPane, récup.getMouton().getX(), récup.getMouton().getY(), imageView, imageView1, récup.getMouton());


                        System.out.println(récup.toString());
                    });
                } else if (récup.GetCellule(row, col).equals(récup.getLoup().getPosition())) {
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
                        récup.getLoup().errer(récup.getLoup(),récup);

                        // Obtenir la position actuelle du loup
                        déplacement(récup, gridPane, récup.getLoup().getX(), récup.getLoup().getY(), imageView, imageView1, récup.getLoup());

                        System.out.println(récup.toString());
                    });


                }
                button.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

                ((GridPane) gridPane).add(button, col, row);
            }
        }


        gridPane.setLayoutX(0);
        gridPane.setLayoutY(0);

        /*gridPane.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                int colIndex = GridPane.getColumnIndex((Node) event.getTarget());
                int rowIndex = GridPane.getRowIndex((Node) event.getTarget());
                System.out.println("Cellule (" + colIndex + ", " + rowIndex + ") a été cliquée.");
            }
        });*/
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setMaxSize(750,500);
        scrollPane.setContent(gridPane);


        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(scrollPane);
        Scene scene = new Scene(stackPane, 1000,  750);

        stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Attrappe moi si tu peux ");
        Image logo = new Image("G:\\Mon Drive\\nonnon on a pas redoublé la\\R2.01 java\\Saé R2.01-2.02 theo 2,thomas,loick\\Textures\\logo.png");
        stage.getIcons().add(logo);

        /*ArrayList voisins2 = new ArrayList();
        for (int y = 0;y<récup.getVoisins(récup.GetCellule(3,1)).size();y++){
            ArrayList oui = new ArrayList();
            oui.add(récup.getVoisins(récup.GetCellule(3,1)).get(y).getX());
            oui.add(récup.getVoisins(récup.GetCellule(3,1)).get(y).getY());
            voisins2.add(oui);
        }
        System.out.println("les voisins sont"+voisins2);*/
        System.out.println(récup.GetCellules());
        System.out.println("\n");
        System.out.println(récup.GetElements());
        System.out.println("\n");
        System.out.println("voici la cellule que je veux get en 0,1"+récup.GetCellule(0,1));
        System.out.println("voici le X de la cellule en 0,1:"+récup.GetCellule(0,1).getX());
        System.out.println("voici le Y de la cellule en 0,1:"+récup.GetCellule(0,1).getY());
        System.out.println("voici le type de la cellule en 0,1:"+récup.GetCellule(0,1).getÉlément());


        //TODO X correspond a la hauteur  du haut vers le bas et y a la largeur de la gauche vers la droite
        for (int y= 1;y < récup.getNx()-1 ;y++ ){

            for (int i = 1 ; i< récup.getNy()-1;i++){

                System.out.println("je vais voir les voisins de la cellule "+y+","+i);
                ArrayList<Cellule> liste = récup.getVoisins(récup.GetCellule(y,i));

                ArrayList<Element> babouin_liste = new ArrayList();
                System.out.println("je suis la en "+y+","+i);
                System.out.println(liste);
                for (int p = 0; p < liste.size(); p++) {
                    babouin_liste.add(liste.get(p).getÉlément());}
                System.out.println("voici la liste des voisins :"+babouin_liste);
            }

        }

        stage.show();
        System.out.println(récup.getMouton().mangé());
    }

    public static void main(String[] args) {
        launch();
    }
}



