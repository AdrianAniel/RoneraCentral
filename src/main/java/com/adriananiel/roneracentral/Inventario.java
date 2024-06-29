package com.adriananiel.roneracentral;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Inventario {
    private List<Ron> listaRones = new ArrayList<>();
    private final String archivo = "BaseDatos/inventario_rones.txt";

    public void agregarRon(Ron ron) {
        listaRones.add(ron);
        guardarRones();
    }

    public Ron buscarRonPorNombre(String nombre) {
        for (Ron ron : listaRones) {
            if (ron.getNombre().equalsIgnoreCase(nombre)) {
                return ron;
            }
        }
        return null;
    }

    public boolean actualizarRon(Ron ronActualizado) {
        int index = listaRones.indexOf(buscarRonPorNombre(ronActualizado.getNombre()));
        if (index!= -1) {
            listaRones.set(index, ronActualizado);
            guardarRones();
            return true;
        }
        return false;
    }

    public boolean eliminarRon(String nombre) {
        int index = listaRones.indexOf(buscarRonPorNombre(nombre));
        if (index!= -1) {
            listaRones.remove(index);
            guardarRones();
            return true;
        }
        return false;
    }

    private void guardarRones() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(archivo))) {
            for (Ron ron : listaRones) {
                writer.println(ron.getNombre() + "," + ron.getCantidadEnAlmacen() + "," + ron.getFechaVencimiento() + "," + ron.getDireccionImagen());
            }
        } catch (IOException e) {
            System.out.println("Error al guardar el inventario: " + e.getMessage());
        }
    }

    public void cargarRonesDesdeArchivo() {
        listaRones.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine())!= null) {
                String[] partes = linea.trim().split(",");
                String nombre = partes[0].trim();
                int cantidad = Integer.parseInt(partes[1].trim());
                String fecha = partes[2].trim();
                String direccionImagen = partes.length > 3? partes[3].trim() : "";
                Ron ron = new Ron(nombre , cantidad, fecha, direccionImagen);
                ron.setDireccionImagen(direccionImagen);
                listaRones.add(ron);
            }
        } catch (IOException e) {
            System.out.println("Error al cargar el inventario desde el archivo: " + e.getMessage());
        }
    }

    public List<Ron> getListaRones() {
        return listaRones;
    }
}