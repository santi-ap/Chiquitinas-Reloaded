/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package items;

import java.sql.Date;
import objetos.Cliente;
import objetos.Usuario;

/**
 *
 * @author santialfonso
 */
public class Orden extends Item{
    
    private int idOrden;
    private double totalOrden;
    private Date fechaOrden;
    private Usuario clienteOrden;

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

    public Usuario getClienteOrden() {
        return clienteOrden;
    }

    public void setClienteOrden(Usuario clienteOrden) {
        this.clienteOrden = clienteOrden;
    }

    @Override
    public double getPrecio(int tipoUsuario) {
        return this.totalOrden;
    }

    @Override
    public String getRecibo(int tipoUsuario) {
        super.recibo = "Orden #" + this.idOrden + "\t\t" + this.fechaOrden + "\n";
        return super.recibo;    
    }
    
}
