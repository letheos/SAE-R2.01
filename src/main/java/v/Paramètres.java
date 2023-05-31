package v;

//importation de fonction utile pour notre programe
import c.EventController;
import javafx.application.Application;
import javafx.beans.binding.DoubleBinding;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ToggleButton;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URLEncoder;

//création d'une classe Paramètre qui servira a la création de la page javaFX
public class Paramètres extends Stage {
    //création de la page
    public Paramètres() {
        //création de la stage
        Stage stage = new Stage();
        //création d'une layout pour pouvoir placer les widget sur la page comme on le souhaite
        BorderPane borderPane = new BorderPane();

        //création d'un texte qui et mis en police 40
        Text text = new Text("Paramètres");
        text.setFont(new Font(40));

        //on centre le texte
        borderPane.setTop(text);
        borderPane.setAlignment(text, Pos.CENTER);

        //création d'une VBox pour mettre des élément précis alligner à la verticale avec un espace de 20 pixel
        //et centré dans la page
        VBox vbox = new VBox();
        vbox.setSpacing(20);
        vbox.setAlignment(Pos.CENTER);

        //création d'une VBox qui contiendra les widget du mode sombre sans prendre en compte l'espacement
        //de la VBox précedente
        VBox Msombre = new VBox();

        //création d'un texte pour le mode Sombre
        Text mode = new Text("Mode Sombre");
        Msombre.getChildren().add(mode);

        //création d'un Togglebutton qui servira a activé et désactivé le mode sombre
        ToggleButton sombre = new ToggleButton("Activer");
        Msombre.getChildren().add(sombre);

        //mise des éléments du mode sombre au centre
        Msombre.setAlignment(Pos.CENTER);

        //mise de la VBox du mode sombre dans la VBox principale
        vbox.getChildren().add(Msombre);

        //création d'un texte pour les widget du volume du jeu et le met en police 13
        Text text1 = new Text("Volume Jeu");
        text1.setFont(new Font(13));
        vbox.getChildren().add(text1);

        //création d'une barre de volume pour gérer le son des musiques créer plut tard on lui met les valeurs de 0 à 100 avec comme valeur de base 50
        Slider volumeSlider = new Slider(0, 100, 50);
        volumeSlider.setShowTickLabels(true);
        volumeSlider.setShowTickMarks(true);
        volumeSlider.setMaxWidth(300);

        //affiche le volume actuel de la barre
        Label volumeLabel = new Label("Volume: " + volumeSlider.getValue());

        //modifie la valeur du label en temps réel
        volumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            volumeLabel.setText("Volume: " + newValue.intValue());
        });

        //création d'une VBox qui acceuillera les label du volume ainsi que la barre de volume et on le centre
        VBox volume = new VBox();
        volume.getChildren().addAll(text1, volumeSlider, volumeLabel);
        volume.setAlignment(Pos.CENTER);

        vbox.getChildren().add(volume);

        // création d'un bouton pour désactiver la musique
        Button desactiver = new Button("Désactiver musique");
        vbox.getChildren().add(desactiver);

        //création d'une HBox qui acceuille le texte et la choice box
        HBox changerM = new HBox();
        //création d'un texte
        Text changer = new Text("Changer Musique");

        //création de la choise box, on lui indique les choix qu'il peut donner puis on lui donne une valeur par défaut
        ChoiceBox<String> choixM = new ChoiceBox<>();
        choixM.getItems().addAll("Temporal_Tower", "Temporal_Tower_Remix", "Temporal_Spire");
        choixM.setValue("Temporal_Tower");

        //ajout du texte et la choise box dans la HBox
        changerM.getChildren().addAll(changer, choixM);
        changerM.setAlignment(Pos.CENTER);
        changerM.setSpacing(20);

        //ajout des VBox et HBox dans le borderPane
        vbox.getChildren().add(changerM);

        borderPane.setCenter(vbox);
        BorderPane.setAlignment(vbox, Pos.CENTER);

        Scene scene = new Scene(borderPane, 400, 600);

        //récupération de la musique puis création du lecteur de music
        String racine = System.getProperty("user.dir");
        String path = racine + "\\src\\Temporal_Tower_Remix.mp3";
        File file = new File(path);
        String uriString;
        try {
            uriString = file.toURI().toURL().toExternalForm();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return;
        }
        Media media = new Media(uriString);
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();

        //changement du volume en récupérant la valeur de la barre de volume en temps réel
        DoubleBinding volumeBinding = volumeSlider.valueProperty().divide(100);
        mediaPlayer.volumeProperty().bind(volumeBinding);
        //lecture en boucle de la musique
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);

        //attribution des événements
        EventController event = new EventController(text, mode, text1, changer, borderPane, mediaPlayer, choixM, volumeSlider, volumeLabel);
        sombre.setOnMouseClicked(event);
        desactiver.setOnMouseClicked(event);
        choixM.setOnMouseClicked(event);
        
        Button accueil = new Button("accueil");
        accueil.setOnMouseClicked(mouseEvent -> {
            stage.close();
            Accueil ij = new Accueil();
        });

        vbox.getChildren().add(accueil);

        //initialisation de la fenêtre
        stage.setTitle("Paramètre");
        stage.setScene(scene);
        stage.sizeToScene();
        String racineProjet = System.getProperty("user.dir");
        Image logo = new Image(racineProjet + "\\src\\images\\logo.png");
        stage.getIcons().add(logo);
        stage.show();
    }
}
