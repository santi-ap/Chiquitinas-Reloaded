/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menus;

import controllers.ControllerCombo;
import controllers.ControllerProducto;
import java.util.Scanner;
import mediador.OrdenMediador;
import objetos.Usuario;

/**
 *
 * @author santialfonso
 */
public class MenuCliente implements MenuDisplayBehavior {
    ControllerCombo controllerCombo = new ControllerCombo();
    ControllerProducto controllerProducto = new ControllerProducto();
    Scanner input = new Scanner(System.in);
    OrdenMediador ordenMediador=new OrdenMediador();

    public MenuCliente() {
    }

    @Override
    public void displayMenu(Usuario usuario) {
        int condicion = 0;
        while (condicion == 0) {
            System.out.println("\n\n\n\n\nMENU DE CLIENTE\n"
                    + "1-Ver Catalogo Producto\n"
                    + "2-Ver Catalogo Combo\n"
                    + "3-Ver Carrito\n"
                    + "4-Ver Ordenes\n"
                    + "5-Salir");
            String opcion = input.nextLine();
            switch (opcion) {
                case "1"://Ver Catalogo Producto
                    this.controllerProducto.printTodosLosProductos(usuario.getTipoUsuario());
                    break;
                case "2"://Ver Catalogo Combo
                    this.controllerCombo.mostrarCombos(usuario.getTipoUsuario());
                    break;
                case "3"://Ver Carrito
                    //logica para mostrar carrito
                    this.confirmarCompra(usuario);
                    break;
                case "4"://Ver Ordenes
                    break;
                case "5"://Salir
                    condicion = 1;
                    break;
            }
        }

    }

    private void confirmarCompra(Usuario usuario) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Comprar productos? [s/n]");
        switch(sc.nextLine())
        {
            case "s": ordenMediador.start(usuario);
            default: System.out.println("Regresando a menu...\n...\n");
                break;
        }
        
    }
}
