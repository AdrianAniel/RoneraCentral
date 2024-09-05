package com.adriananiel.roneracentral.Harold;

import com.adriananiel.roneracentral.Corzo.QualityRonTable;

import java.util.ArrayList;
import java.util.Optional;

public class SuministroSearcher {
    private ArrayList<SuministroTable> suministroList;

    public SuministroSearcher(ArrayList<SuministroTable> suministroList) {
        this.suministroList = suministroList;
    }

    public boolean searchAndRemoveRon(String productName) {
        Optional<SuministroTable> found = suministroList.stream()
                .filter(suministro -> suministro.getNombreCampo().equalsIgnoreCase(productName))
                .findFirst();

        if (found.isPresent()) {
            suministroList.removeIf(suministro -> suministro.getNombreCampo().equalsIgnoreCase(productName));
            System.out.println("Producto encontrado y eliminado: " + found.get());
            return true;
        } else {
            System.out.println("Producto no encontrado.");
            return false;
        }
    }
}
