/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testers;

import controllers.ControllerProducto;
import controllers.ControllerProveedor;
import objetos.Proveedor;
import servicios.ServicioProducto;
import servicios.ServicioProveedor;
import menus.MenuAdminProducto;

/**
 *
 * @author coded
 */
public class DavidTester {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ServicioProveedor sp = new ServicioProveedor();
        MenuAdminProducto map = new MenuAdminProducto();
        ControllerProveedor cp = new ControllerProveedor();
        ControllerProducto conP = new ControllerProducto();
        //System.out.println(sp.select("nombreProveedor", "idProveedor", 2));
        //System.out.println(sp.selecAllNombresProveedor());
        
        //System.out.println(sp.selecAllNombresProveedor()+ "\n");
        
        map.displayMenu();
        
        
        
    
}
}
