/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package items;

import objetos.Proveedor;

/**
 *
 * @author santialfonso
 */
public class Producto extends Decorador {

    private int idProducto;
    private String nombreProducto;
    private double precioProductoCliente;
    private double precioProductoProveedor;
    private double descuentoProductoPromo;
    private int stockMinimoProducto;
    private int cantidadActualProducto;
    private String categoriaProducto;
    private int idProveedorProducto;

   

    public Producto() {
    }
    
     public Producto(int idProducto, String nombreProducto, double precioProductoCliente, double precioProductoProveedor, int stockMinimoProducto, int cantidadActualProducto, String categoriaProducto, int idProveedorProducto) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.precioProductoCliente = precioProductoCliente;
        this.precioProductoProveedor = precioProductoProveedor;
        this.stockMinimoProducto = stockMinimoProducto;
        this.cantidadActualProducto = cantidadActualProducto;
        this.categoriaProducto = categoriaProducto;
        this.idProveedorProducto = idProveedorProducto;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public double getPrecioProductoCliente() {
        return precioProductoCliente;
    }

    public void setPrecioProductoCliente(double precioProductoCliente) {
        this.precioProductoCliente = precioProductoCliente;
    }

    public double getPrecioProductoProveedor() {
        return precioProductoProveedor;
    }

    public void setPrecioProductoProveedor(double precioProductoProveedor) {
        this.precioProductoProveedor = precioProductoProveedor;
    }

    public double getDescuentoProductoPromo() {
        return descuentoProductoPromo;
    }

    public void setDescuentoProductoPromo(double descuentoProductoPromo) {
        this.descuentoProductoPromo = descuentoProductoPromo;
    }

    public int getStockMinimoProducto() {
        return stockMinimoProducto;
    }

    public void setStockMinimoProducto(int stockMinimoProducto) {
        this.stockMinimoProducto = stockMinimoProducto;
    }

    public int getCantidadActualProducto() {
        return cantidadActualProducto;
    }

    public void setCantidadActualProducto(int cantidadActualProducto) {
        this.cantidadActualProducto = cantidadActualProducto;
    }

    public String getCategoriaProducto() {
        return categoriaProducto;
    }

    public void setCategoriaProducto(String categoriaProducto) {
        this.categoriaProducto = categoriaProducto;
    }

    public int getIdProveedorProducto() {
        return idProveedorProducto;
    }

    public void setIdProveedorProducto(int idProveedorProducto) {
        this.idProveedorProducto = idProveedorProducto;
    }

    @Override
    public String toString() {
        return "ID Producto: " + this.getIdProducto() + " | Nombre Producto: " + this.getNombreProducto()
                + " | Precio del Producto para el cliente: ₡" + this.getPrecioProductoCliente()
                + " | Precio con descuento promocional del Producto para el cliente VIP: ₡" + (this.getPrecioProductoCliente() - (this.getPrecioProductoCliente() * this.getDescuentoProductoPromo()))
                + " | Stock Minimo del Producto: " + this.getStockMinimoProducto() + "\n Stock actual del producto: " + this.getCantidadActualProducto()
                + "Precio del Producto ofrecido por el Proveedor: ₡" + this.getPrecioProductoProveedor() + " | Descuento promocional del producto: "
                + this.getDescuentoProductoPromo() * 100 + "%" + " | ID del Proveedor del Producto: " + this.getIdProveedorProducto() + "\n";
    }

}
