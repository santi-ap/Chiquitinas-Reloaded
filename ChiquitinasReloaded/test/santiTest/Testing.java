/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package santiTest;

import controllers.ControllerProducto;
import items.Producto;
import menus.MenuAdminProducto;
import servicios.ServicioProducto;

/**
 *
 * @author santialfonso
 */
public class Testing {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        ServicioProducto sp = new ServicioProducto();
//        sp.update("stockMinProducto", "10", "idProducto", "2123657");
        MenuAdminProducto mAP=new MenuAdminProducto();
        mAP.displayMenu();
//          for(Producto p:sp.selectTodosLosProductos()){
//              System.out.println(p);
//          }
//          for (Object o:sp.selectAll("Proveedor_idProveedor", "1")){
//              System.out.println((Producto)o);
//          }
//        ControllerProducto cp = new ControllerProducto();
//        cp.modidificarNombreProducto(1);
    }
    
}
