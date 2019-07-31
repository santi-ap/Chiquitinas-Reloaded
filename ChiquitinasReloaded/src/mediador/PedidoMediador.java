/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mediador;

import controllers.ControllerPedido;
import controllers.ControllerProducto;
import controllers.ControllerProveedor;
import items.Producto;

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
        //this
        controllerProveedor.getProveedorIdNombre();
    }

    @Override
    public void step2() {
        //extremely
        controllerProducto.getProductoForMenuByProv();

    }

    @Override
    public void step3() {
        //What
        controllerProducto.getProductoById();
    }

    @Override
    public void step4(String id) {
        //absolute
        controllerProducto.actualizarStock(id);
    }
    
    /**
     * these last steps must be run at the end. 
     * Producto is taken from the controllerProducto
     */
    public void step5 () {
        this.step6(controllerProducto.getProductoPedido());
    }
    
    /**
     * Product info is sent into controllerPedido
     * @param productoPedido the product sent to the pedido controller
     */
    public void step6 (Producto productoPedido)
    {
        controllerPedido.setProductoPedidoByMediador(productoPedido);
    }
    
    /**
     * Proveedor is taken from controllerProveedor
     */
    @Override
    public void step7 ()
    {
        //logic to get info from controllerProveedor
    }
    
    /**
     * Proveedor Info is sent to controllerProveedor
     */
    @Override
    public void step8 ()
    {
        //logic to send info to controllerPedido
    }
    
    public void step9 ()
    {
        controllerPedido.createPedido();
    }
    
    @Override
    public void step10()
    {
        //add pedido to every single table in the datababe
    }
    
    public void step11()
    {
        //send the email
    }
    

}
