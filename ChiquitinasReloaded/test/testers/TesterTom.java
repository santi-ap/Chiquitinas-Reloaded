/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testers;

import controllers.*;
import items.*;
import java.sql.Date;
import menus.*;
import servicios.*;
import mediador.*;

/**
 *
 * @author Asus
 */
public class TesterTom {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
          
        /*tester for Mediador*/

        ControllerPedido cp = new ControllerPedido();
        ControllerProducto cpp = new ControllerProducto();
        ControllerProveedor cppp = new ControllerProveedor();
        
        PedidoMediador m = new PedidoMediador(cp, cppp, cpp);
        cp.setMediador(m);
        cpp.setMediador(m);
        cppp.setMediador(m);
        
        m.start();
        /*decorator example*/
//        Pedido ped = new Pedido();
//        ped.setFechaPedido(new Date(System.currentTimeMillis()));
//        ped.setIdPedido(1);
//        
//        Producto p1 = new Producto(ped);
//        p1.setPrecioProductoProveedor(15);
//        p1.setNombreProducto("p1");
//        p1.setCantidadActualProducto(3);
//        
//        Producto p2 = new Producto(p1);
//        p2.setPrecioProductoProveedor(3);
//        p2.setNombreProducto("p2");
//        p2.setCantidadActualProducto(2);
//        Producto p3 = new Producto(p2);
//        p3.setNombreProducto("p3");
//        p3.setCantidadActualProducto(1);
//        p3.setPrecioProductoProveedor(1);
//        System.out.println(p3.getRecibo());
//        System.out.println(p3.getPrecio());

        
//        MenuAdminProducto map = new MenuAdminProducto();
//        boolean ass = map.confirmarAccion();
//        if (ass==true)
//            System.out.println("yi-haw");

//        ServicioPedido sp = new ServicioPedido();
//        Item p = new Pedido ();
//        ((Pedido)p).setIdPedido(69);
//        ((Pedido)p).setTotalPedido(0);
//        ((Pedido)p).setFechaPedido(new Date (System.currentTimeMillis()));
//        sp.insert(p);
                            
    }
    
}
