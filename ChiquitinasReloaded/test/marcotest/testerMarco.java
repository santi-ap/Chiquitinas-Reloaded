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
        ControllerCombo controllerCombo = new ControllerCombo();
        ControllerUsuario controllerUsuario = new ControllerUsuario();
        //mab.displayMenu();
        controllerUsuario.diezProductosMasConsumidos();
        
            
            
    }
    
}
