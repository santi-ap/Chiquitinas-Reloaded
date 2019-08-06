/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import servicios.ServicioUsuario;
import java.sql.SQLException;
import servicios.Servicio;
import menus.*;
import java.util.*;
import objetos.*;

/**
 *
 * @author santialfonso
 */
public class ControllerUsuario extends ControllerFactory{
    protected String idInput;
    protected String contrasennaInput;
    // ---------------------------------------- HAY QUE HACER UN CASTING AQUI PARA PODER IMPLEMENTAR EL PATRON FACTORY
    protected ServicioUsuario servivioUsuario = ((ServicioUsuario)this.CrearServicio());//CASTING DE Servcio A ServicioUsuario
    

    public String getIdInput() {
        return idInput;
    }

    public void setIdInput(String idInput) {
        this.idInput = idInput;
    }

    public String getContrasennaInput() {
        return contrasennaInput;
    }

    public void setContrasennaInput(String contrasennaInput) {
        this.contrasennaInput = contrasennaInput;
    }

    public ServicioUsuario getServivioUsuario() {
        return servivioUsuario;
    }

    public void setServivioUsuario(ServicioUsuario servivioUsuario) {
        this.servivioUsuario = servivioUsuario;
    }
    
    /**
     * METODO NECESARIO PARA IMPLEMENTAR FACTORY
     * @return un nuevo ServicioUsuario
     */
    @Override
    public Servicio CrearServicio() {
        return new ServicioUsuario();
    }
    
/**
 * metodo chequea en la base de datos si el id ingresado ya existe
 * @param idInput es el id que se envia a la base de datos para verificar unicidad
 * @return true si el id ya existe en la base de datos
 */

    public boolean existeId(String idInput) {
            if (this.getServivioUsuario().select("idUsuario", "idUsuario", idInput).toString().equals("noUserFound"))
            {
                return false;
            }
            else
            {
                return true;
            }
    }
    
    /**
     * metodo que verifica si dos strings son iguales.
     * @param s es el primer input ingresado por el usuario.
     * @param p es el segundo input ingresado por el usuario.
     * @return true si son iguales, false si no.
     */
    public boolean verificaString (String s, String p){
        return s.equals(p);
    }
    
     public void verificarTipoUsuario(){
            Scanner sc = new Scanner(System.in);
            MenuCliente menu = new MenuCliente();
            Cliente cliente = new Cliente();
            
            System.out.println("Digite su identificación de usuario");
            int idUsuario = sc.nextInt();
            
            int cantidadPedidos = servivioUsuario.selectCount("idOrden", "User_idUsuario", idUsuario);
            //int cantidadPedidosConversion = Integer.parseInt(cantidadPedidos);
            
            System.out.println(cantidadPedidos);
            
           /* if(cantidadPedidosConversion >= 5){

                cliente.setTipoUsuario(1); 
                menu.displayMenu();

            } else{

                cliente.setTipoUsuario(0);
                menu.displayMenu();

            }
*/


        }

     /**
      * Busca los produtos más consumidos
      */
     
     public void diezProductosMasConsumidos(){
         Scanner sc = new Scanner(System.in);
         System.out.println("Digite su identificación de cliente");
         int userId = sc.nextInt();
         
            System.out.println(servivioUsuario.selectProductosMasConsumidos(userId));
     
     
         
     }


}
