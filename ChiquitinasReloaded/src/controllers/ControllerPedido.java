/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import items.Pedido;
import items.Producto;
import mediador.Colleague;
import mediador.Mediador;
import objetos.Proveedor;
import observer.Observer;
import servicios.Servicio;
import servicios.ServicioPedido;

/**
 *
 * @author santialfonso
 */
public class ControllerPedido extends ControllerFactory implements Observer, Colleague{

     // ---------------------------------- HAY QUE HACER UN CASTING AQUI PARA PODER IMPLEMENTAR EL PATRON FACTORY
    private ServicioPedido servicioPedido = ((ServicioPedido)this.CrearServicio());//CASTING DE Servcio A ServicioPedido
    private Mediador mediador;
    private Producto productoPedido;
    private Pedido pedidoByMediador;
    private Proveedor proveedorPedido;
    public ControllerPedido() {
    }
    
    public ServicioPedido getServicioPedido() {
        return servicioPedido;
    }

    public void setServicioPedido(ServicioPedido servicioPedido) {
        this.servicioPedido = servicioPedido;
    }
    
    /**
     * METODO NECESARIO PARA IMPLEMENTAR FACTORY
     * @return un nuevo ServicioPedido
     */
    @Override
    public Servicio CrearServicio() {
        return new ServicioPedido();
    }

    @Override
    public void update(int stock) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setMediador(Mediador mediador) {
        this.mediador = mediador;
    }
    
    public void setProductoPedidoByMediador(Producto productoPedido)
    {
        this.setProductoPedido(productoPedido);
        mediador.step7();
    }
    
    public void setProveedorPedidoByMediador (Proveedor proveedorPedido)
    {
        this.setProveedorPedido(proveedorPedido);
        //mediador.stepN();

    }
    
    public void createPedido()
    {
        this.pedidoByMediador = new Pedido();
        this.getProductoPedido().setItemDecorado(this.pedidoByMediador);
        mediador.step10();
    }

    /**
     * @return the productoPedido
     */
    public Producto getProductoPedido() {
        return productoPedido;
    }

    /**
     * @param productoPedido the productoPedido to set
     */
    public void setProductoPedido(Producto productoPedido) {
        this.productoPedido = productoPedido;
    }

    /**
     * @return the pedidoByMediador
     */
    public Pedido getPedidoByMediador() {
        return pedidoByMediador;
    }

    /**
     * @param pedidoByMediador the pedidoByMediador to set
     */
    public void setPedidoByMediador(Pedido pedidoByMediador) {
        this.pedidoByMediador = pedidoByMediador;
    }

    /**
     * @return the proveedorPedido
     */
    public Proveedor getProveedorPedido() {
        return proveedorPedido;
    }

    /**
     * @param proveedorPedido the proveedorPedido to set
     */
    public void setProveedorPedido(Proveedor proveedorPedido) {
        this.proveedorPedido = proveedorPedido;
    }
}
