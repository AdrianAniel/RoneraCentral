package com.adriananiel.roneracentral;

public class Ron {
    private String nombre;
    private int cantidadEnAlmacen;
    private String fechaVencimiento;
    private String direccionImagen;

    public Ron(String nombre, int cantidadEnAlmacen, String fechaVencimiento, String direccionImagen) {
        this.nombre = nombre;
        this.cantidadEnAlmacen = cantidadEnAlmacen;
        this.fechaVencimiento = fechaVencimiento;
        this.direccionImagen = direccionImagen;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidadEnAlmacen() {
        return cantidadEnAlmacen;
    }

    public void setCantidadEnAlmacen(int cantidadEnAlmacen) {
        this.cantidadEnAlmacen = cantidadEnAlmacen;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getDireccionImagen() {
        return direccionImagen;
    }

    public void setDireccionImagen(String direccionImagen) {
        this.direccionImagen = direccionImagen;
    }
}
