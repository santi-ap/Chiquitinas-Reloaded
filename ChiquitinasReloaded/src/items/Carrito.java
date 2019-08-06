/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package items;

/**
 *
 * @author santialfonso
 */
public class Carrito extends Item{
    private double totalCarrito;
    
    public Carrito() {
        this.totalCarrito=0f;
    }

    @Override
    public double getPrecio(int tipoUsuario) {
        return this.totalCarrito;
    }

    @Override
    public String getRecibo(int tipoUsuario) {
        super.recibo = "CARRITO:\n";
        return super.recibo;
    }
    
}
