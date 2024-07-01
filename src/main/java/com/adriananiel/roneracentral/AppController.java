package com.adriananiel.roneracentral;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Hyperlink;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class AppController {

    @FXML
    private Hyperlink BtnInventario;

    @FXML
    private Hyperlink BtnCuenta;

    @FXML
    private Pane PaneCambia;

    @FXML
    private Pane PaneInfo;

    @FXML
    private Hyperlink BtnCerrarSesión;
    @FXML
    private AnchorPane VentanaApp;

    private void ajustarTamanoVentana() {
        // Obtener el Stage asociado a la escena de la vista actual
        Stage stage = (Stage) VentanaApp.getScene().getWindow();

        // Establecer el tamaño de la ventana
        stage.setWidth(1000);
        stage.setHeight(600);
        stage.centerOnScreen();
    }

    @FXML
    void eventBtnCuenta(MouseEvent event) throws IOException {
        Pane ventanaGestionarCuentaFXML = FXMLLoader.load(getClass().getResource("GestionarCuenta.fxml"));
        PaneCambia.getChildren().setAll(ventanaGestionarCuentaFXML);

    }

    @FXML
    void eventBtnHome(MouseEvent event) throws IOException {
        Pane ventanaHomeFXML = FXMLLoader.load(getClass().getResource("PaneHome.fxml"));
        PaneCambia.getChildren().setAll(ventanaHomeFXML);
    }


    @FXML
    void eventBtnInventario(MouseEvent event) throws IOException {
        Pane ventanaInventarioFXML = FXMLLoader.load(getClass().getResource("InventoryGestion.fxml"));
        PaneCambia.getChildren().setAll(ventanaInventarioFXML);
    }

    @FXML
    void eventCerrarSesión(MouseEvent event) throws IOException {
        ajustarTamanoVentana();
        Pane ventanaGestionarCuentaFXML = FXMLLoader.load(getClass().getResource("SignIn.fxml"));
        VentanaApp.getChildren().setAll(ventanaGestionarCuentaFXML);

    }
}