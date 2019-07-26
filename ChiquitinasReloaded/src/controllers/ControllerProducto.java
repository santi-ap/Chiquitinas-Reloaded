/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import items.Producto;
import java.util.ArrayList;
import java.util.Scanner;
import mediador.Colleague;
import mediador.Mediador;
import observer.Observer;
import observer.Subject;
import servicios.Servicio;
import servicios.ServicioProducto;

/**
 *
 * @author santialfonso
 */
public class ControllerProducto extends ControllerFactory implements Colleague, Subject {

    // ---------------------------------------- HAY QUE HACER UN CASTING AQUI PARA PODER IMPLEMENTAR EL PATRON FACTORY
    private ServicioProducto servicioProducto = ((ServicioProducto) this.CrearServicio());//CASTING DE Servcio A ServicioProducto
    private Mediador mediador;
    Scanner input = new Scanner(System.in);

    public ControllerProducto() {
    }

    public ServicioProducto getServicioProducto() {
        return servicioProducto;
    }

    public void setServicioProducto(ServicioProducto servicioProducto) {
        this.servicioProducto = servicioProducto;
    }

    /**
     * METODO NECESARIO PARA IMPLEMENTAR FACTORY
     *
     * @return un nuevo ServicioProducto
     */
    @Override
    public Servicio CrearServicio() {
        return new ServicioProducto();
    }

    @Override
    public void setMediador(Mediador mediador) {
        this.mediador = mediador;
    }

    @Override
    public void registrarObserver(Observer observer) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removerObserver(Observer observer) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void notificarObserver() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void buscarProductoPorNombre() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nIngese el nombre del producto que desea buscar: ");
        String nombreProductoDeseado = input.nextLine();
        ArrayList<Object> listaDetallesProducto = this.servicioProducto.selectAll("nombreProducto", nombreProductoDeseado);
        if (!(listaDetallesProducto.get(0).equals("No Existe el Producto"))) {
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n" + (Producto) listaDetallesProducto.get(0));
        } else {
            System.out.println("No Existe el Producto\n\n\n\n");
        }
    }

    /**
     * Metodo para imprimir la lista de productos de un proveedor
     */
    public void getProductoForMenuByProv() {
        System.out.println("\nINSERTE EL ID DEL PROVEEDOR");
        String id = input.nextLine();
        ArrayList<Object> listaProductos = servicioProducto.selectAll("Proveedor_idProveedor", id);

        for (Object o : listaProductos) {

            System.out.println("ID PRODUCTO: " + ((Producto) o).getIdProducto() + "|| PRODUCTO: " + ((Producto) o).getNombreProducto() + "|| PRECIO CLIENTE: c" + ((Producto) o).getPrecioProductoCliente()
                    + "|| PRECIO PROVEEDOR: " + ((Producto) o).getPrecioProductoProveedor());

        }
        mediador.step3();
    }

    /**
     * Este metodo se utiliza para seleccionar el producto e imprime el producto
     * para mostrar el que se selecciono
     */
    public void getProductoById() {
        System.out.println("\nINSERTE EL ID DEL PRODUCTO");
        String id = input.nextLine();
        ArrayList<Object> listaProductos = servicioProducto.selectAll("idProducto", id);
        for (Object o : listaProductos) {

            System.out.println("ID PRODUCTO: " + ((Producto) o).getIdProducto() + "|| PRODUCTO: " + ((Producto) o).getNombreProducto() + "|| PRECIO CLIENTE: c" + ((Producto) o).getPrecioProductoCliente()
                    + "|| PRECIO PROVEEDOR: " + ((Producto) o).getPrecioProductoProveedor());

        }
        mediador.step4(id);
    }

    /**
     * Este metodo toma el la cantidad del producto que se desae comprar y
     * actualiza el stock
     *
     * @param idProducto
     */
    public void actualizarStock(String idProducto) {
        System.out.println("\nINSERTE EL MONTO QUE DESEA COMPRAR");
        int montoCompra = Integer.parseInt(input.nextLine());
        int stockActual = Integer.parseInt(servicioProducto.select("contadorProducto", "idProducto", idProducto));
        String stockNuevo = Integer.toString(stockActual + montoCompra);
        System.out.println("Desea comprar hacer la comprar s/n");
        String aceptar = input.nextLine();

        if (aceptar.equalsIgnoreCase("s")) {
            servicioProducto.update("contadorProducto", stockNuevo, "idProducto", idProducto);
        } else {

        }

    }

}
