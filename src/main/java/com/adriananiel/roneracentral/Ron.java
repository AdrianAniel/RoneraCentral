package com.adriananiel.roneracentral;

import java.time.LocalDate;

public class Ron extends RonGeneral {
    private String tipoAlmacen;

    public Ron(String nombre, String cantidadEnAlmacen, String tipoAlmacen, LocalDate fechaVencimiento, String direccionImagen) {
        super(nombre, cantidadEnAlmacen, fechaVencimiento, direccionImagen);
        this.tipoAlmacen = tipoAlmacen;
    }

    // Getter y Setter
    public String getTipoAlmacen() { return tipoAlmacen; }

    public void setTipoAlmacen(String tipoAlmacen) { this.tipoAlmacen = tipoAlmacen; }
}

