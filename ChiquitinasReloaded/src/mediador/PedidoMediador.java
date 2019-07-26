/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mediador;

import controllers.ControllerPedido;
import controllers.ControllerProducto;
import controllers.ControllerProveedor;
import java.util.Scanner;

/**
 *
 * @author santialfonso
 */
public class PedidoMediador implements Mediador {

    private final ControllerPedido controllerPedido;
    private final ControllerProveedor controllerProveedor;
    private final ControllerProducto controllerProducto;

    public PedidoMediador(ControllerPedido controllerPedido, ControllerProveedor controllerProveedor, ControllerProducto controllerProducto) {
        this.controllerPedido = controllerPedido;
        this.controllerProducto = controllerProducto;
        this.controllerProveedor = controllerProveedor;
    }

    @Override
    public void start() {
        controllerProveedor.getProveedorIdNombre();
    }

    @Override
    public void step2() {
        controllerProducto.getProductoForMenuByProv();

    }

    @Override
    public void step3() {
        controllerProducto.getProductoById();
    }

    @Override
    public void step4(String id) {
        controllerProducto.actualizarStock(id);
    }

}
