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
    public void start(int i) {
        if (i == 1) {//Inicia agregar un producto nuevo de un proveedor existente
            this.crpGetListaProveedores();
        } else {//inicia pedir un producto existente
            if (i == 2) {//Inicia agregar un producto nuevo de un proveedor nuevo
// space for new proveedor
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
