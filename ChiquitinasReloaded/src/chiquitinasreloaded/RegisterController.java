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
}
