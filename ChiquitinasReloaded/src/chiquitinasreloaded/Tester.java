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

    }
    
}
