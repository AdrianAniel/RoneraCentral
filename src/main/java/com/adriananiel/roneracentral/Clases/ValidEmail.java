package com.adriananiel.roneracentral.Clases;

import java.util.regex.Pattern;
import java.util.regex.Matcher;


public class ValidEmail {

    private static final String REGEX_CORREO = "^[A-Za-z0-9+_.-]+@(.+)$";

    public static boolean validEmail(String correo) {
        Pattern pattern = Pattern.compile(REGEX_CORREO);
        Matcher matcher = pattern.matcher(correo);
        return matcher.matches();
    }
}
