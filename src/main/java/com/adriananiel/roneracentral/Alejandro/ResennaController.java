package com.adriananiel.roneracentral.Alejandro;

import com.adriananiel.roneracentral.InventarioRon;
import com.adriananiel.roneracentral.amanda.Resenna;
import com.adriananiel.roneracentral.Ron;
import com.adriananiel.roneracentral.RonGeneral;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    private RegistroResenna resenna = new RegistroResenna();

    @FXML
    private TableView<RonGeneral> TablaView;

    @FXML
    private TableColumn<RonGeneral, String> CalificacionView;

    @FXML
    private TableColumn<RonGeneral, String> NombreView;

    @FXML
    private TableColumn<RonGeneral, String> ComentariosView;

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
        //cargarInicial();
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

    /*public void cargarInicial(){
        inventario.cargarRonesDesdeArchivo();

        ObservableList<Resenna> resennaList = FXCollections.observableArrayList(resenna.getListaResenna());


        List<String> nombresRones = Resenna.stream()
                .map(RonGeneral::getNombre)
                .collect(Collectors.toList());

        ObservableList<String> observableNombresRones = FXCollections.observableArrayList(nombresRones);

        RonsComboBox.setItems(observableNombresRones);

        TablaView.setItems(FXCollections.observableArrayList(Resenna));

        CalificacionView.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getClasificacion()).asString());
        NombreView.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getNombre()));
        ComentariosView.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getComentario()));
    }*/

    public void buscar() {
        inventario.cargarRonesDesdeArchivo();

        String filtro = BarraBusqueda.getText();

        ObservableList<RonGeneral> filtrado = FXCollections.observableArrayList();
        for (RonGeneral ron : inventario.getListaRones()) {
            if (ron.getNombre().toLowerCase().contains(filtro.toLowerCase())) {
                filtrado.add(ron);
            }
        }

        System.out.println("Elementos filtrados: " + filtrado.size());
        TablaView.setItems(filtrado);
    }

    /*@FXML
    void eventActualizar(MouseEvent event) {
        String selectedRon = RonsComboBox.getValue();
        if (selectedRon == null || selectedRon.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error de Actualización");
            alert.setHeaderText(null);
            alert.setContentText("Debe seleccionar un ron del ComboBox antes de actualizar.");
            alert.showAndWait();
            return;
        }

        String clasificacion = ClasificacionField.getText();
        if (clasificacion.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error de Actualización");
            alert.setHeaderText(null);
            alert.setContentText("Debe ingresar una clasificación.");
            alert.showAndWait();
            return;
        }

        String comentarios = Objects.nonNull(ComentariosField.getText()) ? ComentariosField.getText() : "";
        String nombre = "";
        Resenna resenna = RegistroResenna.agregarResenna(new Resenna(nombre, clasificacion, comentarios));
        RonGeneral ron = inventario.buscarRonPorNombre(selectedRon);
        ron.setResenna(resenna);
        inventario.actualizarRonResenna(ron, resenna);
        System.out.println("Reseña añadida");

        limpiarCampos();
        cargarInicial();
    }*/

    @FXML
    void eventBuscar(MouseEvent event) {
        buscar();
    }

    public void eventActualizar(MouseEvent mouseEvent) {

    }
}
