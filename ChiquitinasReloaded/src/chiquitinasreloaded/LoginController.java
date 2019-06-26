/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chiquitinasreloaded;

import java.util.Scanner;

/**
 *
 * @author santialfonso
 */
public class LoginController extends Controller {

    //metodo que tiene toda la logica para logear un usuario
    public Usuario logear(int idU, String passU) {

        ServicioUsuario su = new ServicioUsuario();
        //validar id con base de datos
        
        int idBD = 0;//replace 0 with database method query for id
        String passBD = "0";//replace 0 with database method query for password

        if (idU == idBD) {
            if(passU)

        }

        return null;
    }

    //metodo que imprime el menu de login
    public void menuLogin() {
        Scanner input = new Scanner(System.in);

        System.out.println("Inserte el ID");
        int idU = Integer.parseInt(input.nextLine());

        System.out.println("Inserte la contrasenna");
        String passU = input.nextLine();

        System.out.println("1) Aceptar \n2) Cancelar");
        String op = input.nextLine();

        switch (op) {

            case "1": //inicia proceso para validar datos de usuario e ingresar

                this.logear(idU, passU);
                break;

            case "2": //termina proceso de logeo y vuelve al menu anterior
                break;

        }
    }
}
