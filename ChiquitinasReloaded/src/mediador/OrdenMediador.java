/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mediador;

import objetos.Usuario;
import controllers.*;
import items.Combo;
import items.Orden;
import items.Producto;
import java.util.ArrayList;
import servicios.ServicioCarrito;
import servicios.ServicioComboHasProducto;

/**
 *
 * @author Asus
 */
public class OrdenMediador implements Mediador {

    ControllerOrden controllerOrden;
    ControllerProducto controllerProducto;
    ControllerCarrito controllerCarrito;
    ControllerCombo controllerCombo;

    public OrdenMediador(ControllerOrden controllerOrden, ControllerProducto controllerProducto, ControllerCarrito controllerCarrito, ControllerCombo controllerCombo) {
        this.controllerOrden = controllerOrden;
        this.controllerProducto = controllerProducto;
        this.controllerCarrito = controllerCarrito;
        this.controllerCombo = controllerCombo;
    }

    @Override
    public void start(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void start (Usuario usuario)
    {
        this.sendUserInfoToOrdenController(usuario);
    }
    
    public void sendUserInfoToOrdenController(Usuario usuario)
    {
        controllerOrden.setUsuario(usuario);
        this.lookForCarrito(usuario);
    }
    
    public void lookForCarrito (Usuario usuario) //products and combos
    {
        this.controllerCarrito.lookForCarrito(usuario);
        this.sendCarritoToControllerOrden();
    }
    
    public void sendCarritoToControllerOrden()
    {
        this.controllerOrden.setListaProductos(this.controllerCarrito.getListaProductos());
        this.controllerOrden.setListaCombos(this.controllerCarrito.getListaCombos());
        this.createOrder();
    }
    
    /**
     * creates the order and sends it to the db 
     */
    public void createOrder()
    {
        this.controllerOrden.createOrden();
    }
    
    public void updateStock(Orden orden, ArrayList<Object> listaProductos, ArrayList<Object> listaCombos)
    {
        ServicioComboHasProducto scp = new ServicioComboHasProducto();
        for (Object p:listaProductos)
        {
            this.controllerProducto.updateStockDespuesDeOrden((Producto)p);
        }
        
        for (Object c:listaCombos)
        {
            //logic to update combo stock                                        (which is negative)
            this.controllerCombo.updateStock(((Combo)c).getCantidadActualProductoCombo()*-1, (Combo)c);
            //logic to update productsincombo stock
            ArrayList<Object> temp = scp.selectAllProductsOfCombo(Integer.toString(((Combo)c).getIdCombo()));
            for (Object p:temp)
            {
                //lets get the right ammount first 
                ((Producto)p).setCantidadActualProducto(((Combo)c).getCantidadActualProductoCombo());
                //then we update 
                this.controllerProducto.updateStockDespuesDeOrden((Producto)p);
            }
        }
        //to the next step we goooo
        this.emptyCarrito();
    }
    
    public void emptyCarrito()
    {
        //carrito delenda est
        this.controllerOrden.emptyCarrito();
        
    }
}
