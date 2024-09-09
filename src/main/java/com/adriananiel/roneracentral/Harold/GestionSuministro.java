package com.adriananiel.roneracentral.Harold;

import com.adriananiel.roneracentral.GestionDeProcesos.PedidosController;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GestionSuministro implements Initializable {

    private ArrayList<SuministroTable> SuministroList;
    public SuministroTable suministro;
    private ObservableList<SuministroTable> observableSuministroList;
    EscribirObjeto wo = new EscribirObjeto("BaseDatos/Suministro_DB.ser");

    @FXML
    private TableColumn<SuministroTable ,String> AlmacenamientoView;

    @FXML
    private TableColumn<SuministroTable, String> CantidadView;


    @FXML
    private TableColumn<SuministroTable, String> NombreView;

    @FXML
    private TableView<SuministroTable> TablaView;

    @FXML
    private TextField nombreCampo;

    @FXML
    private TextField cantidadCampo;

    @FXML
    private TextField tipoCampo;


    // Evento de guardar texto FXML

    public void eventAnadir(ActionEvent event) {
        if (nombreCampo.getText().isEmpty() || cantidadCampo.getText().isEmpty() || tipoCampo.getText().isEmpty()) {
            showError("Debes ingresar un texto");
        } else {
            suministro = new SuministroTable(
                    nombreCampo.getText(),
                    cantidadCampo.getText(),
                    tipoCampo.getText());
        }
        SuministroList.add(suministro);
        observableSuministroList.add(suministro);
        wo.Write(SuministroList);

        clearTextFields();
    }

    private void showError(String debesIngresarUnTexto) {
    }

    @FXML
    private void eventActualizar(ActionEvent event) {
        System.out.println("Se presiono el boton actualizar");

        suministro = new SuministroTable(
                nombreCampo.getText(),
                cantidadCampo.getText(),
                tipoCampo.getText());

        for (int i = 0; i < SuministroList.size(); i++) {
            SuministroTable lron = SuministroList.get(i);
            System.out.println("antes del if : nombre del ron a actualizar " + lron.getNombreCampo());
            if (lron.getNombreCampo().equals(suministro.getNombreCampo())) {
                // Actualizar el elemento en ronList y observableRonList usando el índice 'i'
                SuministroList.set(i, suministro);
                observableSuministroList.set(i, suministro);
                TablaView.setItems(observableSuministroList);
                System.out.println("dentro del if despues de actualizar");

                break; // Salir del bucle una vez que se encontró y actualizó el elemento
            }
        }
        SuministroTable selectedSuministro = TablaView.getSelectionModel().getSelectedItem();

        if (selectedSuministro != null) {
            // Actualizar los valores del objeto seleccionado
            selectedSuministro.setNombreCampo(nombreCampo.getText());
            selectedSuministro.setCantidadCampo(cantidadCampo.getText());
            selectedSuministro.setTipoCampo(tipoCampo.getText());

            // Refrescar la tabla para mostrar los cambios
            TablaView.refresh();

            // Guardar los cambios en la base de datos
            wo.Write(SuministroList);

            System.out.println("Se actualizo correctamente el elemento: " + selectedSuministro.getNombreCampo());
        } else {
            System.out.println("No se ha seleccionado ningún elemento para actualizar.");
        }

        System.out.println("Se actualizo correctamente el elemento: " + suministro.getNombreCampo());
        System.out.println("tipo" + suministro.getTipoCampo());
        for (SuministroTable pron : SuministroList) {
            System.out.println(pron.getNombreCampo());
            System.out.println(pron.getTipoCampo());
        }
        clearTextFields();
    }

    public void btnDelete(ActionEvent event) {
            System.out.println("Botón presionado Delete!");

            // Obtener el suministro seleccionado desde la tabla
            SuministroTable selectedSuministro = TablaView.getSelectionModel().getSelectedItem();

            // Verifica si hay una selección válida
            if (selectedSuministro != null) {
                // Elimina el elemento de la lista original y de la lista observable
                SuministroList.remove(selectedSuministro);
                observableSuministroList.remove(selectedSuministro);

                // Actualiza la tabla para reflejar los cambios
                TablaView.refresh();

                // Guarda los cambios en la base de datos
                wo.Write(SuministroList);

                System.out.println("Se eliminó correctamente el elemento: " + selectedSuministro.getNombreCampo());
            } else {
                System.out.println("No se ha seleccionado ningún elemento para eliminar.");
            }

            // Limpiar los campos de texto
            clearTextFields();
        }

   private void clearTextFields(){
        nombreCampo.setText("");
        cantidadCampo.setText("");
        tipoCampo.setText("");
    }

    @FXML
    public void eventBack(MouseEvent mouseEvent) throws IOException {

    }

    private void loadTable() throws IOException, ClassNotFoundException, IOException {
        DateBase suministroDB = new DateBase("BaseDatos/Suministro_DB.ser");
        SuministroList = suministroDB.startDateBase();
        observableSuministroList = FXCollections.observableArrayList(SuministroList);
        TablaView.setItems(observableSuministroList);
        NombreView.setCellValueFactory(new PropertyValueFactory<>("nombreCampo"));
        CantidadView.setCellValueFactory(new PropertyValueFactory<>("cantidadCampo"));
        AlmacenamientoView.setCellValueFactory(new PropertyValueFactory<>("tipoCampo"));

        // Escuchar cambios de selección en la tabla
        TablaView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                // Cargar los datos seleccionados en los TextFields
                nombreCampo.setText(newValue.getNombreCampo());
                cantidadCampo.setText(newValue.getCantidadCampo());
                tipoCampo.setText(newValue.getTipoCampo());
            }
        });

        for (SuministroTable pron : SuministroList) {
            System.out.println(pron.getNombreCampo());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            loadTable();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}