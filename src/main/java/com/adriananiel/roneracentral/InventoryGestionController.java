package com.adriananiel.roneracentral;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Collection;
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
    private InventarioRon inventario = new InventarioRon();
    private InventarioMateriaPrima inventarioMateriaPrima = new InventarioMateriaPrima();
    private ObservableList<Ron> rons = FXCollections.observableArrayList();
    private ObservableList<MateriaPrima> materiaPrima = FXCollections.observableArrayList();

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

    @FXML
    private TableColumn<Ron, String> AlmacenamientoView;

    @FXML
    private TextField BarraBusqueda;

    @FXML
    private ImageView BtnBuscar;

    @FXML
    private TextField TipoField;

    // Aqui empieza el almacenamiento de la materia prima
    @FXML
    private TextField BarraBusquedaMateriaPrima;

    @FXML
    private Button BtnActualizarMateriaPrima;

    @FXML
    private Button BtnAnadirMateriaPrima;

    @FXML
    private ImageView BtnBuscarMateriaPrima;

    @FXML
    private Button BtnEliminarMateriaPrima;

    @FXML
    private Button BtnExaminarMateriaPrima;

    @FXML
    private TextField CantidadFieldMateriaPrima;

    @FXML
    private TableColumn<MateriaPrima, String > CantidadViewMateriaPrima;

    @FXML
    private TextField CaracteristicasFieldMateriaPrima;

    @FXML
    private TableColumn<MateriaPrima, String > CaracteristicasViewMateriaPrima;

    @FXML
    private TableColumn<MateriaPrima, String> FotoViewMateriaPrima;

    @FXML
    private TextField NombreFieldMateriaPrima;

    @FXML
    private TableColumn<MateriaPrima, String> NombreViewMateriaPrima;

    @FXML
    private TableView<MateriaPrima> TablaViewMateriaPriama;

    @FXML
    private DatePicker VencimientoDatePickerMateriaPrima;

    @FXML
    private TableColumn<MateriaPrima, String> VencimientoViewMateriaPrima;

    @FXML
    private ImageView imagenPuestaMateriaPrima;

    public void limpiarCampos(){
        NombreField.clear();
        CantidadField.clear();
        VencimientoDatePicker.setValue(null);
        TipoField.clear();
        NombreFieldMateriaPrima.clear();
        CantidadFieldMateriaPrima.clear();
        VencimientoDatePickerMateriaPrima.setValue(null);
        CaracteristicasFieldMateriaPrima.clear();
    }

    public void cargarRonInventario(){
        inventario.cargarRonesDesdeArchivo();

        ObservableList<Ron> rons = (ObservableList<Ron>) FXCollections.observableArrayList((Collection<? extends RonGeneral>) inventario.getListaRones());

        // Asigna la lista observable al TableView
        TablaView.setItems(rons);

        // Configura las columnas del TableView
        CantidadView.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getCantidadEnAlmacen()).asString());
        NombreView.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getNombre()));
        VencimientoView.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getFechaVencimiento()).asString());
        FotoView.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getDireccionImagen()));
        AlmacenamientoView.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getTipoAlmacen()));
    }

    public void cargarMateriaPrimaInventario(){
        inventarioMateriaPrima.cargarMateriaPrimaDesdeArchivo();

        ObservableList<MateriaPrima> materiaPrima = (ObservableList<MateriaPrima>) FXCollections.observableArrayList((Collection<? extends MateriaPrimaGeneral>) inventarioMateriaPrima.getListaMateriaPrima());

        // Asigna la lista observable al TableView
        TablaViewMateriaPriama.setItems(materiaPrima);

        // Configura las columnas del TableView
        CantidadViewMateriaPrima.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getCantidadEnAlmacen()).asString());
        NombreViewMateriaPrima.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getNombre()));
        VencimientoViewMateriaPrima.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getFechaVencimiento()).asString());
        FotoViewMateriaPrima.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getDireccionImagen()));
        CaracteristicasViewMateriaPrima.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getCaracteristicas()));
    }

    public void buscar() {
        // Asegúrate de que los datos se carguen desde el archivo antes de realizar la búsqueda
        inventario.cargarRonesDesdeArchivo();

        // Obtiene el texto de búsqueda del campo de texto
        String filtro = BarraBusqueda.getText();

        // Crea una nueva lista observable para almacenar los elementos filtrados
        ObservableList<Ron> filtrado = FXCollections.observableArrayList();

        // Itera sobre la lista original de rones para aplicar el filtro
        for (RonGeneral ron : inventario.getListaRones()) {
            // Convierte el nombre del ron y el filtro a minúsculas para hacer la comparación insensible a mayúsculas
            if (ron.getNombre().toLowerCase().contains(filtro.toLowerCase())) {
                // Si el nombre del ron contiene el filtro, añade el ron a la lista filtrada
                filtrado.add((Ron) ron);
            }
        }

        // Muestra el número de elementos filtrados
        System.out.println("Elementos filtrados: " + filtrado.size());

        // Actualiza el TableView con los elementos filtrados
        TablaView.setItems(filtrado);
    }

    public void buscarMateriaPrima() {
        // Asegúrate de que los datos se carguen desde el archivo antes de realizar la búsqueda
        inventarioMateriaPrima.cargarMateriaPrimaDesdeArchivo();

        // Obtiene el texto de búsqueda del campo de texto
        String filtro = BarraBusquedaMateriaPrima.getText();

        // Crea una nueva lista observable para almacenar los elementos filtrados
        ObservableList<MateriaPrima> filtrado = FXCollections.observableArrayList();

        // Itera sobre la lista original de rones para aplicar el filtro
        for (MateriaPrimaGeneral materiaPrima : inventarioMateriaPrima.getListaMateriaPrima()) {
            // Convierte el nombre del ron y el filtro a minúsculas para hacer la comparación insensible a mayúsculas
            if (materiaPrima.getNombre().toLowerCase().contains(filtro.toLowerCase())) {
                // Si el nombre del ron contiene el filtro, añade el ron a la lista filtrada
                filtrado.add((MateriaPrima) materiaPrima);
            }
        }

        // Muestra el número de elementos filtrados
        System.out.println("Elementos filtrados: " + filtrado.size());

        // Actualiza el TableView con los elementos filtrados
        TablaViewMateriaPriama.setItems(filtrado);
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

    //Botones comienzan aquí
    @FXML
    void eventActualizarMateriaPrima(MouseEvent event) {
        String nombre = NombreFieldMateriaPrima.getText();
        String cantidadEnAlmacen = CantidadFieldMateriaPrima.getText();
        LocalDate fechaVencimiento = VencimientoDatePickerMateriaPrima.getValue();
        String direccionImagen = imagePath;
        String caracteristicas = CaracteristicasFieldMateriaPrima.getText();

        inventarioMateriaPrima.actualizarMateriaPrima(new MateriaPrima(nombre, cantidadEnAlmacen, caracteristicas, fechaVencimiento, direccionImagen));
        System.out.println("Materia prima actualizado");

        limpiarCampos();

        cargarMateriaPrimaInventario();
    }

    @FXML
    void eventAnadirMateriaPrima(MouseEvent event) {
        String nombre = NombreFieldMateriaPrima.getText();
        String cantidadEnAlmacen = CantidadFieldMateriaPrima.getText();
        LocalDate fechaVencimiento = VencimientoDatePickerMateriaPrima.getValue();
        String direccionImagen = imagePath;
        String caracteristicas = CaracteristicasFieldMateriaPrima.getText();

        inventarioMateriaPrima.agregarMateriaPrima(new MateriaPrima(nombre, cantidadEnAlmacen, caracteristicas, fechaVencimiento, direccionImagen));
        System.out.println("Materia prima Añadida");

        limpiarCampos();

        cargarMateriaPrimaInventario();
    }

    @FXML
    void eventBuscarMateriaPrima(MouseEvent event) {
        buscarMateriaPrima();
    }

    @FXML
    void eventEliminarMateriaPrima(MouseEvent event) {
        String nombre = NombreFieldMateriaPrima.getText();
        inventarioMateriaPrima.eliminarMateriaPrima(nombre);

        limpiarCampos();

        cargarMateriaPrimaInventario();
    }

    @FXML
    void eventExaminarMateriaPrima(MouseEvent event) {
        examinarPonerFoto();
    }

    @FXML
    void eventExaminar(MouseEvent event) {
        examinarPonerFoto();
    }

    @FXML
    void eventAnadir(MouseEvent event) {
        String nombre = NombreField.getText();
        String cantidadEnAlmacen = CantidadField.getText();
        LocalDate fechaVencimiento = VencimientoDatePicker.getValue();
        String direccionImagen = imagePath;
        String tipoAlmacen = TipoField.getText();

        inventario.agregarRon(new Ron(nombre, cantidadEnAlmacen, tipoAlmacen, fechaVencimiento, direccionImagen));
        System.out.println("Ron Añadido");

        limpiarCampos();

        cargarRonInventario();
    }

    @FXML
    private void eventActualizar(MouseEvent event) {
        String nombre = NombreField.getText();
        String cantidadEnAlmacen = CantidadField.getText();
        LocalDate fechaVencimiento = VencimientoDatePicker.getValue();
        String direccionImagen = imagePath;
        String tipoAlmacen = TipoField.getText();

        inventario.actualizarRon(new Ron(nombre, cantidadEnAlmacen, tipoAlmacen, fechaVencimiento, direccionImagen));
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

    @FXML
    void eventBuscar(MouseEvent event) {
        buscar();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        cargarRonInventario();
        cargarMateriaPrimaInventario();
    }
}