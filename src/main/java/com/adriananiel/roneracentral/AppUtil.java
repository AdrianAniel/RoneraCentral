package com.adriananiel.roneracentral;

public class AppUtil {
    //Variable privada y estática para almacenar el nombre del usuario logueado.
    private static String loggedInUser;

    //Establece el nombre del usuario logueado en la aplicación.
    public static void setLoggedInUser(String user) {
        loggedInUser = user;
    }

    //Obtiene el nombre del usuario actualmente logueado en la aplicación.
    public static String getLoggedInUser() {
        return loggedInUser;
    }
}
