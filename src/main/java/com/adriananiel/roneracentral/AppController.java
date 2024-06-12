package com.adriananiel.roneracentral;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class AppController {

    @FXML
    private Pane BtnInventario;

    @FXML
    private Pane PaneInventario;

    @FXML
    private Pane PaneButtons;

    @FXML
    private Pane LabelMenu;


    @FXML
    void eventBtnInventario(MouseEvent event) throws IOException {
        Pane ventanaInventarioFXML = FXMLLoader.load(getClass().getResource("InventoryGestion.fxml"));
        PaneInventario.getChildren().setAll(ventanaInventarioFXML);

        Pane ventanaButtonsFXML = FXMLLoader.load(getClass().getResource("Buttons.fxml"));
        PaneButtons.getChildren().setAll(ventanaButtonsFXML);

        Pane paneLabelMenu = FXMLLoader.load(getClass().getResource("InventoryManagementLabel.fxml"));
        LabelMenu.getChildren().setAll(paneLabelMenu);

    }

}
