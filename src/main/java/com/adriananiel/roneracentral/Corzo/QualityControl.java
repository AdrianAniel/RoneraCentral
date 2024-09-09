package com.adriananiel.roneracentral.Corzo;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.ArrayList;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;

//Clase Principal
public class QualityControl implements Initializable {

    private ArrayList<QualityRonTable> ronList;
    private ObservableList<QualityRonTable> observableRonList;
    public QualityRonTable ron;
    WriteObject wo = new WriteObject("BaseDatos/ron_db.ser");

    // ...Control de Eventos TextField de JavaFX...

    @FXML
    private TextField product_textfield;

    @FXML
    private TextField quality_textfield;

    @FXML
    private TextField amount_textfield;

    @FXML
    private TextField avb_textfield;

    @FXML
    private  TextField turbidity_textfield;

    @FXML
    private TextField color_textfield;

    @FXML
    private TextField analysis_chemical_textfield;

    @FXML
    private TextField microbiological_analysis_textfield;

    @FXML
    private TextField flavor_textfield;

    @FXML
    private TextField aroma_textfield;

    @FXML
    private TextField pack_and_label_textfield;

    @FXML
    private TextField manufacturing_process_textfield;

    @FXML
    private TextField equipement_textfield;

    @FXML
    private TextField facilities_textfield;

    @FXML
    private TextField human_factor_textfield;

    @FXML
    private TextField filtration_textfield;

    @FXML
    private TextField aging_textfield;

    @FXML
    private TextField blend_textfield;

    @FXML
    private TextField level_ph_textfield;

    @FXML
    private TextField rawn_material_textfield;

    // Controlador de eventos de la TableView

    @FXML
    private TableView<QualityRonTable> table;
    @FXML
    private TableColumn<QualityRonTable, String> product_list;
    @FXML
    private TableColumn<QualityRonTable, String> avb_list;
    @FXML
    private TableColumn<QualityRonTable, String> turbidity_list;
    @FXML
    private TableColumn<QualityRonTable, String> color_list;
    @FXML
    private TableColumn<QualityRonTable, String> chemical_analysis_list;
    @FXML
    private TableColumn<QualityRonTable, String> microbiological_analysis_list;
    @FXML
    private TableColumn<QualityRonTable, String> flavor__list;
    @FXML
    private TableColumn<QualityRonTable, String> aroma_list;
    @FXML
    private TableColumn<QualityRonTable, String> pack_and_label_list;
    @FXML
    private TableColumn<QualityRonTable, String> manufacturing__list;
    @FXML
    private TableColumn<QualityRonTable, String> equipement_list;
    @FXML
    private TableColumn<QualityRonTable, String> facilities_list;
    @FXML
    private TableColumn<QualityRonTable, String> human_factor_list;
    @FXML
    private TableColumn<QualityRonTable, String> filtration_list;
    @FXML
    private TableColumn<QualityRonTable, String> aging_list;
    @FXML
    private TableColumn<QualityRonTable, String> blend_list;
    @FXML
    private TableColumn<QualityRonTable, String> level_ph_list;
    @FXML
    private TableColumn<QualityRonTable, String> rawn_material_list;
    @FXML
    private TableColumn<QualityRonTable, String> quality_list;
    @FXML
    private TableColumn<QualityRonTable, String> amount;

    /////CRUD////
    // Clase para guardar los textfield en la base de datos...
    @FXML  // Evento de Button de JavaFX en la Clase guardarTexto (Write an Read)
    public void guardarTexto(ActionEvent event) {

        if (product_textfield.getText().isEmpty() || quality_textfield.getText().isEmpty() || amount_textfield.getText().isEmpty() || avb_textfield.getText().isEmpty() || turbidity_textfield.getText().isEmpty() || color_textfield.getText().isEmpty() || analysis_chemical_textfield.getText().isEmpty() || microbiological_analysis_textfield.getText().isEmpty() || flavor_textfield.getText().isEmpty() || aroma_textfield.getText().isEmpty() || pack_and_label_textfield.getText().isEmpty() || manufacturing_process_textfield.getText().isEmpty() || equipement_textfield.getText().isEmpty() || facilities_textfield.getText().isEmpty() || human_factor_textfield.getText().isEmpty() || filtration_textfield.getText().isEmpty() || aging_textfield.getText().isEmpty() || blend_textfield.getText().isEmpty() || level_ph_textfield.getText().isEmpty() || rawn_material_textfield.getText().isEmpty()) {
            showError("Debes ingresar un texto");
        } else {
            ron = new QualityRonTable(
                    product_textfield.getText(),
                    quality_textfield.getText(),
                    amount_textfield.getText(),
                    avb_textfield.getText(),
                    turbidity_textfield.getText(),
                    color_textfield.getText(),
                    analysis_chemical_textfield.getText(),
                    microbiological_analysis_textfield.getText(),
                    flavor_textfield.getText(),
                    aroma_textfield.getText(),
                    pack_and_label_textfield.getText(),
                    manufacturing_process_textfield.getText(),
                    equipement_textfield.getText(),
                    facilities_textfield.getText(),
                    human_factor_textfield.getText(),
                    filtration_textfield.getText(),
                    aging_textfield.getText(),
                    blend_textfield.getText(),
                    level_ph_textfield.getText(),
                    rawn_material_textfield.getText()
            );
            if (ronList.isEmpty()) {
                System.out.println("La lista esta vacia");
            } else {
                System.out.println(ronList.getLast().getProduct());
            }

            ronList.add(ron);
            observableRonList.add(ron);
            wo.Write(ronList);

            // Actualizar la tabla para reflejar los cambios
            table.refresh();

            System.out.println(ron.getProduct());
            clearTextFields();
            System.out.println("Se limpio correctamente");

        }
    }

    // Evento de Button de JavaFX en la Clase updateTexto...
    @FXML
    public void updateTexto(ActionEvent actionEvent) {
        System.out.println("Botón presionado Update!");

        // Crear un nuevo objeto QualityRonTable con los valores ingresados
        ron = new QualityRonTable(
                product_textfield.getText(),
                quality_textfield.getText(),
                amount_textfield.getText(),
                avb_textfield.getText(),
                turbidity_textfield.getText(),
                color_textfield.getText(),
                analysis_chemical_textfield.getText(),
                microbiological_analysis_textfield.getText(),
                flavor_textfield.getText(),
                aroma_textfield.getText(),
                pack_and_label_textfield.getText(),
                manufacturing_process_textfield.getText(),
                equipement_textfield.getText(),
                facilities_textfield.getText(),
                human_factor_textfield.getText(),
                filtration_textfield.getText(),
                aging_textfield.getText(),
                blend_textfield.getText(),
                level_ph_textfield.getText(),
                rawn_material_textfield.getText()
        );

        // Iterar sobre ronList usando un bucle for tradicional para tener acceso al índice
        for (int i = 0; i < ronList.size(); i++) {
            QualityRonTable lron = ronList.get(i);
            System.out.println("antes del if : nombre del ron a actualizar " + lron.getProduct());
            if (lron.getProduct().equals(ron.getProduct())) {
                // Actualizar el elemento en ronList y observableRonList usando el índice 'i'
                ronList.set(i, ron);
                observableRonList.set(i, ron);
                table.setItems(observableRonList);
                System.out.println("dentro del if despues de actualizar");

                break; // Salir del bucle una vez que se encontró y actualizó el elemento
            }
        }

        // Actualizar la tabla para reflejar los cambios
        table.refresh();

        // Guardar la lista actualizada
        wo.Write(ronList);

        System.out.println("Se actualizo correctamente el elemento: " + ron.getProduct());
        System.out.println("quality" + ron.getQuality());
        for (QualityRonTable pron : ronList) {
            System.out.println(pron.getProduct());
            System.out.println(pron.getQuality());
        }
    }

    // Evento de Button de JavaFX en la Clase deleteTexto...
    @FXML
    public void deleteTexto(ActionEvent actionEvent){

        System.out.println("Botón presionado Delete!");
        ron = new QualityRonTable(
                product_textfield.getText(),
                quality_textfield.getText(),
                amount_textfield.getText(),
                avb_textfield.getText(),
                turbidity_textfield.getText(),
                color_textfield.getText(),
                analysis_chemical_textfield.getText(),
                microbiological_analysis_textfield.getText(),
                flavor_textfield.getText(),
                aroma_textfield.getText(),
                pack_and_label_textfield.getText(),
                manufacturing_process_textfield.getText(),
                equipement_textfield.getText(),
                facilities_textfield.getText(),
                human_factor_textfield.getText(),
                filtration_textfield.getText(),
                aging_textfield.getText(),
                blend_textfield.getText(),
                level_ph_textfield.getText(),
                rawn_material_textfield.getText()
        );
        // Utilizamos un iterator para eliminar de forma segura de 'ronList' y 'observableRonList'
        Iterator<QualityRonTable> iterator = ronList.iterator();
        while (iterator.hasNext()) {
            QualityRonTable lron = iterator.next();

            if (lron.getProduct().equals(ron.getProduct())) {
                // Eliminamos de la lista usando el iterator
                iterator.remove();

                // Ahora eliminamos de 'observableRonList'
                observableRonList.remove(lron);

                // Salimos del bucle después de encontrar y eliminar el elemento
                break;
            }
        }
        // Actualizar la tabla para reflejar los cambios
        table.refresh();

        wo.Write(ronList);
        System.out.println("Se elimino correctamente el elemento: " + ron.getProduct());
        for ( QualityRonTable pron: ronList) {
            System.out.println(pron.getProduct());
        }
    }

    private void clearTextFields(){
        product_textfield.clear(); // Limpieza de los TextField
        quality_textfield.clear();
        amount_textfield.clear();
        avb_textfield.clear();
        turbidity_textfield.clear();
        color_textfield.clear();
        analysis_chemical_textfield.clear();
        microbiological_analysis_textfield.clear();
        flavor_textfield.clear();
        aroma_textfield.clear();
        pack_and_label_textfield.clear();
        manufacturing_process_textfield.clear();
        equipement_textfield.clear();
        facilities_textfield.clear();
        human_factor_textfield.clear();
        filtration_textfield.clear();
        aging_textfield.clear();
        blend_textfield.clear();
        level_ph_textfield.clear();
        rawn_material_textfield.clear();
    }

    private void showError(String message) {
        System.out.println("Error: " + message);
    }

    private void loadTable() throws IOException, ClassNotFoundException {
        DB ronDB = new DB("BaseDatos/ron_db.ser");
        ronList = ronDB.startDB();
        observableRonList = FXCollections.observableArrayList(ronList);
        table.setItems(observableRonList);
        product_list.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getProduct()));
        avb_list.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getAvb()));
        turbidity_list.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTurbidity()));
        color_list.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getColor()));
        chemical_analysis_list.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getChemical_analysis()));
        microbiological_analysis_list.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getMicrobiological_analysis()));
        flavor__list.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getFlavor()));
        aroma_list.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getAroma()));
        pack_and_label_list.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPack_and_label()));
        manufacturing__list.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getManufacturing_process()));
        equipement_list.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEquipement()));
        facilities_list.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getFacilities()));
        human_factor_list.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getHuman_factor()));
        filtration_list.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getFiltration()));
        aging_list.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getAging()));
        blend_list.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getBlend()));
        level_ph_list.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getLevel_ph()));
        rawn_material_list.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getRawn_material()));
        quality_list.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getQuality()));
        amount.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getAmount()));
        for (QualityRonTable pron : ronList) {
            System.out.println(pron.getProduct());
            System.out.println(pron.getQuality());
        }
    }
    // Metodo Cargar Datos
    private void cargarDatosEnTextFields(QualityRonTable selectedRon) {
        product_textfield.setText(selectedRon.getProduct());
        quality_textfield.setText(selectedRon.getQuality());
        amount_textfield.setText(selectedRon.getAmount());
        avb_textfield.setText(selectedRon.getAvb());
        turbidity_textfield.setText(selectedRon.getTurbidity());
        color_textfield.setText(selectedRon.getColor());
        analysis_chemical_textfield.setText(selectedRon.getChemical_analysis());
        microbiological_analysis_textfield.setText(selectedRon.getMicrobiological_analysis());
        flavor_textfield.setText(selectedRon.getFlavor());
        aroma_textfield.setText(selectedRon.getAroma());
        pack_and_label_textfield.setText(selectedRon.getPack_and_label());
        manufacturing_process_textfield.setText(selectedRon.getManufacturing_process());
        equipement_textfield.setText(selectedRon.getEquipement());
        facilities_textfield.setText(selectedRon.getFacilities());
        human_factor_textfield.setText(selectedRon.getHuman_factor());
        filtration_textfield.setText(selectedRon.getFiltration());
        aging_textfield.setText(selectedRon.getAging());
        blend_textfield.setText(selectedRon.getBlend());
        level_ph_textfield.setText(selectedRon.getLevel_ph());
        rawn_material_textfield.setText(selectedRon.getRawn_material());
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
        // Añadir listener para detectar la selección de una fila en la tabla
        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                cargarDatosEnTextFields(newSelection);
            } else {
                clearTextFields(); // Limpiar los TextFields si no hay selección
            }
        });
    }
}
