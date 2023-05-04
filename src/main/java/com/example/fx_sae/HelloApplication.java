package com.example.fx_sae;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Image image = new Image("C:\\Users\\depla\\OneDrive\\Images\\Herbe.Png");
BackgroundSize backgroundSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false);
BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
Background background = new Background(backgroundImage);

        Parent gridPane = new GridPane();
        for (int row = 20; row < 30; row++) {
    for (int col = 20; col < 30; col++) {
        Button button = new Button();
        button.setPrefSize(50, 50);
        button.setBackground(background);
        button.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        ((GridPane) gridPane).add(button, col, row);
    }
}
    Scene scene = new Scene(gridPane, 400, 400);
    stage = new Stage();
    stage.setScene(scene);
    stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}