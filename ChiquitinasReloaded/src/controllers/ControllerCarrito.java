/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import items.Producto;
import items.Combo;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
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

    private ServicioCarrito servicioCarrito = ((ServicioCarrito) this.CrearServicio());
    private ServicioComboHasProducto servicioComboProd = new ServicioComboHasProducto();
    private ServicioProducto servicioProducto = new ServicioProducto();
    private ServicioCombo servicioCombo = new ServicioCombo();
    private ControllerCombo controllerCombo = new ControllerCombo();
    private OrdenMediador ordenMediador;
    private ArrayList<Object> listaProductos = new ArrayList<Object>();
    private ArrayList<Object> listaCombos = new ArrayList<Object>();
    Scanner input = new Scanner(System.in);
    
    double totalCarrito = 0;
    double descuentoTotal = 0;
    double totalFinal= 0;

    public double getTotalFinal() {
        return totalFinal;
    }

    public ControllerCarrito() {
    }

    public void lookForCarrito(Usuario usuario) {
        this.setListaProductos(this.servicioCarrito.selectAllProductsOfCarrito(usuario.getIdUsuario()));
        this.setListaCombos(this.servicioCarrito.selectAllCombosOfCarrito(usuario.getIdUsuario()));
    }

    public double getTotalCarrito() {
        return totalCarrito;
    }

    @Override
    public Servicio CrearServicio() {
        return new ServicioCarrito();
    }

    @Override
    public void setMediador(Mediador mediador) {
        this.ordenMediador = (OrdenMediador) mediador;
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

    public ArrayList<Object> setListaProductosCarrito(Usuario usuario) {
        return this.servicioCarrito.selectAllProductsOfCarrito(usuario);
    }

    public void printListaProductos(Usuario usuario) {
        
        double total = 0;
        double descuento = 0;
        for (Object p : this.servicioCarrito.selectAllProductsOfCarrito(usuario.getIdUsuario())) {
            System.out.println("ID: "+((Producto) p).getIdProducto() +" "+((Producto) p).getNombreProducto() + "  ₡" + ((Producto) p).getPrecioProductoCliente()
                   + "  x" + this.servicioCarrito.selectMontoProducto(usuario.getIdUsuario(),Integer.toString(((Producto) p).getIdProducto())));
            
            total= total + ((Producto)p).getPrecioProductoCliente()*((Producto) p).getCantidadActualProducto();
            
            if(usuario.getTipoUsuario()==2){
               descuento=descuento+((Producto) p).getPrecioProductoCliente()*((Producto) p).getDescuentoProductoPromo()*Double.parseDouble(this.servicioCarrito.selectMontoProducto(usuario.getIdUsuario(),Integer.toString(((Producto) p).getIdProducto())));
            }
        }
        
        System.out.println("Total Productos: "+total);
        this.totalCarrito = totalCarrito + total;
        this.descuentoTotal=descuento;
        this.totalFinal=totalFinal+totalCarrito-descuentoTotal;
        
    }

    public double getDescuento() {
        return descuentoTotal;
    }
    
    public void printListaCombos(Usuario usuario){
        
        for(Object c: this.servicioCarrito.selectAllCombosOfCarrito(usuario.getIdUsuario())){//recorre una lista de todos los combos
           System.out.println("ID: "+((Combo) c).getIdCombo() +" | Nombre Combo: "+((Combo) c).getNombreCombo());//imprime el id y nombre del combo actual
           for(String idProducto:servicioComboProd.selectListIdProductos("Combo_idCombo", ((Combo) c).getIdCombo())){//recorre una lista de productos que esten en el combo actual
               ArrayList<Object> prodList= this.servicioProducto.selectAll("idProducto", idProducto);//agarra la info del producto actual en el recorrdio de productos en el combo actual
                   Producto prod = ((Producto)prodList.get(0));
                   System.out.println("   "+prod.toString(1));//imprime el producto actual dentro del combo actual
               
           }
            System.out.println("Total combo: " + servicioCombo.select("precioClienteCombo", "idCombo", ((Combo) c).getIdCombo()));
            this.totalCarrito = totalCarrito + Double.parseDouble(servicioCombo.select("precioClienteCombo", "idCombo", ((Combo) c).getIdCombo()));
            this.totalFinal= totalFinal+Double.parseDouble(servicioCombo.select("precioClienteCombo", "idCombo", ((Combo) c).getIdCombo()));
       }
}
    
    public void cambiarMontoProducto(Usuario usuario){
        System.out.println("Inserte el ID del producto");
        int productID = Integer.parseInt(input.nextLine());
        System.out.println("Inserte el monto que desea comprar");
        int monto = Integer.parseInt(input.nextLine());
        
        if(monto==0){
            this.servicioCarrito.deleteProducto(usuario.getIdUsuario(), productID);
        }
    }
    
    public void cambiarMontoCombo(Usuario usuario){
        System.out.println("Inserte el ID del combo");
        int comboID = Integer.parseInt(input.nextLine());
        System.out.println("Inserte el monto que desea comprar");
        int monto = Integer.parseInt(input.nextLine());
        
        if(monto==0){
            this.servicioCarrito.deleteCombo(usuario.getIdUsuario(), comboID);
        }
    }
}
