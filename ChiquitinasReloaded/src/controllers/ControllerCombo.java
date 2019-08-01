/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import servicios.Servicio;
import servicios.ServicioCombo;
import servicios.ServicioComboHasProducto;
import java.util.*;
import java.sql.Date;
import java.sql.Timestamp;
import items.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;  

/**
 *
 * @author santialfonso
 */
public class ControllerCombo extends ControllerFactory{
    // ---------------------------------- HAY QUE HACER UN CASTING AQUI PARA PODER IMPLEMENTAR EL PATRON FACTORY
    private ServicioCombo servicioCombo = ((ServicioCombo)this.CrearServicio());//CASTING DE Servcio A ServicioCombo
    private ServicioComboHasProducto servicioComboHasProducto = new ServicioComboHasProducto();
    
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
    
    
    public void submenuCrearCombo(){
    
    //idCombo,nombreCombo,precioClienteCombo,descuentoCombo, fechaInicioCombo, fechaFinCombo

        
    }
    
    /**
     * @marco
     * Método crea un combo nuevo desde cero. Lo ampliaré para colocar una fecha fin.
     */
    
    public void crearComboNuevo(){
        
    java.util.Date utilDate = new java.util.Date();
    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

    
    String string_date = "12-12-2012";

                    SimpleDateFormat f = new SimpleDateFormat("dd-mm-yyyy");
                    try {
                        java.util.Date d = f.parse(string_date);
                        long milliseconds = d.getTime();
                        
                        java.sql.Date sqlDate1 = new java.sql.Date(milliseconds);
                     
        
        
    Scanner sc = new Scanner(System.in);
    System.out.println("Digite el identificador del combo");
    int idCombo = sc.nextInt();
    System.out.println("Digite el nombre del combo");
    String nombreCombo = sc.next();
    System.out.println("Digite el precio del combo");
    Double precioCombo = sc.nextDouble();
    System.out.println("Digite las unidades disponibles del combo");
    int unidadesCombo = sc.nextInt();
    System.out.println("Digite la cantidad de productos del combo");
    int productosCombo = sc.nextInt();
    System.out.println("Digite el descuento del combo");
    Double descuentoCombo = sc.nextDouble();
    
    Combo combo = new Combo(idCombo, nombreCombo, precioCombo,unidadesCombo, productosCombo, descuentoCombo, sqlDate, sqlDate1);
    servicioCombo.insert(combo);
    System.out.println("Combo "+nombreCombo+" insertado con éxito!"); }   catch (ParseException e) {
                        e.printStackTrace();}  
   
    }
    

    
    /**
     * @Marco
     * Creacion de un submenu para modificar el combo
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
     * @marco
     * Despliega toda la información del combo selecionado
     */
    
    public void desplegarInformacionCombo(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite el identificador del combo");
        int idCombo = sc.nextInt();
        
            servicioCombo.selectAll("idCombo",idCombo);
    
    }
    
    
    public void insertarFechaDeFinEInicioDeCombo(){
    
        Scanner sc = new Scanner(System.in);
        
            Calendar calendar = Calendar.getInstance();
            java.util.Date currentDate = calendar.getTime();
            java.sql.Date date = new java.sql.Date(currentDate.getTime());
            System.out.println("Digite el identificador del producto");
            int idProducto = sc.nextInt();

             servicioCombo.update("fechaInicioCombo", date, "idCombo", idProducto );
             System.out.println("Fecha de inicio de combo ingresada con exito");
             System.out.println("Digite la fecha de fin del combo\nSiga el formato brindado: 2019-07-21");
                servicioCombo.update(idProducto, currentDate, idProducto, date);
             
    
    }
    
    
    
}
