package com.adriananiel.roneracentral.Clases;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

public class Usuario implements Serializable {
    private UUID id;
    private String nombre;
    private String email;
    private String password;
    private boolean status;

    //Constructores
    public Usuario(){}
    public Usuario(String nombre, String email, String password) throws Exception {
        this.id = UUID.randomUUID();
        this.nombre = nombre;
        this.status = false;
        if (ValidEmail.validEmail(email)){
            this.email = email;
        }else{
            throw new Exception("Email invalido.");
        }
        this.password = password;
    }
    //Getters y Setters
    public String getNombre(){
        return nombre;
    };
    public String getEmail(){
        return email;
    };
    public String getPassword(){
        return password;
    };
    public UUID getID(){
        return id;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    };
    public void setEmail(String email){
        this.email = email;
    };
    public void setPassword(String password){
        this.password = password;
    };
    public boolean getStatus(){
        return status;
    }
    public void setStatus(boolean status){
        this.status = status;
    }
    public static void Create(Usuario user) throws ClassNotFoundException, IOException{
        ReadFile ro = new ReadFile("users_db.ser");
        WriteFile wo = new WriteFile("users_db.ser");
        ArrayList<Usuario> newUsersList = new ArrayList<>();
        newUsersList = ro.Read();
        newUsersList.add(user);
        wo.Write(newUsersList);
    }
    public static void Update(Usuario userToUpdate) throws ClassNotFoundException, IOException {
        ReadFile ro = new ReadFile("users_db.ser");
        WriteFile wo = new WriteFile("users_db.ser");
        ArrayList<Usuario> usersList = ro.Read();
        for (Usuario user : usersList) {
            if (user.getID().equals(userToUpdate.getID())) {
                user.setNombre(userToUpdate.getNombre());
                user.setEmail(userToUpdate.getEmail());
                user.setPassword(userToUpdate.getPassword());
                break;
            }
        }
        wo.Write(usersList);
    }
    public static void Delete(Usuario userToDelete) throws ClassNotFoundException, IOException {
        ReadFile ro = new ReadFile("users_db.ser");
        WriteFile wo = new WriteFile("users_db.ser");
        ArrayList<Usuario> usersList = ro.Read();
        for (Usuario user : usersList) {
            if (user.getID().equals(userToDelete.getID())) { 
                usersList.remove(user);
                break;
            }
        }
        wo.Write(usersList);
    }
}
