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
   
    /*          Decorator       */
    private Item itemDecorado;
    private boolean isItemPedido;
     
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

    @Override
    public double getPrecio() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getRecibo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    boolean getIsItemPedido() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @return the itemDecorado
     */
    public Item getItemDecorado() {
        return itemDecorado;
    }

    /**
     * @param itemDecorado the itemDecorado to set
     */
    public void setItemDecorado(Item itemDecorado) {
        this.itemDecorado = itemDecorado;
    }

    /**
     * @return the isItemPedido
     */
    public boolean isIsItemPedido() {
        return isItemPedido;
    }

    /**
     * @param isItemPedido the isItemPedido to set
     */
    public void setIsItemPedido(boolean isItemPedido) {
        this.isItemPedido = isItemPedido;
    }
    
    
    
}
