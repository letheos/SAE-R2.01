module com.example.fx_sae {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.rmi;
    requires java.desktop;
    requires jdk.jfr;
    requires javafx.media;


    opens com.example.fx_sae to javafx.fxml;
    exports com.example.fx_sae;
    exports v;
    exports m;
    exports c;
}