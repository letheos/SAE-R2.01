package c;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import m.*;
import v.Défaite;
import v.Victoire;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;

public class EventDéplacement implements EventHandler {
    private Labyrinthe récup;
    private GridPane gridPane;

    private Boolean animal;//false pour mouton et true pour loup

    private Stage stage;
    private ArrayList<Cellule> positionsMouton;
    private ArrayList<Cellule> positionsLoup;


    public EventDéplacement(Labyrinthe labyrinthe, GridPane gridPane, Stage stage) {
        this.récup = labyrinthe;
        this.gridPane = gridPane;
        this.animal = false;
        this.stage = stage;
        this.positionsMouton = new ArrayList<>();
        this.positionsLoup = new ArrayList<>();
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
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
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
*/
    }//TODO optimiser le code , dans les faits il marche mais pas de manière optimale



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
        imageView.setFitHeight(30);
        imageView.setFitWidth(30);
        ImageView imageView1 = new ImageView(image8);
        imageView1.setFitHeight(30);
        imageView1.setFitWidth(50);
        imageView.setMouseTransparent(true);
        imageView.setPreserveRatio(true);
        imageView1.setMouseTransparent(true);
        imageView1.setPreserveRatio(true);
        ArrayList <Cellule> deuxtours = new ArrayList<Cellule>();
        ArrayList <Cellule> untour = new ArrayList<Cellule>();
        ArrayList<Button> Bdeuxtours = new ArrayList<Button>();
        ArrayList<Button> BunTour = new ArrayList<Button>();


        if (animal == true) {
            //loup
            ArrayList<Cellule> voisins1 = récup.getVoisins(récup.getLoup().getPosition());

            for (int x = 0; x < 3; x++) {
                ArrayList<Cellule> voisins = récup.getVoisins(récup.getLoup().getPosition());
                if(voisins.contains(this.récup.getSortie())){
                    voisins.remove(this.récup.getSortie());

                }
                if (voisins.size() == 0){
                    Alert impossible  = new Alert(Alert.AlertType.INFORMATION);
                    impossible.setTitle("Erreur");
                    impossible.setHeaderText("Arret du jeu ");
                    impossible.setContentText("le loup ne peux pas se déplacer par conséquent le jeu va se terminer sur une victoire du mouton");
                    impossible.showAndWait();
                    stage.close();
                    Victoire v = new Victoire(récup.getMouton());
                    break;
                }

                else {
                    //evite a la bestiolle de retourner sur ses pas
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
                    if(this.positionsLoup.size() == 4){
                        //si il y a 4 cellules dans la liste, on enlève la 1er positions et rajoute la dernière
                        this.positionsLoup.remove(0);
                        this.positionsLoup.add(récup.getLoup().getPosition());
                        System.out.println(positionsLoup);
                    }
                    else{
                        //si il y a moins 4 cellules dans la liste, on rajoute la dernière cellule
                        this.positionsLoup.add(récup.getLoup().getPosition());
                    }
                    System.out.println(récup.toString());


                }
                if (untour.size()> 0){
                    for (int y = 0;y<BunTour.size();y++){
                        BunTour.get(y).setBackground(background);
                        untour.get(y).setÉlément(new Herbe());
                    }
                }
                untour.addAll(deuxtours);
                BunTour.addAll(Bdeuxtours);
                Bdeuxtours.clear();
                deuxtours.clear();
                this.animal = false;
            }

        } else {
            for (int x = 0; x < this.récup.getMouton().getDéplacement(); x++) {
                ArrayList<Cellule> voisins = récup.getVoisins(récup.getMouton().getPosition());
                if (voisins.size() == 0) {
                    Alert impossible = new Alert(Alert.AlertType.INFORMATION);
                    impossible.setTitle("Erreur");
                    impossible.setHeaderText("Arret du jeu ");
                    impossible.setContentText("le Mouton ne peux pas se déplacer par conséquent \n le jeu va se terminer sur une victoire du Loup");
                    impossible.showAndWait();
                    stage.close();
                    Défaite d = new Défaite(récup.getMouton());
                } else {
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
                    if(this.positionsMouton.size() == 4){
                        //si il y a 4 cellules dans la liste, on enlève la 1er positions et rajoute la dernière
                        this.positionsMouton.remove(0);
                        this.positionsMouton.add(récup.getMouton().getPosition());
                        System.out.println(positionsMouton);
                    }
                    else{
                        //si il y a moins 4 cellules dans la liste, on rajoute la dernière cellule
                        this.positionsMouton.add(récup.getMouton().getPosition());
                    }
                    if (stage.isShowing() == false) {
                        System.out.println("arret urgence");
                        break;
                    }
                    System.out.println(Bdeuxtours);
                    System.out.println(deuxtours);
                    System.out.println(BunTour);
                    System.out.println(untour);
                    System.out.println(récup.toString());
                }

            }
            récup.getMouton().manger(récup.getMouton().getPosition(), gridPane);
            deuxtours.add(récup.getMouton().getPosition());
            Button button2 = null;
            for (Node node : gridPane.getChildren()) {
                if (GridPane.getColumnIndex(node) == récup.getMouton().getY() && GridPane.getRowIndex(node) == récup.getMouton().getX()) {
                    if (node instanceof Button) {
                        button2 = (Button) node;
                        break;
                    }
                }
            }
            Bdeuxtours.add(button2);
            System.out.println(Bdeuxtours);
            System.out.println(deuxtours);
            System.out.println(BunTour);
            System.out.println(untour);
            if (untour.size()> 0){
                for (int x = 0;x<BunTour.size();x++){
                    BunTour.get(x).setBackground(background);
                    untour.get(x).setÉlément(new Herbe());
                }
                BunTour.clear();
                untour.clear();
            }
            untour.addAll(deuxtours);
            BunTour.addAll(Bdeuxtours);
            Bdeuxtours.clear();
            deuxtours.clear();
            this.animal = true;


        }
        //tODO finir d'implémenter le déplacement
    }
}

