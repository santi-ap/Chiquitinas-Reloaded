/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menus;
import java.util.*;
import controllers.ControllerCombo;
import objetos.Usuario;
/**
 *
 * @author santialfonso
 */
public class MenuAdminCombo implements MenuDisplayBehavior{
    
    Scanner sc = new Scanner(System.in);
    boolean condicionSalida = true;
    ControllerCombo controllerCombo = new ControllerCombo();

    public MenuAdminCombo() {
    }

    @Override
    public void displayMenu(Usuario usuario) {
        do {
            System.out.println("Menu de combos\n1-Crear combo\n2-Modificar combo\n3-Buscar combo\n4-Ver combo\n5-Menu principal ");
            int decisionUsuario = sc.nextInt();
            switch(decisionUsuario){
            
                case 1: 
                    
                case 2:
                    
                controllerCombo.submenuModificarCombo();
                
                    
                case 3:
                    
                case 4:    
            
                case 5:
                 
                condicionSalida = false;   
            }
            
        }while(condicionSalida == true);
    }
    
}
