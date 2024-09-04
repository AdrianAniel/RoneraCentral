package com.adriananiel.roneracentral;

public class Resenna {
    private int id;
    private int clasificacion;
    private String comentario;

    public Resenna(int id, int clasificacion, String comentario) {
        this.id = id;
        this.clasificacion = clasificacion;
        this.comentario = comentario;
    }

    public Resenna(int clasificacion, String comentario) {
        this.clasificacion = clasificacion;
        this.comentario = comentario;
    }

    public Resenna() {
    }

    // Getters y Setters
    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
    }
    public int getClasificacion() {

        return clasificacion;
    }

    public void setClasificacion(int clasificacion) {

        this.clasificacion = clasificacion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {

        this.comentario = comentario;
    }

}
