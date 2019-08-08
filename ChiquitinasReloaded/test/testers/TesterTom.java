/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testers;
import items.*;
import servicios.ServicioCombo;


/**
 *
 * @author Asus
 */
public class TesterTom {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
          
        /*
        
        TODO 
        TEST RECIBO FOR COMBOS, AND GET PRECIO FOR COMBO
        DO AND TEST ORDEN
        DO AND TEST CARRITO
        
        */
//        Carrito car = new Carrito();
        Producto p2 = new Producto();
        Orden car = new Orden();
        Combo c = new Combo (car);
        ServicioCombo sc = new ServicioCombo ();
        c.setIdCombo(1);
        c.setDescuentoCombo(0.2);
        c.setPrecioComboCliente(1000);
        c.setCantidadActualProductoCombo(5);
        Producto p = new Producto(c);
        p.setCantidadActualProducto(10);
        p.setPrecioProductoCliente(1000);
        p.setNombreProducto("Ass");
        p2.setItemDecorado(p);
        
        System.out.println(p.getRecibo(1));
        System.out.println(p.getPrecio(1));
        
//        /*tester for Mediador*/
//
//        ControllerPedido cp = new ControllerPedido();
//        ControllerProducto cpp = new ControllerProducto();
//        ControllerProveedor cppp = new ControllerProveedor();
//        
//        PedidoMediador m = new PedidoMediador(cp, cppp, cpp);
//        cp.setMediador(m);
//        cpp.setMediador(m);
//        cppp.setMediador(m);
//        
//        m.start(1);
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

    String o = "9,1,1";
    String[] oo = o.split(",");
        System.out.println(oo[0]);
        System.out.println(oo[1]);
        System.out.println(oo[2]);
                            
    }
    
}
