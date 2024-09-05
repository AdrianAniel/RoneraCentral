package com.adriananiel.roneracentral.GestionDeProcesos;

import com.adriananiel.roneracentral.GestionDeProcesos.libs.BaseDatosArchivos;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PedidosController implements Initializable {
    @FXML
    private VBox VBoxLayout;
    @FXML
    private Button btnGuardar;
    @FXML
    public TextField inputCliente;
    @FXML
    public TextArea inputDescripcion;
    @FXML
    public TextField inputPedido;
    @FXML
    public TextField inputPrecio;
    @FXML
    public TextField inputPrioridad;
    @FXML
    private Text textCantPedidos;
    @FXML
    private Text textPrecioTotal;
    @FXML
    public Text textTitle;
    @FXML
    private VBox panelPedidos;
    @FXML
    private VBox vBoxButton;

    private MyUpdateUser myUpdateUser;

    private ProcesosController procesosController;

    public void setController(ProcesosController procesosController){
        this.procesosController = procesosController;
    }

    @FXML
    void setOnMouseEntered(MouseEvent event) {
        btnGuardar.setStyle("-fx-background-radius: 10; -fx-background-color: #304561;");
        btnGuardar.setFocusTraversable(true);
    }
    @FXML
    void setOnMouseExit(MouseEvent event) {
        btnGuardar.setStyle("-fx-background-radius: 10; -fx-background-color: #1a273c;");
        btnGuardar.setFocusTraversable(false);
    }
    @FXML
    void eventBtnSave(MouseEvent event)throws IOException{
        BaseDatosArchivos bd = new BaseDatosArchivos();

       try {
           Pedido pedido = new Pedido();
           pedido.setPedido(inputPedido.getText());
           pedido.setClientName(inputCliente.getText());
           pedido.setPrice(Integer.parseInt(inputPrecio.getText()));
           pedido.setDescription(inputDescripcion.getText());
           pedido.setPrioridad(inputPrioridad.getText());

           bd.guardarDatos(pedido, true);

           inputCliente.setText("");
           inputDescripcion.setText("");
           inputPedido.setText("");
           inputPrecio.setText("");
           inputPrioridad.setText("");

           this.getClass().getClassLoader();

           btnGuardar.setStyle("-fx-background-color: #6cac23; -fx-background-radius: 10");
           initialize(null, null);
       }catch (NumberFormatException error){
           inputPrecio.setStyle("-fx-background-color: #dcdcdc; -fx-border-color: #ff5370; -fx-background-radius: 7; -fx-border-radius: 7;");
           btnGuardar.setStyle("-fx-background-color: #ff5370; -fx-background-radius: 10");
       }
    }

    @FXML
    void eventTextCliqued(MouseEvent event) throws IOException{
        inputPrecio.setStyle("-fx-background-color: #dcdcdc; -fx-border-color: #999999; -fx-background-radius: 7; -fx-border-radius: 7;");
        btnGuardar.setStyle("-fx-background-color:   #1a273c; -fx-background-radius: 10");
    }

    void updateButton(Button button){
        vBoxButton.getChildren().clear();
        vBoxButton.getChildren().setAll(button);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        BaseDatosArchivos bd = new BaseDatosArchivos();
        ArrayList<Pedido> pedidos = new ArrayList<>(bd.leerDatosPedido());
        VBoxLayout.getChildren().setAll();
        myUpdateUser = new MyUpdateUser() {
            @Override
            public void updateUser(Pedido pedido, PedidosController pedidosController) {
                setData(pedido);
                FXMLLoader loader1 = new FXMLLoader();
                loader1.setLocation(getClass().getResource("button-edit.fxml"));
                try{
                    Button button = loader1.load();
                    updateButton(button);
                    ButtonEditController buttonEditController = loader1.getController();
                    buttonEditController.setData(pedido, pedidosController);
                }catch (IOException e){
                    throw new RuntimeException(e);
                }
            }
        };

        int precio = 0;

        for (int i = 0; i < pedidos.size(); i++) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("PeidosItems.fxml"));
            precio += pedidos.get(i).getPrice();
            try {
                HBox hBox = loader.load();
                PeidosItemsController peidosItemsController = loader.getController();
                peidosItemsController.setDate(pedidos.get(i), i, myUpdateUser);
                peidosItemsController.setController(this);
                VBoxLayout.getChildren().add(hBox);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        textCantPedidos.setText(String.valueOf(pedidos.size()));
        textPrecioTotal.setText(String.format("%s$", precio));
    }

    void setData(Pedido pedido){
        inputCliente.setText(pedido.getClientName());
        inputDescripcion.setText(pedido.getDescription());
        inputPedido.setText(pedido.getPedido());
        inputPrecio.setText(String.valueOf(pedido.getPrice()));
        inputPrioridad.setText(pedido.getPrioridad());
    }
}
