/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import items.Producto;
import java.util.ArrayList;
import java.util.Scanner;
import mediador.Colleague;
import mediador.Mediador;
import observer.Observer;
import observer.Subject;
import servicios.Servicio;
import servicios.ServicioComboHasProducto;
import servicios.ServicioProducto;

/**
 *
 * @author santialfonso
 */
public class ControllerProducto extends ControllerFactory implements Colleague, Subject {

    // ---------------------------------------- HAY QUE HACER UN CASTING AQUI PARA PODER IMPLEMENTAR EL PATRON FACTORY
    private ServicioProducto servicioProducto = ((ServicioProducto) this.CrearServicio());//CASTING DE Servcio A ServicioProducto
    private Mediador mediador;
    ServicioComboHasProducto servComboHasProd = new ServicioComboHasProducto();
    Scanner input = new Scanner(System.in);

    public ControllerProducto() {
    }

    public ServicioProducto getServicioProducto() {
        return servicioProducto;
    }

    public void setServicioProducto(ServicioProducto servicioProducto) {
        this.servicioProducto = servicioProducto;
    }

    /**
     * METODO NECESARIO PARA IMPLEMENTAR FACTORY
     *
     * @return un nuevo ServicioProducto
     */
    @Override
    public Servicio CrearServicio() {
        return new ServicioProducto();
    }

    @Override
    public void setMediador(Mediador mediador) {
        this.mediador = mediador;
    }

    @Override
    public void registrarObserver(Observer observer) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removerObserver(Observer observer) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void notificarObserver() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void buscarProductoPorNombre() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nIngese el nombre del producto que desea buscar: ");
        String nombreProductoDeseado = input.nextLine();
        ArrayList<Object> listaDetallesProducto = this.servicioProducto.selectAll("nombreProducto", nombreProductoDeseado);
        if (!(listaDetallesProducto.get(0).equals("No Existe el Producto"))) {
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n" + (Producto) listaDetallesProducto.get(0));
        } else {
            System.out.println("No Existe el Producto\n\n\n\n");
        }
    }

    public void printTodosLosProductos() {
        for (Producto p : servicioProducto.selectTodosLosProductos()) {
            System.out.println(p);
        }
    }

    public void modidificarNombreProducto(int idProducto) {
        System.out.println("Ingrese el nuevo nombre del producto: ");
        String nombreProdNuevo = input.nextLine();
        servicioProducto.update("nombreProducto", nombreProdNuevo, "idProducto", idProducto);
    }

    public void modidificarPrecioProducto(int idProducto) {
        System.out.println("Ingrese el nuevo precio del producto: ");
        String precioClienteProductoNuevo = input.nextLine();
        servicioProducto.update("precioClienteProducto", precioClienteProductoNuevo, "idProducto", idProducto);
    }

    public void modidificarStockMinProducto(int idProducto) {
        System.out.println("Ingrese el nuevo stock minimo del producto: ");
        String stockMinProductoNuevo = input.nextLine();
        servicioProducto.update("stockMinProducto", stockMinProductoNuevo, "idProducto", idProducto);
    }

    /*
    @marco
    Busca por un producto utilizando su identificador único(primaryKey)
    */
    public void buscarProductoId(int idProducto){
        
        System.out.println(servicioProducto.selectAll("idProducto", idProducto));
}
    
    /*
    @marco
    Este método es idéntico al mñetodo buscarProductoId excepto que retorna el descuento. 
    Me gustaría evacuar una duda antes de eliminarlo o implementarlo
    */
    
    public void buscarProductoReturnDescuento(int idProducto){
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();
        servicioProducto.select("descuentoPromo", "idProducto", idProducto);
}
    
    /*
    @marco
    Definir el descuento del producto e insertarlo a la base de datos
    */


    public void descuento(double cantidadDescuento){
        Scanner sc = new Scanner(System.in);
        boolean control = true;
        do{
        System.out.println("Digite el ID del producto al que desea modificar la promoción");
        int idProducto = sc.nextInt();
        double calculo =cantidadDescuento/100;
        //No estaba seguro si la DB acepta decimales, entonces tuve que hacer conversión a String
        String conversion = Double.toString(calculo);
        //Este el task de taiga que me asignaron para aceptar cambios
        System.out.println("Desea confirmar cambios a la promoción\n1-Sí\n2-No");
        int confirmarCambios = input.nextInt();
        
            if(confirmarCambios == 1){
            servicioProducto.update("descuentoPromo",conversion,"idProducto", idProducto);
            System.out.println("Modificación exitosa");
            System.out.println("Desea continuar agregando promociones?\n1-Sí\n2-No");
            int continuar = input.nextInt();
                if(continuar == 1){
                
                    control = true;
                } else{
                
                    control = false;
                }
            
            
            } else{
                control = false;
                break;
                
            }
        
        } while(control == true);

        
        
        }
  
    
    public int menuPromos(){
    
    System.out.println("1-Crear promoción\n2-Modificar promoción\n3-Buscar promoción\n4-Ver promoción\n5-Menú principal");
    int userInput = input.nextInt();
    return userInput;
    }
    
    public double descuentoUsuario(){
    
    System.out.println("Digite el monto del descuento"); 
    double descuento = input.nextDouble();
    return descuento;
    
    }
    
    public int idUsuario(){
    
    System.out.println("Digite el identificador único del producto");
    int idProducto = input.nextInt();
    return idProducto;
    
    }
    
    public void buscarProductosConDescuento(){
    
        System.out.println(servicioProducto.buscarProductoConDescuento());
    
    }
    

    public void getDatosForMenuProducto(String id) {
        ArrayList<Object> listaProductos = servicioProducto.selectAll("Proveedor_idProveedor", id);

        for (Object o : listaProductos) {

            System.out.println("ID PRODUCTO: " + ((Producto) o).getIdProducto() + "|| PRODUCTO: " + ((Producto) o).getNombreProducto() + "|| PRECIO CLIENTE: c" + ((Producto) o).getPrecioProductoCliente()
                    + "|| PRECIO PROVEEDOR: " + ((Producto) o).getPrecioProductoProveedor());

        }

    }
    
    public void borrarProducto(){
        this.printTodosLosProductos();
        System.out.println("Seleccione el id del producto que desea eliminar: ");
        String idProducto = input.nextLine();
        servicioProducto.delete("idProducto", idProducto);
        this.servComboHasProd.delete("Producto_idProducto", idProducto);
    }
}


