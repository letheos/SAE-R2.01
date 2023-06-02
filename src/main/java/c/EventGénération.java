package c;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import m.Cactus;
import m.Labyrinthe;
import m.Mur;
import m.marguerite;

public class EventGénération {
    private int x;
    private int y;

    private Labyrinthe récup;
    private EvenementsMenu eventmenu;

    private GridPane gridPane;

    public EventGénération(int x , int y,Labyrinthe laby,EvenementsMenu eventMenu){
        this.x = x;
        this.y = y;
        this.récup = laby;
        this.eventmenu = eventMenu;


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


        GridPane gridPane = new GridPane();
        EventGridPane eventPane = new EventGridPane(eventmenu, récup, gridPane);
        for (int row = 0; row < this.x; row++) {
            for (int col = 0; col < this.y; col++) {

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
                button.setOnMouseEntered(mouseEvent -> {
                    button.setStyle("-fx-border-color: blue;");
                });
                button.setOnMouseExited(mouseEvent -> {
                    button.setStyle("-fx-border-color: black;");
                });

                gridPane.add(button, col, row);
                this.gridPane  =gridPane;

            }
        }
    }
    public GridPane getGridPane(){
        return this.gridPane;
    }
}