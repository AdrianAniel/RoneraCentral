package com.adriananiel.roneracentral;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class InventarioMateriaPrima {
    private List<MateriaPrimaGeneral> listaMateriaPrima = new ArrayList<>();
    private final String archivo = "BaseDatos/inventario_materia_prima.txt";

    public void agregarMateriaPrima(MateriaPrimaGeneral materiaPrima) {
        listaMateriaPrima.add(materiaPrima);
        guardarMateriaPrima();
    }

    public MateriaPrimaGeneral buscarMateriaPrimaPorNombre(String nombre) {
        for (MateriaPrimaGeneral materiaPrima : listaMateriaPrima) {
            if (materiaPrima.getNombre().equalsIgnoreCase(nombre)) {
                return materiaPrima;
            }
        }
        return null;
    }

    public boolean actualizarMateriaPrima(MateriaPrimaGeneral materiaPrimaActualizado) {
        int index = listaMateriaPrima.indexOf(buscarMateriaPrimaPorNombre(materiaPrimaActualizado.getNombre()));
        if (index!= -1) {
            listaMateriaPrima.set(index, materiaPrimaActualizado);
            guardarMateriaPrima();
            return true;
        }
        return false;
    }

    public boolean eliminarMateriaPrima(String nombre) {
        int index = listaMateriaPrima.indexOf(buscarMateriaPrimaPorNombre(nombre));
        if (index!= -1) {
            listaMateriaPrima.remove(index);
            guardarMateriaPrima();
            return true;
        }
        return false;
    }

    private void guardarMateriaPrima() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(archivo))) {
            for (MateriaPrimaGeneral materiaPrima : listaMateriaPrima) {
                if (materiaPrima instanceof MateriaPrima) { // Verifica si el objeto es realmente una instancia de MateriaPrima
                    MateriaPrima materiaPrimaEspecificado = (MateriaPrima) materiaPrima; // Realiza el cast
                    writer.println(materiaPrima.getNombre() + "," + materiaPrima.getCantidadEnAlmacen() + "," + materiaPrima.getFechaVencimiento() + "," + materiaPrimaEspecificado.getCaracteristicas() + ","+ materiaPrima.getDireccionImagen());
                }
            }
        } catch (IOException e) {
            System.out.println("Error al guardar el inventario: " + e.getMessage());
        }
    }

    public void cargarMateriaPrimaDesdeArchivo() {
        listaMateriaPrima.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine())!= null) {
                String[] partes = linea.trim().split(",");
                String nombre = partes[0].trim();
                String cantidad = partes[1].trim();
                LocalDate fecha = LocalDate.parse(partes[2].trim());
                String caracteristicas = partes[3].trim();
                String direccionImagen = partes.length > 4? partes[4].trim() : "";
                MateriaPrimaGeneral materiaPrima = new MateriaPrima(nombre , cantidad, caracteristicas, fecha, direccionImagen);
                materiaPrima.setDireccionImagen(direccionImagen);
                listaMateriaPrima.add(materiaPrima);
            }
        } catch (IOException e) {
            System.out.println("Error al cargar el inventario desde el archivo: " + e.getMessage());
        }
    }

    public List<MateriaPrimaGeneral> getListaMateriaPrima() {
        return listaMateriaPrima;
    }
}