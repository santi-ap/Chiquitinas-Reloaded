/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chiquitinasreloaded;

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
        //esto es un cambio
        //esto es un cambio desde la nube
        RegisterController rc = new RegisterController();
        String test;
        test = rc.ingresarContrasenna();
        System.out.println(test);
        test = rc.ingresarContrasennaAgain();
        System.out.println(test);
        test = rc.ingresarNombre();
        System.out.println(test);
        test = Integer.toString(rc.ingresarId());
        System.out.println(test);
                
    }
    
}
