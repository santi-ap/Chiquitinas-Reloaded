    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import items.Producto;
import java.util.ArrayList;
import java.util.Scanner;
import mediador.*;
import observer.*;
import servicios.*;

/**
 *
 * @author santialfonso
 */
public class ControllerProducto extends ControllerFactory implements Colleague, Subject {

    // ---------------------------------------- HAY QUE HACER UN CASTING AQUI PARA PODER IMPLEMENTAR EL PATRON FACTORY
    private ServicioProducto servicioProducto = ((ServicioProducto) this.CrearServicio());//CASTING DE Servcio A ServicioProducto
    private PedidoMediador mediador;
    private Producto productoPedido;
    private ServicioComboHasProducto servComboHasProd = new ServicioComboHasProducto();
    private Observer observer;
    private Scanner input = new Scanner(System.in);
    private String idProveedorForMediador;
    private OrdenMediador ordenMediador;
    private int montoCompra = 20;

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
        this.mediador = (PedidoMediador)mediador;
    }

    public void setOrdenMediador(Mediador mediador)
    {
        this.ordenMediador = (OrdenMediador)mediador;
    }
    public Observer getObserver() {
        return observer;
    }

    public void setObserver(Observer observer) {
        this.observer = observer;
    }

    @Override
    public void registrarObserver(Observer observer) {
        this.setObserver(observer);
    }

    @Override
    public void removerObserver(Observer observer) {
        this.setObserver(null);
    }

    @Override
    public void notificarObserver(Producto producto) {
        this.getObserver().updateObserver(producto);
    }

    public void buscarProductoPorNombre() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nIngese el nombre del producto que desea buscar: ");
        String nombreProductoDeseado = getInput().nextLine();
        ArrayList<Object> listaDetallesProducto = this.servicioProducto.selectAll("nombreProducto", nombreProductoDeseado);
        if (!(listaDetallesProducto.get(0).equals("No Existe el Producto"))) {
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n" + (Producto) listaDetallesProducto.get(0));
        } else {
            System.out.println("No Existe el Producto\n\n\n\n");
        }
    }

    /**
     * Metodo para imprimir la lista de productos de un proveedor
     */
    public void getProductoForMenuByProv() {
        //retarded.
        System.out.println("\nINSERTE EL ID DEL PROVEEDOR");
        idProveedorForMediador = getInput().nextLine();
        ArrayList<Object> listaProductos = servicioProducto.selectAll("Proveedor_idProveedor", idProveedorForMediador);

        for (Object o : listaProductos) {
            System.out.println("ID PRODUCTO: " + ((Producto) o).getIdProducto() + "|| PRODUCTO: " + ((Producto) o).getNombreProducto() + "|| PRECIO CLIENTE: c" + ((Producto) o).getPrecioProductoCliente()
                    + "|| PRECIO PROVEEDOR: " + ((Producto) o).getPrecioProductoProveedor());
        }
        mediador.opeGetProductoSeleccionado();
    }

    public void printTodosLosProductos(int tipoUsuario) {//imprime todos los productos
        for (Producto p : servicioProducto.selectTodosLosProductos()) {
            System.out.println(p.toString(tipoUsuario));
        }
    }

    public void modidificarNombreProducto(int idProducto) {
        System.out.println("Ingrese el nuevo nombre del producto: ");
        String nombreProdNuevo = getInput().nextLine();
        servicioProducto.update("nombreProducto", nombreProdNuevo, "idProducto", idProducto);
    }

    public void modidificarPrecioProducto(int idProducto) {
        System.out.println("Ingrese el nuevo precio del producto: ");
        String precioClienteProductoNuevo = getInput().nextLine();
        servicioProducto.update("precioClienteProducto", precioClienteProductoNuevo, "idProducto", idProducto);
    }

    public void modidificarStockMinProducto(int idProducto) {
        System.out.println("Ingrese el nuevo stock minimo del producto: ");
        String stockMinProductoNuevo = getInput().nextLine();
        servicioProducto.update("stockMinProducto", stockMinProductoNuevo, "idProducto", idProducto);
    }

    /*
    @marco
    Busca por un producto utilizando su identificador único(primaryKey)
     */
    public void buscarProductoId(int idProducto) {

        System.out.println(servicioProducto.selectAll("idProducto", idProducto));
    }

    /*
    @marco
    Este método es idéntico al mñetodo buscarProductoId excepto que retorna el descuento. 
    Me gustaría evacuar una duda antes de eliminarlo o implementarlo
     */
    public void buscarProductoReturnDescuento(int idProducto) {
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();
        servicioProducto.select("descuentoPromo", "idProducto", idProducto);
    }

    /*
    @marco
    Definir el descuento del producto e insertarlo a la base de datos
     */
    public void descuento(double cantidadDescuento) {
        Scanner sc = new Scanner(System.in);
        boolean control = true;
        do {
            System.out.println("Digite el ID del producto al que desea modificar la promoción");
            int idProducto = sc.nextInt();
            double calculo = cantidadDescuento / 100;
            //No estaba seguro si la DB acepta decimales, entonces tuve que hacer conversión a String
            String conversion = Double.toString(calculo);
            //Este el task de taiga que me asignaron para aceptar cambios
            System.out.println("Desea confirmar cambios a la promoción\n1-Sí\n2-No");
            int confirmarCambios = getInput().nextInt();

            if (confirmarCambios == 1) {
                servicioProducto.update("descuentoPromo", conversion, "idProducto", idProducto);
                System.out.println("Modificación exitosa");
                System.out.println("Desea continuar agregando promociones?\n1-Sí\n2-No");
                int continuar = getInput().nextInt();
                if (continuar == 1) {

                    control = true;
                } else {

                    control = false;
                }

            } else {
                control = false;
                break;

            }

        } while (control == true);

    }

    public int menuPromos() {

        System.out.println("1-Crear promoción\n2-Modificar promoción\n3-Buscar promoción\n4-Ver promoción\n5-Menú principal");
        int userInput = getInput().nextInt();
        return userInput;
    }

    public double descuentoUsuario() {

        System.out.println("Digite el monto del descuento");
        double descuento = getInput().nextDouble();
        return descuento;

    }

//    public void buscarProductosConDescuento(){
//    
//        System.out.println(servicioProducto.buscarProductoConDescuento());
//    
//    }
    public int idUsuario() {

        System.out.println("Digite el identificador único del producto");
        int idProducto = getInput().nextInt();
        return idProducto;

    }

    public void buscarProductosConDescuento() {

       servicioProducto.buscarProductosDescuento();
    }

    public void getDatosForMenuProducto(String id) {
        ArrayList<Object> listaProductos = servicioProducto.selectAll("Proveedor_idProveedor", id);

        for (Object o : listaProductos) {

            System.out.println("ID PRODUCTO: " + ((Producto) o).getIdProducto() + "|| PRODUCTO: " + ((Producto) o).getNombreProducto() + "|| PRECIO CLIENTE: c" + ((Producto) o).getPrecioProductoCliente()
                    + "|| PRECIO PROVEEDOR: " + ((Producto) o).getPrecioProductoProveedor());

        }

    }

    public void borrarProducto() {
        this.printTodosLosProductos(0);
        System.out.println("Seleccione el id del producto que desea eliminar: ");
        String idProducto = getInput().nextLine();
        servicioProducto.delete("idProducto", idProducto);
        this.servComboHasProd.delete("Producto_idProducto", idProducto);
        this.getServComboHasProd().delete("Producto_idProducto", idProducto);

    }

    /**
     * Este metodo se utiliza para seleccionar el producto e imprime el producto
     * para mostrar el que se selecciono
     */
    public void getProductoById() {
        //the
        System.out.println("\nINSERTE EL ID DEL PRODUCTO");
        String id = getInput().nextLine();
        ArrayList<Object> listaProductos = servicioProducto.selectAll("idProducto", id);
        for (Object o : listaProductos) {
            this.setProductoPedido((Producto) o);//This info will be sent to controller pedido by the mediator

            System.out.println("ID PRODUCTO: " + ((Producto) o).getIdProducto() + "|| PRODUCTO: " + ((Producto) o).getNombreProducto() + "|| PRECIO CLIENTE: c" + ((Producto) o).getPrecioProductoCliente()
                    + "|| PRECIO PROVEEDOR: " + ((Producto) o).getPrecioProductoProveedor());

        }
        mediador.opeSetMontoOrden(id);
    }

    /**
     * Este metodo toma el la cantidad del producto que se desae comprar y
     * actualiza el stock
     *
     * @param idProducto
     */
    public void actualizarStock(String idProducto) {
        //Fuck!
        System.out.println("\nINSERTE EL MONTO QUE DESEA COMPRAR");
        montoCompra = Integer.parseInt(getInput().nextLine());
        int stockActual = Integer.parseInt(servicioProducto.select("contadorProducto", "idProducto", idProducto));
        String stockNuevo = Integer.toString(stockActual + montoCompra);
        System.out.println("Desea comprar hacer la comprar s/n");
        String aceptar = getInput().nextLine();

        if (aceptar.equalsIgnoreCase("s")) {
            servicioProducto.update("contadorProducto", stockNuevo, "idProducto", idProducto);
        } else {

        }
        ((PedidoMediador) mediador).takeProductoFromControllerProducto();

    }

    public void crearProducto() {
        System.out.println("Inserte el ID del proveedor");
        this.idProveedorForMediador = input.nextLine();

        System.out.println("Inserte el ID para el producto");
        int idProducto = Integer.parseInt(input.nextLine());

        System.out.println("Inserte el nombre del producto");
        String nombrePorducto = input.nextLine();

        System.out.println("Inserte el precio para el cliente");
        double precioCliente = Double.parseDouble(input.nextLine());

        System.out.println("Inserte el stock minimo para el producto");
        int stockMinimo = Integer.parseInt(input.nextLine());

        System.out.println("Inserte la cantidad que desea ordenar");
        int montoOrden = Integer.parseInt(input.nextLine());

        System.out.println("Inserte la categoria del producto");
        String categoria = input.nextLine();

        System.out.println("Inserte el precio del proveedor");
        double precioProveedor = Double.parseDouble(input.nextLine());

        productoPedido = new Producto(idProducto, nombrePorducto, precioCliente, precioProveedor, stockMinimo, montoOrden, categoria, Integer.parseInt(this.idProveedorForMediador));

        servicioProducto.insert(productoPedido);
        ((PedidoMediador) mediador).sendProductoToControllerPedido(productoPedido);

    }

    /**
     * @return the input
     */
    public Scanner getInput() {
        return input;
    }

    /**
     * @param input the input to set
     */
    public void setInput(Scanner input) {
        this.input = input;
    }

    /**
     * @return the productoPedido
     */
    public Producto getProductoPedido() {
        return productoPedido;
    }

    /**
     * @param productoPedido the productoPedido to set
     */
    public void setProductoPedido(Producto productoPedido) {
        this.productoPedido = productoPedido;
    }

    /**
     * @return the servComboHasProd
     */
    public ServicioComboHasProducto getServComboHasProd() {
        return servComboHasProd;
    }

    /**
     * @param servComboHasProd the servComboHasProd to set
     */
    public void setServComboHasProd(ServicioComboHasProducto servComboHasProd) {
        this.servComboHasProd = servComboHasProd;
    }

    public void getIdProveedorPedidobyMediador() {
        ((PedidoMediador) mediador).sendProveedorIdToControllerPedido(this.idProveedorForMediador);

    }

    /**
     * @return the montoCompra
     */
    public int getMontoCompra() {
        return montoCompra;
    }

    /**
     * @param montoCompra the montoCompra to set
     */
    public void setMontoCompra(int montoCompra) {
        this.montoCompra = montoCompra;
    }

    /**
     * Metodo para actualizar el stock de un producto despues de que un cliente
     * haya hecho una orden. En este metodo tambien se ejecuta el observer
     * pattern para ordenar mas del producto al proveedor si baja o iguala el
     * stock minimo
     *
     * @param producto Este producto deberia ser la insancia del producto que se
     * esta comprando
     */
    public void updateStockDespuesDeOrden(Producto producto) {
        //do logic to update the stock after an order from a client
        int cant = producto.getCantidadActualProducto() * -1;
        this.productoPedido = producto;
        actualizarStockDespuesDeCompra(cant);
        //then logic for observer pattern to order more from the provider if need be
        //if actual stock is equal to or lesser than minStock, it should initiate observer pattern to order more from proveedor
        if (producto.getCantidadActualProducto() <= producto.getStockMinimoProducto()) {//this is problematic, the info about the stockminimo is never sent and it isn't part of the product, especially for combos (Do a select from the db instead)
            Observer controllerPedidoObserver = new ControllerPedido(this);//instanciamos un nuevo observer 
            controllerPedidoObserver.suscribeObserver();//vinculamos el observer con el object
            this.notificarObserver(producto);//le dice al observer que haga un nuevo pedido del mismo producto
            controllerPedidoObserver.unSubscribeObserver();//rompemos el vinculo del observer con el object
        }
    }

    public String getIdProveedorForMediador() {
        return idProveedorForMediador;
    }

    public void setIdProveedorForMediador(String idProveedorForMediador) {
        this.idProveedorForMediador = idProveedorForMediador;
    }

    /**
     * le suma el MontoCompra(que es 20 por default) al stock actual del
     * producto en la base de datos y lo actualiza.
     * @param cantidadPedido la cantidad que se desea pedir
     */
    public void actualizarStockDespuesDeCompra(int cantidadCompra) {
        if (cantidadCompra != 0) {//si la cantidad es diferente de cero, entonces le suma la cantidad ingresada por el usuario
            this.setMontoCompra(cantidadCompra);//se cambia el MontoCompra a la cantidad ingresada por el usuario
            int stockAntesDePedido = Integer.parseInt(this.servicioProducto.select("contadorProducto", "idProducto", this.getProductoPedido().getIdProducto()));//agarra el stock actual en la base de datos
            int stockDespuesDePedido = stockAntesDePedido + this.getMontoCompra();//le suma el MontoCompra al stock actual
            this.servicioProducto.update("contadorProducto", stockDespuesDePedido, "idProducto", this.getProductoPedido().getIdProducto());//actualiza la DB con el nuevo stock sumado
        } else {//si la cantidad es igual a cero, entonces se pide el default que es 20
            int stockAntesDePedido = Integer.parseInt(this.servicioProducto.select("contadorProducto", "idProducto", this.getProductoPedido().getIdProducto()));//agarra el stock actual en la base de datos
            int stockDespuesDePedido = stockAntesDePedido + this.getMontoCompra();//le suma el MontoCompra al stock actual
            this.servicioProducto.update("contadorProducto", stockDespuesDePedido, "idProducto", this.getProductoPedido().getIdProducto());//actualiza la DB con el nuevo stock sumado
        }
    }
    
    

}
