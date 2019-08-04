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
public final class Producto extends Decorador {

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


    /**
     * constructor sobrecargado para decorador, also checks if the root item was
     * a pedido or carrito/orden
     *
     * @param item el item que se quiere decorar (pedido, factura, carrito)
     */
    public Producto(Item item) {
        this.itemDecorado = item;
        /*  THIS LOGIC TELLS THE LAST DECORATOR IF HE'S DECORATING A PEDIDO OR A ORDEN/CARRITO  */

        //if the item passed is a pedido, 
        if (item instanceof Pedido) {
            this.setIsItemPedido(true);//this bool is set to 1
        } else if (item instanceof Orden || item instanceof Carrito) {//else if item is an order, or a carrito
            this.setIsItemPedido(false);//bool set to 0
        } else if (item instanceof Producto) {//if this isn't any of the previous objects, check if it's either a combo or a product
            if (((Producto) item).getIsItemPedido() == true) {//if it's a product, check what the boolean of item is
                this.setIsItemPedido(true);//and set it as the same 
            } else {
                this.setIsItemPedido(false);
            }
        } else /*this is a combo, so it must be from either a carrito or a order*/ {
                this.setIsItemPedido(false);
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
    public String toString() {
        return "ID Producto: " + this.getIdProducto() + " | Nombre Producto: " + this.getNombreProducto()
                + " | Precio del Producto para el cliente: ₡" + this.getPrecioProductoCliente()
                + " | Precio con descuento promocional del Producto para el cliente VIP: ₡" + (this.getPrecioProductoCliente() - (this.getPrecioProductoCliente() * this.getDescuentoProductoPromo()))
                + " | Stock Minimo del Producto: " + this.getStockMinimoProducto() + "\n Stock actual del producto: " + this.getCantidadActualProducto()
                + "Precio del Producto ofrecido por el Proveedor: ₡" + this.getPrecioProductoProveedor() + " | Descuento promocional del producto: "
                + this.getDescuentoProductoPromo() * 100 + "%" + " | ID del Proveedor del Producto: " + this.getIdProveedorProducto() + "\n";
    }

    /**
     * GETS PRECIO TOTAL FOR DECORATOR
     *
     * @param tipoUsuario
     * @return
     */
    @Override
    public double getPrecio(int tipoUsuario) {
        //if the Item is a pedido, then the price should be the one from the Proveedor
        if (this.getIsItemPedido() == true) {
            return (this.getItemDecorado().getPrecio(tipoUsuario) + (this.getCantidadActualProducto()*this.getPrecioProductoProveedor()));
        } else /*it's either an orden or a carrito, so we use the price for the client*/ {
            //let's check if the client is a vip or not, so we know if the promo applies
            if (tipoUsuario == 2)
            return (this.getItemDecorado().getPrecio(tipoUsuario) + ((this.getPrecioProductoCliente()*(1-this.descuentoProductoPromo))*this.getCantidadActualProducto()));
            else
            return (this.getItemDecorado().getPrecio(tipoUsuario) + (this.getPrecioProductoCliente()*this.getCantidadActualProducto()));
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
    /**
     *
     * @param tipoUsuario
     * @return the recibo with the added product
     */
    @Override
    public String getRecibo(int tipoUsuario) {
        return (this.getItemDecorado().getRecibo(tipoUsuario) +"Cantidad ordenada: "+ this.getCantidadActualProducto() + "\t| Nombre Producto: " + this.getNombreProducto() + "\t| Precio Unitario: ‎₡" + this.getPrecioUnitario(tipoUsuario) + "\n");
    }


    public double getPrecioUnitario(int tipoUser)
    {
        switch(tipoUser)
        {
            case 0: return this.getPrecioProductoProveedor();
            case 2: return (this.getPrecioProductoCliente()*this.getDescuentoProductoPromo());
            default: return this.getPrecioProductoCliente();
        }
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

    /**
     * este es un toString que toma como parametro el tipo de usuario. La idea
     * es que imprime de una forma mas organizada para los usuarios
     *
     * @param tipoUsuario si es "1" imprime sin descuento
     * @return imprime datos del producto de forma organizada
     */
    public String toString(int tipoUsuario) {
        if (tipoUsuario == 1) {//impirme productos sin promos
            return "ID Producto: " + this.getIdProducto() + " | Nombre Producto: " + this.getNombreProducto()
                    + " | Precio del Producto: ₡" + this.getPrecioProductoCliente()
                    + " | Stock actual del producto: " + this.getCantidadActualProducto();
        } else if (tipoUsuario == 2) {//imprime productos con promos
            return "ID Producto: " + this.getIdProducto() + " | Nombre Producto: " + this.getNombreProducto()
                    + " | Precio del Producto: ₡" + this.getPrecioProductoCliente()
                    + " | Precio con descuento promocional del Producto para el cliente VIP: ₡" + (this.getPrecioProductoCliente() - (this.getPrecioProductoCliente() * this.getDescuentoProductoPromo()))
                    + "\n    Stock actual del producto: " + this.getCantidadActualProducto()
                    + " | Descuento promocional del producto: " + this.getDescuentoProductoPromo() * 100 + "%";
        } else {//imprime toda la info de los productos
            return this.toString();

        }
    }
}
