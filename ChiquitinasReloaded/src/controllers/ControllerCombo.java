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
import java.sql.Date;
import java.sql.Timestamp;
import items.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;  
import mediador.Colleague;
import mediador.Mediador;
import mediador.OrdenMediador;


/**
 *
 * @author santialfonso
 */
public class ControllerCombo extends ControllerFactory implements Colleague{
    // ---------------------------------- HAY QUE HACER UN CASTING AQUI PARA PODER IMPLEMENTAR EL PATRON FACTORY
    private ServicioCombo servicioCombo = ((ServicioCombo)this.CrearServicio());//CASTING DE Servcio A ServicioCombo
    private ServicioComboHasProducto servicioComboHasProducto = new ServicioComboHasProducto();
    ServicioProducto servicioProducto = new ServicioProducto();
    private OrdenMediador mediador;
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
    
    
    public void insertarProductoAUnCombo(int idCombo){
    //Lo mejoraré para no estar pidiendo de nuevo el identifier del combo, sino que solamente el del producto
    Scanner sc = new Scanner(System.in);
    boolean condicionSalida = true;
    do{
    System.out.println("Digite el identificador del producto");
    int idProducto = sc.nextInt();
    String conversionIdCombo = String.valueOf(idCombo);
    String conversionIdProducto = String.valueOf(idProducto);
    String stringParaMetodo = idCombo +","+idProducto;
    servicioComboHasProducto.insert(stringParaMetodo);
    System.out.println("Producto agregado con exito al combo " +idCombo);
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
        
   
    Scanner sc = new Scanner(System.in);
    System.out.println("\nDigite el identificador del combo");
    int idCombo = sc.nextInt();
    System.out.println("Digite el nombre del combo");
    sc.nextLine();
    String nombreCombo = sc.nextLine();
    System.out.println("Digite el precio del combo");
    Double precioCombo = sc.nextDouble();
    System.out.println("Digite las unidades disponibles del combo");
    int unidadesCombo = sc.nextInt();
    System.out.println("Digite la cantidad de productos del combo");
    int productosCombo = sc.nextInt();
    System.out.println("\n");
    this.insertarProductoAUnCombo(idCombo);
    System.out.println("Digite el descuento del combo");
    Double descuentoCombo = sc.nextDouble();
    System.out.println("Escoja una de las siguientes opciones\n1-Fecha de inicio actual y fecha de fin personalizada\n2-Fecha de inicio y fin personalizadas");
    int respuestaUsuario = sc.nextInt();
    
    if(respuestaUsuario == 1){
     java.util.Date utilDate = new java.util.Date();
     java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                                System.out.println("Digite el año de fin del combo");
                                int ano = sc.nextInt();
                                System.out.println("Digite el mes de fin del combo");
                                int mes = sc.nextInt();
                                System.out.println("Digite el dia de fin del combo");
                                int dia = sc.nextInt();
                                String fecha = dia+"-"+mes+"-"+ano;

                                String string_date = fecha;

                                SimpleDateFormat f = new SimpleDateFormat("dd-mm-yyyy");
                                try {
                                    java.util.Date d = f.parse(string_date);
                                    long milliseconds = d.getTime();

                                    java.sql.Date sqlDate1 = new java.sql.Date(milliseconds);
     
    Combo combo = new Combo(idCombo, nombreCombo, precioCombo,unidadesCombo, productosCombo, descuentoCombo, sqlDate, sqlDate1);
    servicioCombo.insert(combo);
    servicioCombo.update("fechaInicioCombo", sqlDate, "idCombo", idCombo);
    servicioCombo.update("fechaFinCombo", sqlDate1, "idCombo", idCombo);
    
    System.out.println("Combo "+nombreCombo+" insertado con éxito!"); }catch (ParseException e) {
                        e.printStackTrace();}  
    
    } else{
    
                                System.out.println("Digite el año de inicio del combo");
                                int anoInicio = sc.nextInt();
                                System.out.println("Digite el mes de inicio del combo");
                                int mesInicio = sc.nextInt();
                                System.out.println("Digite el dia de inicio del combo");
                                int diaInicio = sc.nextInt();
                                
                                 System.out.println("Digite el año de fin del combo");
                                int anoFin = sc.nextInt();
                                System.out.println("Digite el mes de fin del combo");
                                int mesFin = sc.nextInt();
                                System.out.println("Digite el dia de fin del combo");
                                int diaFin = sc.nextInt();
                                String fechaInicio = diaInicio+"-"+mesInicio+"-"+anoInicio;
                                String fechaFin = diaFin+"-"+mesFin+"-"+"-"+anoFin;

                                String string_date = fechaInicio;
                                String string_date1 = fechaFin;

                                SimpleDateFormat f = new SimpleDateFormat("dd-mm-yyyy");
                                SimpleDateFormat ff = new SimpleDateFormat("dd-mm-yyyy");
                                try {
                                    
                                    java.util.Date d = f.parse(string_date);
                                    java.util.Date dd = ff.parse(string_date1);
                                    long milliseconds = d.getTime();
                                    long milliseconds1 = dd.getTime();

                                    java.sql.Date sqlDate = new java.sql.Date(milliseconds);
                                    java.sql.Date sqlDate1 = new java.sql.Date(milliseconds1);
                                    
     
    Combo combo = new Combo(idCombo, nombreCombo, precioCombo,unidadesCombo, productosCombo, descuentoCombo, sqlDate, sqlDate1);
    servicioCombo.insert(combo);
    servicioCombo.update("fechaInicioCombo", sqlDate, "idCombo", idCombo);
    servicioCombo.update("fechaFinCombo", sqlDate1, "idCombo", idCombo);
    System.out.println("Combo "+nombreCombo+" insertado con éxito!"); 
                                }catch (ParseException e) {
                        e.printStackTrace();}  
    
    
    }
   
   
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
         
        //this.insertarProductoAUnCombo();
        
        } else if(respuestaUsuario == 2){
            System.out.println("Digite el identificador del combo");
            int identificadorCombo = sc.nextInt();
            this.actualizarPrecioDelCombo(identificadorCombo);
        
        }
    
    }
    
    /**
     * @marco
     * Modifica toda la información del producto 
     */
    
    public void modificarTodaInformacionCombo(){
        boolean condicionSalida = true;
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite el identificador del combo que desea modificar");
        int idCombo = sc.nextInt();
        do {
        System.out.println("Qué valor desea modificar\n1-Nombre del Combo\n2-Precio del combo\n3-ID del combo\n4-Disponibilidad del combo\n5-Descuento combo\n6-Fecha de inicio del combo\n7-Fecha de fin del combo\n8-Salir");
        int respuestaUsuario = sc.nextInt();
                switch(respuestaUsuario){
                
                    case 1:
                        System.out.println("Digite el nombre del combo");
                        String nombreDelCombo = sc.next();
                        System.out.println("Desea realizar la modificación?\n1-Si\n2-No");
                        int confirmarCambio = sc.nextInt();
                        if(confirmarCambio == 1){
                        
                        servicioCombo.update("nombreCombo", nombreDelCombo, "idCombo", idCombo);} else{condicionSalida = false;}
                        System.out.println("Nombre del combo con ID: "+idCombo+" actualizado con éxito a: "+nombreDelCombo);
                            System.out.println("Desea continuar modificando este combo?\n1-Si\n2-No");
                            int continuar = sc.nextInt();
                            if(continuar ==1){
                                condicionSalida = true;
                            } else{
                            
                                condicionSalida = true;
                            }
                            break;
                        
                    case 2:
                        System.out.println("Digite el precio del combo");
                        int precioDelCombo = sc.nextInt();
                        System.out.println("Desea realizar la modificación?\n1-Si\n2-No");
                        int confirmarCambio1 = sc.nextInt();
                        if(confirmarCambio1 == 1){
                        servicioCombo.update("precioClienteCombo", precioDelCombo, "idCombo", idCombo);}else{condicionSalida = false;}
                        System.out.println("Valor del combo "+idCombo+" actualizado con éxito a: "+precioDelCombo+" colones");
                            System.out.println("Desea continuar modificando este combo?\n1-Si\n2-No");
                            int continuar2 = sc.nextInt();
                            if(continuar2 ==1){
                                condicionSalida = true;
                            } else{
                            
                                condicionSalida = true;
                            }
                        break;
                    case 3:    
                        System.out.println("Digite el ID del combo");
                        int idDelCombo = sc.nextInt();  
                        System.out.println("Desea realizar la modificación?\n1-Si\n2-No");
                        int confirmarCambio2 = sc.nextInt();
                        if(confirmarCambio2 == 1){
                        servicioCombo.update("idCombo", idDelCombo, "idCombo", idCombo);}else{condicionSalida = false;}  
                        System.out.println("Identificador del combo: "+idCombo+" actualizado con éxito a: "+idDelCombo);
                            System.out.println("Desea continuar modificando este combo?\n1-Si\n2-No");
                                int continuar3 = sc.nextInt();
                                if(continuar3 ==1){
                                    condicionSalida = true;
                                } else{

                                    condicionSalida = true;
                                }
                        break;
                    case 4:
                         System.out.println("Digite las unidades disponibles del combo");
                         int stockCombo = sc.nextInt();  
                         System.out.println("Desea realizar la modificación?\n1-Si\n2-No");
                         int confirmarCambio3 = sc.nextInt();
                         if(confirmarCambio3 == 1){
                         servicioCombo.update("contadorProductoCombo", stockCombo, "idCombo", idCombo);}else{condicionSalida = false;}  
                         System.out.println("Unidades existentes del combo: "+idCombo+" actualizadas a: "+stockCombo);
                            System.out.println("Desea continuar modificando este combo?\n1-Si\n2-No");
                               int continuar4 = sc.nextInt();
                               if(continuar4 ==1){
                                   condicionSalida = true;
                               } else{

                                   condicionSalida = true;
                               }
                        break;
                    case 5:
                         System.out.println("Digite el valor de descuento del combo");
                         double descuentoCombo = sc.nextDouble();
                         double conversionDescuentoCombo = descuentoCombo/100;
                         System.out.println("Desea realizar la modificación?\n1-Si\n2-No");
                         int confirmarCambio4 = sc.nextInt();
                         if(confirmarCambio4 == 1){
                         servicioCombo.update("descuentoCombo", conversionDescuentoCombo, "idCombo", idCombo);}else{condicionSalida = false;}
                         System.out.println("Descuento del combo: "+idCombo+" actualizado con éxito a: "+conversionDescuentoCombo+"%");
                            System.out.println("Desea continuar modificando este combo?\n1-Si\n2-No");
                               int continuar5 = sc.nextInt();
                               if(continuar5 ==1){
                                   condicionSalida = true;
                               } else{

                                   condicionSalida = true;
                               }
                        break;
                    case 6:
                        System.out.println("\nEscoja una de las siguientes opciones\n1-Fecha actual\n2-Fecha personalizada");
                        int opcionFecha = sc.nextInt();
                        if(opcionFecha == 1){
                            java.util.Date utilDate = new java.util.Date();
                            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                             System.out.println("Desea realizar la modificación?\n1-Si\n2-No");
                             int confirmarCambio5 = sc.nextInt();
                             if(confirmarCambio5 == 1){
                                servicioCombo.update("fechaInicioCombo",sqlDate , "idCombo", idCombo);}else{condicionSalida = false;} 
                                    System.out.println("");
                                        System.out.println("Desea continuar modificando este combo?\n1-Si\n2-No");
                                            int continuar6 = sc.nextInt();
                                            if(continuar6 ==1){
                                                condicionSalida = true;
                                            } else{

                                                condicionSalida = true;
                                            }
                        } else{
                            System.out.println("\nDigite el año");
                                int ano = sc.nextInt();
                                System.out.println("Digite el mes");
                                int mes = sc.nextInt();
                                System.out.println("Digite el dia");
                                int dia = sc.nextInt();
                                String fecha = dia+"-"+mes+"-"+ano;

                                String string_date = fecha;

                                SimpleDateFormat f = new SimpleDateFormat("dd-mm-yyyy");
                                try {
                                    java.util.Date d = f.parse(string_date);
                                    long milliseconds = d.getTime();

                                    java.sql.Date sqlDate1 = new java.sql.Date(milliseconds);

                                    System.out.println("Desea realizar la modificación?\n1-Si\n2-No");
                                    int confirmarCambio6 = sc.nextInt();
                                    if(confirmarCambio6 == 1){
                                    servicioCombo.update("fechaInicioCombo", sqlDate1, "idCombo", idCombo);}else{condicionSalida = false;}  
                                    System.out.println("Desea continuar modificando este combo?\n1-Si\n2-No");
                                        int continuar7 = sc.nextInt();
                                        if(continuar7 ==1){
                                            condicionSalida = true;
                                        } else{

                                            condicionSalida = true;
                                        } }catch (ParseException e) {
                                    e.printStackTrace();}  
                        
                        }
    
                        break;
                    case 7:
                        System.out.println("Digite el año");
                        int ano = sc.nextInt();
                        System.out.println("Digite el mes");
                        int mes = sc.nextInt();
                        System.out.println("Digite el dia");
                        int dia = sc.nextInt();
                        String fecha = dia+"-"+mes+"-"+ano;
                        
                        String string_date = fecha;

                    SimpleDateFormat f = new SimpleDateFormat("dd-mm-yyyy");
                    try {
                        java.util.Date d = f.parse(string_date);
                        long milliseconds = d.getTime();
                        
                        java.sql.Date sqlDate1 = new java.sql.Date(milliseconds);
                        System.out.println("Desea realizar la modificación?\n1-Si\n2-No");
                                    int confirmarCambio7 = sc.nextInt();
                                    if(confirmarCambio7 == 1){
                        servicioCombo.update("fechaFinCombo", sqlDate1, "idCombo", idCombo);  }else{condicionSalida = false;}
                        System.out.println("Desea continuar modificando este combo?\n1-Si\n2-No");
                            int continuar7 = sc.nextInt();
                            if(continuar7 ==1){
                                condicionSalida = true;
                            } else{
                            
                                condicionSalida = true;
                            } }catch (ParseException e) {
                        e.printStackTrace();}  
                        break;
                    case 8: 
                        condicionSalida = false;
                        break; 
                    
                    case 9:
                    
                        
                    break;}
                        
     //UPDATE Combo SET precioClienteCombo = 5000 WHERE idCombo = 1;
                }while(condicionSalida == true);
    
    }
    
    /**
     * @marco
     * Submenú para ver los combos
     */
    
    public void verCombos(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Elija una de las siguintes opciones\n1-Ver combos actuales\n2-Ver combos pasados\n3-Ver combos futuros");
        int combos = sc.nextInt();
        
        String fechaInicio = servicioCombo.select("fechaInicioCombo", "idCombo", 100);
        
        //"SELECT " + queBuscamos + " FROM Combo WHERE " + queColumna + " = ?;";
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
    public void mostrarCombos(int tipoUsuario){
        for(Combo c:servicioCombo.selectTodosLosCombos()){//recorre una lista de todos los combos
           System.out.println("Id Combo: "+c.getIdCombo()+" | Nombre Combo: "+c.getNombreCombo());//imprime el id y nombre del combo actual
           for(String idProducto:servicioComboHasProducto.selectListIdProductos("Combo_idCombo", c.getIdCombo())){//recorre una lista de productos que esten en el combo actual
               ArrayList<Object> prodList= this.servicioProducto.selectAll("idProducto", idProducto);//agarra la info del producto actual en el recorrdio de productos en el combo actual
                   Producto prod = ((Producto)prodList.get(0));
                   System.out.println("   "+prod.toString(tipoUsuario));//imprime el producto actual dentro del combo actual
               
           }
       }
        
    }
    
    /**
     * @marco
     * Despliega toda la información del combo selecionado
     */
    
    public void desplegarInformacionCombo(){
        boolean condicionSalida = true;
        do{
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite el identificador del combo");
        int idCombo = sc.nextInt();
        
            System.out.println(servicioCombo.selectAllInfoCombo("idCombo",idCombo));
            System.out.println("\nDesea buscar la información de otro combo?\n1-Si\n2-No");
            int respuestaUsuario = sc.nextInt();
            if(respuestaUsuario == 1){
            
                condicionSalida = true;
            } else{condicionSalida = false;}
            
        }while(condicionSalida == true);
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
    
    public void updateStock(int cantidadCompra, Combo combo)
    {
        
        int stockAntesDePedido = Integer.parseInt(this.servicioCombo.select("contadorOfertaCombo", "idCombo", combo.getIdCombo()));//agarra el stock actual en la base de datos
        int stockDespuesDePedido = stockAntesDePedido + cantidadCompra;//le suma el MontoCompra al stock actual
        this.servicioCombo.update("contadorOfertaCombo", stockDespuesDePedido, "idCombo", combo.getIdCombo());//actualiza la DB con el nuevo stock sumado
        
    }

    @Override
    public void setMediador(Mediador mediador) {
        this.mediador = (OrdenMediador)mediador;
    }
    
}
