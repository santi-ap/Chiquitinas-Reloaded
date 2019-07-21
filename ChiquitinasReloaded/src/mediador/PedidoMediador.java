/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mediador;

import controllers.ControllerPedido;
import controllers.ControllerProducto;
import controllers.ControllerProveedor;

/**
 *
 * @author santialfonso
 */
public class PedidoMediador implements Mediador{
    private ControllerPedido controllerPedido;
    private ControllerProveedor controllerProveedor;
    private ControllerProducto controllerProducto;

    public PedidoMediador() {
    }

    public ControllerPedido getControllerPedido() {
        return controllerPedido;
    }

    public void setControllerPedido(ControllerPedido controllerPedido) {
        this.controllerPedido = controllerPedido;
    }

    public ControllerProveedor getControllerProveedor() {
        return controllerProveedor;
    }

    public void setControllerProveedor(ControllerProveedor controllerProveedor) {
        this.controllerProveedor = controllerProveedor;
    }

    public ControllerProducto getControllerProducto() {
        return controllerProducto;
    }

    public void setControllerProducto(ControllerProducto controllerProducto) {
        this.controllerProducto = controllerProducto;
    }
    
}
