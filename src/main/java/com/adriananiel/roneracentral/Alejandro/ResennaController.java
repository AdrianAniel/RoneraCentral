package com.adriananiel.roneracentral;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class ResennaController implements Initializable {

    private InventarioRon inventario = new InventarioRon();

    @FXML
    private TableView<Ron> TablaView;

    @FXML
    private TableColumn<Ron, String> CalificacionView;

    @FXML
    private TableColumn<Ron, String> NombreView;

    @FXML
    private TableColumn<Ron, String> ComentariosView;

    @FXML
    private ComboBox<String> RonsComboBox;

    @FXML
    private TextField ClasificacionField;

    @FXML
    private TextField ComentariosField;

    @FXML
    private TextField BarraBusqueda;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cargarInicial();
        ClasificacionField.addEventFilter(KeyEvent.KEY_TYPED, this::filtrarSoloNumeros);
    }

    private void filtrarSoloNumeros(KeyEvent event) {
        if (!event.getCharacter().matches("[0-9]")) {
            event.consume();
        }
    }

    public void limpiarCampos(){
        ClasificacionField.clear();
        ComentariosField.clear();
    }

    public void cargarInicial(){
        inventario.cargarRonesDesdeArchivo();

        ObservableList<Ron> rons = (ObservableList<Ron>) FXCollections.observableArrayList((Collection<? extends RonGeneral>) inventario.getListaRones());

        // Mapea la lista de RonGeneral a una lista de nombres (String)
        List<String> nombresRones = inventario.getListaRones().stream()
                .map(RonGeneral::getNombre)
                .collect(Collectors.toList());

        // Convierte la lista de nombres a una lista observable
        ObservableList<String> observableNombresRones = FXCollections.observableArrayList(nombresRones);

        // Asigna la lista observable al ComboBox
        RonsComboBox.setItems(observableNombresRones);

        // Asigna la lista observable al TableView
        TablaView.setItems(rons);

        // Configura las columnas del TableView
        CalificacionView.setCellValueFactory(cellData -> new SimpleObjectProperty<>(Objects.nonNull(cellData.getValue().getResenna()) ? cellData.getValue().getResenna().getClasificacion() : "").asString());
        NombreView.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getNombre()));
        ComentariosView.setCellValueFactory(cellData -> new SimpleObjectProperty<>(Objects.nonNull(cellData.getValue().getResenna()) ? cellData.getValue().getResenna().getComentario() : "").asString());
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


    @FXML
    void eventActualizar(MouseEvent event) {
        // Verificar si se ha seleccionado un valor en el ComboBox
        String selectedRon = RonsComboBox.getValue();
        if (selectedRon == null || selectedRon.isEmpty()) {
            // Mostrar un mensaje de error si no hay selección
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error de Actualización");
            alert.setHeaderText(null);
            alert.setContentText("Debe seleccionar un ron del ComboBox antes de actualizar.");
            alert.showAndWait();
            return; // Salir del método si no se ha seleccionado un ron
        }

        // Verificar el valor del TextField
        String clasificacion = ClasificacionField.getText();
        if (clasificacion == null || clasificacion.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error de Actualización");
            alert.setHeaderText(null);
            alert.setContentText("Debe ingresar una clasificación.");
            alert.showAndWait();
            return; // Salir del método si la clasificación está vacía
        }
        String comentarios = Objects.nonNull(ComentariosField.getText())?ComentariosField.getText():"";
        var resenna = RegistroResenna.agregarResenna(new Resenna(Integer.parseInt(clasificacion), comentarios));
        var ron = InventarioRon.buscarRonPorNombre(selectedRon);
        ron.setResenna(resenna);
        inventario.actualizarRonResenna(ron, resenna);
        System.out.println("Reseña Añadida");

        limpiarCampos();

        cargarInicial();
    }


    @FXML
    void eventBuscar(MouseEvent event) {
        buscar();
    }

}