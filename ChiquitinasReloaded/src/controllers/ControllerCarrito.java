/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import items.Carrito;
import items.Combo;
import items.Producto;
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
    private OrdenMediador ordenMediador;
    private ArrayList<Object> listaProductos = new ArrayList<Object>();
    private ArrayList<Object> listaCombos = new ArrayList<Object>();
    ServicioCombo servicioCombo = new ServicioCombo();
    ServicioProducto servicioProducto = new ServicioProducto();

    Scanner input = new Scanner(System.in);

    public ControllerCarrito() {
    }

    public void lookForCarrito(Usuario usuario) {
        this.setListaProductos(this.servicioCarrito.selectAllProductsOfCarrito(usuario.getIdUsuario()));
        this.setListaCombos(this.servicioCarrito.selectAllCombosOfCarrito(usuario.getIdUsuario()));
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
    
    /**
     * metodo para agregar un producto al carrito
     * @param usuario para asociar el carrito con el usuario ingresado
     */
    public void agregarProductoCarrito(Usuario usuario) {
        System.out.println("\n\nIngrese un ID para agregarlo al carrito u oprima 's' para ir atras");//primero le pregunta al user que ingrese un ID u oprima 's' para salir
        String idProducto = input.nextLine();
        if (idProducto.equalsIgnoreCase("s")) {//si es 's', se devuelve al menu anterior
            return;
        }
        System.out.println("\n\nIngrese la cantidad que desea agregar al carrito");//pide ingresar la cantidad deseada
        String cantidadProducto = input.nextLine();
        Producto producto = ((Producto) servicioProducto.selectAll("idProducto", idProducto).get(0));//busca el producto en el DB con el ID escogido por el user
        System.out.println("\n\nSeguro que desea agregar " + cantidadProducto + " de " + producto.getNombreProducto() + "?\n1-Si\n2-No");//le pide al user confirmar si lo quiere agregar al carrito
        String confirmacion = input.nextLine();
        if (confirmacion.equals("1")) {
            servicioCarrito.insertProductCarrito(usuario.getIdUsuario(), idProducto, cantidadProducto);//si dice que si, entonces lo agrega al carrito
        }
    }
    
    /**
     * metodo para agregar un combo al carrito
     * @param usuario para asociar el carrito con el usuario ingresado
     */
    public void agregarComboCarrito(Usuario usuario) {
        System.out.println("\n\nIngrese un ID para agregarlo al carrito u oprima 's' para ir atras");//primero le pregunta al user que ingrese un ID u oprima 's' para salir
        String idCombo = input.nextLine();
        if (idCombo.equalsIgnoreCase("s")) {//si es 's', se devuelve al menu anterior
            return;
        }
        System.out.println("\n\nIngrese la cantidad que desea agregar al carrito");//pide ingresar la cantidad deseada
        String cantidadCombo = input.nextLine();
        Combo combo = servicioCombo.selectCombo("idCombo", idCombo);//busca el combo en el DB con el ID escogido por el user
        System.out.println("\n\nSeguro que desea agregar " + cantidadCombo + " de " + combo.getNombreCombo() + "?\n1-Si\n2-No");//le pide al user confirmar si lo quiere agregar al carrito
        String confirmacion = input.nextLine();
        if (confirmacion.equals("1")) {
            servicioCarrito.insertComboCarrito(usuario.getIdUsuario(), idCombo, cantidadCombo);//si dice que si, entonces lo agrega al carrito
        }
    }

}
