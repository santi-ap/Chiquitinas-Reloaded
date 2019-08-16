/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menus;

import controllers.RegisterController;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import objetos.Usuario;

/**
 *
 * @author santialfonso
 */
public class MenuAdminCliente implements MenuDisplayBehavior{

    public MenuAdminCliente() {
    }

    @Override
    public void displayMenu(Usuario usuario) {
        RegisterController rc = new RegisterController();
        boolean condicionSalida = true;
        Scanner sc = new Scanner (System.in);
        do {
            System.out.println("Menu de Usuarios\n1-Mostrar Usuarios\n2-Crear Usuario\n3-Menu principal ");
            int decisionUsuario = sc.nextInt();
            switch(decisionUsuario){
            
                case 1: rc.mostrarUsers();
                    
                break;
                
                case 2: {
                try 
                {
                    rc.formRegistro();
                } catch (SQLException ex) {
                    Logger.getLogger(MenuAdminCliente.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                    
                break;
                    
                case 3:
                 
                condicionSalida = false;   
            }
            
        }while(condicionSalida == true);    
    }
    
    
    
}
