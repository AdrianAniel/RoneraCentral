package com.adriananiel.roneracentral.Harold;

import java.io.Serializable;
//Tabla serializadora

public class SuministroTable implements Serializable {
    String NombreCampo;
    String CantidadCampo;
    String TipoCampo;

    public SuministroTable (String NombreCampo, String CantidadCampo, String TipoCampo){
        this.NombreCampo = NombreCampo;
        this.CantidadCampo = CantidadCampo;
        this.TipoCampo = TipoCampo;
    }

    public String getNombreCampo() {
        return NombreCampo;
    }

    public void setNombreCampo(String nombreCampo) {
        NombreCampo = nombreCampo;
    }

    public String getCantidadCampo() {
        return CantidadCampo;
    }

    public void setCantidadCampo(String cantidadCampo) {
        CantidadCampo = cantidadCampo;
    }

    public String getTipoCampo() {
        return TipoCampo;
    }

    public void setTipoCampo(String tipoCampo) {
        TipoCampo = tipoCampo;
    }
}
