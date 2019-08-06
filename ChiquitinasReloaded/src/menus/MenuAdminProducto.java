/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menus;

import controllers.ControllerPedido;
import controllers.ControllerProducto;
import controllers.ControllerProveedor;
import java.util.Scanner;
import mediador.Mediador;
import mediador.PedidoMediador;
import objetos.Usuario;

/**
 *
 * @author santialfonso
 */
public class MenuAdminProducto implements MenuDisplayBehavior {

    ControllerProducto controllerProducto = new ControllerProducto();
    ControllerProveedor controllerPreoveedor = new ControllerProveedor();
    ControllerPedido controllerPedido = new ControllerPedido();

    Mediador pedidoProdExistenteMediador = new PedidoMediador(controllerPedido, controllerPreoveedor, controllerProducto);

    Scanner input = new Scanner(System.in);

    public MenuAdminProducto() {
    }

    @Override
    public void displayMenu(Usuario usuario) {
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

    public void subMenuModificarProducto() {
        System.out.println("\n\n\n\nSUBMENU PARA MODIFICAR UN PRODUCTO\n");
        controllerProducto.printTodosLosProductos(0);
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
                        System.out.println("1-Crear promoción\n2-Modificar promoción\n3-Buscar promoción\n4-Ver promoción\n5-Menú principal");
                        int userInput = input.nextInt();
                        switch (userInput) {
                            case 1:
                                System.out.println("Digite el monto del descuento");
                                double descuento = input.nextDouble();
                                controllerProducto.descuento(descuento);
                                condicionSalida = true;
                                break;

                            case 2:
                                System.out.println("Digite el monto del descuento");
                                double descuentoModificacion = input.nextDouble();
                                controllerProducto.descuento(descuentoModificacion);
                                /*Hay una pulga cuando sale de las opciones, porque el menú principal lo arroja dos veces
                    No estoy seguro donde está el glitch*/
                                condicionSalida = true;
                                break;

                            case 3:
                                System.out.println("Digite el identificador único del producto");
                                int idProducto = input.nextInt();
                                controllerProducto.buscarProductoId(idProducto);
                                condicionSalida = true;
                                break;

                            case 4:

                            case 5:

                                condicionSalida = false;
                                break;

                        }
                    } while (condicionSalida == true);

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
                    this.pedirProducto(3);
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
                    this.pedirProducto(1);
                    break;
                case "2"://Opcion para Proveedo un proveedor nuevo
                    this.pedirProducto(2);
                    break;
                case "3"://opcion para ir atras
                    condicion = 1;
                    break;

            }
        }
    }

   

    public void pedirProducto(int i) {

        this.controllerProducto.setMediador(pedidoProdExistenteMediador);
        this.controllerPreoveedor.setMediador(pedidoProdExistenteMediador);
        this.controllerPedido.setMediador(pedidoProdExistenteMediador);

        pedidoProdExistenteMediador.start(i);
    }
    
    /**
     * 
     * @return true if the user desires to continue with the process, false if they want to cancel
     */
    public boolean confirmarAccion ()
    {
        Scanner sc = new Scanner (System.in);
        System.out.println("Ingresar 1 para confirmar, 2 para cancelar la accion:");
        int i = Integer.parseInt(sc.nextLine());
        if (i==1)
        {
            return true;
        } else {
            return false;
        }
            
    }

}

