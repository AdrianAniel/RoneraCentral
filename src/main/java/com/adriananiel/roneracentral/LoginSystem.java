package com.adriananiel.roneracentral;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoginSystem {
    private static final String FILE_NAME = "BaseDatos/Users.txt";

    public void addUser(String username, String password) throws IOException {
        try (FileWriter writer = new FileWriter(FILE_NAME, true)) { // 'true' para añadir al final del archivo sin sobrescribir
            writer.write(username + ":" + password + "\n");
        }
    }

    public boolean checkCredentials(String username, String password) throws IOException {
        return readUsers().containsKey(username) && readUsers().get(username).equals(password);
    }

    private Map<String, String> readUsers() throws IOException {
        Map<String, String> users = new HashMap<>();
        if (!new File(FILE_NAME).exists()) return users; // Si el archivo no existe, devuelve un mapa vacío

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine())!= null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    users.put(parts[0], parts[1]);
                }
            }
        }
        return users;
    }
}