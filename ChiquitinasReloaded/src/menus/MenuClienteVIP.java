/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menus;

import controllers.ControllerCarrito;
import controllers.ControllerCombo;
import controllers.ControllerProducto;
import controllers.ControllerUsuario;
import java.util.Scanner;
import objetos.Usuario;

/**
 *
 * @author santialfonso
 */
public class MenuClienteVIP implements MenuDisplayBehavior {
    ControllerCombo controllerCombo = new ControllerCombo();
    ControllerProducto controllerProducto = new ControllerProducto();
    ControllerUsuario controllerUsuario = new ControllerUsuario();
    ControllerCarrito controllerCarrito = new ControllerCarrito();
    Scanner input = new Scanner(System.in);

    public MenuClienteVIP() {
    }

    @Override
    public void displayMenu(Usuario usuario) {
        int condicion = 0;
        System.out.println("\n\t\tBienvenido de nuevo "+usuario.getNombreUsuario()+"\n\t\t  Promociones del día\n");
        controllerUsuario.desplegarPromocionesVIP();
        
        controllerUsuario.ultimaOrdenPantallaInicio(Integer.parseInt(usuario.getIdUsuario()));
        while (condicion == 0) {
            System.out.println("\n\n\n\n\nMENU DE CLIENTE VIP\n"
                    + "1-Ver Catalogo Producto\n"
                    + "2-Ver Catalogo Combo\n"
                    + "3-Ver Carrito\n"
                    + "4-Ver Ordenes\n"
                    + "5-Ver Promociones\n"
                    + "6-Salir");
            String opcion = input.nextLine();
            switch (opcion) {
                case "1"://Ver Catalogo Producto
                    this.controllerProducto.printTodosLosProductos(usuario.getTipoUsuario());
                    break;
                case "2"://Ver Catalogo Combo
                    this.controllerCombo.mostrarCombos(usuario.getTipoUsuario());
                    break;
                case "3"://Ver Carrito
                    this.menuCarrito(usuario);
                    break;
                case "4"://Ver Ordenes
                    switch(controllerUsuario.subMenuOrdenes()){
                        case 1: 
                            controllerUsuario.ultimaOrden(Integer.parseInt(usuario.getIdUsuario()));
                        case 2:
                            
                    
                    }
                    break;
                case "5"://Ver Promociones
                    break;
                case "6"://Salir
                    condicion = 1;
                    break;
            }
        }

    }
    
    public void menuCarrito(Usuario usuario){
        int condicion = 0;
        while (condicion == 0) {
            System.out.println("PRODUCTOS:\n");
            this.controllerCarrito.printListaProductos(usuario);
            System.out.println("\nCOMBOS:\n");
            this.controllerCarrito.printListaCombos(usuario);
            System.out.println("-----------------------------------------");
            System.out.println("TOTAL CARRITO: "+this.controllerCarrito.getTotalCarrito());
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
}
