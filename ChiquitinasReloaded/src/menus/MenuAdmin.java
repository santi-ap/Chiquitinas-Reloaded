/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menus;

/**
 *
 * @author santialfonso
 */
public class MenuAdmin implements MenuDisplayBehavior{
    
     private MenuAdminCombo menuAdminCombo = new MenuAdminCombo();
     private MenuAdminProveedor menuAdminProveedor = new MenuAdminProveedor();
     private MenuAdminProducto menuAdminProducto = new MenuAdminProducto();
     private MenuAdminCliente menuAdminCliente = new MenuAdminCliente();

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
    public void displayMenu() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
