/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import objetos.Usuario;
import controllers.ControllerUsuario;
import servicios.ServicioUsuario;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author santialfonso
 */
public class LoginController extends ControllerUsuario {

    //metodo que tiene toda la logica para logear un usuario
    public Usuario logear(String idInput, String passInput) {

        ServicioUsuario su = new ServicioUsuario();
        //validar id con base de datos
        
        String idBD = su.select("idUsuario", "idUsuario", idInput).toString();
        String passBD = su.select("contrasennaUsuario", "idUsuario", idInput).toString();
        ArrayList<Object> typeBD = su.selectAll("idUsuario", idInput);
        

        if (idInput.equals(idBD)) {
            if(passInput.equals(passBD)){
                if( typeBD.get(3).toString().equals("0") ){
                    System.out.println("Menu Admin");
                }else{
                    System.out.println("Menu Cliente");
                }
                
                
            }else{
                System.out.println("Usuario o contrasenna incorrecta");
            this.formLogin();
            }

        }else{
            System.out.println("Usuario o contrasenna incorrecta");
            this.formLogin();
        }

        return null;
    }


    //metodo que imprime el menu de login
    public void formLogin() {
        Scanner input = new Scanner(System.in);

        System.out.println("Inserte el ID");
        String idInput = input.nextLine();

        System.out.println("Inserte la contrasenna");
        String passInput = input.nextLine();

        System.out.println("1) Aceptar \n2) Cancelar");
        String op = input.nextLine();

        switch (op) {

            case "1": //inicia proceso para validar datos de usuario e ingresar

                this.logear(idInput, passInput);
                break;

            case "2": //termina proceso de logeo y vuelve al menu anterior
                break;

        }

}
}
