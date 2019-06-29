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
        Usuario u = new Cliente("2","testName2","testPass2",1);
        us.insert(u);
        //Usuario admin = new Admin(1,"Nombre","pass",1);
//        System.out.println(us.selectAll("idUsuario", "5"));
//        us.delete("idUsuario", "2");
//        us.update("contrasennaUsuario","123", "idUsuario", "3");
    }
    
}
