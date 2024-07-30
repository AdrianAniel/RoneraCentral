package com.adriananiel.roneracentral;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Inventario {
    private List<RonGeneral> listaRones = new ArrayList<>();
    private final String archivo = "BaseDatos/inventario_rones.txt";

    public void agregarRon(RonGeneral ron) {
        listaRones.add(ron);
        guardarRones();
    }

    public RonGeneral buscarRonPorNombre(String nombre) {
        for (RonGeneral ron : listaRones) {
            if (ron.getNombre().equalsIgnoreCase(nombre)) {
                return ron;
            }
        }
        return null;
    }

    public boolean actualizarRon(RonGeneral ronActualizado) {
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
            for (RonGeneral ron : listaRones) {
                if (ron instanceof Ron) { // Verifica si el objeto es realmente una instancia de Ron
                    Ron ronEspecificado = (Ron) ron; // Realiza el cast
                    writer.println(ron.getNombre() + "," + ron.getCantidadEnAlmacen() + "," + ron.getFechaVencimiento() + "," + ronEspecificado.getTipoAlmacen() + ","+ ron.getDireccionImagen());
                }
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
                String cantidad = partes[1].trim();
                LocalDate fecha = LocalDate.parse(partes[2].trim());
                String tipoAlmacen = partes[3].trim();
                String direccionImagen = partes.length > 4? partes[4].trim() : "";
                RonGeneral ron = new Ron(nombre , cantidad, tipoAlmacen, fecha, direccionImagen);
                ron.setDireccionImagen(direccionImagen);
                listaRones.add(ron);
            }
        } catch (IOException e) {
            System.out.println("Error al cargar el inventario desde el archivo: " + e.getMessage());
        }
    }

    public List<RonGeneral> getListaRones() {
        return listaRones;
    }
}