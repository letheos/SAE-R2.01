module com.example.fx_sae {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.rmi;
    requires java.desktop;
    requires javafx.media;


    opens com.example.fx_sae to javafx.fxml;
    exports com.example.fx_sae;
    exports v;
}