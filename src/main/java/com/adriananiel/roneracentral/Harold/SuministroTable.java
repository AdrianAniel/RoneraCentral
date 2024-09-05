package com.adriananiel.roneracentral.Harold;

import java.io.Serializable;

public class SuministroTable implements Serializable {
    String nombreField;
    String cantidadField;
    String tipoField;

    public SuministroTable (String nombreField, String cantidadField, String tipoField){
        this.nombreField = nombreField;
        this.cantidadField = cantidadField;
        this.tipoField = tipoField;
    }

    public String getNombreField() {
        return nombreField;
    }

    public void setNombreField(String nombreField) {
        this.nombreField = nombreField;
    }

    public String getCantidadField() {
        return cantidadField;
    }

    public void setCantidadField(String cantidadField) {
        this.cantidadField = cantidadField;
    }

    public String getTipoField() {
        return tipoField;
    }

    public void setTipoField(String tipoField) {
        this.tipoField = tipoField;
    }
}
