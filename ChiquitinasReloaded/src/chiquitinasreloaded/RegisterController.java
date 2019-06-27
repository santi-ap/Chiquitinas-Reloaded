/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chiquitinasreloaded;

import java.sql.SQLException;
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
    
    //metodo que imprime el form de registro
    public Usuario formRegistro() throws SQLException{
        Usuario u;
        long id;
        System.out.println("Inizializando registro...");
        id = this.ingresarId();
        if (super.existeId(id)) //checking if id is already in the db
        {
            System.out.println("Usuario ya existe, reiniciando...");
            return formRegistro ();
        } else if (this.verificaContrasennaAdmin()){ //if not, check if the user wants to create an admin or client account
            this.setContrasennaInput(this.ingresarContrasenna());
            this.setContrasennaDosInput(this.ingresarContrasennaAgain());
            if (super.verificaString(this.getContrasennaInput(), this.getContrasennaDosInput())) //verifica contraseña 
            {
                u = new Admin ();
                u.setContrasennaUsuario(contrasennaInput);
                u.setIdUsuario(id);
                u.setNombreUsuario(this.ingresarNombre());
                u.setTipoUsuario(0);
                return (Usuario)u;
            } else {
                System.out.println("Contraseñas no coinciden. Reiniciando...");
                return this.formRegistro();
            }
        } else {
            this.setContrasennaInput(this.ingresarContrasenna());
            this.setContrasennaDosInput(this.ingresarContrasennaAgain());
            if (super.verificaString(this.getContrasennaInput(), this.getContrasennaDosInput())) //verifica contraseña 
            {
                u = new Cliente ();
                u.setContrasennaUsuario(contrasennaInput);
                u.setIdUsuario(id);
                u.setNombreUsuario(this.ingresarNombre());
                u.setTipoUsuario(0);
                return (Usuario)u;
            } else {
                System.out.println("Contraseñas no coinciden. Reiniciando...");
                return this.formRegistro();
            }
            
        }
        
            
    }
    
    public boolean verificaContrasennaAdmin()
    {
        Scanner sc = new Scanner (System.in);
        System.out.println("Ingresar contraseña de administrador para crear una cuenta Admin.\nDe lo contrario, presione ENTER:");
        String s = sc.nextLine();
        if (s.equals("")||s.equals(null))
        {
            System.out.println("Creando CLIENTE...");
            return false;
        } else if (s.equals(this.getAdminContrasenna())) {
            System.out.println("Creando ADMIN...");
            return true;
        } else {
            System.out.println("Contraseña incorrecta, reiniciando...");
            return verificaContrasennaAdmin();
        }
    }
    /**
     * pregunta al user por el id
     * @return el id ingresado por el user
     */
    public long ingresarId ()
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
