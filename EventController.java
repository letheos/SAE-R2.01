package com.example.sae_parametre;

import javafx.beans.binding.DoubleBinding;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class EventController implements EventHandler<MouseEvent> {
    private Text text;
    private Text mode;
    private Text text1;
    private Text changer;
    private BorderPane borderPane;
    private ChoiceBox<String> choixM;
    private Slider vol;
    private MediaPlayer mediaPlayer;

    public EventController(Text t, Text m, Text t1, Text c, BorderPane borderPane, MediaPlayer mediaPlayer, ChoiceBox<String> choixM, Slider v) {
        this.text = t;
        this.mode = m;
        this.text1 = t1;
        this.changer = c;
        this.borderPane = borderPane;
        this.choixM = choixM;
        this.vol = v;
        this.mediaPlayer = mediaPlayer;
    }

    public EventController() {
    }

    @Override
    public void handle(MouseEvent event) {
        if (event.getSource() instanceof ToggleButton) {
            ToggleButton sombre = (ToggleButton) event.getSource();
            if (sombre.isSelected()) {
                sombre.setText("Désactiver");
                borderPane.backgroundProperty().set(Background.fill(Color.BLACK));
                text.setFill(Color.WHITE);
                mode.setFill(Color.WHITE);
                text1.setFill(Color.WHITE);
                changer.setFill(Color.WHITE);
            } else {
                sombre.setText("Activer");
                borderPane.backgroundProperty().set(Background.fill(Color.WHITE));
                text.setFill(Color.BLACK);
                mode.setFill(Color.BLACK);
                text1.setFill(Color.BLACK);
                changer.setFill(Color.BLACK);
            }
        } else if (event.getSource().getClass().toString().contains("Button")){
            mediaPlayer.stop();
        } choixM.setOnAction(events -> {
            String newValue = choixM.getValue();
            String newMusicPath = getClass().getResource(newValue + ".mp3").toExternalForm();
            mediaPlayer.stop();
            Media newMedia = new Media(newMusicPath);
            mediaPlayer = new MediaPlayer(newMedia);
            mediaPlayer.play();

            DoubleBinding volumeBinding = vol.valueProperty().divide(100);
            mediaPlayer.volumeProperty().bind(volumeBinding);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        });
    }
}