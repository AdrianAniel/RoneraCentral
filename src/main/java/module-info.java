module com.adriananiel.roneracentral {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.desktop;
    requires javafx.swing;

    opens com.adriananiel.roneracentral to javafx.fxml;
    opens com.adriananiel.roneracentral.GestionDeProcesos to javafx.fxml;

    exports com.adriananiel.roneracentral;
    exports com.adriananiel.roneracentral.GestionDeProcesos to javafx.fxml;
}