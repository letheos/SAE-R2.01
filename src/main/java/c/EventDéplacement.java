package c;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Cell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import m.*;
import v.Défaite;
import v.Victoire;

import java.lang.reflect.Array;
import java.rmi.MarshalException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;

public class EventDéplacement implements EventHandler {
    private Labyrinthe récup;
    private GridPane gridPane;

    private Boolean animal;//false pour mouton et true pour loup

    private Stage stage;



    private ArrayList <Cellule> deuxtours;
    private ArrayList <Cellule> untour;

    private ArrayList<Cellule> positionsMouton;
    private ArrayList<Cellule> positionsLoup;

    public EventDéplacement(Labyrinthe labyrinthe, GridPane gridPane, Stage stage) {
        this.récup = labyrinthe;
        this.gridPane = gridPane;
        this.animal = false;
        this.stage = stage;
        this.deuxtours = new ArrayList<>();
        this.untour = new ArrayList<>();
        this.positionsMouton = new ArrayList<>();
        this.positionsLoup = new ArrayList<>();
    }

    private void repousse(Cellule cell){
        String racineProjet = System.getProperty("user.dir");
        String cheminHerbe = racineProjet + "\\src\\images\\Herbe.png";
        Image image = new Image(cheminHerbe);
        BackgroundSize backgroundSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);
        String cheminMouton = racineProjet + "\\src\\images\\Mouton.png";
        String cheminLoup = racineProjet + "\\src\\images\\Loup.png";
        Image image7 = new Image(cheminMouton);
        Image image8 = new Image(cheminLoup);
        ImageView imageView = new ImageView(image7);
        String cheminMur = racineProjet + "\\src\\images\\Mur.png";
        String chemincactus = racineProjet + "\\src\\images\\cactus.jpg";
        String cheminMargeurite = racineProjet + "\\src\\images\\Margeurites.jpg";
        String cheminTerre = racineProjet + "\\src\\images\\Terre.png";
        Image image2 = new Image(cheminMur);
        Image image3 = new Image(chemincactus);
        Image image4 = new Image(cheminMargeurite);
        Image image5 = new Image(cheminTerre);
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

        BackgroundImage backgroundImage2 = new BackgroundImage(image2, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        BackgroundImage backgroundImage3 = new BackgroundImage(image3, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        BackgroundImage backgroundImage4 = new BackgroundImage(image4, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        BackgroundImage backgroundImage5 = new BackgroundImage(image5, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background2 = new Background(backgroundImage2);
        Background background3 = new Background(backgroundImage3);
        Background background4 = new Background(backgroundImage4);
        Background background5 = new Background(backgroundImage5);
        imageView.setFitHeight(30);
        imageView.setFitWidth(30);
        imageView1.setFitHeight(30);
        imageView1.setFitWidth(50);
        imageView.setMouseTransparent(true);
        imageView.setPreserveRatio(true);
        imageView1.setMouseTransparent(true);
        imageView1.setPreserveRatio(true);
        ArrayList<Végétal> elements = new ArrayList();
        Herbe herbe = new Herbe();
        Herbe herbe1 = new Herbe();
        marguerite marg = new marguerite();
        Cactus cactus = new Cactus();
        elements.add(herbe);
        elements.add(herbe1);
        elements.add(marg);
        elements.add(cactus);
        Button button2 = null;
            for (Node node : gridPane.getChildren()) {
                if (GridPane.getColumnIndex(node) == cell.getY() && GridPane.getRowIndex(node) == cell.getX()) {
                    if (node instanceof Button) {
                        button2 = (Button) node;
                        break;
                    }
                }
            }
            Random random = new Random();
            int nombre = random.nextInt(elements.size());
            cell.setÉlément(elements.get(nombre));
            if (cell.getÉlément() instanceof Herbe){
                button2.setBackground(background);
            }
            else if (cell.getÉlément() instanceof Cactus){
                button2.setBackground(background3);
            } else if (cell.getÉlément() instanceof marguerite) {
                button2.setBackground(background4);
            }


    }
    private void déplacement(Labyrinthe récup, GridPane gridPane, int newX, int newY, ImageView imageView, ImageView imageView1, Animaux animal) {

        // Vérifier si la nouvelle position est valide

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
            if (récup.getMouton().getPosition() == récup.getLoup().getPosition()){
                Défaite defaite = new Défaite(récup.getMouton());
                stage.close();

            }
            if (récup.getMouton().getPosition() == récup.getSortie()){
                stage.close();
                Victoire victoire = new Victoire(récup.getMouton());
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
*/ System.out.println(récup.toString());
        }



    @Override
    public void handle(Event event) {
        String racineProjet = System.getProperty("user.dir");
        String cheminHerbe = racineProjet + "\\src\\images\\Herbe.png";
        Image image = new Image(cheminHerbe);
        BackgroundSize backgroundSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);
        String cheminMouton = racineProjet + "\\src\\images\\Mouton.png";
        String cheminLoup = racineProjet + "\\src\\images\\Loup.png";
        Image image7 = new Image(cheminMouton);
        Image image8 = new Image(cheminLoup);
        ImageView imageView = new ImageView(image7);
        String cheminMur = racineProjet + "\\src\\images\\Mur.png";
        String chemincactus = racineProjet + "\\src\\images\\cactus.jpg";
        String cheminMargeurite = racineProjet + "\\src\\images\\Margeurites.jpg";
        String cheminTerre = racineProjet + "\\src\\images\\Terre.png";
        Image image2 = new Image(cheminMur);
        Image image3 = new Image(chemincactus);
        Image image4 = new Image(cheminMargeurite);
        Image image5 = new Image(cheminTerre);
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
        BackgroundImage backgroundImage2 = new BackgroundImage(image2, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        BackgroundImage backgroundImage3 = new BackgroundImage(image3, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        BackgroundImage backgroundImage4 = new BackgroundImage(image4, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        BackgroundImage backgroundImage5 = new BackgroundImage(image5, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background2 = new Background(backgroundImage2);
        Background background3 = new Background(backgroundImage3);
        Background background4 = new Background(backgroundImage4);
        Background background5 = new Background(backgroundImage5);
        imageView.setFitHeight(30);
        imageView.setFitWidth(30);
        imageView1.setFitHeight(30);
        imageView1.setFitWidth(50);
        imageView.setMouseTransparent(true);
        imageView.setPreserveRatio(true);
        imageView1.setMouseTransparent(true);
        imageView1.setPreserveRatio(true);


        if (animal == true) {
            positionsLoup = new ArrayList<>();
            if ((récup.dijkstra2(récup.getLoup().getPosition(),5)).contains(récup.getMouton().getPosition())){

                        ArrayList<Cellule> chemin = récup.chemindijkstra(récup.getLoup().getPosition(),récup.getMouton().getPosition(),new ArrayList<Cellule>());


                        if (chemin.size() > 1){
                            chemin.remove(0);
                        }




                        for (int i = 0;i< 3;i++){
                            Cellule cell = chemin.get(i);
                            déplacement(this.récup,this.gridPane,cell.getX(),cell.getY(),imageView,imageView1,récup.getLoup());
                            //TODO finir le déplacement en faisant juste la boucle for qui utilise déplacement avec les cellules de la liste dans une limite de 3
                        }
                    }
            else {
                for (int x = 0; x < 3; x++) {

                    ArrayList<Cellule> voisins = récup.getVoisins(récup.getLoup().getPosition());
                    if (voisins.contains(this.récup.getSortie())) {
                        voisins.remove(this.récup.getSortie());
                    }
                    if (voisins.size() == 0) {
                        Alert impossible = new Alert(Alert.AlertType.INFORMATION);
                        impossible.setTitle("Erreur");
                        impossible.setHeaderText("Arret du jeu ");
                        impossible.setContentText("le loup ne peux pas se déplacer par conséquent le jeu va se terminer sur une victoire du mouton");
                        impossible.showAndWait();
                        stage.close();
                        Victoire v = new Victoire(récup.getMouton());
                        break;
                    } else {
                        for (Cellule cell:positionsLoup){
                        if(voisins.contains(cell)){
                            voisins.remove(cell);
                        }
                    }
                        Random random = new Random();
                        int oui = random.nextInt(voisins.size());

                        Cellule choix = voisins.get(oui);
                        int newX = choix.getX();
                        int newY = choix.getY();
                        déplacement(this.récup, this.gridPane, newX, newY, imageView, imageView1, récup.getLoup());


                    }
                }
                /*if (untour.size()> 0){

                for (int y = 0;y<this.untour.size();y++){
                    repousse(untour.get(y));
                    }
                untour.clear();
                }

            untour.addAll(deuxtours);deuxtours.clear();*/

            /*if(récup.getLoup().vision(récup) == true){
                System.out.println("le loup voit le mouton");
            }*/

            }
            this.animal = false;
                positionsMouton = new ArrayList<>();
        } else {



            //Todo modifier pour adapter au mouton
            if ((récup.dijkstra2(récup.getMouton().getPosition(),5)).contains(récup.getLoup().getPosition())){
                        ArrayList<Cellule> interdits = récup.dijkstra2(récup.getLoup().getPosition(),1);
                        if (interdits.contains(récup.getMouton().getPosition())){
                            interdits.remove(récup.getMouton().getPosition());
                }

                        ArrayList<Cellule> chemin = récup.chemindijkstra(récup.getMouton().getPosition(),récup.getSortie(),interdits);
                       if (chemin.size() > 1){
                            chemin.remove(0);
                        }

                        //TOdo finir de corriger le déplacement du loup pour qu'il attrape le mouton
                        if (récup.dijkstra2(récup.getLoup().getPosition(),2).contains(récup.getSortie())){
                                this.stage.close();
                                Défaite defaite = new Défaite(récup.getMouton());
                        }
                        else {
                            for (int i = 0; i < récup.getMouton().getDéplacement(); i++) {
                                Cellule cell = chemin.get(i);

                                déplacement(this.récup, this.gridPane, cell.getX(), cell.getY(), imageView, imageView1, récup.getMouton());

                            }
                        }
                    }

            else{
            for (int x = 0; x < this.récup.getMouton().getDéplacement(); x++) {
                ArrayList<Cellule> voisins = récup.getVoisins(récup.getMouton().getPosition());
                if (voisins.size() == 0) {
                    Alert impossible = new Alert(Alert.AlertType.INFORMATION);
                    impossible.setTitle("Erreur");
                    impossible.setHeaderText("Arret du jeu");
                    impossible.setContentText("le Mouton ne peux pas se déplacer par conséquent \n le jeu va se terminer sur une victoire du Loup");
                    impossible.showAndWait();
                    stage.close();
                    Défaite d = new Défaite(récup.getMouton());
                }
                else {
                    for (Cellule cell:positionsMouton){
                        if(voisins.contains(cell)){
                            voisins.remove(cell);
                        }
                    }
                    Random random = new Random();
                    int oui = random.nextInt(voisins.size());
                    Cellule choix = voisins.get(oui);
                    int newX = choix.getX();
                    int newY = choix.getY();
                    déplacement(this.récup, this.gridPane, newX, newY, imageView, imageView1, récup.getMouton());
                    if (stage.isShowing() == false) {
                        break;
                    }

                }
            }
            }
            if (récup.getMouton().getPosition().getÉlément() != null){
                this.deuxtours.add(récup.getMouton().getPosition());
            }
            récup.getMouton().manger(récup.getMouton().getPosition(), gridPane);
            if (this.untour.size()> 0){
                for (int x = 0;x<this.untour.size();x++){
                    repousse(untour.get(x));
                }
                untour.clear();

            }

            this.untour.addAll(this.deuxtours);
            this.deuxtours.clear();
            this.animal = true;
            /*if(récup.getMouton().vision(récup) == true){
                System.out.println("le mouton voit le Loup");
            }*/


        }
        //tODO finir d'implémenter le déplacement
    }
    }

