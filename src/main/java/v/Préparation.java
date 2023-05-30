package v;

import c.*;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import m.*;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import c.EvenementsMenu;
import c.EventGridPane;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class Préparation extends Stage implements Serializable {
    private  int nx;
    private int ny;

    private Labyrinthe récup;

    public Préparation(int nx,int ny) throws IOException, ClassNotFoundException {
        this.nx = nx;
        this.ny = ny;
        Stage stage = new Stage();
        Labyrinthe test;
        //nx correspond a la hauteur et ny a la largeur
        test = new Labyrinthe(this.nx, this.ny);


        System.out.println(test.GetCellules());
        System.out.println("le labyrinthe est un " + test.getNx() + test.getNy());



        //mouton doit etre a x = ny- et y = nx-2 si tout en bas a droite


        System.out.println(test.
                toString());

        System.out.println(test.toString());
        test.sauvegarderLabyrinthe("labyrintheprefait.dat");
        this.récup = Labyrinthe.chargerLabyrinthe("labyrintheprefait.dat");
        System.out.println(this.récup.toString());
        /*int babouin_compteur = 0;
        for (int x = 0; x < this.récup.getNx(); x++) {
            for (int y = 0; y < test.getNy(); y++) {
                if (this.récup.DéfinirSortie(x, y) == true) {
                    babouin_compteur += 1;
                }
            }
        }*/


        System.out.println(this.récup.toString());


        //System.out.println("les voisins sont"+this.récup.getVoisins(8,8));
        //System.out.println("les voisins sont"+this.récup.getVoisins(4,4));
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
        EventGridPane eventPane = new EventGridPane(eventmenu, this.récup, gridPane);
        for (int row = 0; row < this.récup.getNx(); row++) {
            for (int col = 0; col < this.récup.getNy(); col++) {
                Button button = new Button();
                button.setMinSize(50, 50);
                button.setMaxSize(50, 50);

                if (this.récup.GetCellule(row, col).getÉlément() instanceof Mur) {
                    button.setBackground(background2);
                } else if (this.récup.GetCellule(row, col).getÉlément() instanceof Cactus) {
                    button.setBackground(background3);
                } else if (this.récup.GetCellule(row, col).getÉlément() instanceof marguerite) {
                    button.setBackground(background4);
                } else if (this.récup.GetCellule(row, col).getÉlément() == null) {
                    button.setBackground(background5);
                } else {
                    button.setBackground(background);
                }

                if (this.récup.getMouton() != null && this.récup.GetCellule(row, col).equals(this.récup.getMouton().getPosition())) {
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
                } else if (this.récup.getLoup() != null && this.récup.GetCellule(row, col).equals(this.récup.getLoup().getPosition())) {
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
                button.setOnMouseEntered(mouseEvent -> {
                    button.setStyle("-fx-border-color: blue;");
                });
                button.setOnMouseExited(mouseEvent -> {
                    button.setStyle("-fx-border-color: black;");
                });
                gridPane.add(button, col, row);
            }
        }



        gridPane.setLayoutX(0);
        gridPane.setLayoutY(0);


        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setMaxSize(500, 500);
        scrollPane.setContent(gridPane);


        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(scrollPane);
        Button boutonaccueil = new Button("Accueil");
        boutonaccueil.setOnMouseClicked(mouseEvent -> {
            Accueil ij = new Accueil();
            stage.close();
        });

        Label labelTaille = new Label("Taille");
        ComboBox<Integer> hauteur = new ComboBox<>();
        hauteur.setPromptText("hauteur");
        ComboBox<Integer> largeur = new ComboBox<>();
        largeur.setPromptText("largeur");

        for (int i = 4; i <= 40; i++) {
            hauteur.getItems().add(i);
            largeur.getItems().add(i);
        }

        hauteur.setOnAction(actionEvent -> {
            this.nx = (int) hauteur.getValue();

        });

        largeur.setOnAction(actionEvent -> {
            this.ny = (int) largeur.getValue();

        });
        ComboBox<String> environnement = new ComboBox();
        environnement.getItems().add("Normal");
        environnement.getItems().add("aquatique");
        environnement.getItems().add("Pokémon");
        Button générerLabyrinthe = new Button("Générer Labyrinthe");
        générerLabyrinthe.setOnMouseClicked(mouseEvent -> {
            if(hauteur.getValue() != null && largeur.getValue() != null){
                this.récup = new Labyrinthe(hauteur.getValue(),largeur.getValue());
                System.out.println("le labyrinthe a bien été changé en x:"+this.récup.getNx()+"y:"+this.récup.getNy());
                this.récup.toString();
                System.out.println(this.récup.getNx());
                System.out.println(this.récup.getNy());
                EventGénération eventGénération = new EventGénération(hauteur.getValue(),largeur.getValue(),this.récup,eventmenu);
                scrollPane.setContent(eventGénération.getGridPane());}
            else{
                Alert reportThomasCeTrolleur = new Alert(Alert.AlertType.INFORMATION);
                reportThomasCeTrolleur.setTitle("Erreur");
                reportThomasCeTrolleur.setHeaderText("Action impossible");
                reportThomasCeTrolleur.setContentText("Vous essayez de générer un labyrinthe alors que vous n'avez pas sélectionné correctement les deux valeurs hauteur et largeur");
                reportThomasCeTrolleur.showAndWait();
            }

        });
        Button Lancer = new Button("Lancer");
        Lancer.setMinSize(100, 100);
        Lancer.setMaxSize(100, 100);

        Button chargerLabyrinthe = new Button("ChargerLabyrinthe");
        chargerLabyrinthe.setOnMouseClicked(mouseEvent -> {
            File debut = new File(racineProjet);
            FileChooser explorateur = new FileChooser();

            explorateur.setInitialDirectory(debut);
            FileChooser.ExtensionFilter filtreText = new FileChooser.ExtensionFilter("Text", "*.txt");
            explorateur.getExtensionFilters().add(filtreText);
            File dossierSelec = explorateur.showOpenDialog(stage);
            if (dossierSelec != null) {

                System.out.println(dossierSelec.getAbsolutePath());
                String recette = test.recup(dossierSelec.getAbsolutePath());
                this.récup = new Labyrinthe(test.recupToLaby(recette));

                //System.out.println(test.toString());
                EventGénération eventGénération = new EventGénération(récup.getNx(),récup.getNy(),this.récup,eventmenu);
                scrollPane.setContent(eventGénération.getGridPane());
            }
        });


        Button Aleatoire = new Button("Aléatoire");

        Aleatoire.setOnMouseClicked(mouseEvent -> {
            this.récup = new Labyrinthe(test);
            récup.aleatoire();
            EventGénération eventGénération = new EventGénération(récup.getNx(),récup.getNy(),this.récup,eventmenu);
            scrollPane.setContent(eventGénération.getGridPane());

        });

        Button sauvegarde = new Button("sauvergarde");
        EventFonction ef = new EventFonction(test);
        sauvegarde.setOnMouseClicked(ef);

        VBox gauche = new VBox();
        //TODO finir de corriger le bug
        gauche.getChildren().addAll(boutonaccueil, labelTaille, hauteur, largeur, Lancer, générerLabyrinthe,chargerLabyrinthe,Aleatoire,sauvegarde);
        VBox milieu = new VBox();
        milieu.getChildren().add(stackPane);
        VBox droite = new VBox();
        Lancer.setOnMouseClicked(mouseEvent -> {
                    System.out.println("voici le labyrinthe que je prend en entrée");
                    System.out.println("voici le mouton : " + this.récup.getMouton());
                    System.out.println("voici le Loup :" + this.récup.getLoup());
                    System.out.println("voici la sortie : " + this.récup.getSortie());
                    System.out.println(this.récup.toString());
                    if (this.récup.getSortie() != null && this.récup.getMouton() != null && this.récup.getLoup() != null) {

                        for (Node node : gauche.getChildren()) {
                            node.setVisible(false);
                        }
                        for (Node node : droite.getChildren()) {
                            node.setVisible(false);

                        }
                        Button automatique = new Button("Automatique");
                        Button Jouertour = new Button("Jouer Tour");
                        Jouertour.setOnMouseClicked(mouseEvent1 ->
                        {

                            //TODO implémenter le déplacement alternatif pour loup et mouton
                            ArrayList<Cellule> voisins = this.récup.getVoisins(this.récup.getMouton().getPosition());
                            Random random = new Random();
                            Cellule cell = voisins.get(random.nextInt(voisins.size()));
                        });
                        HBox boutons = new HBox();
                        boutons.getChildren().addAll(automatique, Jouertour);
                        boutons.setAlignment(Pos.CENTER);
                        boutons.setSpacing(50);
                        milieu.getChildren().add(boutons);
                        eventmenu.setAction("null");
                    } else {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Erreur");
                        alert.setHeaderText("Lancement impossible");
                        alert.setContentText("Il est impossible de lancer le jeu si celui-ci ne possède pas au moins un \n -Une sortie \n -Un Loup \n -Un Mouton");
                        alert.showAndWait();
                    }
                });


        Button DéfinirSortie = new Button("Définir sortie");
        DéfinirSortie.setOnMouseClicked(eventmenu);
        //TODO finir d'implémenter le déplacement , rajouter logo accueil et empêcher le lancement si as de sortie, pas de mouton et pas de loup

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


        stage.setScene(scene);
        stage.setTitle("Attrape moi si tu peux ");
        Image logo = new Image(racineProjet + "\\src\\images\\logo.png");
        stage.getIcons().add(logo);

        /*ArrayList voisins2 = new ArrayList();
        for (int y = 0;y<this.récup.getVoisins(this.récup.GetCellule(3,1)).size();y++){
            ArrayList oui = new ArrayList();
            oui.add(this.récup.getVoisins(this.récup.GetCellule(3,1)).get(y).getX());
            oui.add(this.récup.getVoisins(this.récup.GetCellule(3,1)).get(y).getY());
            voisins2.add(oui);
        }
        System.out.println("les voisins sont"+voisins2);*/
            /*System.out.println(this.récup.GetCellules());
            System.out.println("\n");
            System.out.println(this.récup.GetElements());
            System.out.println("\n");
            System.out.println("voici la cellule que je veux get en 0,1" + this.récup.GetCellule(0, 1));
            System.out.println("voici le X de la cellule en 0,1:" + this.récup.GetCellule(0, 1).getX());
            System.out.println("voici le Y de la cellule en 0,1:" + this.récup.GetCellule(0, 1).getY());
            System.out.println("voici le type de la cellule en 0,1:" + this.récup.GetCellule(0, 1).getÉlément());*/


        //TODO X correspond a la hauteur  du haut vers le bas et y a la largeur de la gauche vers la droite
            /*for (int y = 1; y < this.récup.getNx() - 1; y++) {

                for (int i = 1; i < this.récup.getNy() - 1; i++) {

                    System.out.println("je vais voir les voisins de la cellule " + y + "," + i);
                    ArrayList<Cellule> liste = this.récup.getVoisins(this.récup.GetCellule(y, i));

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
        if (this.récup.getMouton() != null){
            System.out.println(this.récup.getMouton().mangé());}
    }

    public int getNx(){
        return this.nx;
    }
    public int getNy(){
        return this.ny;
    }
}
