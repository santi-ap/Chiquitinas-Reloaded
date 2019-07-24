/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package items;

import java.sql.Date;
import objetos.Proveedor;

/**
 *
 * @author santialfonso
 */
public class Pedido extends Item{
    
    private int idPedido;
    private double totalPedido;
    private Date fechaPedido;
    private Proveedor proveedorPedido;
    
    
    public Pedido() {
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public double getTotalPedido() {
        return totalPedido;
    }

    public void setTotalPedido(double totalPedido) {
        this.totalPedido = totalPedido;
    }

    public Date getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public Proveedor getProveedorPedido() {
        return proveedorPedido;
    }

    public void setProveedorPedido(Proveedor proveedorPedido) {
        this.proveedorPedido = proveedorPedido;
    }
    
}
