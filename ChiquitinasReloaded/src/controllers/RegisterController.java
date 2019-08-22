/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import objetos.Admin;
import objetos.Admin;
import objetos.Cliente;
import objetos.Cliente;
import objetos.Usuario;
import objetos.Usuario;
import controllers.ControllerUsuario;
import servicios.ServicioUsuario;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author santialfonso
 */
public class RegisterController extends ControllerUsuario {

    private String nombreInput;
    private String contrasennaDosInput;
    private final static String ADMINCONTRASENNA = "admin";
    ServicioUsuario su = new ServicioUsuario();

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
    public Usuario registar(Usuario u) {
        su.insert(u);
        return u;
    }

    /**
     * metodo que imprime el formulario de registro
     *
     * @return el usuario registrado
     * @throws SQLException
     */
    public void formRegistro() throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Inizializando registro...");
        System.out.println("Ingresar:\n1 - para registrar Cliente\n2 - para registrar Admin");
        Usuario u;
        boolean isAdmin = false;
        try {
            int userInput = sc.nextInt();
            if (userInput == 1) {
                u = new Cliente();
            } else if (userInput == 2) {
                u = new Admin();
                isAdmin = true;
            } else {
                System.out.println("Input no valido, reiniciando...");
                this.formRegistro();
                return;
            }

            super.setIdInput(this.ingresarId()); //asking user for id
            if (super.existeId(super.getIdInput())) //checking if id is already in the db
            {
                System.out.println("Usuario ya existe, reiniciando...");
                this.formRegistro();
            } else {
                this.setNombreInput(this.ingresarNombre());
                this.setContrasennaInput(this.ingresarContrasenna());
                this.setContrasennaDosInput(this.ingresarContrasennaAgain());
                if (super.verificaString(this.getContrasennaInput(), this.getContrasennaDosInput())) //verifica contraseña 
                {
                    if (isAdmin) {
                        if (this.verificaContrasennaAdmin()) {
                            u.setContrasennaUsuario(this.getContrasennaInput());
                            u.setIdUsuario(this.getIdInput());
                            u.setNombreUsuario(this.getNombreInput());
                        } else {
                            System.out.println("Contraseña Invalida, reiniciando...");
                            this.formRegistro();
                            return;
                        }
                    } else { //it's a client 
                        u.setContrasennaUsuario(this.getContrasennaInput());
                        u.setIdUsuario(this.getIdInput());
                        u.setNombreUsuario(this.getNombreInput());
                    }
                    // --SANTI--     /////////////////////// AQUI ES DONDE SE DECIDE SI ACEPTA REGISTRAR O CANCELAR ////////////////////////////////////////////// --SANTI--//
                    System.out.println("1 - Registrar\n2 - Cancelar");
                    switch (sc.nextInt()) {
                        case 1://SI ESCOGE REGISTRAR, LLAMA A LA FUNCION DE REGISTAR
                            u.setContrasennaUsuario(this.getContrasennaInput());
                            u.setIdUsuario(this.getIdInput());
                            u.setNombreUsuario(this.getNombreInput());
                            this.registar(u);
                            LoginController lg = new LoginController();
                            break;
                        case 2://SI ESCOGE CANCELAR, SE REINICA EL FORM 
                            this.formRegistro();
                            return;
                        default: //SI ESCOGE CUALQUIER OTRA COSA IGUAL SE REINICIA EL FORM
                            System.out.println("Input no valido, reiniciando...");
                            this.formRegistro();
                            return;
                    }
                    // --SANTI--     /////////////////////// AQUI TERMINA DE DECIDIR SI ACEPTA REGISTRAR O CANCELAR ////////////////////////////////////////////// --SANTI--// 

                } else {
                    System.out.println("Contraseñas no coinciden. Reiniciando...");
                    this.formRegistro();
                    return;
                }

            }
        } catch (Exception e) {
            System.out.println(e + "Input not valid.");
            this.formRegistro();
            return;
        }
    }

    public boolean verificaContrasennaAdmin() {
        Scanner sc = new Scanner(System.in);
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
     *
     * @return el id ingresado por el user
     */
    public String ingresarId() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingresar ID:");
        return sc.nextLine();
    }

    /**
     * pregunta al user por el nombre
     *
     * @return el nombre
     */
    public String ingresarNombre() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingresar nombre:");
        return sc.nextLine();
    }

    /**
     * pregunta al user por la contraseña que quiere utilizar
     *
     * @return la contraseña
     */
    public String ingresarContrasenna() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingresar contraseña:");
        return sc.nextLine();
    }

    /**
     * pregunta al user de repetir la contraseña ingresada
     *
     * @return la contraseña
     */
    public String ingresarContrasennaAgain() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingresar contraseña una segunda vez:");
        return sc.nextLine();
    }

    public void mostrarUsers() {
        ArrayList<Usuario> userList = this.getServivioUsuario().selectAllUsers();
        System.out.println("\t\t\t\tUSUARIOS");
        System.out.println("ID\t\tNOMBRE\t\tTIPO\n__\t\t______\t\t____\n");
        for (Usuario u:userList)
        {
            switch (u.getTipoUsuario())
            {
                case 0:             
                    System.out.println(u.getIdUsuario() + "\t\t" + u.getNombreUsuario() + "\t\tADMIN");

                    
                    break;
                case 1:
                    System.out.println(u.getIdUsuario() + "\t\t" + u.getNombreUsuario() + "\t\tCLIENTE");

                    break;
                case 2:
                    System.out.println(u.getIdUsuario() + "\t\t" + u.getNombreUsuario() + "\t\tCLIENTE VIP");

                    break;
            }
        }
    }
}
