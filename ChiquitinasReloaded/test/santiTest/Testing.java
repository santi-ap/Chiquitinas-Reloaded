/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package santiTest;

import static chiquitinasreloaded.Tester.primerMenu;
import controllers.ControllerCombo;
import controllers.ControllerProducto;
import controllers.LoginController;
import controllers.RegisterController;
import items.Combo;
import items.Producto;
import java.util.ArrayList;
import java.util.Scanner;
import menus.MenuAdminProducto;
import menus.MenuCliente;
import servicios.ServicioCombo;
import servicios.ServicioComboHasProducto;
import servicios.ServicioProducto;
import java.sql.SQLException;

/**
 *
 * @author santialfonso
 */
public class Testing {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {

        ServicioProducto sp = new ServicioProducto();
        ControllerCombo conCombo = new ControllerCombo();
        MenuCliente menuCliente = new MenuCliente();
//        sp.update("stockMinProducto", "10", "idProducto", "2123657");
//        MenuAdminProducto mAP=new MenuAdminProducto();
//        mAP.displayMenu();
//          for(Producto p:sp.selectTodosLosProductos()){
//              System.out.println(p);
//          }
//          for (Object o:sp.selectAll("Proveedor_idProveedor", "1")){
//              System.out.println((Producto)o);
//          }
//        ControllerProducto cp = new ControllerProducto();
//        cp.modidificarNombreProducto(1);
//        String string = "1,5";
//        String[] parts = string.split(",");
//        String part1 = parts[0]; // 1
//        String part2 = parts[1]; // 5
//        System.out.println(part2);
//        ServicioComboHasProducto scp = new ServicioComboHasProducto();
//        scp.insert(string);
       ServicioCombo servCombo = new ServicioCombo();
       ServicioComboHasProducto servCHP = new ServicioComboHasProducto();
       
//       for(Combo c:servCombo.selectTodosLosCombos()){
//           System.out.println("Id Combo: "+c.getIdCombo()+" | Nombre Combo: "+c.getNombreCombo());
//           for(String s:servCHP.selectListIdProductos("Combo_idCombo", c.getIdCombo())){
//               ArrayList<Object> prodList= sp.selectAll("idProducto", s);
//                   Producto prod = ((Producto)prodList.get(0));
//                   System.out.println("   "+prod.toString(0));
//               
//           }
//           
//       }
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
