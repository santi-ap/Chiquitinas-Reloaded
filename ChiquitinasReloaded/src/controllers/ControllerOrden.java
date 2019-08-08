/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import servicios.ServicioOrdenHasCombo;
import items.Combo;
import items.Orden;
import items.Producto;
import java.sql.Date;
import java.util.ArrayList;
import mediador.*;
import objetos.Cliente;
import objetos.Usuario;
import servicios.Servicio;
import servicios.ServicioCarrito;
import servicios.ServicioOrden;
import servicios.ServicioOrdenHasProducto;

/**
 *
 * @author santialfonso
 */
public class ControllerOrden extends ControllerFactory implements Colleague{
    
    // ---------------------------------------- HAY QUE HACER UN CASTING AQUI PARA PODER IMPLEMENTAR EL PATRON FACTORY
    private ServicioOrden servcioOrden = ((ServicioOrden)this.CrearServicio());//CASTING DE Servcio A ServicioOrden
    private ServicioOrdenHasProducto servcioOrdenHP = ((ServicioOrdenHasProducto)this.CrearServicioHP());//CASTING DE Servcio A ServicioOrdenHP
    private ServicioOrdenHasCombo servcioOrdenHC = ((ServicioOrdenHasCombo)this.CrearServicioHC());//CASTING DE Servcio A ServicioOrdenHS
    private Usuario usuario;
    private ArrayList<Object> listaProductos = new ArrayList<Object>();
    private ArrayList<Object> listaCombos = new ArrayList<Object>();
    private Orden orden;
    private Mediador ordenMediador;
    public ControllerOrden() {
       
    }

    @Override
    public void setMediador(Mediador mediador)
    {
        this.ordenMediador=mediador;
    }
    public ServicioOrden getServcioOrden() {
        return servcioOrden;
    }

    public void setServcioOrden(ServicioOrden servcioOrden) {
        this.servcioOrden = servcioOrden;
    }

    /**
     * METODO NECESARIO PARA IMPLEMENTAR FACTORY
     * @return un nuevo ServicioOrden
     */
    @Override
    public Servicio CrearServicio() {
        return new ServicioOrden();
    }    
    /**
     * METODO NECESARIO PARA IMPLEMENTAR FACTORY
     * @return un nuevo ServicioOrdenHasProducto
     */
    
    public Servicio CrearServicioHP() {
        return new ServicioOrdenHasProducto();
    }    
    /**
     * METODO NECESARIO PARA IMPLEMENTAR FACTORY
     * @return un nuevo ServicioOrdenHasCombo
     */
    
    public Servicio CrearServicioHC() {
        return new ServicioOrdenHasCombo();
    }

    /**
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the listaProductos
     */
    public ArrayList<Object> getListaProductos() {
        return listaProductos;
    }

    /**
     * @param listaProductos the listaProductos to set
     */
    public void setListaProductos(ArrayList<Object> listaProductos) {
        this.listaProductos = listaProductos;
    }

    /**
     * @return the listaCombos
     */
    public ArrayList<Object> getListaCombos() {
        return listaCombos;
    }

    /**
     * @param listaCombos the listaCombos to set
     */
    public void setListaCombos(ArrayList<Object> listaCombos) {
        this.listaCombos = listaCombos;
    }

    public void createOrden() {
        this.orden = new Orden();
        //logic to get the last id and to set it as the orderid
        this.orden.setIdOrden(this.getServcioOrden().selectMaxId());
        //logic to set the client
        this.orden.setClienteOrden(this.usuario);
        //logic to set the current date
        this.orden.setFechaOrden(new Date(System.currentTimeMillis()));
        
        //inserting the order in the table so the products have a reference
        this.getServcioOrden().insert(orden);
        
        double precioOrden=0f;
        boolean isFirst = true;//tells the for if it is the first cycle
        
        //temporary product for for 
        Producto pTemp = new Producto();
        for (Object p: this.listaProductos)
        {//decorating order with products
            if (isFirst)//if this is the first
            {
                
                isFirst = false;
                ((Producto)p).setItemDecorado(this.orden);
                //inserting orden to intermediate table
                String temp = Integer.toString(this.orden.getIdOrden()) +","+ Integer.toString(((Producto)p).getIdProducto()) +","+ Integer.toString(((Producto)p).getCantidadActualProducto());
                //Orden_has_Producto
                System.out.println(temp);
                this.servcioOrdenHP.insert(temp);
                precioOrden+=((Producto)p).getPrecioForOrden(usuario.getTipoUsuario());
                
            } else {
                ((Producto)p).setItemDecorado(pTemp);
                //inserting orden to intermediate table
                String temp = Integer.toString(orden.getIdOrden()) +","+ Integer.toString(((Producto)p).getIdProducto()) +","+ Integer.toString(((Producto)p).getCantidadActualProducto());
                //Orden_has_Producto
                this.servcioOrdenHP.insert(temp);
                precioOrden+=((Producto)p).getPrecioForOrden(usuario.getTipoUsuario());

            }  
            pTemp = (Producto)p;              
        }
        
        //temporary combo
        Combo cTemp = new Combo();
        for (Object c: this.listaCombos)
        {//decorating order with combos
            if (isFirst)//if this is the first
            {
                //inserting orden to intermediate table
                String temp = Integer.toString(orden.getIdOrden()) +","+ Integer.toString(((Combo)c).getIdCombo()) +","+ Integer.toString(((Combo)c).getCantidadActualProductoCombo());
                //Orden_has_Combo
                this.servcioOrdenHP.insert(temp);
                isFirst = false;
                ((Combo)c).setItemDecorado(orden);
                precioOrden+=((Combo)c).getPrecioForOrden(usuario.getTipoUsuario());
                
            } else {
                //inserting orden to intermediate table
                String temp = Integer.toString(orden.getIdOrden()) +","+ Integer.toString(((Combo)c).getIdCombo()) +","+ Integer.toString(((Combo)c).getCantidadActualProductoCombo());
                //Orden_has_Combo
                this.servcioOrdenHP.insert(temp);
                ((Combo)c).setItemDecorado(cTemp);
                precioOrden+=((Combo)c).getPrecioForOrden(usuario.getTipoUsuario());

            }
                
            cTemp = (Combo)c;              
        }
        
        //inserting orden to db
        System.out.println("inserting orden");
        this.servcioOrden.update("totalOrden", Double.toString(precioOrden), "idOrden", Integer.toString(this.orden.getIdOrden()));
        System.out.println("order inserted");
        //calling back the mediator
        ((OrdenMediador)this.ordenMediador).updateStock(this.orden, this.listaProductos, this.listaCombos);
 
    }
    
    public void emptyCarrito()
    {
        ServicioCarrito sc = new ServicioCarrito();
        sc.delete("Usuario_IdUsuario", usuario.getIdUsuario());
    }
    
}
