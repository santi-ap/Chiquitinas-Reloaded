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
    private final static String ADMINCONTRASENNA="admin";
    ServicioUsuario su=new ServicioUsuario();

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
        return ADMINCONTRASENNA;
    }

    
    //metodo que hace toda la logico para registar un usuario nuevo
    public Usuario registar(Usuario u){
        su.insert(u);
        return u;
    }
    
    /**
     * metodo que imprime el formulario de registro
     * @return el usuario registrado
     * @throws SQLException 
     */
    public Usuario formRegistro() throws SQLException{
        String id = "";
        Scanner sc = new Scanner (System.in);
        System.out.println("Inizializando registro...");
        System.out.println("Ingresar:\n1 - para registrar Cliente\n2 - para registrar Admin");
        Usuario u;
        boolean isAdmin = false;
        try 
        {
            int userInput = sc.nextInt();
            if (userInput == 1 )
            {
                u = new Cliente();
            }
            else if (userInput == 2)
            {
                u = new Admin();
                isAdmin = true;
            } else {
                System.out.println("Input no valido, reiniciando...");
                return this.formRegistro();
            }
            
        id = this.ingresarId(); //asking user for id
        if (super.existeId(id)) //checking if id is already in the db
        {
            System.out.println("Usuario ya existe, reiniciando...");
            return formRegistro ();
        } else {
            this.setContrasennaInput(this.ingresarContrasenna());
            this.setContrasennaDosInput(this.ingresarContrasennaAgain());
            if (super.verificaString(this.getContrasennaInput(), this.getContrasennaDosInput())) //verifica contraseña 
            {
               if (isAdmin)
               {
                   if (this.verificaContrasennaAdmin())
                   {
                       return u;
                   } else {
                       System.out.println("Contraseña Invalida, reiniciando...");
                       return this.formRegistro();
                   }
               } else {
   // --SANTI--     /////////////////////// AQUI ES DONDE SE DECIDE SI ACEPTA REGISTRAR O CANCELAR ////////////////////////////////////////////// --SANTI--//
                    System.out.println("1 - Registrar\n2 - Cancelar");
                    switch (sc.nextInt())
                    {
                        case 1://SI ESCOGE REGISTRAR, LLAMA A LA FUNCION DE REGISTAR
                            this.registar(u);
                            break;
                        case 2://SI ESCOGE CANCELAR, SE REINICA EL FORM 
                            return formRegistro();
                        default: //SI ESCOGE CUALQUIER OTRA COSA IGUAL SE REINICIA EL FORM
                            System.out.println("Input no valido, reiniciando...");
                            return formRegistro();
                    }
   // --SANTI--     /////////////////////// AQUI TERMINA DE DECIDIR SI ACEPTA REGISTRAR O CANCELAR ////////////////////////////////////////////// --SANTI--//                 
                    return u;   
               }
            } else {
                System.out.println("Contraseñas no coinciden. Reiniciando...");
                return this.formRegistro();
            }
            
        }         
    } catch (Exception e) {
        System.out.println(e + "Input not valid.");
        return formRegistro();
    }
    }
    public boolean verificaContrasennaAdmin()
    {
        Scanner sc = new Scanner (System.in);
        System.out.println("Ingresar contraseña de administrador para crear una cuenta Admin:");
        String s = sc.nextLine();
        if (s.equals(this.getAdminContrasenna())) {
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
    public String ingresarId ()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingresar ID:");
        return sc.nextLine();
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
