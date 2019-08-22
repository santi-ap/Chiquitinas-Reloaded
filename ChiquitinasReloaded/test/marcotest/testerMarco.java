/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marcotest;
import menus.*;
import controllers.*;
import servicios.*;
import controllers.ControllerUsuario;
import java.util.*;
import java.sql.Date;
import objetos.*;
/**
 *
 * @author santialfonso
 */
public class testerMarco {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        MenuAdminCombo mab = new MenuAdminCombo();
        MenuCliente mc = new MenuCliente();
        MenuClienteVIP vip = new MenuClienteVIP();
        ControllerCombo controllerCombo = new ControllerCombo();
        ControllerUsuario controllerUsuario = new ControllerUsuario();
        ServicioUsuario su = new ServicioUsuario();
        //mab.displayMenu();
       //ControllerProducto cp = new ControllerProducto();
      // ServicioProducto sp = new ServicioProducto();
       ServicioCombo sc = new ServicioCombo();
       Usuario cliente = new Usuario("2", "Marco", "testPass1", 2);
       //mab.displayMenu(cliente);*/
       //mab.displayMenu(cliente);
       //mab.displayMenu(cliente);
       
       vip.displayMenu(cliente);
       
       
       
       
      
        
       
        
        
            
            
    }
    
}
