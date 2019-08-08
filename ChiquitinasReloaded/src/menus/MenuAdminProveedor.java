/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menus;
import controllers.ControllerProveedor;
import java.util.Scanner;
import objetos.Usuario;

/**
 *
 * @author santialfonso
 */
public class MenuAdminProveedor implements MenuDisplayBehavior{
    
    ControllerProveedor controllerProveedor= new ControllerProveedor();
    Scanner input = new Scanner(System.in);

    public MenuAdminProveedor() {
    }

    public void displayMenu() {
        int condicion = 0;
        while (condicion == 0) {
            System.out.println("\n\n\n\n\nMENU DE INVENTARIO\n"
                    + "1-Agregar un proveedor\n"
                    + "2-\n"
                    + "3-\n"
                    + "4-\n"
                    + "5-Atras");
            String opcion = input.nextLine();
            switch (opcion) {
                case "1"://Opcion para Agregar un proveedor
                   this.controllerProveedor.crearProveedor();
                    break;
                case "2"://
                    
                    break;
                case "3"://
                    
                    break;
                case "4":// 
                   
                    break;
                case "5"://
                    condicion = 1;
                    break;
            }
        }

    }   

    @Override
    public void displayMenu(Usuario usuario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
