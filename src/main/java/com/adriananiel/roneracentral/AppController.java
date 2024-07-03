package com.adriananiel.roneracentral;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

    public void cerrarAbrirVentana(){
        // Obtener el Stage desde el PaneGestionarCuenta
        Stage stage = (Stage) VentanaApp.getScene().getWindow();
        stage.close(); // Cierra la ventana

        try {
            // Cargar el FXML de la nueva ventana
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SignIn.fxml"));
            Parent root = loader.load(); // Esto carga el FXML y devuelve el nodo raíz
            SignInController controller = loader.getController(); // Obtiene el controlador de la vista cargada

            // Crear una nueva escena y mostrarla en una nueva ventana
            Stage nuevaVentana = new Stage();
            Scene escenaNuevaVentana = new Scene(root);
            nuevaVentana.setScene(escenaNuevaVentana);
            nuevaVentana.show();
        } catch (IOException e) {
            System.err.println("Error al cargar la nueva ventana: " + e.getMessage());
        }
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
        cerrarAbrirVentana();
    }
}