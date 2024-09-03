package com.adriananiel.roneracentral;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.*;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class AppController implements Initializable {

    private String rol;

    @FXML
    private Hyperlink BtnInventario;

    @FXML
    private Hyperlink BtnCuenta;

    @FXML
    private Pane PaneCambia;

    @FXML
    private Pane PaneInfo;

    @FXML
    private Hyperlink BtnCerrarSesión;

    @FXML
    private AnchorPane VentanaApp;

    @FXML
    private Hyperlink BtnCalidad;

    @FXML
    private Hyperlink BtnProcesos;

    public void activarDesactivarMenu(){
        obtenerRolDeUsuario();
        String inventario = "Inventario";
        String procesos = "Procesos";
        String calidad = "Calidad";

        if (Objects.equals(this.rol, inventario)) {
            System.out.println("Inventario activado en sentencia condicional");
            BtnCalidad.setDisable(true);
            BtnProcesos.setDisable(true);

        } else if (Objects.equals(this.rol, procesos)) {
            System.out.println("Procesos activado en sentencia condicional");
            BtnCalidad.setDisable(true);
            BtnInventario.setDisable(true);

        } else if (Objects.equals(this.rol, calidad)) {
            System.out.println("Calidad activado en sentencia condicional");
            BtnInventario.setDisable(true);
            BtnProcesos.setDisable(true);

        }
    }

    public void cerrarAbrirVentana(){
        // Obtener el Stage desde el PaneGestionarCuenta
        Stage stage = (Stage) VentanaApp.getScene().getWindow();
        stage.close(); // Cierra la ventana

        try {
            // Cargar el FXML de la nueva ventana
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SignIn.fxml"));
            Parent root = loader.load(); // Esto carga el FXML y devuelve el nodo raíz
            SignInController controller = loader.getController(); // Obtiene el controlador de la vista cargada

            // Crear una nueva escena y mostrarla en una nueva ventana
            Stage nuevaVentana = new Stage();
            Scene escenaNuevaVentana = new Scene(root);
            nuevaVentana.setScene(escenaNuevaVentana);
            nuevaVentana.show();
        } catch (IOException e) {
            System.err.println("Error al cargar la nueva ventana: " + e.getMessage());
        }
    }

    public void obtenerRolDeUsuario() {
        String loggedInUserName = AppUtil.getLoggedInUser();
        if (loggedInUserName != null) {
            System.out.println("Bienvenido, " + loggedInUserName);
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader("BaseDatos/usuarios.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userDetails = line.split(",");
                String username = userDetails[0];
                String rol = userDetails[4]; // Asumiendo que el índice correcto para el rol es 4

                if (username.equals(loggedInUserName)) {
                    this.rol = rol; // Asignar el rol encontrado
                    System.out.println("Este es el rol " + rol);
                } else {
                    System.out.println("Rol no encontrado para " + username);
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void eventBtnCuenta(MouseEvent event) throws IOException {
        Pane ventanaGestionarCuentaFXML = FXMLLoader.load(getClass().getResource("GestionarCuenta.fxml"));
        PaneCambia.getChildren().setAll(ventanaGestionarCuentaFXML);

    }

    @FXML
    void eventBtnHome(MouseEvent event) throws IOException {
        Pane ventanaHomeFXML = FXMLLoader.load(getClass().getResource("PaneHome.fxml"));
        PaneCambia.getChildren().setAll(ventanaHomeFXML);
    }


    @FXML
    void eventBtnInventario(MouseEvent event) throws IOException {
        Pane ventanaInventarioFXML = FXMLLoader.load(getClass().getResource("InventoryGestion.fxml"));
        PaneCambia.getChildren().setAll(ventanaInventarioFXML);
    }

    @FXML
    void eventBtnCalidad(MouseEvent event) throws IOException {
        Pane ventanaQualityControlFXML = FXMLLoader.load(getClass().getResource("QualityControl.fxml"));
        PaneCambia.getChildren().setAll(ventanaQualityControlFXML);
    }

    @FXML
    void eventCerrarSesión(MouseEvent event) throws IOException {
        cerrarAbrirVentana();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        activarDesactivarMenu();
    }

    @FXML
    void eventBtnProcesos(MouseEvent event) throws IOException{
        Pane ventanaInventarioFXML = FXMLLoader.load(getClass().getResource("GestionDeProcesos/Procesos.fxml"));
        PaneCambia.getChildren().setAll(ventanaInventarioFXML);
    }
}