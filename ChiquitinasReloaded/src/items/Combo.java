/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package items;

import java.util.*;

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
    Calendar cal = Calendar. getInstance();
    private Date fechaInicioCombo = cal.getTime();
    java.util.Date utilDate = new java.util.Date();
    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
    java.sql.Date fechaFinCombo;
   
    /*          Decorator       */
    private Item itemDecorado;
    private boolean isItemPedido;
    
    public Combo(int idComboA, String nombreCombo, double precioClienteCombo, int contadorOfertaCombo, int contadorProductoCombo, double descuentoCombo, Date fechaInicioCombo, Date fechaFinCombo){
        this.idCombo = idComboA;
        this.nombreCombo = nombreCombo;
        this.precioComboCliente = precioClienteCombo;
        this.cantidadOfertaCombo = contadorOfertaCombo;
        this.cantidadActualProductoCombo = contadorProductoCombo;
        this.descuentoCombo = descuentoCombo;
        this.fechaInicioCombo = sqlDate;
        this.fechaFinCombo = sqlDate;
    
    };
     
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

    public void setFechaFinCombo(java.sql.Date fechaFinCombo) {
        this.fechaFinCombo = fechaFinCombo;
    }

    public java.sql.Date getSqlDate() {
        return sqlDate;
    }

    public void setSqlDate(java.sql.Date sqlDate) {
        this.sqlDate = sqlDate;
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
