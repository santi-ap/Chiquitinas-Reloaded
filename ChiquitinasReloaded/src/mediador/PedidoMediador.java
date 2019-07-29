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
    @Override
    public void step7() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    

}
