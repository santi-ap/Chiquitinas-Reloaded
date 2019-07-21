/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package items;

/**
 *
 * @author santialfonso
 */
public class Producto extends Decorador{
    
    private int idProducto;
    private String nombreProducto;
    private double precioProductoCliente;
    private double precioProductoProveedor;
    private double descuentoProductoPromo;
    private int stockMinimoProducto;
    private int cantidadActualProducto;
    private String categoriaProducto;
    
    
    public Producto() {
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
    
    
}
