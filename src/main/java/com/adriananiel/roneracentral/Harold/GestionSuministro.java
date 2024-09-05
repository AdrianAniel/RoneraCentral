package com.adriananiel.roneracentral.Harold;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.awt.*;
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
    private TextField nombreField;

    @FXML
    private TextField cantidadField;

    @FXML
    private TextField tipoField;

    @FXML
    private TableView<SuministroTable> TablaView;

    @FXML
    private TableColumn<SuministroTable, String> NombreView;

    @FXML
    private TableColumn<SuministroTable, String> CantidadView;

    @FXML
    private TableColumn<SuministroTable, String> AlmacenamientoView;

    // Evento de guardar texto FXML

    public void eventAnadir(ActionEvent event) {
        if (nombreField.getText().isEmpty() || cantidadField.getText().isEmpty() || tipoField.getText().isEmpty()) {
            showError("Debes ingresar un texto");
        } else {
            suministro = new SuministroTable(
                    nombreField.getText(),
                    cantidadField.getText(),
                    tipoField.getText());
        }
        SuministroList.add(suministro);
        observableSuministroList.add(suministro);
        wo.Write(SuministroList);

       /* clearTextFields();*/
    }

    private void showError(String debesIngresarUnTexto) {
    }

    @FXML
    private void eventActualizar(ActionEvent event) {
        System.out.println("Se presiono el boton actualizar");

        suministro = new SuministroTable(
                nombreField.getText(),
                cantidadField.getText(),
                tipoField.getText());

        for (int i = 0; i < SuministroList.size(); i++) {
            SuministroTable lron = SuministroList.get(i);
            System.out.println("antes del if : nombre del ron a actualizar " + lron.getNombreField());
            if (lron.getNombreField().equals(suministro.getNombreField())) {
                // Actualizar el elemento en ronList y observableRonList usando el índice 'i'
                SuministroList.set(i, suministro);
                observableSuministroList.set(i, suministro);
                TablaView.setItems(observableSuministroList);
                System.out.println("dentro del if despues de actualizar");

                break; // Salir del bucle una vez que se encontró y actualizó el elemento
            }
        }
        wo.Write(SuministroList);

        System.out.println("Se actualizo correctamente el elemento: " + suministro.getNombreField());
        System.out.println("tipo" + suministro.getTipoField());
        for (SuministroTable pron : SuministroList) {
            System.out.println(pron.getNombreField());
            System.out.println(pron.getTipoField());
        }
    }
    private void eventEliminar(ActionEvent event){

        System.out.println("Botón presionado Delete!");
        suministro = new SuministroTable(
                nombreField.getText(),
                cantidadField.getText(),
                tipoField.getText());

        int index = 0;
        for (SuministroTable lron : SuministroList){
            if (lron.getNombreField().equals(suministro.getNombreField())) {
                int currentIndex = index;
                SuministroList.remove(lron);
                observableSuministroList.remove(currentIndex);
                break;
            }
            index++;
        }
        wo.Write(SuministroList);
        System.out.println("Se elimino correctamente el elemento: " + suministro.getNombreField());
        for (SuministroTable pron: SuministroList) {
            System.out.println(pron.getNombreField());
        }
    }

   private void clearTextFields(){
        nombreField.setText("");
        cantidadField.setText("");
        tipoField.setText("");
    }



    private void loadTable() throws IOException, ClassNotFoundException, IOException {
        DateBase suministroDB = new DateBase("BaseDatos/Suministro_DB.ser");
        SuministroList = suministroDB.startDateBase();
        observableSuministroList = FXCollections.observableArrayList(SuministroList);
        TablaView.setItems(observableSuministroList);


        NombreView.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNombreField()));
        CantidadView.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTipoField()));
        AlmacenamientoView.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTipoField()));

        for (SuministroTable pron : SuministroList) {
            System.out.println(pron.getNombreField());
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