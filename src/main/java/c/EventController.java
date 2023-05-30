package c;

//importation des fonctions utiles
import javafx.beans.binding.DoubleBinding;
import javafx.event.EventHandler;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
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
    //importation des variable pour modifier la fenêtre
    private Text text;
    private Text mode;
    private Text text1;
    private Text changer;
    private BorderPane borderPane;
    private ChoiceBox<String> choixM;
    private Slider vol;
    private Label vollabel;
    private MediaPlayer mediaPlayer;

    //création d'un constructeur
    public EventController(Text t, Text m, Text t1, Text c, BorderPane borderPane, MediaPlayer mediaPlayer, ChoiceBox<String> choixM, Slider v, Label vollab) {
        this.text = t;
        this.mode = m;
        this.text1 = t1;
        this.changer = c;
        this.borderPane = borderPane;
        this.choixM = choixM;
        this.vol = v;
        this.vollabel = vollab;
        this.mediaPlayer = mediaPlayer;
    }

    //création d'un constructeur null
    public EventController() {
    }

    @Override
    public void handle(MouseEvent event) {
        //vérification si la source de l'événement est un bouton boolean
        if (event.getSource() instanceof ToggleButton) {
            ToggleButton sombre = (ToggleButton) event.getSource();
            //si le bouton est sur le mode inactif, change le texte du bouton, la couleur du texte, et la couleur de fond
            if (sombre.isSelected()) {
                sombre.setText("Désactiver");
                borderPane.backgroundProperty().set(Background.fill(Color.valueOf("#534643")));
                text.setFill(Color.WHITE);
                mode.setFill(Color.WHITE);
                text1.setFill(Color.WHITE);
                changer.setFill(Color.WHITE);
                vollabel.setTextFill(Color.WHITE);
            } else { //sinon on rétablie le texte du bouton et les couleurs
                sombre.setText("Activer");
                borderPane.backgroundProperty().set(Background.fill(Color.WHITE));
                text.setFill(Color.BLACK);
                mode.setFill(Color.BLACK);
                text1.setFill(Color.BLACK);
                changer.setFill(Color.BLACK);
                vollabel.setTextFill(Color.BLACK);
            }
        } else if (event.getSource().getClass().toString().contains("Button")){//si l'événement vient d'un bouton, arrête la musique
            mediaPlayer.stop();
        } choixM.setOnAction(events -> {//si l'événement vient de la choise box lorsqu'un choix est donner
            //la valeur du choix est mis dans cette variable
            String newValue = choixM.getValue();
            //la valeur est modifier, on rajoute .mp3 au fichier puis on cherche le fichier dans le dossier ressouce
            String newMusicPath = getClass().getResource(newValue + ".mp3").toExternalForm();
            //on arrête l'ancienne musique puis on lance la nouvelle
            mediaPlayer.stop();
            Media newMedia = new Media(newMusicPath);
            mediaPlayer = new MediaPlayer(newMedia);
            mediaPlayer.play();

            //récupération de la valeur de la barre de volume en temps réel pour régler le volume
            DoubleBinding volumeBinding = vol.valueProperty().divide(100);
            mediaPlayer.volumeProperty().bind(volumeBinding);
            //lecture en boucle de la musique
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        });
    }
}
