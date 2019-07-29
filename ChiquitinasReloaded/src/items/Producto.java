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
public final class Producto extends Decorador{
    
    private int idProducto;
    private String nombreProducto;
    private double precioProductoCliente;
    private double precioProductoProveedor;
    private double descuentoProductoPromo;
    private int stockMinimoProducto;
    private int cantidadActualProducto; //during a new order, the nuevoproducto.cantidadactual represents the number of products ordered.
    private String categoriaProducto;
    private int idProveedorProducto;
    
    /*          Decorator       */
    private Item itemDecorado;
    private boolean isItemPedido;
    
    public Producto (){};
    /**
     * constructor sobrecargado para decorador, also checks if the root item was a pedido or carrito/orden
     * @param item el item que se quiere decorar (pedido, factura, carrito)
     */
    public Producto(Item item) 
    {
        this.itemDecorado = item;
        /*  THIS LOGIC TELLS THE LAST DECORATOR IF HE'S DECORATING A PEDIDO OR A ORDEN/CARRITO  */
        
        //if the item passed is a pedido, or if this item has been passed on from a root pedido, the head of the chain is a pedido => 
        if (item instanceof Pedido)
        {
            this.setIsItemPedido(true);
        } else if (item instanceof Orden || item instanceof Carrito ) { 
            this.setIsItemPedido(false);
        } else if (item instanceof Producto) {
            if (((Producto) item).getIsItemPedido()==true)
            {
                this.setIsItemPedido(true);
            } else {
                this.setIsItemPedido(false);
            }
        } else /*this is a combo*/ {
            if (((Combo) item).getIsItemPedido()==true)
            {
                this.setIsItemPedido(true);
            } else {
                this.setIsItemPedido(false);
            }
        }
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
    public String toString(){
        return "ID Producto: " + this.getIdProducto() + " | Nombre Producto: " + this.getNombreProducto()
                    + " | Precio del Producto para el cliente: ₡" + this.getPrecioProductoCliente() 
                    +" | Precio con descuento promocional del Producto para el cliente VIP: ₡" + (this.getPrecioProductoCliente()-(this.getPrecioProductoCliente()*this.getDescuentoProductoPromo())) 
                    + " | Stock Minimo del Producto: " + this.getStockMinimoProducto() + "\n Stock actual del producto: " + this.getCantidadActualProducto() 
                    + "Precio del Producto ofrecido por el Proveedor: ₡" + this.getPrecioProductoProveedor() + " | Descuento promocional del producto: " 
                    + this.getDescuentoProductoPromo() * 100 + "%" + " | ID del Proveedor del Producto: " + this.getIdProveedorProducto()+"\n";
    }

    
    @Override
    public double getPrecio() {
        //if the Item is a pedido, then the price should be the one from the Proveedor
        if (this.getIsItemPedido() == true)
        {
            return (this.getItemDecorado().getPrecio()+this.getPrecioProveedorPorCantidad());
        } else /*it's either an orden or a carrito, so we use the price for the client*/{
            return (this.getItemDecorado().getPrecio()+this.getPrecioProductoCliente());
        }
            
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
     * 
     * @return the recibo with the added product 
     */
    @Override
    public String getRecibo() {
        return (this.getItemDecorado().getRecibo() + this.getCantidadActualProducto() + "\t" + this.getNombreProducto() + "\t" + this.getPrecioProveedorPorCantidad() + "\n");
    }
    
    public double getPrecioProveedorPorCantidad()
    {
        return (this.getCantidadActualProducto()*this.getPrecioProductoProveedor());
    }

    /**
     * @return the isItemPedido
     */
    public boolean getIsItemPedido() {
        return isItemPedido;
    }

    /**
     * @param isItemPedido the isItemPedido to set
     */
    public void setIsItemPedido(boolean isItemPedido) {
        this.isItemPedido = isItemPedido;
    }
    
}
