/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chiquitinasreloaded;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author santialfonso
 */
public class Tester {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {

        // TODO code application logic here
       
//        System.out.println("creating controller...");
//        RegisterController rc = new RegisterController();
//        System.out.println("Initializing Controller...");
//        rc.getServivioUsuario().selectAll("*", "idUsuario");
       //     ServicioUsuario su = new ServicioUsuario();
         //   System.out.println(su.selectAll("idUsuario", "1").toString());
         
         LoginController lc = new LoginController();
         lc.formLogin();

        
//////////////////////////////////////////////////////////////////////////////////////////      
//        Test for database DO NOT DELETE (run to check if db connection is working     //
//        ServicioUsuario us = new ServicioUsuario();                                   //
//        Usuario u = new Cliente("2","testName2","testPass2",1);                       //
//        System.out.println(us.selectAll("idUsuario", "1").toString());                //
//////////////////////////////////////////////////////////////////////////////////////////   


        RegisterController rc = new RegisterController();
        rc.formRegistro();
        

//        ServicioUsuario us = new ServicioUsuario();
//        Usuario u = new Cliente("2","testName2","testPass2",1);
//        System.out.println(us.select("idUsuario", "idUsuario", "1").toString());
//        System.out.println(us.selectAll("idUsuario", "1"));
//        rc.formRegistro();




    }
    
}
