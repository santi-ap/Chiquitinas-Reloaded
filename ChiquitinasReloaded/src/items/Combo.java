/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package items;

import java.util.Date;

/**
 *
 * @author santialfonso
 */
public class Combo extends Decorador{

    private int idCombo;
    private String nombreCombo;
    private double precioComboCliente;
    private int cantidadOfertaCombo;
    private int cantidadActualProductoCombo;
    private double descuentoCombo;
    private Date fechaInicioCombo;
    private Date fechaFinCombo;
    
    public Combo() {
    }

    public int getIdCombo() {
        return idCombo;
    }

    public void setIdCombo(int idCombo) {
        this.idCombo = idCombo;
    }

    public String getNombreCombo() {
        return nombreCombo;
    }

    public void setNombreCombo(String nombreCombo) {
        this.nombreCombo = nombreCombo;
    }

    public double getPrecioComboCliente() {
        return precioComboCliente;
    }

    public void setPrecioComboCliente(double precioComboCliente) {
        this.precioComboCliente = precioComboCliente;
    }

    public int getCantidadOfertaCombo() {
        return cantidadOfertaCombo;
    }

    public void setCantidadOfertaCombo(int cantidadOfertaCombo) {
        this.cantidadOfertaCombo = cantidadOfertaCombo;
    }

    public int getCantidadActualProductoCombo() {
        return cantidadActualProductoCombo;
    }

    public void setCantidadActualProductoCombo(int cantidadActualProductoCombo) {
        this.cantidadActualProductoCombo = cantidadActualProductoCombo;
    }

    public double getDescuentoCombo() {
        return descuentoCombo;
    }

    public void setDescuentoCombo(double descuentoCombo) {
        this.descuentoCombo = descuentoCombo;
    }

    public Date getFechaInicioCombo() {
        return fechaInicioCombo;
    }

    public void setFechaInicioCombo(Date fechaInicioCombo) {
        this.fechaInicioCombo = fechaInicioCombo;
    }

    public Date getFechaFinCombo() {
        return fechaFinCombo;
    }

    public void setFechaFinCombo(Date fechaFinCombo) {
        this.fechaFinCombo = fechaFinCombo;
    }
    
    
    
}
