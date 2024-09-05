package com.adriananiel.roneracentral.GestionDeProcesos;

import com.adriananiel.roneracentral.GestionDeProcesos.libs.BaseDatosArchivos;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class ButtonEditController {
    @FXML
    private Button btnEditar;

    private Pedido pedido;

    private PedidosController pedidosController;
    @FXML
    void eventBtnEditar(MouseEvent event) {
        BaseDatosArchivos bd = new BaseDatosArchivos();
        ArrayList<Pedido> pedidos = bd.leerDatosPedido();
        Pedido p = new Pedido();
        AtomicInteger indice = new AtomicInteger(0);
        pedidos.forEach(pedido1 -> {
            if (pedido1.getIdPedido().equals(pedido.getIdPedido())){
                return;
            }
            indice.getAndIncrement();
        });
        pedidos.removeIf(pedido1 -> pedido1.getIdPedido().equals(pedido.getIdPedido()));

        p.setPedido(pedidosController.inputPedido.getText());
        p.setClientName(pedidosController.inputCliente.getText());
        p.setPrice(Integer.parseInt(pedidosController.inputPrecio.getText()));
        p.setDescription(pedidosController.inputDescripcion.getText());
        p.setPrioridad(pedidosController.inputPrioridad.getText());


        pedidosController.inputCliente.setText("");
        pedidosController.inputDescripcion.setText("");
        pedidosController.inputPedido.setText("");
        pedidosController.inputPrecio.setText("");
        pedidosController.inputPrioridad.setText("");

        pedidos.add(indice.get()-1, p);
        bd.guardarDatos(pedidos);
        pedidosController.initialize(null, null);
    }

    @FXML
    void setOnMouseEnteredEditar(MouseEvent event) {
        btnEditar.setStyle("-fx-background-radius: 10; -fx-background-color: #304561;");
        btnEditar.setFocusTraversable(true);
    }

    @FXML
    void setOnMouseExitEditar(MouseEvent event) {
        btnEditar.setStyle("-fx-background-radius: 10; -fx-background-color: #1a273c;");
        btnEditar.setFocusTraversable(false);
    }

    void setData(Pedido pedido, PedidosController pedidosController){
        this.pedidosController = pedidosController;
        this.pedido = pedido;
    }
}
