/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menus;
import java.util.*;
import servicios.*;

import objetos.Usuario;

/**
 *
 * @author santialfonso
 */
public class MenuAdminCliente implements MenuDisplayBehavior{
    
    ServicioUsuario servicioUsuario = new ServicioUsuario();

    public MenuAdminCliente() {
    }

    @Override
    public void displayMenu(Usuario usuario) {
        
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite la identificación del usuario que desea administrar:");
        int idUsuario = sc.nextInt();
         boolean condicionSalida = true;
        
            do{
            System.out.println("Elija una opción:\n1-Modificar nombre\n2-Modificar identificación\n3-Modificar contraseña\n4-Modificar tipo de usuario\n5-Eliminar usuario\n6-Salir");
            
            int menuOpcion = sc.nextInt();
            
            switch(menuOpcion){
                case 1:
                    System.out.println("Digite el nuevo nombre del usuario:");
                    sc.nextLine();
                    String nombreUsuario = sc.nextLine();
                    servicioUsuario.update("nombreUsuario", nombreUsuario, "idUsuario", idUsuario);
                        System.out.println("Nombre de usuario actualizado con éxito");
                        System.out.println("Desea continuar modificando más información del usuario?\n1-Si\n2-No");
                        int continuar = sc.nextInt();
                            if(continuar ==1){
                            
                                condicionSalida = true;
                            
                            } else{
                            
                                condicionSalida = false;
                            
                            }
                        
                    break;
                    
                case 2:
                    System.out.println("Digite la nueva identificación del usuario:");
                    int idDelUsuario = sc.nextInt();
                    
                    servicioUsuario.update("idUsuario", idDelUsuario, "idUsuario", idUsuario);
                    System.out.println("Identificación de usuario actualizada con éxito");
                    System.out.println("Desea continuar modificando más información del usuario?\n1-Si\n2-No");
                        int continuar1 = sc.nextInt();
                            if(continuar1 ==1){
                            
                                condicionSalida = true;
                            
                            } else{
                            
                                condicionSalida = false;
                            
                            }
                    break;
                    
                case 3: 
                    System.out.println("Digite la nueva contraseña del usuario:");
                    sc.nextLine();
                    String contrasenna = sc.nextLine();
                    servicioUsuario.update("contrasennaUsuario", contrasenna, "idUsuario", idUsuario);
                        System.out.println("Contraseña del usuario actualizada con éxito");
                        System.out.println("Desea continuar modificando más información del usuario?\n1-Si\n2-No");
                        int continuar2 = sc.nextInt();
                            if(continuar2 ==1){
                            
                                condicionSalida = true;
                            
                            } else{
                            
                                condicionSalida = false;
                            
                            }
                    
                    
                    break;
                
                case 4: 
                    
                    System.out.println("Digite el nuevo tipo de usuario:");
                    int tipoUsuario = sc.nextInt();
                    
                    servicioUsuario.update("tipoUsuario", tipoUsuario, "idUsuario", idUsuario);
                    System.out.println("Tipo de usuario actualizado con éxito");
                    System.out.println("Desea continuar modificando más información del usuario?\n1-Si\n2-No");
                        int continuar3 = sc.nextInt();
                            if(continuar3 ==1){
                            
                                condicionSalida = true;
                            
                            } else{
                            
                                condicionSalida = false;
                            
                            }
                        break;
                case 5:
                    
                    System.out.println("Desea eliminar el usuario con ID: "+idUsuario+"\n1-Si\n2-No");
                    int estadoCuenta = sc.nextInt();
                    System.out.println("Clique" + " s"+ "para eliminar usuario "+ "o clique "+ "n"+ " para cancelar");
                    sc.nextLine();
                    String respuesta = sc.nextLine().toLowerCase();
 
                    if(respuesta == "s"){ 
                    if(estadoCuenta == 1){
                    estadoCuenta = 1;
                    servicioUsuario.update("estadoCuenta", 0, "idUsuario", idUsuario);
                    System.out.println("Usuario eliminado con éxito!");
                        System.out.println("Desea continuar modificando más información del usuario?\n1-Si\n2-No");
                        int continuar4 = sc.nextInt();
                            if(continuar4 ==1){
                            
                                condicionSalida = true;
                                break;
                            
                            } else{
                            
                                condicionSalida = false;
                                break;
                               
                            }
                        
                    }
                    
                    
                    else{
                    
                        
                        
                    estadoCuenta = 0;
                    servicioUsuario.update("estadoCuenta", 1, "idUsuario", idUsuario);
                   
                    System.out.println("Acción cancelada!");
                    
                    System.out.println("Usuario eliminado con éxito!");
                        System.out.println("Desea continuar modificando más información del usuario?\n1-Si\n2-No");
                        int continuar4 = sc.nextInt();
                            if(continuar4 ==1){
                            
                                condicionSalida = true;
                                break;
                            
                            } else{
                            
                                condicionSalida = false;
                                
                            }
                            
                    }
                            
                            break;
                    
                    }
                    
                    else{
                    
                    
                        condicionSalida = true;
                    
                    }
                   
                    
                    
                    
                case 6:
                    condicionSalida = false;
                    break;
            
            
            
            
            } 
            
            
    }while(condicionSalida==true);
            
    
}
    
}
