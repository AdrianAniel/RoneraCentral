package com.adriananiel.roneracentral.GestionDeProcesos;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProcesosController implements Initializable {
    @FXML
    private Button btnPedidos;

    @FXML
    private Button btnReportes;

    @FXML
    private Button btnSumunistros;

    @FXML
    private BorderPane panel;

    @FXML
    void eventBtnPedidos(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Pedidos.fxml"));
        Pane ventanaGestionarCuentaFXML = loader.load();
        panel.setCenter(ventanaGestionarCuentaFXML);

        PedidosController pedidosController = loader.getController();
        pedidosController.setController(this);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Pane ventanaGestionarCuentaFXML = null;
        try {
            ventanaGestionarCuentaFXML = FXMLLoader.load(getClass().getResource("ProcesosHome.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        panel.setCenter(ventanaGestionarCuentaFXML);
    }
}
