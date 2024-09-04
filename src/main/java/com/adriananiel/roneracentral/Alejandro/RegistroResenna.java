package com.adriananiel.roneracentral.Alejandro;

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
        resenna.setId(++ultimoId);
        listaResenna.add(resenna);
        guardarResenna();
        return resenna;
    }

    private static void cargarResennas() {
        cargarResennasDesdeArchivo();
        // Actualiza el último ID según la lista cargada
        if (!listaResenna.isEmpty())
            ultimoId = listaResenna.stream().mapToInt(Resenna::getId).max().orElse(0);
    }

    public static Resenna buscarResenaPorId(int id) {
        cargarResennasDesdeArchivo();
        for (Resenna resenna : listaResenna) {
            if (id == resenna.getId()) {
                return resenna;
            }
        }
        return null;
    }

    public static boolean eliminarResena(int id) {
        int index = listaResenna.indexOf(buscarResenaPorId(id));
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
                writer.println(resenna.getId() + "," + resenna.getClasificacion() + "," + resenna.getComentario());
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
                String id = partes[0].trim();
                String clasificacion = partes[1].trim();
                String comentarios = partes.length>2?partes[2].trim():"";
                Resenna resenna = new Resenna(Integer.parseInt(id), Integer.parseInt(clasificacion), comentarios);
                listaResenna.add(resenna);
            }
        } catch (IOException e) {
            System.out.println("Error al cargar las reseñas desde el archivo: " + e.getMessage());
        }
    }
}