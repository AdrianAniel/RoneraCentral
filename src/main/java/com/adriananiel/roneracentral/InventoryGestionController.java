package com.adriananiel.roneracentral;

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
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

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
    private TableColumn<Ron, String> CantidadView;

    @FXML
    private TableColumn<Ron, String> NombreView;

    @FXML
    private TableColumn<Ron, String> VencimientoView;

    @FXML
    private TextField NombreField;

    @FXML
    private TextField CantidadField;

    @FXML
    private TextField VencimientoField;

    @FXML
    private Button BtnActualizar;

    @FXML
    private Button BtnEliminar;

    public void limpiarCampos(){
        NombreField.clear();
        CantidadField.clear();
        VencimientoField.clear();
    }

    public void cargarRonInventario(){
        inventario.cargarRonesDesdeArchivo();

        ObservableList<Ron> rons = FXCollections.observableArrayList(inventario.getListaRones());

        // Asigna la lista observable al TableView
        TablaView.setItems(rons);

        // Configura las columnas del TableView
        CantidadView.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getCantidadEnAlmacen()).asString());
        NombreView.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getNombre()));
        VencimientoView.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getFechaVencimiento()));
    }

    public void examinarPonerFoto(){FileChooser fileChooser = new FileChooser();
        // Puedes agregar más filtros si es necesario
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos de imagen", "*.png", "*.jpg")); // Filtra por archivos de texto

        File selectedFile = fileChooser.showOpenDialog(null); // Pasamos null ya que estamos en un contexto de aplicación sin Stage
        if (selectedFile!= null) {
            System.out.println("Archivo seleccionado: " + selectedFile.getAbsolutePath());

            // Aquí pone la foto seleccionada con fileChooser en la UI que establecimos
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

    @FXML
    void eventExaminar(MouseEvent event) {
            examinarPonerFoto();
    }

    @FXML
    void eventAnadir(MouseEvent event) {
        String nombre = NombreField.getText();
        String cantidadEnAlmacen = CantidadField.getText();
        String fechaVencimiento = VencimientoField.getText();

        inventario.agregarRon(new Ron(nombre, Integer.parseInt(cantidadEnAlmacen), fechaVencimiento));
        System.out.println("Ron Añadido");

        limpiarCampos();

        cargarRonInventario();
    }

    @FXML
    private void eventActualizar(MouseEvent event) {
        String nombre = NombreField.getText();
        String cantidadEnAlmacen = CantidadField.getText();
        String fechaVencimiento = VencimientoField.getText();

        inventario.actualizarRon(new Ron(nombre, Integer.parseInt(cantidadEnAlmacen), fechaVencimiento));
        System.out.println("Ron actualizado");

        limpiarCampos();

        cargarRonInventario();
    }

    @FXML
    void eventEliminar(MouseEvent event) {
        String nombre = NombreField.getText();
        inventario.eliminarRon(nombre);

        limpiarCampos();

        cargarRonInventario();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cargarRonInventario();
    }
}