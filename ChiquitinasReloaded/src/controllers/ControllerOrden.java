/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import items.Combo;
import items.Orden;
import items.Producto;
import java.sql.Date;
import java.util.ArrayList;
import objetos.Cliente;
import objetos.Usuario;
import servicios.Servicio;
import servicios.ServicioOrden;

/**
 *
 * @author santialfonso
 */
public class ControllerOrden extends ControllerFactory{
    
    // ---------------------------------------- HAY QUE HACER UN CASTING AQUI PARA PODER IMPLEMENTAR EL PATRON FACTORY
    private ServicioOrden servcioOrden = ((ServicioOrden)this.CrearServicio());//CASTING DE Servcio A ServicioOrden
    private Usuario usuario;
    private ArrayList<Object> listaProductos = new ArrayList<Object>();
    private ArrayList<Object> listaCombos = new ArrayList<Object>();
    private Orden orden;
    public ControllerOrden() {
       
    }

    public ServicioOrden getServcioOrden() {
        return servcioOrden;
    }

    public void setServcioOrden(ServicioOrden servcioOrden) {
        this.servcioOrden = servcioOrden;
    }

    /**
     * METODO NECESARIO PARA IMPLEMENTAR FACTORY
     * @return un nuevo ServicioUsuario
     */
    @Override
    public Servicio CrearServicio() {
        return new ServicioOrden();
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
        orden = new Orden();
        //logic to get the last id and to set it as the orderid
        orden.setIdOrden(this.getServcioOrden().selectMaxId());
        //logic to set the client
        orden.setClienteOrden((Cliente)this.usuario);
        //logic to set the current date
        orden.setFechaOrden(new Date(System.currentTimeMillis()));
        
        boolean isFirst = true;//tells the for if it is the first cycle
        
        //temporary product for for 
        Producto pTemp = new Producto();
        for (Object p: this.listaProductos)
        {//decorating order with products
            if (isFirst)//if this is the first
            {
                isFirst = false;
                ((Producto)p).setItemDecorado(orden);
            } else
                ((Producto)p).setItemDecorado(pTemp);
                
            pTemp = (Producto)p;              
        }
        
        //temporary combo
        Combo cTemp = new Combo();
        for (Object c: this.listaCombos)
        {//decorating order with combos
            if (isFirst)//if this is the first
            {
                isFirst = false;
                ((Combo)c).setItemDecorado(orden);
            } else
                ((Combo)c).setItemDecorado(cTemp);
                
            cTemp = (Combo)c;              
        }
        
        
        //logic to get the right price on the orden
        if(cTemp==null)//this means there were no combos, so the last added product is pTemp
            orden.setTotalOrden(pTemp.getPrecio(usuario.getTipoUsuario()));
        else
            orden.setTotalOrden(cTemp.getPrecio(usuario.getTipoUsuario()));
        //inserting orden to db
        this.servcioOrden.insert(orden);
        //inserting orden to intermediate db
        
    }
    
}
