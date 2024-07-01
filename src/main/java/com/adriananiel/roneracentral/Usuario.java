package com.adriananiel.roneracentral;

public class Usuario {
    private String username;
    private String password;
    private String email;
    private String imagenDireccion;

    public Usuario(String username, String password, String email, String imagenDireccion) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.imagenDireccion = imagenDireccion;

    }

    // Getters y Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImagenDireccion() {
        return imagenDireccion;
    }

    public void setImagenDireccion(String imagenDireccion) {
        this.imagenDireccion = imagenDireccion;
    }
}
