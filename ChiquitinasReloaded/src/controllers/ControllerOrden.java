/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import servicios.Servicio;
import servicios.ServicioOrden;

/**
 *
 * @author santialfonso
 */
public class ControllerOrden extends ControllerFactory{
    
    // ---------------------------------------- HAY QUE HACER UN CASTING AQUI PARA PODER IMPLEMENTAR EL PATRON FACTORY
    private ServicioOrden servcioOrden = ((ServicioOrden)this.CrearServicio());//CASTING DE Servcio A ServicioOrden
    
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
    
}
