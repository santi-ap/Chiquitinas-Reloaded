/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chiquitinasreloaded;

import controllers.RegisterController;
import controllers.LoginController;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import servicios.ServicioProducto;
import menus.*;


/**
 *
 * @author santialfonso
 */
public class Tester {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        
        
//////////////////////////////////////////////////////////////////////////////////////////      
//        Test for database DO NOT DELETE (run to check if db connection is working     //
//        ServicioUsuario us = new ServicioUsuario();                                   //
//        Usuario u = new Cliente("2","testName2","testPass2",1);                       //
//        System.out.println(us.selectAll("idUsuario", "1").toString());                //
//        ServicioProducto sp = new ServicioProducto();                                 //
//        System.out.println(sp.select("nombreProducto", "idProducto", "2123657"));     //
//////////////////////////////////////////////////////////////////////////////////////////   
        
        primerMenu(); //ESTE ES EL PRIMER MENU QUE SE VA A VER CUANDO EMPIECE EL PROGRAMA
     

     
       

    }
    
    public static void ini()
    {
        ;
    }
    
    //ESTE ES EL PRIMER MENU QUE SE VA A VER CUANDO EMPIECE EL PROGRAMA
    public static void primerMenu() throws SQLException{
        Scanner sc = new Scanner(System.in);
        System.out.println("1 - Login\n2 - Registrar");
        String input = sc.nextLine();
        if(input.equals("1")){
            LoginController lc = new LoginController();
            lc.formLogin();
        }else if(input.equals("2")){
            RegisterController rc = new RegisterController();
            rc.formRegistro();
        }else{
            System.out.println("Input no valido");
            primerMenu();
            return;
        }
        
    }
    
}


