package com.example.fx_sae;


import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.util.Objects;
import java.util.Random;

public class tmp implements EventHandler {
    private GridPane gridPane;
    private Labyrinthe labyrinthe;
    private String action;

    public tmp(GridPane gridPane, Labyrinthe labyrinthe) {
        this.gridPane = gridPane;
        this.labyrinthe = labyrinthe;
    }

    @Override
    public void handle(Event event) {
        //Object source;
        if (event.getSource() instanceof Button) {
            Button button = (Button) event.getSource();
            Random random = new Random();
            int x = random.nextInt(labyrinthe.getNy() - 2) + 1;
            int y = random.nextInt(labyrinthe.getNx() - 2) + 1;
            //labyrinthe.PoserMargueurite(x, y);
            //Cellule cell = labyrinthe.GetCellule(x, y);
            Button button2 = null;
            for (Node node : gridPane.getChildren()) {
                if (GridPane.getColumnIndex(node) == x && GridPane.getRowIndex(node) == y) {
                    if (node instanceof Button) {
                        button2 = (Button) node;
                        break;
                    }
                }
                if (Objects.equals(button.getText(), "Placer Mur")) {
                    labyrinthe.PoserMur(x, y);
                    Cellule cell = labyrinthe.GetCellule(x, y);
                    assert button2 != null;
                    button2.setPrefSize(50, 50);
                    Image imageMargeurite = new Image("C:\\document\\cour\\sae s2\\sae s201-202\\SAE-R2.01-Theos\\Mur.jpg");
                    BackgroundImage backMargeurite = new BackgroundImage(imageMargeurite, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(50, 50, false, false, false, false));
                    Background backImMar = new Background(backMargeurite);
                    button2.setBackground(backImMar);
                } else if (button.getText().equals("placer Marguerite")) {
                    labyrinthe.PoserMargueurite(x, y);
                    assert button2 != null;
                    button2.setPrefSize(50, 50);
                    Image imageMargeurite = new Image("C:\\document\\cour\\sae s2\\sae s201-202\\SAE-R2.01-Theos\\Margeurites.jpg");
                    BackgroundImage backMargeurite = new BackgroundImage(imageMargeurite, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(50, 50, false, false, false, false));
                    Background backImMar = new Background(backMargeurite);
                    button2.setBackground(backImMar);

                } else if (button.getText().equals("placer Cactus")) {
                    labyrinthe.PoserCactus(x, y);
                    assert button2 != null;
                    button2.setPrefSize(50, 50);
                    Image imageCactus = new Image("C:\\document\\cour\\sae s2\\sae s201-202\\SAE-R2.01-Theos\\cactus.jpg");
                    BackgroundImage backCactus = new BackgroundImage(imageCactus, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(50, 50, false, false, false, false));
                    Background backImCac = new Background(backCactus);
                    button2.setBackground(backImCac);
                } else if (button.getText().equals("Placer Herbe")) {
                    labyrinthe.PoserHerbe(x, y);
                    assert button2 != null;
                    button2.setPrefSize(50, 50);
                    Image imageCactus = new Image("C:\\document\\cour\\sae s2\\sae s201-202\\SAE-R2.01-Theos\\Herbe.png");
                    BackgroundImage backCactus = new BackgroundImage(imageCactus, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(50, 50, false, false, false, false));
                    Background backImCac = new Background(backCactus);
                    button2.setBackground(backImCac);
                    labyrinthe.toString();
                    System.out.println("oiefzpifepzi");

                } else if (button.getText().equals("Placer Terre")) {
                    Cellule cell = labyrinthe.GetCellule(x, y);
                    cell.setÉlément(null);
                    button2.setPrefSize(50, 50);
                    Image imageCactus = new Image("C:\\document\\cour\\sae s2\\sae s201-202\\SAE-R2.01-Theos\\pingouin.jpg");
                    BackgroundImage backCactus = new BackgroundImage(imageCactus, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(50, 50, false, false, false, false));
                    Background backImCac = new Background(backCactus);
                    button2.setBackground(backImCac);
                }
            }
        }
    }


    public String getAction() {
        return this.action;
    }

    public tmp() {

    }
}