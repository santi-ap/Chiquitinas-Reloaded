/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menus;

import controllers.ControllerProducto;
import java.util.Scanner;

/**
 *
 * @author santialfonso
 */
public class MenuAdminProducto implements MenuDisplayBehavior {

    Scanner input = new Scanner(System.in);
    ControllerProducto controllerProducto = new ControllerProducto();

    public MenuAdminProducto() {
    }
    public void clearScreen() {  
    System.out.print("\033[H\033[2J");  
    System.out.flush();  
   }

    @Override
    public void displayMenu() {
        int condicion = 0;
        while (condicion == 0) {
            System.out.println("MENU DE INVENTARIO\n"
                    + "1-Agregar/Pedir un producto\n"
                    + "2-Buscar producto por nombre\n"
                    + "3-Modificar producto\n"
                    + "4-Eliminar producto\n"
                    + "5-Atras");
            String opcion = input.nextLine();
            switch (opcion) {
                case "1"://Opcion para Agregar/Pedir un producto
                    break;
                case "2"://Opcion para Buscar producto por nombre
                    this.clearScreen();
                    this.controllerProducto.buscarProductoPorNombre();
                    break;
                case "3"://opcion para Modificar producto
                    break;
                case "4":// opcion para Eliminar producto
                    break;
                case "5"://opcion para ir atras
                    condicion = 1;
                    break;
            }
        }
    }
    
    

}
