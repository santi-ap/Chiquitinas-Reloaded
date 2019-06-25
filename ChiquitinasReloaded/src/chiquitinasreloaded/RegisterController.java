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
public class RegisterController extends Controller{
    private String nombreInput;
    private  String contrasennaDosInput;
    private static String adminContrasenna="admin";

    public String getNombreInput() {
        return nombreInput;
    }

    public void setNombreInput(String nombreInput) {
        this.nombreInput = nombreInput;
    }

    public String getContrasennaDosInput() {
        return contrasennaDosInput;
    }

    public void setContrasennaDosInput(String contrasennaDosInput) {
        this.contrasennaDosInput = contrasennaDosInput;
    }

    public static String getAdminContrasenna() {
        return adminContrasenna;
    }

    public static void setAdminContrasenna(String adminContrasenna) {
        RegisterController.adminContrasenna = adminContrasenna;
    }
    
    //metodo que hace toda la logico para registar un usuario nuevo
    public Usuario registar(){
        return null;
    }
    
    //metodo que imprime el menu de registro
    public void menuRegistro(){
        
    }
    
    /**
     * pregunta al user por el id
     * @return el id ingresado por el user
     */
    public int ingresarId ()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingresar ID:");
        return sc.nextInt();
    }
    
    /**
     * pregunta al user por el nombre
     * @return el nombre
     */
    public String ingresarNombre()
    {
        Scanner sc = new Scanner (System.in);
        System.out.println("Ingresar nombre:");
        return sc.nextLine();
    }
    
    /**
     * pregunta al user por la contraseña que quiere utilizar
     * @return la contraseña
     */
    public String ingresarContrasenna()
    {
        Scanner sc = new Scanner (System.in);
        System.out.println("Ingresar contraseña:");
        return sc.nextLine();
    }
    
    /**
     * pregunta al user de repetir la contraseña ingresada
     * @return la contraseña
     */
    public String ingresarContrasennaAgain()
    {
        Scanner sc = new Scanner (System.in);
        System.out.println("Ingresar contraseña una segunda vez:");
        return sc.nextLine();
    }
}
