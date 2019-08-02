/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import items.Combo;
import items.Producto;
import servicios.Servicio;
import servicios.ServicioCombo;
import servicios.ServicioComboHasProducto;
import java.util.*;
import servicios.ServicioProducto;

/**
 *
 * @author santialfonso
 */
public class ControllerCombo extends ControllerFactory{
    // ---------------------------------- HAY QUE HACER UN CASTING AQUI PARA PODER IMPLEMENTAR EL PATRON FACTORY
    private ServicioCombo servicioCombo = ((ServicioCombo)this.CrearServicio());//CASTING DE Servcio A ServicioCombo
    private ServicioComboHasProducto servicioComboHasProducto = new ServicioComboHasProducto();
    ServicioProducto servicioProducto = new ServicioProducto();
    
    public ControllerCombo() {
    }

    public ServicioCombo getServicioCombo() {
        return servicioCombo;
    }

    public void setServicioCombo(ServicioCombo servicioCombo) {
        this.servicioCombo = servicioCombo;
    }
    
     public ServicioComboHasProducto getServicioComboHasProducto() {
        return servicioComboHasProducto;
    }

    public void setServicioComboHasProducto(ServicioComboHasProducto servicioComboHasProducto) {
        this.servicioComboHasProducto = servicioComboHasProducto;
    }
    
    /**
     * METODO NECESARIO PARA IMPLEMENTAR FACTORY
     * @return un nuevo ServicioCombo
     */
    @Override
    public Servicio CrearServicio() {
        return new ServicioCombo();
    }
    
    /*
    @marco
    Se utiliza el método de insert como un STRING donde el primer valor es el combo ID separa de una coma y el segundo el ID del producto
    */
    
    
    public void insertarProductoAUnCombo(){
    //Lo mejoraré para no estar pidiendo de nuevo el identifier del combo, sino que solamente el del producto
    boolean condicionSalida = true;
    Scanner sc = new Scanner(System.in);
    System.out.println("Digite el identificador del combo");
    int codigoDelCombo = sc.nextInt();
    do{
    System.out.println("Digite el identificador del producto");
    int idProducto = sc.nextInt();
    String conversionIdCombo = String.valueOf(codigoDelCombo);
    String conversionIdProducto = String.valueOf(idProducto);
    String stringParaMetodo = codigoDelCombo +","+idProducto;
    servicioComboHasProducto.insert(stringParaMetodo);
    System.out.println("Producto agregado con exito al combo " +codigoDelCombo);
        System.out.println("Desea continuar agregando productos al combo?\n1-Si\n2-No");
        int respuestaUsuario = sc.nextInt();
            if(respuestaUsuario == 1){
            
            condicionSalida = true;
            
            } else{
            
            condicionSalida = false;    
            
            }
    
    
    }while(condicionSalida == true);
    
}
    
    /**
     * @Marco
     * Creacion de un submenu para modificar el producto
     */
    
    public void submenuModificarCombo(){
    
        Scanner sc = new Scanner(System.in);
        System.out.println("1-Agregar producto a un combo\n2-Modificar precio de un combo");
        int respuestaUsuario = sc.nextInt();
        if(respuestaUsuario == 1){
         
        this.insertarProductoAUnCombo();
        
        } else if(respuestaUsuario == 2){
            System.out.println("Digite el identificador del combo");
            int identificadorCombo = sc.nextInt();
            this.actualizarPrecioDelCombo(identificadorCombo);
        
        }
    
    }
    
    /**
     * 
     * @marco 
     * Actualiza el precio del producto y es parte del menu combos
     */
    public void actualizarPrecioDelCombo(int idCombo){
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el nuevo precio del combo: ");
        String precioClienteProductoNuevo = sc.nextLine();
        servicioCombo.update("precioClienteCombo", precioClienteProductoNuevo, "idCombo", idCombo);
        System.out.println("Actualizacion exitosa de combo con id "+idCombo + " a: "+precioClienteProductoNuevo);
        

}
    /**
     * @santi
     * Muestra los combos y los productos dentro de esos combos
     */
    public void mostrarCombos(){
        for(Combo c:servicioCombo.selectTodosLosCombos()){//recorre una lista de todos los combos
           System.out.println("Id Combo: "+c.getIdCombo()+" | Nombre Combo: "+c.getNombreCombo());//imprime el id y nombre del combo actual
           for(String idProducto:servicioComboHasProducto.selectListIdProductos("Combo_idCombo", c.getIdCombo())){//recorre una lista de productos que esten en el combo actual
               ArrayList<Object> prodList= this.servicioProducto.selectAll("idProducto", idProducto);//agarra la info del producto actual en el recorrdio de productos en el combo actual
                   Producto prod = ((Producto)prodList.get(0));
                   System.out.println("   "+prod.toString(0));//imprime el producto actual dentro del combo actual
               
           }
       }
        
    }
    
    
}
