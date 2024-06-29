package com.adriananiel.roneracentral;

import java.util.ArrayList;

public class Login {
    public static boolean iniciarSesion(String username, String password) {
        try {
            ArrayList<Usuario> usuarios = Registro.getUsuarios();
            for (Usuario usuario : usuarios) {
                System.out.println("Comprobando usuario: " + usuario.getUsername()); // Debugging
                if (usuario.getUsername().equals(username) && usuario.getPassword().equals(password)) {
                    System.out.println("Inicio de sesión exitoso.");
                    return true;
                }
            }
        } catch (Exception e) {
            System.err.println("Error al iniciar sesión: " + e.getMessage());
        }
        System.out.println("Error en el inicio de sesión. Nombre de usuario o contraseña incorrectos.");
        return false;
    }
}
