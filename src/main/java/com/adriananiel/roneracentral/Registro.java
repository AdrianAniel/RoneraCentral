package com.adriananiel.roneracentral;

import java.util.ArrayList;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Registro {
    private static ArrayList<Usuario> usuarios = new ArrayList<>();

    public static void registrarUsuario(Usuario nuevoUsuario) throws IOException {
        usuarios.add(nuevoUsuario);
        guardarUsuarios(); // Ahora actualizará el archivo sin borrar el contenido existente
    }

    private static void guardarUsuarios() throws IOException {
        List<String> contenidoActual = Files.readAllLines(Paths.get("BaseDatos/usuarios.txt"));
        contenidoActual.add(usuarioToString(usuarios)); // Agrega el nuevo usuario al final del archivo
        Files.write(Paths.get("BaseDatos/usuarios.txt"), contenidoActual); // Sobrescribe el archivo con el contenido actualizado
    }

    private static String usuarioToString(ArrayList<Usuario> usuarios) {
        StringBuilder sb = new StringBuilder();
        for (Usuario usuario : usuarios) {
            sb.append(usuario.getUsername()).append(",").append(usuario.getPassword()).append(",").append(usuario.getEmail()).append("\n");
        }
        return sb.toString();
    }

    public static ArrayList<Usuario> getUsuarios() {
        try {
            ArrayList<Usuario> tempUsuarios = new ArrayList<>();
            List<String> lines = Files.readAllLines(Paths.get("BaseDatos/usuarios.txt"));
            for (String line : lines) {
                String[] parts = line.split(",");
                if (parts.length == 3) { // Asegúrate de que cada línea tiene las tres partes esperadas
                    String username = parts[0];
                    String password = parts[1];
                    String email = parts[2];
                    tempUsuarios.add(new Usuario(username, password, email));
                }
            }
            usuarios = tempUsuarios; // Actualiza la lista de usuarios
            System.out.println("Contenido leído del archivo usuarios.txt:");
            lines.forEach(System.out::println); // Imprime el contenido del archivo
            return usuarios;
        } catch (IOException e) {
            System.err.println("Error al leer el archivo usuarios.txt: " + e.getMessage());
            return null;
        }
    }
}
