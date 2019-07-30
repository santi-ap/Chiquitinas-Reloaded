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
    public void start(int i) {
        if (i == 1) {//Inicia agregar un producto nuevo de un proveedor existente
            this.crpGetListaProveedores();
        } else {//inicia pedir un producto existente
            if (i == 2) {//Inicia agregar un producto nuevo de un proveedor nuevo
                this.crpCrearProveedor();
            } else {
                this.opeGetListaProveedores();
            }
        }
    }

    //-----------------------ORDENAR PRODUCTO EXISTENTE (ope)-----------------------
    @Override
    public void opeGetListaProveedores() {// Imprime la lista de proveedores
        controllerProveedor.getProveedorIdNombre();
        this.opeGetListaProductoProv();

    }

    @Override
    public void opeGetListaProductoProv() {//Imprime la lista de productos de un proveedor
        controllerProducto.getProductoForMenuByProv();

    }

    @Override
    public void opeGetProductoSeleccionado() {//Imprime el producto que se selecciono
        controllerProducto.getProductoById();
    }

    @Override
    public void opeSetMontoOrden(String id) {// Pide la cantidad que se quiere pedir y actualiza el stock
        controllerProducto.actualizarStock(id);
    }
    //-----------------------END ORDENAR PRODUCTO-----------------------

    //------------------------------------------------------------------
    //-----------------------CREAR PRODUCTO NUEVO(crp)-----------------------
    public void crpCrearProveedor() {
        controllerProveedor.crearProveedor();
        this.crpCrearProducto();
    }

    @Override
    public void crpGetListaProveedores() {// Imprime la lista de proveedores
        controllerProveedor.getProveedorIdNombre();
        this.crpCrearProducto();
    }

    @Override
    public void crpCrearProducto() {// se pide los datos del producto, se crea y se inserta a la base datos
        controllerProducto.crearProducto();
    }
    //-----------------------END CREAR PRODUCTO-----------------------

    /*THIS MUST BE RUN AT THE END*/
    /**
     *
     * Producto is taken from the controllerProducto
     */
    public void takeProductoFromControllerProducto() {
        System.out.println("Guardando Informaciones.");
        this.sendProductoToControllerPedido(controllerProducto.getProductoPedido());
    }

    /**
     * Product info is sent into controllerPedido
     *
     * @param productoPedido the product sent to the pedido controller
     */
    public void sendProductoToControllerPedido(Producto productoPedido) {
        controllerPedido.setProductoPedidoByMediador(productoPedido);
    }

    /**
     * ProveedorId is taken from controllerProducto
     */
    public void takeProveedorFromControllerProveedor() {
        controllerProducto.getIdProveedorPedidobyMediador();
    }

    /**
     * ProveedorId is sent to controllerPedido
     *
     * @param idProveedor
     */
    public void sendProveedorIdToControllerPedido(String idProveedor) {
        controllerPedido.setIdProveedorPedidoByMediador(idProveedor);
    }

    /**
     * pedido is created using the selected ammount chosen by the user
     */
    public void createPedidoWithAmmount() {
        System.out.println("Creando Pedido.");
        //during a new order, the nuevoproducto.cantidadactual represents the number of products ordered.
        controllerPedido.getProductoPedido().setCantidadActualProducto(controllerProducto.getMontoCompra());                        //this line is done in 4 methods using the mediator pattern.
        controllerPedido.createPedido();
    }

    /**
     * sending info to pedido database
     */
    public void insertIntoPedido() {
        System.out.println("Registrando Pedido.");

        controllerPedido.getServicioPedido().insert(controllerPedido.getPedidoByMediador());
        this.insertIntoPedidoHasProducto();

    }

    /**
     * sending info to pedidoHasProducto
     */
    public void insertIntoPedidoHasProducto() {

        controllerPedido.insertIntoPedidoHasProducto();
    }

    /**
     * sending info to pedidohasproveedor
     */
    public void insertIntoPedidoHasProveedor() {
        controllerPedido.insertIntoPedidoHasProveedor();
    }

    /*          STEPS TODO                      */
 /*  get the ammount of products bought     
        we also need to copy these last steps to the other mediador*/
    public void enviarCorreo() {
        controllerPedido.enviarCorreo();
        System.out.println("Correo Enviado.");
    }

}
