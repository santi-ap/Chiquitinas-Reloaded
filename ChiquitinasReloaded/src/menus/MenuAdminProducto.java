/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menus;

import controllers.ControllerProducto;
import controllers.ControllerProveedor;
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
            System.out.println("\n\n\n\n\nMENU DE INVENTARIO\n"
                    + "1-Agregar/Pedir un producto\n"
                    + "2-Buscar producto por nombre\n"
                    + "3-Modificar producto\n"
                    + "4-Eliminar producto\n"
                    + "5-Atras");
            String opcion = input.nextLine();
            switch (opcion) {
                case "1"://Opcion para Agregar/Pedir un producto
                    this.menuAgregarPedirProducto();
                    break;
                case "2"://Opcion para Buscar producto por nombre
                    this.controllerProducto.buscarProductoPorNombre();
                    break;
                case "3"://opcion para Modificar producto
                    this.subMenuModificarProducto();
                    break;
                case "4":// opcion para Eliminar producto
                    this.controllerProducto.borrarProducto();
                    break;
                case "5"://opcion para ir atras
                    condicion = 1;
                    break;
            }
        }
    }

    
    public void subMenuModificarProducto(){
        System.out.println("\n\n\n\nSUBMENU PARA MODIFICAR UN PRODUCTO\n");
        controllerProducto.printTodosLosProductos();
        System.out.println("Escoga el id del producto que desea modificar");
        int idProductoAModificar = Integer.parseInt(input.nextLine());
        int condicion = 0;
        while (condicion == 0) {
            System.out.println("ESCOGA UNA OPCION\n"
                    + "1-Modificar el nombre\n"
                    + "2-Modificar el precio\n"
                    + "3-Modificar el stock minimo\n"
                    + "4-Opciones de descuento promocional\n"
                    + "5-Atras");
            String opcion = input.nextLine();
            switch (opcion) {
                case "1"://Modificar el nombre
                    controllerProducto.modidificarNombreProducto(idProductoAModificar);
                    break;
                case "2"://Modificar el precio
                    controllerProducto.modidificarPrecioProducto(idProductoAModificar);
                    break;
                case "3"://Modificar el stock minimo
                    controllerProducto.modidificarStockMinProducto(idProductoAModificar);
                    break;
                case "4"://Opciones de descuento promocional
                    //Para regresar a este menú desde las opciones del switch tuve que crear un flag boolean de salida
                    boolean condicionSalida = true;
                    do {
                    controllerProducto.menuPromos();
                    switch(controllerProducto.menuPromos()){
                    case 1: 
                        
                    controllerProducto.descuento(controllerProducto.descuentoUsuario());
                    condicionSalida = true;
                    break;
                    
                    case 2:
                    controllerProducto.descuento(controllerProducto.descuentoUsuario());
                    /*Hay una pulga cuando sale de las opciones, porque el menú principal lo arroja dos veces
                    No estoy seguro donde está el glitch*/
                    condicionSalida = true;
                    break;
                        
                    case 3:
                    controllerProducto.buscarProductoId(controllerProducto.idUsuario());
                    condicionSalida = true;
                    break;
                        
                    case 4:
                        
                    controllerProducto.buscarProductosConDescuento();
                        
                    case 5:    
                    
                        condicionSalida = false;
                        break;
                        
                } } while(condicionSalida == true);
                    
                    break;
                case "5"://opcion para ir atras
                    condicion = 1;
                    break;
            }
        }
    }


    public void menuAgregarPedirProducto() {
        int condicion = 0;
        while (condicion == 0) {
            System.out.println("\n\n\n\n\nDESEA PEDIR UN PRODUCTO NUEVO O EXISTENTE?\n"
                    + "1-Producto Nuevo\n"
                    + "2-Producto Exsitente\n"
                    + "3-Atras");
            String opcion = input.nextLine();
            switch (opcion) {
                case "1"://Opcion para Agregar un producto nuevo
                    this.menuAgregarPoductoNuevo();
                    break;
                case "2"://Opcion para Pedir un producto existente
                    this.seleccionarProductoExistente();
                    break;
                case "3"://opcion para ir atras
                    condicion = 1;
                    break;

            }
        }
    }

    public void menuAgregarPoductoNuevo() {
        int condicion = 0;
        while (condicion == 0) {
            System.out.println("\n\n\n\n\nDESEA ORDENAR DE UN PROVEEDOR EXISTENTE O NUEVO?\n"
                    + "1-Proveedor Exsitente\n"
                    + "2-Proveedor Nuevo\n"
                    + "3-Atras");
            String opcion = input.nextLine();
            switch (opcion) {
                case "1"://Opcion para Pedir de un proveedor existente
                    
                    break;
                case "2"://Opcion para Proveedo un producto nuevo

                    break;
                case "3"://opcion para ir atras
                    condicion = 1;
                    break;

            }
        }
    }
    
    public void seleccionarProductoExistente(){
      ControllerProveedor cp = new ControllerProveedor();
      
      cp.getProveedorIdNombre();
      
        System.out.println("\nINSERTER EL ID DEL PROVEEDOR");
        String idProveedor = input.nextLine();
        controllerProducto.getDatosForMenuProducto(idProveedor);
        System.out.println("\nINSERTER EL ID DEL PRODUCTO");
         String idProducto = input.nextLine();
    }
}
