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
import java.util.logging.Level;
import java.util.logging.Logger;

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
    
    /*THIS MUST BE RUN AT THE END*/
    /**
     * 
     * Producto is taken from the controllerProducto
     */
    public void takeProductoFromControllerProducto () {
        this.sendProductoToControllerPedido(controllerProducto.getProductoPedido());
    }
    
    /**
     * Product info is sent into controllerPedido
     * @param productoPedido the product sent to the pedido controller
     */
    public void sendProductoToControllerPedido (Producto productoPedido)
    {
        controllerPedido.setProductoPedidoByMediador(productoPedido);
    }
    
    /**
     * ProveedorId is taken from controllerProducto
     */
    
    public void takeProveedorFromControllerProveedor ()
    {
        controllerProducto.getIdProveedorPedidobyMediador();
    }
    
    /**
     * ProveedorId is sent to controllerPedido
     * @param idProveedor
     */ 
    
    public void sendProveedorIdToControllerPedido (String idProveedor)
    {
        controllerPedido.setIdProveedorPedidoByMediador(idProveedor);
    }
    
    /**
     * pedido is created using the selected ammount chosen by the user
     */
    public void createPedidoWithAmmount ()
    {
        //during a new order, the nuevoproducto.cantidadactual represents the number of products ordered.
        controllerPedido.getProductoPedido().setCantidadActualProducto(controllerProducto.getMontoCompra());                        //this line is done in 4 methods using the mediator pattern.
        controllerPedido.createPedido();
    }
    
    /**
     * sending info to pedido database
     */
    
    public void insertIntoPedido()
    {
        controllerPedido.getServicioPedido().insert(controllerPedido.getPedidoByMediador());        
        this.insertIntoPedidoHasProducto();


    }
    
    /**
     * sending info to pedidoHasProducto
     */
    public void insertIntoPedidoHasProducto ()
    {
        controllerPedido.insertIntoPedidoHasProducto();
    }
    
    /**
     * sending info to pedidohasproveedor
     */
    public void insertIntoPedidoHasProveedor ()
    {
        controllerPedido.insertIntoPedidoHasProveedor();
    }
    
    /*          STEPS TODO                      */
    /*  get the ammount of products bought     
        we also need to copy these last steps to the other mediador*/
    public void enviarCorreo()
    {
        controllerPedido.enviarCorreo();
    }

    

}
