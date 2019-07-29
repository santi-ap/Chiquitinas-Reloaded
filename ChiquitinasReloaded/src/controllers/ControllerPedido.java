/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import mediador.Colleague;
import mediador.Mediador;
import observer.Observer;
import servicios.Servicio;
import servicios.ServicioPedido;

/**
 *
 * @author santialfonso
 */
public class ControllerPedido extends ControllerFactory implements Observer, Colleague{

     // ---------------------------------- HAY QUE HACER UN CASTING AQUI PARA PODER IMPLEMENTAR EL PATRON FACTORY
    private ServicioPedido servicioPedido = ((ServicioPedido)this.CrearServicio());//CASTING DE Servcio A ServicioPedido
    private Mediador mediador;
    
    public ControllerPedido() {
    }
    
    public ServicioPedido getServicioPedido() {
        return servicioPedido;
    }

    public void setServicioPedido(ServicioPedido servicioPedido) {
        this.servicioPedido = servicioPedido;
    }
    
    /**
     * METODO NECESARIO PARA IMPLEMENTAR FACTORY
     * @return un nuevo ServicioPedido
     */
    @Override
    public Servicio CrearServicio() {
        return new ServicioPedido();
    }

    @Override
    public void update(int stock) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setMediador(Mediador mediador) {
        this.mediador = mediador;
    }
    
    
    
}
