package v;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import m.Animaux;
import m.Mouton;




public class Défaite extends Stage {
    private Mouton mouton;

    public Défaite(Mouton mouton){
        Stage stage = new Stage();
        this.mouton = mouton;
        Button button  = new Button("Accueil");
        button.setOnMouseClicked(mouseEvent -> {
            stage.close();
            Accueil accueil = new Accueil();
        });
        Label label  = new Label(mouton.mangé());
        Label label2 = new Label("le mouton a Perdu");
        label2.setTextFill(Color.RED);
        VBox vbox = new VBox();
        vbox.getChildren().addAll(button,label2,label);
        vbox.setAlignment(Pos.TOP_CENTER);
        Scene scene = new Scene(vbox,350,200);
        stage.setScene(scene);
        String racineProjet = System.getProperty("user.dir");
        javafx.scene.image.Image logo = new Image(racineProjet + "\\src\\images\\logo.png");
            stage.getIcons().add(logo);
        stage.show();
    }
}
