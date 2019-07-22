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
    
    public void subMenuModificarProducto(){
        int condicion = 0;
        while (condicion == 0) {
            System.out.println("\n\n\n\nSUBMENU PARA MODIFICAR UN PRODUCTO\n"
                    + "1-Modificar el nombre\n"
                    + "2-Modificar el precio\n"
                    + "3-Modificar el stock minimo\n"
                    + "4-Opciones de descuento promocional\n"
                    + "5-Atras");
            String opcion = input.nextLine();
            switch (opcion) {
                case "1"://Modificar el nombre
                    break;
                case "2"://Modificar el precio
                    break;
                case "3"://Modificar el stock minimo
                    break;
                case "4"://Opciones de descuento promocional
                    break;
                case "5"://opcion para ir atras
                    condicion = 1;
                    break;
            }
        }
    }
    

}
