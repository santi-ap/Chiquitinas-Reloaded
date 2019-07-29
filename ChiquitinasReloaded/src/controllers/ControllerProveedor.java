/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.Scanner;
import mediador.Colleague;
import mediador.Mediador;
import objetos.Proveedor;
import servicios.Servicio;
import servicios.ServicioProveedor;

/**
 *
 * @author santialfonso
 */
public class ControllerProveedor extends ControllerFactory implements Colleague {

    // ---------------------------------------- HAY QUE HACER UN CASTING AQUI PARA PODER IMPLEMENTAR EL PATRON FACTORY
    private ServicioProveedor servicioProveedor = ((ServicioProveedor) this.CrearServicio());//CASTING DE Servcio A ServicioProveedor
    private Mediador mediador;
    private Scanner input = new Scanner(System.in);

    public ControllerProveedor() {
    }

    public ServicioProveedor getServicioProveedor() {
        return servicioProveedor;
    }

    public void setServicioProveedor(ServicioProveedor servicioProveedor) {
        this.servicioProveedor = servicioProveedor;
    }

    /**
     * METODO NECESARIO PARA IMPLEMENTAR FACTORY
     *
     * @return un nuevo ServicioProveedor
     */
    @Override
    public Servicio CrearServicio() {
        return new ServicioProveedor();
    }

    @Override
    public void setMediador(Mediador mediador) {
        this.mediador = mediador;
    }

    /**
     * Metodo para conseguit la lista de proveedores
     */
    public void getProveedorIdNombre() {
        //looks
        ServicioProveedor sp = new ServicioProveedor();
        for (String s : sp.selecAllNombresProveedor()) {
            System.out.println("\n" + s);
        }
    }
    
    public void crearProveedor(){
        System.out.println("Inserte el ID para el proveedor");
        int idProveedor = Integer.parseInt(input.nextLine());
        
        System.out.println("Inserte el nombre del proveedor");
        String nombreProveedor = input.nextLine();
        
        System.out.println("Inserte el telefono del proveedor");
        int telProveedor = Integer.parseInt(input.nextLine());
        
        System.out.println("Inserte el correo del proveedor");
        String correoProveedor = input.nextLine();
        
        System.out.println("Desea agregarlo s/n");
        String op = input.nextLine();
        
        if(op.equalsIgnoreCase("s")){
        Proveedor prov = new Proveedor(idProveedor, nombreProveedor, telProveedor, correoProveedor);
        servicioProveedor.insert(prov);
        }
        
        
    }

}
