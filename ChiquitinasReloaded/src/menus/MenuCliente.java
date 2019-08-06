/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menus;

import controllers.ControllerCarrito;
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
    ControllerCarrito controllerCarrito = new ControllerCarrito();
    Scanner input = new Scanner(System.in);
    OrdenMediador ordenMediador = new OrdenMediador();
    
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
                    this.controllerProducto.printTodosLosProductos(usuario.getTipoUsuario());//imprime todos los productos
                    this.controllerCarrito.agregarProductoCarrito(usuario);//llama el metodo para agregar un producto al carrito
                    break;
                case "2"://Ver Catalogo Combo
                    this.controllerCombo.mostrarCombos(usuario.getTipoUsuario());//imprime todos los combos con sus productos asociados
                    this.controllerCarrito.agregarComboCarrito(usuario);//llama el metodo para agregar un combo al carrito
                    break;
                case "3"://Ver Carrito
                    this.menuCarrito(usuario);
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
    
    public void menuCarrito(Usuario usuario) {
        int condicion = 0;
        while (condicion == 0) {
            System.out.println("PRODUCTOS:\n");
            this.controllerCarrito.printListaProductos(usuario);
            System.out.println("\nCOMBOS:\n");
            this.controllerCarrito.printListaCombos(usuario);
            System.out.println("-----------------------------------------");
            System.out.println("TOTAL CARRITO: " + this.controllerCarrito.getTotalCarrito());
            System.out.println("DESCUENTO: "+this.controllerCarrito.getDescuento());
            System.out.println("TOTAL FINAL: "+this.controllerCarrito.getTotalFinal());
            System.out.println("\n\n\n\n\nMENU CARRITO\n"
                    + "1-Comprar\n"
                    + "2-Cambiar cantidad producto\n"
                    + "3-Cambiar cantidad combo\n"
                    + "4-\n"
                    + "5-\n"
                    + "6-Salir");
            String opcion = input.nextLine();
            switch (opcion) {
                case "1"://Ver Catalogo Producto
                    break;
                case "2"://Cambiar cantidad producto
                    controllerCarrito.cambiarMontoProducto(usuario);
                    break;
                case "3"://Cambiar cantidad combo
                    controllerCarrito.cambiarMontoCombo(usuario);
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
    
    private void confirmarCompra(Usuario usuario) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Comprar productos? [s/n]");
        switch (sc.nextLine()) {
            case "s":
                ordenMediador.start(usuario);
            default:
                System.out.println("Regresando a menu...\n...\n");
                break;
        }
    }
    
    public void menuVerCarrito(Usuario usuario) {
        
        int condicion = 0;
        while (condicion == 0) {
            System.out.println("\n\n\n\n\nMENU CARRITO\n"
                    + "1-Ver Productos\n"
                    + "2-Ver combos\n"
                    + "3-Salir");
            String opcion = input.nextLine();
            switch (opcion) {
                case "1"://Ver Productos
                    
                    break;
                case "2"://Ver Combos
                    
                    break;
                case "3"://Salir
                    condicion = 1;
                    break;
            }
        }
    }
}
