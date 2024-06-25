package com.adriananiel.roneracentral;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javafx.scene.image.ImageView;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;

public class InventoryGestionController {

    @FXML
    private Pane PaneInfo;

    @FXML
    private Button BtnExaminar;

    @FXML
    private ImageView imagenPuesta;

    @FXML
    void eventExaminar(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();
        // Puedes agregar más filtros si es necesario
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos de imagen", "*.png", "*.jpg")); // Filtra por archivos de texto

        File selectedFile = fileChooser.showOpenDialog(null); // Pasamos null ya que estamos en un contexto de aplicación sin Stage
        if (selectedFile!= null) {
            System.out.println("Archivo seleccionado: " + selectedFile.getAbsolutePath());
            // Aquí puedes procesar el archivo seleccionado
        }
        try {
            // Cargar la imagen utilizando ImageIO
            BufferedImage bufferedImage = ImageIO.read(selectedFile);
            WritableImage image = SwingFXUtils.toFXImage(bufferedImage, null);
            imagenPuesta.setImage(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}