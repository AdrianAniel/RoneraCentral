package com.adriananiel.roneracentral;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class InventoryGestionController {

    @FXML
    private Pane Legendaro;

    @FXML
    private Pane PaneInfo;

    @FXML
    void eventLegendaro(MouseEvent event) throws IOException {
        Pane PaneInfoFXML = FXMLLoader.load(getClass().getResource("PaneInfo.fxml"));
        PaneInfo.getChildren().setAll(PaneInfoFXML);
    }
}
