/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menus;

import controllers.ControllerCarrito;
import controllers.ControllerCombo;
import controllers.ControllerOrden;
import controllers.ControllerProducto;
import controllers.ControllerUsuario;
import java.util.Scanner;
import mediador.Colleague;
import mediador.Mediador;
import mediador.OrdenMediador;
import objetos.Usuario;

/**
 *
 * @author santialfonso
 */
public class MenuCliente implements MenuDisplayBehavior, Colleague {

    ControllerCombo controllerCombo = new ControllerCombo();
    ControllerProducto controllerProducto = new ControllerProducto();
    ControllerCarrito controllerCarrito = new ControllerCarrito();
    ControllerUsuario controllerUsuario = new ControllerUsuario();
    ControllerOrden controllerOrden = new ControllerOrden();
    Usuario clienteVIP = new Usuario();
    Scanner input = new Scanner(System.in);

    OrdenMediador ordenMediador = new OrdenMediador(controllerOrden, controllerProducto, controllerCarrito, controllerCombo);

    public MenuCliente() {
        controllerOrden.setMediador(ordenMediador);
        controllerProducto.setOrdenMediador(ordenMediador);
        controllerCarrito.setMediador(ordenMediador);
        controllerCombo.setMediador(ordenMediador);
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
                    + "5-Cambiar Contrasenna\n"
                    + "6-Salir");
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
                    break;
                case "4"://Ver Ordenes
                    this.controllerOrden.mostrarOrden(usuario);
                    break;
                case "5"://Cambiar Contra
                    controllerUsuario.cambiarContra(usuario);
                    break;
                case "6"://Salir
                    controllerUsuario.convertirAVIP(Integer.parseInt(usuario.getIdUsuario()));
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
            System.out.println("DESCUENTO: " + this.controllerCarrito.getDescuento());
            System.out.println("TOTAL FINAL: " + this.controllerCarrito.getTotalFinal());
            System.out.println("\n\n\n\n\nMENU CARRITO\n"
                    + "1-Comprar\n"
                    + "2-Cambiar cantidad producto\n"
                    + "3-Cambiar cantidad combo\n"
                    + "4-\n"
                    + "5-\n"
                    + "6-Salir");
            String opcion = input.nextLine();
            switch (opcion) {
                case "1":
                    this.confirmarCompra(usuario);
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
        if (sc.nextLine().equalsIgnoreCase("s")) {
            ordenMediador.start(usuario);
            this.controllerCarrito.setTotalCarrito(0d);
            this.controllerCarrito.setTotalFinal(0d);
        } else {
            System.out.println("Regresando a menu...\n...\n");
        }
    }

    public void menuVerCarrito(Usuario usuario) {

    }

    @Override
    public void setMediador(Mediador mediador) {
        this.ordenMediador = (OrdenMediador) mediador;
//        int condicion = 0;
//        while (condicion == 0) {
//            System.out.println("\n\n\n\n\nMENU CARRITO\n"
//                    + "1-Ver Productos\n"
//                    + "2-Ver combos\n"
//                    + "3-Salir");
//            String opcion = input.nextLine();
//            switch (opcion) {
//                case "1"://Ver Productos
//                    
//                    break;
//                case "2"://Ver Combos
//                    
//                    break;
//                case "3"://Salir
//                    condicion = 1;
//                    break;
//            }
//        }
    }
}
