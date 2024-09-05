package com.adriananiel.roneracentral.GestionDeProcesos;
import com.adriananiel.roneracentral.GestionDeProcesos.libs.BaseDatosArchivos;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class PeidosItemsController {


    @FXML
    private Text cliente;

    @FXML
    private HBox conteiner;

    @FXML
    private Text pedido;

    @FXML
    private Text precio;

    @FXML
    private Text prioridad;

    private MyUpdateUser myUpdateUser;
    public int index;

    private Pedido pedidoUser;

    public PedidosController pedidosController;

    public void setDate(Pedido pedido, int index, MyUpdateUser myUpdateUser){
        this.myUpdateUser = myUpdateUser;
        this.pedidoUser = pedido;
        this.index = index;
        this.cliente.setText(pedido.getClientName());
        this.pedido.setText(pedido.getPedido());
        this.precio.setText(String.valueOf(pedido.getPrice()));
        this.prioridad.setText(pedido.getPrioridad());
        conteiner.setStyle((index % 2) == 0 ? "-fx-background-color: #d3d3d3": "-fx-background-color: #e8e8e8");

    }

    @FXML
    void deleteImen(MouseEvent event) {
        BaseDatosArchivos bd = new BaseDatosArchivos();
        bd.borrarDatosPedidos(index);
        pedidosController.initialize(null, null);
    }

    @FXML
    void editImen(MouseEvent event) {
        myUpdateUser.updateUser(pedidoUser , pedidosController);
    }

    public void setController( PedidosController pedidosController){
        this.pedidosController = pedidosController;
    }

}