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
    requires java.base;

    // Abrir el paquete al módulo javafx.fxml para permitir la inyección de campos privados
    opens com.adriananiel.roneracentral.Harold to javafx.fxml;

    // Exportar el paquete si es necesario para que otros módulos lo usen
    exports com.adriananiel.roneracentral.Harold;




    opens com.adriananiel.roneracentral to javafx.fxml;
    opens com.adriananiel.roneracentral.GestionDeProcesos to javafx.fxml;

    exports com.adriananiel.roneracentral;
    exports com.adriananiel.roneracentral.Corzo;
    opens com.adriananiel.roneracentral.Corzo to javafx.fxml;
    opens com.adriananiel.roneracentral.Alejandro;
    exports com.adriananiel.roneracentral.Alejandro to javafx.fxml;
    exports com.adriananiel.roneracentral.GestionDeProcesos to javafx.fxml;
}