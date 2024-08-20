package com.adriananiel.roneracentral.Corzo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class QualityRonSearcher {

    private ArrayList<QualityRonTable> ronList;

    public QualityRonSearcher(ArrayList<QualityRonTable> ronList) {
        this.ronList = ronList;
    }

    public boolean searchAndRemoveRon(String productName) {
        Optional<QualityRonTable> found = ronList.stream()
                .filter(ron -> ron.getProduct().equalsIgnoreCase(productName))
                .findFirst();

        if (found.isPresent()) {
            ronList.removeIf(ron -> ron.getProduct().equalsIgnoreCase(productName));
            System.out.println("Producto encontrado y eliminado: " + found.get());
            return true;
        } else {
            System.out.println("Producto no encontrado.");
            return false;
        }
    }
}
