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

/**
 *
 * @author santialfonso
 */
public class ControllerCombo extends ControllerFactory{
    // ---------------------------------- HAY QUE HACER UN CASTING AQUI PARA PODER IMPLEMENTAR EL PATRON FACTORY
    private ServicioCombo servicioCombo = ((ServicioCombo)this.CrearServicio());//CASTING DE Servcio A ServicioCombo
    private ServicioComboHasProducto servicioComboHasProducto = ((ServicioComboHasProducto)this.CrearServicio());
    
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
    Scanner sc = new Scanner(System.in);
    System.out.println("Digite el identificador del combo");
    int primer = sc.nextInt();
    System.out.println("Digite el identificador del producto");
    int segundo = sc.nextInt();
    String conversionIdCombo = String.valueOf(primer);
    String conversionIdProducto = String.valueOf(segundo);
    String stringParaMetodo = primer +","+segundo;
    
    servicioComboHasProducto.insert(stringParaMetodo);
    
    }
    
}
