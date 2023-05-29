package c;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import m.Labyrinthe;

public class EventLancement implements EventHandler {
    private VBox gauche;
    private VBox droite;

    private VBox milieu;

    private Labyrinthe laby;

    private EvenementsMenu evenementsMenu;
    public EventLancement(VBox gauche,VBox milieu,VBox droite,EvenementsMenu event,Labyrinthe laby){
        this.gauche  = gauche;
        this.droite = droite;
        this.milieu = milieu;
        this.evenementsMenu = event;
        this.laby = laby;
    }
    public void handle(Event event) {
        System.out.println("voici mon labyrinthe"+laby.getNx()+" "+laby.getNy());
        if(this.laby.getSortie() != null && this.laby.getMouton() != null && this.laby.getLoup() != null){

        for (Node node : gauche.getChildren()) {
        node.setVisible(false);
    }
        for (Node node : droite.getChildren()){
            node.setVisible(false);

        }
        Button automatique = new Button("Automatique");
        Button Jouertour = new Button("Jouer Tour");
        HBox boutons = new HBox();
        boutons.getChildren().addAll(automatique,Jouertour);
        boutons.setAlignment(Pos.CENTER);
        boutons.setSpacing(50);
        this.milieu.getChildren().add(boutons);
        this.evenementsMenu.setAction("null");
    }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Erreur");
            alert.setHeaderText("Lancement impossible");
            alert.setContentText("Il est impossible de lancer le jeu si celui-ci ne possède pas au moins un \n -Une sortie \n -Un Loup \n -Un Mouton");
            alert.showAndWait();
        }
    }


}
