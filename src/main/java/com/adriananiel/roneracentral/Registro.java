package com.adriananiel.roneracentral;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

//Clase responsable de gestionar la base de datos de usuarios. Utiliza archivos para persistir y recuperar datos.
public class Registro {
    // Lista de usuarios en memoria
    private static List<Usuario> listaUsuarios = new ArrayList<>();
    // Ruta del archivo donde se guardará/leerá la base de datos de usuarios
    private static final String archivo = "BaseDatos/usuarios.txt";

    //Agrega un nuevo usuario a la lista y lo guarda en el archivo.
    public static void agregarUsuario(Usuario usuario) {
        listaUsuarios.add(usuario);
        guardarUsuarios();
    }

    //Busca un usuario por su nombre de usuario.
    public static Usuario buscarUsuarioPorNombre(String nombre) {
        for (Usuario usuario : listaUsuarios) {
            if (usuario.getUsername().equalsIgnoreCase(nombre)) {
                return usuario;
            }
        }
        return null;
    }

    //Actualiza información de un usuario existente.
    public static boolean actualizarUsuario(String usernameActual, String nuevoUsername, String nuevaPassword, String nuevoEmail, String nuevaImagenDireccion, String nuevoRol) {
        int index = listaUsuarios.indexOf(buscarUsuarioPorNombre(usernameActual));
        if (index!= -1) {
            Usuario usuarioActualizado = new Usuario(nuevoUsername, nuevaPassword, nuevoEmail, nuevaImagenDireccion, nuevoRol);
            listaUsuarios.set(index, usuarioActualizado);
            guardarUsuarios();
            return true;
        }
        return false;
    }

    //Elimina un usuario de la lista y lo borra del archivo.
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
                writer.println(usuario.getUsername() + "," + usuario.getPassword() + "," + usuario.getEmail() + "," + usuario.getImagenDireccion() + "," + usuario.getRol());
            }
        } catch (IOException e) {
            System.out.println("Error al guardar los usuarios: " + e.getMessage());
        }
    }

    //Guarda la lista de usuarios en el archivo especificado.
    public static List<Usuario> buscarUsuariosPorCredenciales(String username, String password) {
        List<Usuario> usuariosCoincidentes = new ArrayList<>();
        for (Usuario usuario : listaUsuarios) {
            if (usuario.getUsername().equals(username) && usuario.getPassword().equals(password)) {
                usuariosCoincidentes.add(usuario);
            }
        }
        return usuariosCoincidentes;
    }

    //Busca usuarios que coinciden con las credenciales proporcionadas.
    public static void cargarUsuariosDesdeArchivo() {
        listaUsuarios.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.trim().split(",");
                String username = partes[0].trim();
                String password = partes[1].trim();
                String email = partes[2].trim();
                String imagenDireccion = partes.length > 3 ? partes[3].trim() : "";
                String rol = partes.length > 4 ? partes[4].trim():"";
                Usuario usuario = new Usuario(username, password, email, imagenDireccion, rol);
                listaUsuarios.add(usuario);
            }
        } catch (IOException e) {
            System.out.println("Error al cargar los usuarios desde el archivo: " + e.getMessage());
        }
    }
}