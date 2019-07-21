/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package items;

import java.util.Date;
import objetos.Cliente;

/**
 *
 * @author santialfonso
 */
public class Orden extends Item{
    
    private int idOrden;
    private double totalOrden;
    private Date fechaOrden;
    private Cliente clienteOrden;

    public Orden() {
    }

    public int getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(int idOrden) {
        this.idOrden = idOrden;
    }

    public double getTotalOrden() {
        return totalOrden;
    }

    public void setTotalOrden(double totalOrden) {
        this.totalOrden = totalOrden;
    }

    public Date getFechaOrden() {
        return fechaOrden;
    }

    public void setFechaOrden(Date fechaOrden) {
        this.fechaOrden = fechaOrden;
    }

    public Cliente getClienteOrden() {
        return clienteOrden;
    }

    public void setClienteOrden(Cliente clienteOrden) {
        this.clienteOrden = clienteOrden;
    }
    
}
