package com.adriananiel.roneracentral;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//Clase encargada de gestionar el inventario de rones. Utiliza archivos para persistir y recuperar datos.
public class InventarioRon {
    // Lista de objetos RonGeneral
    private List<RonGeneral> listaRones = new ArrayList<>();
    // Ruta del archivo donde se guardará/leerá el inventario
    private final String archivo = "BaseDatos/inventario_rones.txt";

    //Agrega un nuevo rón al inventario y lo guarda en el archivo.
    public void agregarRon(RonGeneral ron) {
        listaRones.add(ron);
        guardarRones();
    }

    //Busca un rón por su nombre y devuelve un objeto si lo encuentra.
    public RonGeneral buscarRonPorNombre(String nombre) {
        for (RonGeneral ron : listaRones) {
            if (ron.getNombre().equalsIgnoreCase(nombre)) {
                return ron;
            }
        }
        return null;
    }

    //Actualiza un rón existente en el inventario.
    public boolean actualizarRon(RonGeneral ronActualizado) {
        int index = listaRones.indexOf(buscarRonPorNombre(ronActualizado.getNombre()));
        if (index!= -1) {
            listaRones.set(index, ronActualizado);
            guardarRones();
            return true;
        }
        return false;
    }

    //Elimina un rón del inventario.
    public boolean eliminarRon(String nombre) {
        int index = listaRones.indexOf(buscarRonPorNombre(nombre));
        if (index!= -1) {
            listaRones.remove(index);
            guardarRones();
            return true;
        }
        return false;
    }

    //Guarda el inventario de rones en el archivo especificado.
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

    //Carga el inventario de rones desde el archivo especificado.
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

    //Devuelve la lista completa de rones.
    public List<RonGeneral> getListaRones() {

        return listaRones;
    }
}