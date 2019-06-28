/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chiquitinasreloaded;

import java.util.ArrayList;

/**
 *
 * @author santialfonso
 */
public class Tester {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
       
        ServicioUsuario us = new ServicioUsuario();
        Usuario u = new Cliente("3","testName3","testPass3",1);
        us.insert(u);
        //Usuario admin = new Admin(1,"Nombre","pass",1);
    }
    
}
