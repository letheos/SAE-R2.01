package v;
import c.EventAccueil;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Accueil extends Stage  {


    public Accueil()  {
        Stage stage = new Stage();
        stage.setTitle("Accueil");
        Button Lancer = new Button("Lancer");
        Lancer.setOnMouseClicked(new EventAccueil(stage));
        Button quitter = new Button("quitter");
        quitter.setOnMouseClicked(new EventAccueil(stage));
        Button Crédits = new Button("Crédits");
        Crédits.setOnMouseClicked(new EventAccueil(stage));
        Button Paramètres = new Button("Paramètres");
        Paramètres.setOnMouseClicked(new EventAccueil(stage));
        VBox vbox = new VBox();
        vbox.getChildren().addAll(Lancer,Paramètres,Crédits,quitter);
        vbox.setSpacing(40);
        vbox.setAlignment(Pos.TOP_CENTER);
        vbox.setPadding(new Insets(20, 0, 0, 0));
        Scene scene = new Scene(vbox,250,350);
        String racineProjet = System.getProperty("user.dir");
        Image logo = new Image(racineProjet + "\\src\\images\\logo.png");
        stage.getIcons().add(logo);
        stage.setScene(scene);
        stage.show();

    }


}