package com.example.sae_parametre;

import javafx.application.Application;
import javafx.beans.binding.DoubleBinding;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ToggleButton;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class Parametre extends Application {
    @Override
    public void start(Stage stage) {
        BorderPane borderPane = new BorderPane();

        Text text = new Text("Paramètres");
        text.setFont(new Font(40));

        VBox vbox = new VBox();
        vbox.setSpacing(20);
        vbox.setAlignment(Pos.CENTER);

        VBox Msombre = new VBox();

        Text mode = new Text("Mode Sombre");
        Msombre.getChildren().add(mode);

        ToggleButton sombre = new ToggleButton("Activer");
        Msombre.getChildren().add(sombre);

        Msombre.setAlignment(Pos.CENTER);

        vbox.getChildren().add(Msombre);

        Text text1 = new Text("Volume Jeu");
        text1.setFont(new Font(13));
        vbox.getChildren().add(text1);

        Slider volumeSlider = new Slider(0, 100, 50);
        volumeSlider.setShowTickLabels(true);
        volumeSlider.setShowTickMarks(true);
        volumeSlider.setMaxWidth(300);

        Label volumeLabel = new Label("Volume: " + volumeSlider.getValue());

        volumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            volumeLabel.setText("Volume: " + newValue.intValue());
        });

        VBox volume = new VBox();
        volume.getChildren().addAll(text1,volumeSlider,volumeLabel);
        volume.setAlignment(Pos.CENTER);

        vbox.getChildren().add(volume);

        borderPane.setTop(text);
        borderPane.setAlignment(text, Pos.CENTER);

        Button desactiver = new Button("Désactiver musique");
        vbox.getChildren().add(desactiver);

        HBox changerM = new HBox();
        Text changer = new Text("Changer Musique");

        ChoiceBox<String> choixM = new ChoiceBox<>();
        choixM.getItems().addAll("Temporal_Tower","Temporal_Tower_(Remix)","Temporal_Spire");
        choixM.setValue("Temporal_Tower");

        changerM.getChildren().addAll(changer,choixM);
        changerM.setAlignment(Pos.CENTER);
        changerM.setSpacing(20);

        vbox.getChildren().add(changerM);

        borderPane.setCenter(vbox);
        BorderPane.setAlignment(vbox, Pos.CENTER);

        Scene scene = new Scene(borderPane, 400, 600);

        String path = getClass().getResource("Temporal_Tower.mp3").toExternalForm();
        Media media = new Media(path);
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();

        DoubleBinding volumeBinding = volumeSlider.valueProperty().divide(100);
        mediaPlayer.volumeProperty().bind(volumeBinding);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);

        EventController event = new EventController(text, mode, text1, changer, borderPane, mediaPlayer, choixM, volumeSlider);
        sombre.setOnMouseClicked(event);
        desactiver.setOnMouseClicked(event);
        choixM.setOnMouseClicked(event);

        stage.setTitle("Paramètre");
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}