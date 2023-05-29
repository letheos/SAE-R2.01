package c;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.input.MouseEvent;

public class EventController implements EventHandler<MouseEvent> {
    private Scene scene;
    private BorderPane borderPane;

    public EventController(Scene scene, BorderPane borderPane) {
        this.scene = scene;
        this.borderPane = borderPane;
    }

    public EventController() {
    }

    @Override
    public void handle(MouseEvent event) {
        if (event.getSource() instanceof ToggleButton) {
            ToggleButton sombre = (ToggleButton) event.getSource();
            if (sombre.isSelected()) {
                System.out.println("Activé");
                sombre.setText("Désactiver");
                scene.setFill(Color.BLACK);
                borderPane.requestLayout();
            } else {
                System.out.println("Désactivé");
                sombre.setText("Activer");
                scene.setFill(Color.BLUE);
                borderPane.requestLayout();
            }
        }
    }
}