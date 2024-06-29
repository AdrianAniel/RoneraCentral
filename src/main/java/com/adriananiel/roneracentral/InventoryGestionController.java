package com.adriananiel.roneracentral;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;

public class InventoryGestionController implements Initializable {

    private static String imagePath;
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
    private DatePicker VencimientoDatePicker;

    @FXML
    private Button BtnActualizar;

    @FXML
    private Button BtnEliminar;

    @FXML
    private TableColumn<Ron, String> FotoView;

    public void limpiarCampos(){
        NombreField.clear();
        CantidadField.clear();
        VencimientoDatePicker.setValue(null);
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
        FotoView.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getDireccionImagen()));
    }

    public void examinarPonerFoto(){FileChooser fileChooser = new FileChooser();
        // Puedes agregar más filtros si es necesario
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos de imagen", "*.png", "*.jpg")); // Filtra por archivos de texto

        File selectedFile = fileChooser.showOpenDialog(null); // Pasamos null ya que estamos en un contexto de aplicación sin Stage
        if (selectedFile!= null) {
            imagePath = selectedFile.getAbsolutePath();
            System.out.println("Archivo seleccionado: " + selectedFile.getAbsolutePath());

            // Aquí pone la foto seleccionada con fileChooser en la UI que establecimos
            try {
                // Cargar la imagen utilizando ImageIO
                BufferedImage bufferedImage = ImageIO.read(selectedFile);
                WritableImage image = SwingFXUtils.toFXImage(bufferedImage, null);
                imagenPuesta.setImage(image);
                System.out.println("Imagen cargada: " + image.getUrl());
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
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fechaVencimiento = VencimientoDatePicker.getValue();
        String fechaVencimiento1 = fechaVencimiento.format(dateFormatter);
        String direccionImagen = imagePath;

        inventario.agregarRon(new Ron(nombre, Integer.parseInt(cantidadEnAlmacen), fechaVencimiento1, direccionImagen));
        System.out.println("Ron Añadido");

        limpiarCampos();

        cargarRonInventario();
    }

    @FXML
    private void eventActualizar(MouseEvent event) {
        String nombre = NombreField.getText();
        String cantidadEnAlmacen = CantidadField.getText();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fechaVencimiento = VencimientoDatePicker.getValue();
        String fechaVencimiento1 = fechaVencimiento.format(dateFormatter);
        String direccionImagen = imagePath;

        inventario.actualizarRon(new Ron(nombre, Integer.parseInt(cantidadEnAlmacen), fechaVencimiento1, direccionImagen));
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