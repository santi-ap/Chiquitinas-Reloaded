/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import mediador.Colleague;
import mediador.Mediador;
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

}
