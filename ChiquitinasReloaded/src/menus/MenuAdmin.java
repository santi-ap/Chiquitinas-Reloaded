/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menus;

import controllers.ControllerPedido;
import controllers.ControllerUsuario;
import java.util.Scanner;
import objetos.Usuario;

/**
 *
 * @author santialfonso
 */
public class MenuAdmin implements MenuDisplayBehavior {

    private MenuAdminCombo menuAdminCombo = new MenuAdminCombo();
    private MenuAdminProveedor menuAdminProveedor = new MenuAdminProveedor();
    private MenuAdminProducto menuAdminProducto = new MenuAdminProducto();
    private MenuAdminCliente menuAdminCliente = new MenuAdminCliente();
    ControllerUsuario controllerUsuario = new ControllerUsuario();
    ControllerPedido controllerPedido = new ControllerPedido();

    public MenuAdmin() {
    }

    public MenuAdminCombo getMenuAdminCombo() {
        return menuAdminCombo;
    }

    public void setMenuAdminCombo(MenuAdminCombo menuAdminCombo) {
        this.menuAdminCombo = menuAdminCombo;
    }

    public MenuAdminProveedor getMenuAdminProveedor() {
        return menuAdminProveedor;
    }

    public void setMenuAdminProveedor(MenuAdminProveedor menuAdminProveedor) {
        this.menuAdminProveedor = menuAdminProveedor;
    }

    public MenuAdminProducto getMenuAdminProducto() {
        return menuAdminProducto;
    }

    public void setMenuAdminProducto(MenuAdminProducto menuAdminProducto) {
        this.menuAdminProducto = menuAdminProducto;
    }

    public MenuAdminCliente getMenuAdminCliente() {
        return menuAdminCliente;
    }

    public void setMenuAdminCliente(MenuAdminCliente menuAdminCliente) {
        this.menuAdminCliente = menuAdminCliente;
    }

    @Override
    public void displayMenu(Usuario usuario) {
        boolean cond = true;
        Scanner input = new Scanner(System.in);
        while (cond) {
            System.out.println("\n\n\n\n\nMENU ADMIN\n"
                    + "1-Administrar Usuarios\n"
                    + "2-Combos\n"
                    + "3-Productos\n"
                    + "4-Proveedores\n"
                    + "5-Cambiar Contrasenna\n"
                    + "6-Ver Pedidos\n"
                    + "7-Atras");
            String opcion = input.nextLine();
            switch (opcion) {
                case "1"://Opcion para Agregar/Pedir un producto
                    this.menuAdminCliente.displayMenu(usuario);
                    break;
                case "2"://Opcion para Buscar producto por nombre
                    this.menuAdminCombo.displayMenu(usuario);
                    break;
                case "3"://opcion para Modificar producto
                    this.menuAdminProducto.displayMenu(usuario);
                    break;
                case "4":// opcion para Eliminar producto
                    this.menuAdminProveedor.displayMenu();
                    break;
                case "5"://Cambiar Contra
                    controllerUsuario.cambiarContra(usuario);
                    break;
                case "6"://Ver pedidos
                    this.controllerPedido.mostrarPedido();
                    break;
                case "7"://opcion para ir atras
                    cond = false;
                    break;
            }
        }
    }

}
