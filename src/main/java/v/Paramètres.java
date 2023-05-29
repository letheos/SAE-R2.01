package v;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Paramètres extends Stage {

    public Paramètres() {
        Stage stage = new Stage();
        Label label = new Label("ptdr c'était le travail de thomas");
        Scene scene = new Scene(label, 200, 200);
        stage.setScene(scene);
        stage.show();
    }
}
