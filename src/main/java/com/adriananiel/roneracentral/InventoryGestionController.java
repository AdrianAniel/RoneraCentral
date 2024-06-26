package com.adriananiel.roneracentral;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import com.adriananiel.roneracentral.Inventario;
import com.adriananiel.roneracentral.Ron;

public class InventoryGestionController implements Initializable {

    private Inventario inventario = new Inventario();
    private ObservableList<Ron> rons = FXCollections.observableArrayList();

    @FXML
    private Pane PaneInfo;

    @FXML
    private Button BtnExaminar;

    @FXML
    private ImageView imagenPuesta;

    @FXML
    private TableView<Ron> TablaView;

    @FXML
    private TableColumn<Ron, Integer> CantidadView;

    @FXML
    private TableColumn<Ron, String> NombreView;

    @FXML
    private TableColumn<Ron, String> VencimientoView;



    @FXML
    void eventExaminar(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();
        // Puedes agregar más filtros si es necesario
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos de imagen", "*.png", "*.jpg")); // Filtra por archivos de texto

        File selectedFile = fileChooser.showOpenDialog(null); // Pasamos null ya que estamos en un contexto de aplicación sin Stage
        if (selectedFile!= null) {
            System.out.println("Archivo seleccionado: " + selectedFile.getAbsolutePath());
            // Aquí puedes procesar el archivo seleccionado
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        inventario.cargarRonesDesdeArchivo();

        ObservableList<Ron> rons = FXCollections.observableArrayList(inventario.getListaRones());

        // Asigna la lista observable al TableView
        TablaView.setItems(rons);

        // Configura las columnas del TableView
        CantidadView.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getCantidadEnAlmacen()));
        NombreView.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getNombre()));
        VencimientoView.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getFechaVencimiento()));

    }
}