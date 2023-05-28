package v;

import c.EvenementsMenu;
import c.EventGridPane;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import m.Cactus;
import m.Labyrinthe;
import m.Mur;
import m.marguerite;

import java.io.IOException;
import java.io.Serializable;

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
public class Préparation extends Stage implements Serializable {
    public Préparation() throws IOException, ClassNotFoundException {
        Labyrinthe test;
        //nx correspond a la hauteur et ny a la largeur
        test = new Labyrinthe(10, 10);


        System.out.println(test.GetCellules());
        System.out.println("le labyrinthe est un " + test.getNx() + test.getNy());



        //mouton doit etre a x = ny- et y = nx-2 si tout en bas a droite


        System.out.println(test.toString());

        System.out.println(test.toString());
        test.sauvegarderLabyrinthe("labyrintheprefait.dat");
        Labyrinthe récup = Labyrinthe.chargerLabyrinthe("labyrintheprefait.dat");
        System.out.println(récup.toString());
        /*int babouin_compteur = 0;
        for (int x = 0; x < récup.getNx(); x++) {
            for (int y = 0; y < test.getNy(); y++) {
                if (récup.DéfinirSortie(x, y) == true) {
                    babouin_compteur += 1;
                }
            }
        }*/


        System.out.println(récup.toString());


        //System.out.println("les voisins sont"+récup.getVoisins(8,8));
        //System.out.println("les voisins sont"+récup.getVoisins(4,4));
        String racineProjet = System.getProperty("user.dir");
        String cheminHerbe = racineProjet + "\\src\\images\\Herbe.png";
        String cheminMur = racineProjet + "\\src\\images\\Mur.png";
        String chemincactus = racineProjet + "\\src\\images\\cactus.jpg";
        String cheminMargeurite = racineProjet + "\\src\\images\\Margeurites.jpg";
        String cheminTerre = racineProjet + "\\src\\images\\Terre.png";
        String cheminMouton = racineProjet + "\\src\\images\\Mouton.png";
        String cheminLoup = racineProjet + "\\src\\images\\Loup.png";
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
        EvenementsMenu eventmenu = new EvenementsMenu();

        GridPane gridPane = new GridPane();
EventGridPane eventPane = new EventGridPane(eventmenu, récup, gridPane);
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

        if (récup.getMouton() != null && récup.GetCellule(row, col).equals(récup.getMouton().getPosition())) {
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
        } else if (récup.getLoup() != null && récup.GetCellule(row, col).equals(récup.getLoup().getPosition())) {
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
        }

        button.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        button.setOnMouseClicked(eventPane);
        gridPane.add(button, col, row);
    }
}



            gridPane.setLayoutX(0);
            gridPane.setLayoutY(0);


            ScrollPane scrollPane = new ScrollPane();
            scrollPane.setMaxSize(750, 500);
            scrollPane.setContent(gridPane);


            StackPane stackPane = new StackPane();
            stackPane.getChildren().add(scrollPane);
            Button boutonaccueil = new Button("Accueil");

            Label labelTaille = new Label("Taille");
            ComboBox<Integer> hauteur = new ComboBox<>();
            ComboBox<Integer> largeur = new ComboBox<>();

            for (int i = 4; i <= 40; i++) {
                hauteur.getItems().add(i);
                largeur.getItems().add(i);
            }
            ComboBox<String> environnement = new ComboBox();
            environnement.getItems().add("Normal");
            environnement.getItems().add("aquatique");
            environnement.getItems().add("Pokémon");
            Button générerLabyrinthe = new Button("Générer Labyrinthe");
            Button Lancer = new Button("Lancer");
            Lancer.setMinSize(100, 100);
            Lancer.setMaxSize(100, 100);
            Button chargerLabyrinthe = new Button("Charger Labyrinthe");
            VBox gauche = new VBox();
            gauche.getChildren().addAll(boutonaccueil, labelTaille, hauteur, largeur, Lancer, générerLabyrinthe,chargerLabyrinthe);
            VBox milieu = new VBox();
            milieu.getChildren().add(stackPane);
            VBox droite = new VBox();
            Button DéfinirSortie = new Button("Définir sortie");
            DéfinirSortie.setOnMouseClicked(eventmenu);


            Button PoserMur = new Button("PoserMur");
            PoserMur.setOnMouseClicked(eventmenu);
            Button PoserHerbe = new Button("PoserHerbe");
            PoserHerbe.setOnMouseClicked(eventmenu);
            Button PoserMargeurite = new Button("PoserMargeurite");
            PoserMargeurite.setOnMouseClicked(eventmenu);
            Button PoserCactus = new Button("PoserCactus");
            PoserCactus.setOnMouseClicked(eventmenu);
            Button PoserMouton = new Button("PoserMouton");
            PoserMouton.setOnMouseClicked(eventmenu);
            Button PoserLoup = new Button("PoserLoup");
            PoserLoup.setOnMouseClicked(eventmenu);
            droite.getChildren().addAll(DéfinirSortie,PoserMur, PoserHerbe, PoserMargeurite, PoserCactus, PoserMouton, PoserLoup);
            HBox hbox = new HBox();
            gauche.setSpacing(10);
            droite.setSpacing(20);
            Pane paneGauche = new Pane(gauche);
            paneGauche.setStyle("-fx-padding: 10px;");
            Pane paneMilieu = new Pane(milieu);
            paneMilieu.setStyle("-fx-padding: 10px;");
            ;
            Pane paneDroite = new Pane(droite);
            paneDroite.setStyle("-fx-padding: 10px;");
            hbox.getChildren().addAll(paneGauche, paneMilieu, paneDroite);
            Scene scene = new Scene(hbox, 750, 750);

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Attrape moi si tu peux ");
            Image logo = new Image(racineProjet + "\\src\\images\\logo.png");
            stage.getIcons().add(logo);

        /*ArrayList voisins2 = new ArrayList();
        for (int y = 0;y<récup.getVoisins(récup.GetCellule(3,1)).size();y++){
            ArrayList oui = new ArrayList();
            oui.add(récup.getVoisins(récup.GetCellule(3,1)).get(y).getX());
            oui.add(récup.getVoisins(récup.GetCellule(3,1)).get(y).getY());
            voisins2.add(oui);
        }
        System.out.println("les voisins sont"+voisins2);*/
            /*System.out.println(récup.GetCellules());
            System.out.println("\n");
            System.out.println(récup.GetElements());
            System.out.println("\n");
            System.out.println("voici la cellule que je veux get en 0,1" + récup.GetCellule(0, 1));
            System.out.println("voici le X de la cellule en 0,1:" + récup.GetCellule(0, 1).getX());
            System.out.println("voici le Y de la cellule en 0,1:" + récup.GetCellule(0, 1).getY());
            System.out.println("voici le type de la cellule en 0,1:" + récup.GetCellule(0, 1).getÉlément());*/


            //TODO X correspond a la hauteur  du haut vers le bas et y a la largeur de la gauche vers la droite
            /*for (int y = 1; y < récup.getNx() - 1; y++) {

                for (int i = 1; i < récup.getNy() - 1; i++) {

                    System.out.println("je vais voir les voisins de la cellule " + y + "," + i);
                    ArrayList<Cellule> liste = récup.getVoisins(récup.GetCellule(y, i));

                    ArrayList<Element> babouin_liste = new ArrayList();
                    System.out.println("je suis la en " + y + "," + i);
                    System.out.println(liste);
                    for (int p = 0; p < liste.size(); p++) {
                        babouin_liste.add(liste.get(p).getÉlément());
                    }
                    System.out.println("voici la liste des voisins :" + babouin_liste);
                }

            }*/

            stage.show();
            if (récup.getMouton() != null){
                System.out.println(récup.getMouton().mangé());}
        }
    }

