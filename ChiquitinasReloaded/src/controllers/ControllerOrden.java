/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import chainofresponsablity.Handler;
import chainofresponsablity.HandlerCombo;
import chainofresponsablity.HandlerProducto;
import servicios.ServicioOrdenHasCombo;
import items.Combo;
import items.Item;
import items.Orden;
import items.Pedido;
import items.Producto;
import java.sql.Date;
import java.util.ArrayList;
import mediador.*;
import objetos.Cliente;
import objetos.Proveedor;
import objetos.Usuario;
import servicios.Servicio;
import servicios.ServicioCarrito;
import servicios.ServicioCombo;
import servicios.ServicioOrden;
import servicios.ServicioOrdenHasProducto;
import servicios.ServicioPedidoHasProducto;
import servicios.ServicioProducto;
import servicios.ServicioProveedor;

/**
 *
 * @author santialfonso
 */
public class ControllerOrden extends ControllerFactory implements Colleague {

    // ---------------------------------------- HAY QUE HACER UN CASTING AQUI PARA PODER IMPLEMENTAR EL PATRON FACTORY
    private ServicioOrden servcioOrden = ((ServicioOrden) this.CrearServicio());//CASTING DE Servcio A ServicioOrden
    private ServicioOrdenHasProducto servcioOrdenHP = ((ServicioOrdenHasProducto) this.CrearServicioHP());//CASTING DE Servcio A ServicioOrdenHP
    private ServicioOrdenHasCombo servcioOrdenHC = ((ServicioOrdenHasCombo) this.CrearServicioHC());//CASTING DE Servcio A ServicioOrdenHS
    private Usuario usuario;
    private ArrayList<Object> listaProductos = new ArrayList<Object>();
    private ArrayList<Object> listaCombos = new ArrayList<Object>();
    private Orden orden;
    private Mediador ordenMediador;
    ServicioCombo servicioCombo = new ServicioCombo();

    public ControllerOrden() {

    }

    @Override
    public void setMediador(Mediador mediador) {
        this.ordenMediador = mediador;
    }

    public ServicioOrden getServcioOrden() {
        return servcioOrden;
    }

    public void setServcioOrden(ServicioOrden servcioOrden) {
        this.servcioOrden = servcioOrden;
    }

    /**
     * METODO NECESARIO PARA IMPLEMENTAR FACTORY
     *
     * @return un nuevo ServicioOrden
     */
    @Override
    public Servicio CrearServicio() {
        return new ServicioOrden();
    }

    /**
     * METODO NECESARIO PARA IMPLEMENTAR FACTORY
     *
     * @return un nuevo ServicioOrdenHasProducto
     */
    public Servicio CrearServicioHP() {
        return new ServicioOrdenHasProducto();
    }

    /**
     * METODO NECESARIO PARA IMPLEMENTAR FACTORY
     *
     * @return un nuevo ServicioOrdenHasCombo
     */
    public Servicio CrearServicioHC() {
        return new ServicioOrdenHasCombo();
    }

    /**
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the listaProductos
     */
    public ArrayList<Object> getListaProductos() {
        return listaProductos;
    }

    /**
     * @param listaProductos the listaProductos to set
     */
    public void setListaProductos(ArrayList<Object> listaProductos) {
        this.listaProductos = listaProductos;
    }

    /**
     * @return the listaCombos
     */
    public ArrayList<Object> getListaCombos() {
        return listaCombos;
    }

    /**
     * @param listaCombos the listaCombos to set
     */
    public void setListaCombos(ArrayList<Object> listaCombos) {
        this.listaCombos = listaCombos;
    }

    public void createOrden() {
        this.orden = new Orden();
        //logic to get the last id and to set it as the orderid
        this.orden.setIdOrden(this.getServcioOrden().selectMaxId());
        //logic to set the client
        this.orden.setClienteOrden(this.usuario);
        //logic to set the current date
        this.orden.setFechaOrden(new Date(System.currentTimeMillis()));

        //inserting the order in the table so the products have a reference
        this.getServcioOrden().insert(orden);

        //COR PATTERN
        Handler hp = new HandlerProducto();
        Handler hc = new HandlerCombo();
        
        hp.setHandler(hc);
        
        double precioOrden = 0f;
        boolean isFirst = true;//tells the for if it is the first cycle

        //temporary product for for 
        Producto pTemp = new Producto();
        if (!this.listaProductos.isEmpty()) {
            for (Object p : this.listaProductos) {//decorating order with products
                if (isFirst)//if this is the first
                {

                    isFirst = false;
                    ((Producto) p).setItemDecorado(this.orden);
                    //inserting orden to intermediate table
                    hp.processItem((Item)p, this.orden.getIdOrden());
                    precioOrden += ((Producto) p).getPrecioForOrden(usuario.getTipoUsuario());

                } else {
                    ((Producto) p).setItemDecorado(pTemp);
                    //inserting orden to intermediate table
                    hp.processItem((Item)p, this.orden.getIdOrden());

                    precioOrden += ((Producto) p).getPrecioForOrden(usuario.getTipoUsuario());

                }
                pTemp = (Producto) p;
            }
        }
        if (!this.listaCombos.isEmpty()) {

            //temporary combo
            Combo cTemp = new Combo();
            for (Object c : this.listaCombos) {//decorating order with combos
                if (isFirst)//if this is the first
                {
                    //inserting orden to intermediate table
                    hp.processItem((Item)c, this.orden.getIdOrden());

                    isFirst = false;
                    ((Combo) c).setItemDecorado(orden);
                    precioOrden += ((Combo) c).getPrecioForOrden(usuario.getTipoUsuario());

                } else {
                    //inserting orden to intermediate table
                    hp.processItem((Item)c, this.orden.getIdOrden());

                    ((Combo) c).setItemDecorado(cTemp);
                    precioOrden += ((Combo) c).getPrecioForOrden(usuario.getTipoUsuario());

                }

                cTemp = (Combo) c;
            }
        } 
        
        if (this.listaProductos.isEmpty() && this.listaCombos.isEmpty()) {
            System.out.println("ERROR: CARRITO VACIO");
            return;
        }
        //inserting orden to db
        System.out.println("inserting orden");
        this.servcioOrden.update("totalOrden", Double.toString(precioOrden), "idOrden", Integer.toString(this.orden.getIdOrden()));
        System.out.println("order inserted");
        //calling back the mediator
        ((OrdenMediador) this.ordenMediador).updateStock(this.orden, this.listaProductos, this.listaCombos);

    }

    public void emptyCarrito() {
        ServicioCarrito sc = new ServicioCarrito();
        sc.delete("Usuario_IdUsuario", usuario.getIdUsuario());
    }

    /**
     * muestra las ordenes hechas por clientes con sus combos y productos
     * @param usuario lo toma para buscar las ordenes relacionadas con ese usuario
     */
    public void mostrarOrden(Usuario usuario) {
        ArrayList<Object> listaOrdenes = this.servcioOrden.selectAll("User_idUsuario", usuario.getIdUsuario());//busca las ordenes hechas por el usuario
        ServicioOrdenHasProducto servicioOrdenHasProducto = new ServicioOrdenHasProducto();
        ServicioProducto servicioProducto = new ServicioProducto();

        for (Object object : listaOrdenes) {//pasa por cada orden
            Orden orden = ((Orden) object);//hacemos un cast a tipo Orden
            //imprime una guia
            System.out.println("\nId Orden\tProductos ordenes\tCantidad de producto ordenado\tCombos ordenados\tCantidad de combo ordenados\tPrecio total del pedido\t\tFecha del pedido");
            System.out.println("--------\t-----------------\t-----------------------------\t----------------\t---------------------------\t-----------------------\t\t----------------");
            
            //busca los datos de los producos de la orden
            ArrayList<Object> listaDatosProductoOrden = servicioOrdenHasProducto.selectAll("Orden_idOrden", orden.getIdOrden());
            for (int i = 0; i < listaDatosProductoOrden.size(); i = i + 2) {//pasa por los productos de la orden
                String idProducto = ((String) listaDatosProductoOrden.get(i));//agarra el id del producto
                String cantidadProducto = ((String) listaDatosProductoOrden.get(i + 1));//agarra la cantidad
                Producto producto = ((Producto) servicioProducto.selectAll("idProducto", idProducto).get(0));//busca el producto con el id
                //imprime los datos de la orden junto con el producto
                System.out.println(orden.getIdOrden() + "\t\t" + producto.getNombreProducto() + "\t\t\t" + cantidadProducto
                        + "\t\t\t\t" + "\t\t\t\t\t\t\t₡" + orden.getTotalOrden() + "\t\t\t\t" + orden.getFechaOrden());
            }
            
            //busca los datos de los combos relacionados a la orden
            ArrayList<Object> listaDatosComboOrden = servcioOrdenHC.selectAll("Orden_idOrden", orden.getIdOrden());
            if (listaDatosComboOrden.size() > 0) {//si hay combos
                for (int i = 0; i < listaDatosComboOrden.size(); i = i + 2) {//pasa por los combos
                    String idCombo = ((String) listaDatosComboOrden.get(i));//agarra el id del combo
                    String cantidadCombo = ((String) listaDatosComboOrden.get(i + 1));//agarra la cantidad
                    Combo combo = servicioCombo.selectCombo("idCombo", idCombo);//busca el combo con el id
                    //imprime los datos de la orden junto con el combo
                    System.out.println(orden.getIdOrden() + "\t\t\t\t\t\t\t\t\t" + combo.getNombreCombo() + "\t\t\t" + cantidadCombo
                            + "\t\t\t\t" + orden.getTotalOrden() + "\t\t\t\t" + orden.getFechaOrden());
                }
            }

        }
    }
}
