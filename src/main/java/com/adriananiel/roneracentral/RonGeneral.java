package com.adriananiel.roneracentral;

import java.time.LocalDate;

public abstract class RonGeneral {
    private String nombre;
    private String cantidadEnAlmacen;
    private LocalDate fechaVencimiento;
    private String direccionImagen;

    public RonGeneral(String nombre, String cantidadEnAlmacen, LocalDate fechaVencimiento, String direccionImagen) {
        this.nombre = nombre;
        this.fechaVencimiento = fechaVencimiento;
        this.direccionImagen = direccionImagen;
        this.cantidadEnAlmacen = cantidadEnAlmacen;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCantidadEnAlmacen() {
        return cantidadEnAlmacen;
    }

    public void setCantidadEnAlmacen(String cantidadEnAlmacen) {
        this.cantidadEnAlmacen = cantidadEnAlmacen;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getDireccionImagen() {
        return direccionImagen;
    }

    public void setDireccionImagen(String direccionImagen) {
        this.direccionImagen = direccionImagen;
    }
}
