/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menus;

import java.util.Scanner;

/**
 *
 * @author santialfonso
 */
public class MenuCliente implements MenuDisplayBehavior {

    Scanner input = new Scanner(System.in);

    public MenuCliente() {
    }

    @Override
    public void displayMenu() {
        int condicion = 0;
        while (condicion == 0) {
            System.out.println("\n\n\n\n\nMENU DE CLIENTE\n"
                    + "1-Ver Catalogo Producto\n"
                    + "2-Ver Catalogo Combo\n"
                    + "3-Ver Carrito\n"
                    + "4-Ver Ordenes\n"
                    + "5-Ver Promociones"
                    + "6-Salir");
            String opcion = input.nextLine();
            switch (opcion) {
                case "1"://Ver Catalogo Producto
                    break;
                case "2"://Ver Catalogo Combo
                    break;
                case "3"://Ver Carrito
                    this.menuCarrito();
                    break;
                case "4"://Ver Ordenes
                    break;
                case "5"://Ver Promociones
                    break;
                case "6"://Salir
                    condicion = 1;
                    break;
            }
        }

    }
    
    public void menuCarrito(){
        int condicion = 0;
        while (condicion == 0) {
            System.out.println("\n\n\n\n\nMENU CARRITO\n"
                    + "1-Comprar\n"
                    + "2-Cambiar cantidad producto\n"
                    + "3-Cambiar cantidad combo\n"
                    + "4-\n"
                    + "5-"
                    + "6-Salir");
            String opcion = input.nextLine();
            switch (opcion) {
                case "1"://Ver Catalogo Producto
                    break;
                case "2"://Ver Catalogo Combo
                        System.out.println("Inserte el monto del producto que desea comprar");
                        int monto = Integer.parseInt(input.nextLine());
                        
                        if(monto==0){
                            
                        }
                    break;
                case "3"://Ver Carrito
                    break;
                case "4"://Ver Ordenes
                    break;
                case "5"://Ver Promociones
                    break;
                case "6"://Salir
                    condicion = 1;
                    break;
            }
        }
    }
}
