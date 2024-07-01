package com.adriananiel.roneracentral;
import java.util.List;

public class Login {

    public static boolean iniciarSesion(String username, String password) {
        List<Usuario> usuariosCoincidentes = Registro.buscarUsuariosPorCredenciales(username, password);
        if (!usuariosCoincidentes.isEmpty()) {
            AppUtil.setLoggedInUser(username);
            System.out.println("Inicio de sesión exitoso.");
            return true;
        } else {
            System.out.println("Error en el inicio de sesión. Nombre de usuario o contraseña incorrectos.");
            return false;
        }
    }
}
