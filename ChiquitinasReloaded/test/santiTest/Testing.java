/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package santiTest;

import servicios.ServicioProducto;

/**
 *
 * @author santialfonso
 */
public class Testing {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ServicioProducto sp = new ServicioProducto();
        System.out.println(sp.selectAll("idProveedor", 2));
        
    }
    
}
