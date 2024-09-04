package com.adriananiel.roneracentral;
import java.util.List;

public class Login {
    //Intenta iniciar una sesión de usuario basándose en las credenciales proporcionadas.
    public static boolean iniciarSesion(String username, String password) {
        // Busca usuarios coincidentes en la base de datos según las credenciales proporcionadas
        List<Usuario> usuariosCoincidentes = Registro.buscarUsuariosPorCredenciales(username, password);
        // Verifica si se encontraron usuarios coincidentes
        if (!usuariosCoincidentes.isEmpty()) {
            // Si se encuentran coincidencias, establece el usuario logueado en AppUtil
            AppUtil.setLoggedInUser(username);
            System.out.println("Inicio de sesión exitoso.");
            return true;
        } else {
            System.out.println("Error en el inicio de sesión. Nombre de usuario o contraseña incorrectos.");
            return false;
        }
    }
}
