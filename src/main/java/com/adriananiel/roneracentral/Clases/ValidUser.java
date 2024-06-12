package com.adriananiel.roneracentral.Clases;

import java.io.IOException;
import java.util.ArrayList;

public class ValidUser {
    public Usuario valid(String email, String password) {
    ReadFile ro = new ReadFile("users_db.ser");
    ArrayList<Usuario> usersList = new ArrayList<>();
    try {
        usersList = ro.Read();
    } catch (ClassNotFoundException | IOException e) {
        e.printStackTrace();
    }
    for (Usuario user: usersList){
        if (user.getEmail().equals(email) && user.getPassword().equals(password)){
            return user;
        }
    }
    return null;
    }
}
