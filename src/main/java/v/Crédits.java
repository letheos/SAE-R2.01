package v;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;



public class Crédits extends Stage {
    public Crédits() {


        Stage stage = new Stage();
        Button accueil = new Button("accueil");
        accueil.setOnMouseClicked(mouseEvent -> {
            stage.close();
            Accueil ij = new Accueil();
        });
        Label label1 = new Label("Chef de Projet &\n Président de la SquirrelMonkeyCorp:");
        label1.setTextFill(Color.DARKRED);
        VBox bas = new VBox();
        VBox haut = new VBox();
        haut.getChildren().add(accueil);
        bas.getChildren().add(label1);
        haut.setAlignment(Pos.TOP_LEFT);
        bas.setAlignment(Pos.CENTER);

        Label label2 = new Label("Theo Parent Alias LeTheos");
        label2.setTextFill(Color.DARKBLUE);
        bas.getChildren().add(label2);
        Label label3 = new Label("\n\nVice-Président:");
        label3.setTextFill(Color.BLUE);
        Label label4 = new Label("Loick-Morneau");
        label4.setTextFill(Color.DARKRED);
        Label label5 = new Label("\n\n Membres de la société:");
        label5.setTextFill(Color.RED);
        Label label6 = new Label("Theo Duterte-Richardot");
        Label label7 = new Label("Thomas Meriaux");

        bas.getChildren().add(label3);
        bas.getChildren().add(label4);
        bas.getChildren().addAll(label5,label6,label7);
        VBox page = new VBox();
        page.getChildren().addAll(haut,bas);
        Scene scene = new Scene(page,350,500);
        String racineProjet = System.getProperty("user.dir");
        Image logo = new Image(racineProjet + "\\src\\images\\logo.png");
        stage.getIcons().add(logo);
        stage.setScene(scene);

        stage.show();
    }
}