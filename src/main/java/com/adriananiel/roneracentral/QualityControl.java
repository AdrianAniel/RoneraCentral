package com.adriananiel.roneracentral;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import java.io.FileWriter;
import java.io.IOException;

public class QualityControl {

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

    @FXML
    public void guardarTexto(ActionEvent event) {
        String product = product_textfield.getText();
        String quality = quality_textfield.getText();
        String amount = amount_textfield.getText();
        String avb = avb_textfield.getText();
        String turbidity = turbidity_textfield.getText();
        String color = color_textfield.getText();
        String chemical_analysis = analysis_chemical_textfield.getText();
        String microbiological_analysis = microbiological_analysis_textfield.getText();
        String flavor = flavor_textfield.getText();
        String aroma = aroma_textfield.getText();
        String pack_and_label = pack_and_label_textfield.getText();
        String manufacturing_process = manufacturing_process_textfield.getText();
        String equipement = equipement_textfield.getText();
        String facilities = facilities_textfield.getText();
        String human_factor = human_factor_textfield.getText();
        String filtration = filtration_textfield.getText();
        String aging = aging_textfield.getText();
        String blend = blend_textfield.getText();
        String level_ph = level_ph_textfield.getText();
        String rawn_material = rawn_material_textfield.getText();

        if (product.isEmpty() || quality.isEmpty() || amount.isEmpty() || avb.isEmpty() || turbidity.isEmpty() || color.isEmpty() || chemical_analysis.isEmpty() || microbiological_analysis.isEmpty() || flavor.isEmpty() || aroma.isEmpty() || pack_and_label.isEmpty() || manufacturing_process.isEmpty() || equipement.isEmpty() || facilities.isEmpty() || human_factor.isEmpty() || filtration.isEmpty() || aging.isEmpty() || blend.isEmpty() || level_ph.isEmpty() || rawn_material.isEmpty()) {
            showError("Debes ingresar un texto");
            return;
        }
        String text = product + "," + quality + "," + amount + "," + avb + "," + turbidity + "," + color + "," + chemical_analysis + "," + microbiological_analysis + "," + flavor + "," + aroma + "," + pack_and_label + "," + manufacturing_process + "," + equipement + "," + facilities + "," + human_factor + "," + filtration + "," + aging + "," + blend + "," + level_ph + "," + rawn_material;
        try (FileWriter writer = new FileWriter("BaseDatos/inventario_calidad.txt", true)) {
            writer.write(text + "\n");
            System.out.println("Texto guardado correctamente");
            product_textfield.clear(); // Limpiar el TextField
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
        } catch (IOException e) {
            showError("Error al guardar el texto: " + e.getMessage());
        }
    }

    private void showError(String message) {
        System.out.println("Error: " + message);
    }
}
