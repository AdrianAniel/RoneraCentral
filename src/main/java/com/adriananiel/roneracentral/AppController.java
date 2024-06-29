package com.adriananiel.roneracentral;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Hyperlink;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import java.io.IOException;

public class AppController {

    @FXML
    private Hyperlink BtnInventario;

    @FXML
    private Hyperlink BtnCuenta;

    @FXML
    private Pane PaneCambia;

    @FXML
    private Pane PaneBusqueda;

    @FXML
    private Pane PaneInfo;

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
}