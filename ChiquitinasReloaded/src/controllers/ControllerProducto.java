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
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"+(Producto)listaDetallesProducto.get(0));
        }else
            System.out.println("No Existe el Producto\n\n\n\n");
    }
    
    public void printTodosLosProductos(){
        for(Producto p:servicioProducto.selectTodosLosProductos()){
              System.out.println(p);
          }
    }
    
    public void modidificarNombreProducto(int idProducto){
        System.out.println("Ingrese el nuevo nombre del producto: ");
        String nombreProdNuevo = input.nextLine();
        servicioProducto.update("nombreProducto", nombreProdNuevo, "idProducto", idProducto);
    }
    
    public void modidificarPrecioProducto(int idProducto){
        System.out.println("Ingrese el nuevo precio del producto: ");
        String precioClienteProductoNuevo = input.nextLine();
        servicioProducto.update("precioClienteProducto", precioClienteProductoNuevo, "idProducto", idProducto);
    }
    
    public void modidificarStockMinProducto(int idProducto){
        System.out.println("Ingrese el nuevo stock minimo del producto: ");
        String stockMinProductoNuevo = input.nextLine();
        servicioProducto.update("stockMinProducto", stockMinProductoNuevo, "idProducto", idProducto);
    }
}
