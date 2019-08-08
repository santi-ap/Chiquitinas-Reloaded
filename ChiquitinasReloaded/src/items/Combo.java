/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package items;

import java.util.*;
import servicios.ServicioCombo;
import servicios.ServicioComboHasProducto;

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
    
    public Combo (){}
    /**
     * overcharged constructor for decorator
     * @param itemDecorado 
     */
    public Combo (Item itemDecorado)
    {
        this.itemDecorado = itemDecorado;
        /*  THIS LOGIC TELLS THE LAST DECORATOR IF HE'S DECORATING A PEDIDO OR A ORDEN/CARRITO  */

        //if the item passed is a pedido, 
        if (itemDecorado instanceof Pedido) {
            this.setIsItemPedido(true);//this bool is set to 1
        } else if (itemDecorado instanceof Orden || itemDecorado instanceof Carrito) {//else if item is an order, or a carrito
            this.setIsItemPedido(false);//bool set to 0
        } else if (itemDecorado instanceof Producto) {//if this isn't any of the previous objects, check if it's either a combo or a product
            if (((Producto) itemDecorado).getIsItemPedido() == true) {//if it's a product, check what the boolean of item is
                this.setIsItemPedido(true);//and set it as the same 
            } else {
                this.setIsItemPedido(false);
            }
        } else /*this is a combo, so it must be from either a carrito or a order*/ {
                this.setIsItemPedido(false);
        }

    }
    public Combo(int idComboA, String nombreCombo, double precioClienteCombo, int contadorOfertaCombo, int contadorProductoCombo, double descuentoCombo, Date fechaInicioCombo, Date fechaFinCombo){
        this.idCombo = idComboA;
        this.nombreCombo = nombreCombo;
        this.precioComboCliente = precioClienteCombo;
        this.cantidadOfertaCombo = contadorOfertaCombo;
        this.cantidadActualProductoCombo = contadorProductoCombo;
        this.descuentoCombo = descuentoCombo;
        this.fechaInicioCombo = sqlDate;
        this.fechaFinCombo = sqlDate;
    
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
    
    
    /**
     *  
     * @param tipoUsuario passed on to be sent to next product 
     * @return  the price of the combo with the applied discount
     */
    @Override
    public double getPrecio(int tipoUsuario) {
        //we can only be decorating either a carrito or a orden, so the only price to be shown is the client's one
        //we need to give the price with the discount applied if the type is 2
        if (tipoUsuario==2)
            return (this.getItemDecorado().getPrecio(tipoUsuario) + (this.getCantidadActualProductoCombo()*(this.getPrecioComboCliente()*(1-this.getDescuentoCombo()))));
        else
            return (this.getItemDecorado().getPrecio(tipoUsuario) +(this.getCantidadActualProductoCombo()* this.getPrecioComboCliente()));

            
    }
    
    public double getPrecioUnitario(int tipoUsuario)
    {
         switch(tipoUsuario)
        {
            case 2: return (this.getPrecioComboCliente()*this.getDescuentoCombo());
            default: return this.getPrecioComboCliente();
        }
    }

    @Override
    public String getRecibo(int tipoUsuario) {
        if (tipoUsuario==2)
            return (this.getItemDecorado().getRecibo(tipoUsuario) + "ID COMBO: "+ this.getIdCombo() + "\t| Cantidad: " + this.getCantidadActualProductoCombo() + "\t| Precio: " + this.getPrecioComboCliente()*(1-this.getDescuentoCombo())+
                "\tDescuento: " + this.descuentoCombo +"% |"+"\n\tProductos:\n" + this.getProductsAsString() + "\n");
        else
            return (this.getItemDecorado().getRecibo(tipoUsuario) + "ID COMBO: "+ this.getIdCombo() + "\t| Cantidad: " + this.getCantidadActualProductoCombo() + "\t| Precio: " + this.getPrecioComboCliente()+
                "\n\tProductos:\n" + this.getProductsAsString() + "\n");   
    }
    /**
     * Used by the method getRecibo to get useful info from db
     */
    public String getProductsAsString ()
    {
        ServicioComboHasProducto sc = new ServicioComboHasProducto();

        String productos = "";
        for (Object p: sc.selectAllProductsOfCombo(idCombo))
        {
            productos += "ID: ";
            productos += ((Producto)p).getIdProducto();
            productos += "\t| Nombre Producto: ";
            productos += ((Producto)p).getNombreProducto();
            productos += "\t\t| Categoria Producto: ";
            productos += ((Producto)p).getCategoriaProducto();
            productos += "\n";
        }
        return productos;
    }

    boolean getIsItemPedido() {
        return this.isItemPedido;
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
    
    public double getPrecioForOrden(int tipoUsuario)
    {
        if (tipoUsuario==2)
            return (this.getCantidadActualProductoCombo()*(this.getPrecioComboCliente()*(1-this.getDescuentoCombo())));
        else
            return (this.getCantidadActualProductoCombo()*(this.getPrecioComboCliente()));
    }
    
}
