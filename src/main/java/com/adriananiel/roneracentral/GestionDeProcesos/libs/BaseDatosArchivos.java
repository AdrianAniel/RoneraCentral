package com.adriananiel.roneracentral.GestionDeProcesos.libs;

import com.adriananiel.roneracentral.GestionDeProcesos.Pedido;

import java.io.*;
import java.util.ArrayList;

public class BaseDatosArchivos {

    private String rutaArcivo = "BaseDatos/";


    public void guardarDatos(Pedido pedido, boolean rescribir) {
        ArrayList<Pedido> p = leerDatosPedido();
        //ArrayList<Pedido> p = new ArrayList<Pedido>();
        p.add(pedido);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(String.format("%sPedidos.txt", rutaArcivo), false))) {
            oos.writeObject(p);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void guardarDatos(ArrayList<Pedido> p) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(String.format("%sPedidos.txt", rutaArcivo), false))) {
            oos.writeObject(p);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public ArrayList<Pedido> leerDatosPedido() {
        String archivo = String.format("%sPedidos.txt", rutaArcivo);
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        ArrayList<Pedido> pedidos = new ArrayList<Pedido>();
        try {
            fis = new FileInputStream(archivo);
            ois = new ObjectInputStream(fis);
            pedidos = (ArrayList<Pedido>) ois.readObject();

        }catch (IOException | ClassNotFoundException  e) {
            e.printStackTrace(System.out);
        } finally {
            try {
                if (ois != null) ois.close();
                if (fis != null) fis.close();
            } catch (IOException e) {
                e.printStackTrace(System.out);
            }
        }
        return pedidos;
    }

    public void borrarDatosPedidos(){
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(String.format("%sPedidos.txt", rutaArcivo)))) {
            oos.writeObject(new ArrayList<Pedido>());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void borrarDatosPedidos(int index){
        ArrayList<Pedido> pedidos = new ArrayList<>(leerDatosPedido());
        pedidos.remove(index);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(String.format("%sPedidos.txt", rutaArcivo), false))) {
            oos.writeObject(pedidos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
