/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import items.Producto;
import java.util.ArrayList;
import java.util.List;
import mediador.Colleague;
import mediador.Mediador;
import mediador.OrdenMediador;
import objetos.Usuario;
import servicios.*;

/**
 *
 * @author Asus
 */
public class ControllerCarrito extends ControllerFactory implements Colleague {
    
    private ServicioCarrito servicioCarrito = ((ServicioCarrito)this.CrearServicio());
    private OrdenMediador ordenMediador;
    private ArrayList<Object> listaProductos = new ArrayList<Object>();
    private ArrayList<Object> listaCombos = new ArrayList<Object>();
    public ControllerCarrito(){}
    
    public void lookForCarrito(Usuario usuario)
    {
        this.setListaProductos(this.servicioCarrito.selectAllProductsOfCarrito(usuario.getIdUsuario()));
        this.setListaCombos(this.servicioCarrito.selectAllCombosOfCarrito(usuario.getIdUsuario())); 
    }

    @Override
    public Servicio CrearServicio() {
        return new ServicioProducto();
    }

    @Override
    public void setMediador(Mediador mediador) {
        this.ordenMediador = (OrdenMediador)mediador;
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
    
}
