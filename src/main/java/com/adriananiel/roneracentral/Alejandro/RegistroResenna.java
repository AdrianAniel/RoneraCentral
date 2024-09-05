package com.adriananiel.roneracentral.Alejandro;

import com.adriananiel.roneracentral.amanda.Resenna;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RegistroResenna {

    private static int ultimoId = 0;
    private static List<Resenna> listaResenna = new ArrayList<>();
    private static final String archivo = "BaseDatos/resenna.txt";

    static {
        cargarResennas();
    }
    public static Resenna agregarResenna(Resenna resenna) {
        resenna.setId(String.valueOf(++ultimoId));
        listaResenna.add(resenna);
        guardarResenna();
        return resenna;
    }

    private static void cargarResennas() {
        cargarResennasDesdeArchivo();

        // Actualiza el último ID según la lista cargada
        if (!listaResenna.isEmpty()) {
            boolean seen = false;
            long best = 0;
            for (Resenna resenna : listaResenna) {
                long nombre = Long.parseLong(resenna.getNombre());
                if (!seen || nombre > best) {
                    seen = true;
                    best = nombre;
                }
            }
            ultimoId = seen ? (int) best : 0;
        }
    }

    public static Resenna buscarResenaPorNombre(String nombre) {
        cargarResennasDesdeArchivo();

        return listaResenna.stream()
                .filter(resenna -> resenna.getNombre().equalsIgnoreCase(nombre))
                .findFirst()
                .orElse(null);
    }

    public static boolean eliminarResena(int nombre) {
        int index = listaResenna.indexOf(buscarResenaPorNombre(String.valueOf(nombre)));
        if (index!= -1) {
            listaResenna.remove(index);
            guardarResenna();
            return true;
        }
        return false;
    }

    private static void guardarResenna() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(archivo))) {
            for (Resenna resenna : listaResenna) {
                writer.println(resenna.getNombre() + "," + resenna.getClasificacion() + "," + resenna.getComentario());
            }
        } catch (IOException e) {
            System.out.println("Error al guardar las reseñas: " + e.getMessage());
        }
    }

    public static void cargarResennasDesdeArchivo() {
        listaResenna.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.trim().split(",");
                String nombre = partes[0].trim();
                String clasificacion = partes[1].trim();
                String comentarios = partes.length>2?partes[2].trim():"";
                Resenna resenna = new Resenna(nombre, clasificacion, comentarios);
                listaResenna.add(resenna);
            }
        } catch (IOException e) {
            System.out.println("Error al cargar las reseñas desde el archivo: " + e.getMessage());
        }
    }

    public List<Resenna> getListaResenna() {
        return listaResenna;
    }
}