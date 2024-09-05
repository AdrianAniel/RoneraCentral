package com.adriananiel.roneracentral.amanda;

public class Resenna {
    private String nombre;
    private String clasificacion;
    private String comentario;

    public Resenna(String nombre, String clasificacion, String comentario) {
        this.nombre = nombre;
        this.clasificacion = clasificacion;
        this.comentario = comentario;
    }

    // Getters y Setters
    public String getNombre() {

        return nombre;
    }

    public void setId(String nombre) {

        this.nombre = nombre;
    }
    public String getClasificacion() {

        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {

        this.clasificacion = clasificacion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {

        this.comentario = comentario;
    }

}
