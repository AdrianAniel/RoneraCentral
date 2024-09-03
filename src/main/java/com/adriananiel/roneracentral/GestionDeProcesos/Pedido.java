package com.adriananiel.roneracentral.GestionDeProcesos;

import com.adriananiel.roneracentral.GestionDeProcesos.libs.GenerarID;

import java.io.Serializable;

public class Pedido implements Serializable {

    private String idPedido;
    private String clientName;

    private String pedido;

    private String description;

    private int price;

    private String prioridad;

    public Pedido() {
    }

    public Pedido(String clientName, String pedido, String description, int price, String prioridad) {
        this.idPedido = GenerarID.generarRandomID();
        this.clientName = clientName;
        this.pedido = pedido;
        this.description = description;
        this.price = price;
        this.prioridad = prioridad;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getPedido() {
        return pedido;
    }

    public void setPedido(String pedido) {
        this.pedido = pedido;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }
}
