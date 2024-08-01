package com.adriananiel.roneracentral;

import java.time.LocalDate;

public class MateriaPrima extends MateriaPrimaGeneral {
    private String caracteristicas;

    public MateriaPrima(String nombre, String cantidadEnAlmacen, String caracteristicas, LocalDate fechaVencimiento, String direccionImagen) {
        super(nombre, cantidadEnAlmacen, fechaVencimiento, direccionImagen);
        this.caracteristicas = caracteristicas;
    }

    // Getter y Setter
    public String getCaracteristicas() { return caracteristicas; }

    public void setCaracteristicas(String caracteristicas) { this.caracteristicas = caracteristicas; }
}

