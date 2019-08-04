/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mediador;

import objetos.Usuario;
import controllers.*;

/**
 *
 * @author Asus
 */
public class OrdenMediador implements Mediador {

    ControllerOrden controllerOrden;
    ControllerProducto controllerProducto;
    ControllerCarrito controllerCarrito;
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
    }
    
    public void sendCarritoToControllerOrden()
    {
        this.controllerOrden.setListaProductos(this.controllerCarrito.getListaProductos());
        this.controllerOrden.setListaCombos(this.controllerCarrito.getListaCombos());
        this.createOrder();
    }
    public void createOrder()
    {
        this.controllerOrden.createOrden();
    }
    
    public void updateStock()
    {
        //call santi's method
    }
    
    public void emptyCarrito()
    {
        
    }
}
