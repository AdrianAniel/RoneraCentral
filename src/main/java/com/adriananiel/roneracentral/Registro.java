package com.adriananiel.roneracentral;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Registro {
    private static List<Usuario> listaUsuarios = new ArrayList<>();
    private static final String archivo = "BaseDatos/usuarios.txt";

    public static void agregarUsuario(Usuario usuario) {
        listaUsuarios.add(usuario);
        guardarUsuarios();
    }

    public static Usuario buscarUsuarioPorNombre(String nombre) {
        for (Usuario usuario : listaUsuarios) {
            if (usuario.getUsername().equalsIgnoreCase(nombre)) {
                return usuario;
            }
        }
        return null;
    }

    public static boolean actualizarUsuario(String usernameActual, String nuevoUsername, String nuevaPassword, String nuevoEmail, String nuevaImagenDireccion) {
        int index = listaUsuarios.indexOf(buscarUsuarioPorNombre(usernameActual));
        if (index!= -1) {
            Usuario usuarioActualizado = new Usuario(nuevoUsername, nuevaPassword, nuevoEmail, nuevaImagenDireccion);
            listaUsuarios.set(index, usuarioActualizado);
            guardarUsuarios();
            return true;
        }
        return false;
    }

    public static boolean eliminarUsuario(String nombre) {
        int index = listaUsuarios.indexOf(buscarUsuarioPorNombre(nombre));
        if (index!= -1) {
            listaUsuarios.remove(index);
            guardarUsuarios();
            return true;
        }
        return false;
    }

    private static void guardarUsuarios() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(archivo))) {
            for (Usuario usuario : listaUsuarios) {
                writer.println(usuario.getUsername() + "," + usuario.getPassword() + "," + usuario.getEmail() + "," + usuario.getImagenDireccion());
            }
        } catch (IOException e) {
            System.out.println("Error al guardar los usuarios: " + e.getMessage());
        }
    }

    public static List<Usuario> buscarUsuariosPorCredenciales(String username, String password) {
        List<Usuario> usuariosCoincidentes = new ArrayList<>();
        for (Usuario usuario : listaUsuarios) {
            if (usuario.getUsername().equals(username) && usuario.getPassword().equals(password)) {
                usuariosCoincidentes.add(usuario);
            }
        }
        return usuariosCoincidentes;
    }

    public static void cargarUsuariosDesdeArchivo() {
        listaUsuarios.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine())!= null) {
                String[] partes = linea.trim().split(",");
                String username = partes[0].trim();
                String password = partes[1].trim();
                String email = partes[2].trim();
                String imagenDireccion = partes.length > 3? partes[3].trim() : "";
                Usuario usuario = new Usuario(username, password, email, imagenDireccion);
                listaUsuarios.add(usuario);
            }
        } catch (IOException e) {
            System.out.println("Error al cargar los usuarios desde el archivo: " + e.getMessage());
        }
    }
}