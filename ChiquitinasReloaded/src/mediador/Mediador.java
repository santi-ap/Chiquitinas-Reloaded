/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mediador;

/**
 *
 * @author santialfonso
 */
public interface Mediador {

    public abstract void start(int i);
    
    public abstract void opeGetListaProveedores();

    public abstract void opeGetListaProductoProv();

    public abstract void opeGetProductoSeleccionado();
    
    public abstract void opeSetMontoOrden(String id);
    
    public abstract void crpGetListaProveedores();
    
    public abstract void crpCrearProducto();
    

}
