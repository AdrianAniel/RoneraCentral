package com.adriananiel.roneracentral;

public class Ron {
    public String nombre;
    public int cantidadEnAlmacen;
    public String fechaVencimiento;

    public Ron(String nombre, int cantidadEnAlmacen, String fechaVencimiento) {
        this.nombre = nombre;
        this.cantidadEnAlmacen = cantidadEnAlmacen;
        this.fechaVencimiento = fechaVencimiento;
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
}
