/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

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
        System.out.println("Ingese el nombre del producto que desea buscar: ");
        String nombreProductoDeseado = input.nextLine();
        ArrayList<String> listaDetallesProducto = this.servicioProducto.selectAll("nombreProducto", nombreProductoDeseado);
        if (!(listaDetallesProducto.get(0).equals("No Existe el Producto"))) {
            System.out.println("ID Producto: " + listaDetallesProducto.get(0) + "\nNombre Producto: " + listaDetallesProducto.get(1)
                    + "\nPrecio del Producto para el cliente: ₡" + listaDetallesProducto.get(2) + "\nStock Minimo del Producto: " + listaDetallesProducto.get(3)
                    + "\nStock actual del producto: " + listaDetallesProducto.get(4) + "\nPrecio del Producto ofrecido por el Proveedor: ₡" + listaDetallesProducto.get(5)
                    + "\nDescuento promocional del producto: " + Double.parseDouble(listaDetallesProducto.get(6)) * 100 + "%" + "\nID del Proveedor del Producto: " + listaDetallesProducto.get(7));
        }else
            System.out.println("No Existe el Producto");
    }

}
