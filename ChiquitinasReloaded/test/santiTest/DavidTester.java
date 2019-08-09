/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package santiTest;

import controllers.ControllerCarrito;
import controllers.ControllerProducto;
import controllers.ControllerProveedor;
import controllers.ControllerUsuario;
import objetos.Proveedor;
import servicios.ServicioProducto;
import servicios.ServicioProveedor;
import menus.MenuAdminProducto;
import menus.MenuCliente;
import menus.MenuClienteVIP;
import objetos.Usuario;
import servicios.ServicioCarrito;
import servicios.ServicioUsuario;

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
        ServicioUsuario serU = new ServicioUsuario();
        ServicioCarrito serCar = new ServicioCarrito();
        MenuAdminProducto map = new MenuAdminProducto();
        ControllerProveedor cp = new ControllerProveedor();
        ControllerProducto conP = new ControllerProducto();
        ControllerCarrito conCar = new ControllerCarrito();
        ControllerUsuario conUs = new ControllerUsuario();
        MenuCliente mc = new MenuCliente();
        MenuClienteVIP mcv = new MenuClienteVIP();
        Usuario u = new Usuario();
        //System.out.println(sp.select("nombreProveedor", "idProveedor", 2));
        //System.out.println(sp.selecAllNombresProveedor());
        
        //System.out.println(sp.selecAllNombresProveedor()+ "\n");
       u = serU.selectUsuario(4);
       conUs.cambiarContra(u);
        
    
}
}
