/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import items.Carrito;
import items.Combo;
import items.Producto;
import items.Combo;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import mediador.Colleague;
import mediador.Mediador;
import mediador.OrdenMediador;
import objetos.Usuario;
import servicios.*;

/**
 *
 * @author Asus
 */
public class ControllerCarrito extends ControllerFactory implements Colleague {

    private ServicioCarrito servicioCarrito = ((ServicioCarrito) this.CrearServicio());
    private ServicioComboHasProducto servicioComboProd = new ServicioComboHasProducto();
    private ServicioProducto servicioProducto = new ServicioProducto();
    private ServicioCombo servicioCombo = new ServicioCombo();
    private ControllerCombo controllerCombo = new ControllerCombo();
    private OrdenMediador ordenMediador;
    private ArrayList<Object> listaProductos = new ArrayList<Object>();
    private ArrayList<Object> listaCombos = new ArrayList<Object>();
    Scanner input = new Scanner(System.in);

    private double totalCarrito = 0;
    private double descuentoTotal = 0;
    private double totalFinal = 0;

    public double getTotalFinal() {
        return totalFinal;
    }

    public ControllerCarrito() {
    }

    public void lookForCarrito(Usuario usuario) {
        this.setListaProductos(this.servicioCarrito.selectAllProductsOfCarrito(usuario.getIdUsuario()));
        this.setListaCombos(this.servicioCarrito.selectAllCombosOfCarrito(usuario.getIdUsuario()));
    }

    public double getTotalCarrito() {
        return totalCarrito;
    }

    @Override
    public Servicio CrearServicio() {
        return new ServicioCarrito();
    }

    @Override
    public void setMediador(Mediador mediador) {
        this.ordenMediador = (OrdenMediador) mediador;
    }

    /**
     * @return the listaProductos
     */
    public ArrayList<Object> getListaProductos() {
        return listaProductos;
    }

    /**
     * @param listaProductos the listaProductos to set
     */
    public void setListaProductos(ArrayList<Object> listaProductos) {
        this.listaProductos = listaProductos;
    }

    /**
     * @return the listaCombos
     */
    public ArrayList<Object> getListaCombos() {
        return listaCombos;
    }

    /**
     * @param listaCombos the listaCombos to set
     */
    public void setListaCombos(ArrayList<Object> listaCombos) {
        this.listaCombos = listaCombos;
    }

    public ArrayList<Object> setListaProductosCarrito(Usuario usuario) {
        return this.servicioCarrito.selectAllProductsOfCarrito(usuario);
    }

    public void printListaProductos(Usuario usuario) {

        this.setTotalCarrito(0);
        this.setDescuentoTotal(0);
        this.setTotalFinal(0);

        double total = 0;
        double descuento = 0;
        for (Object p : this.servicioCarrito.selectAllProductsOfCarrito(usuario.getIdUsuario())) {
            System.out.println("ID: " + ((Producto) p).getIdProducto() + " " + ((Producto) p).getNombreProducto() + "  ₡" + ((Producto) p).getPrecioProductoCliente()
                    + "  x" + this.servicioCarrito.selectMontoProducto(usuario.getIdUsuario(), Integer.toString(((Producto) p).getIdProducto())));

            total = total + ((Producto) p).getPrecioProductoCliente() * ((Producto) p).getCantidadActualProducto();

            if (usuario.getTipoUsuario() == 2) {// se tomo tipo de usuario para determinar si se aplica la promo
                descuento = descuento + ((Producto) p).getPrecioProductoCliente() * ((Producto) p).getDescuentoProductoPromo() * Double.parseDouble(this.servicioCarrito.selectMontoProducto(usuario.getIdUsuario(), Integer.toString(((Producto) p).getIdProducto())));
            }
        }

        System.out.println("Total Productos: ₡" + total);
        this.setTotalCarrito(totalCarrito + total);
        this.setDescuentoTotal(descuento);
        this.setTotalFinal(totalFinal + totalCarrito - getDescuentoTotal());

    }

    public double getDescuento() {
        return getDescuentoTotal();
    }

    public void printListaCombos(Usuario usuario) {

        for (Object c : this.servicioCarrito.selectAllCombosOfCarrito(usuario.getIdUsuario())) {//recorre una lista de todos los combos
            System.out.println("ID: " + ((Combo) c).getIdCombo() + " | Nombre Combo: " + ((Combo) c).getNombreCombo() + " x" + this.servicioCarrito.selectMontoCombo(usuario.getIdUsuario(), Integer.toString(((Combo) c).getIdCombo())));//imprime el id y nombre del combo actual
            for (String idProducto : servicioComboProd.selectListIdProductos("Combo_idCombo", ((Combo) c).getIdCombo())) {//recorre una lista de productos que esten en el combo actual
                ArrayList<Object> prodList = this.servicioProducto.selectAll("idProducto", idProducto);//agarra la info del producto actual en el recorrdio de productos en el combo actual
                Producto prod = ((Producto) prodList.get(0));
                System.out.println("   " + prod.toString(1));//imprime el producto actual dentro del combo actual

            }
            System.out.println("Total combo: ₡" + Double.parseDouble(servicioCombo.select("precioClienteCombo", "idCombo", ((Combo) c).getIdCombo()))*Double.parseDouble(this.servicioCarrito.selectMontoCombo(usuario.getIdUsuario(), Integer.toString(((Combo) c).getIdCombo()))));
            this.setTotalCarrito(totalCarrito + Double.parseDouble((servicioCombo.select("precioClienteCombo", "idCombo", ((Combo) c).getIdCombo()))) * ((Combo) c).getCantidadActualProductoCombo());
            this.setTotalFinal(totalFinal + Double.parseDouble(servicioCombo.select("precioClienteCombo", "idCombo", ((Combo) c).getIdCombo())) * ((Combo) c).getCantidadActualProductoCombo());
        }
    }

    public void cambiarMontoProducto(Usuario usuario) {// metodo para cambiar la cantidad de un producto en el carrito, si el monto es cero, lo remueve del carrito
        System.out.println("Inserte el ID del producto");
        int productID = Integer.parseInt(input.nextLine());
        System.out.println("Inserte el monto que desea comprar");
        int monto = Integer.parseInt(input.nextLine());

        if (monto == 0) {
            this.servicioCarrito.deleteProducto(usuario.getIdUsuario(), productID);
        } else {
            Producto producto = servicioProducto.selectProducto("idProducto", productID);//busca el combo en el DB con el ID escogido por el user
            if (monto > producto.getCantidadActualProducto()) {//si escoge una cantidad mayor de lo que hay
                System.out.println("No se puede pedir mas combos de los que hay");
                return;
            } else {
                this.servicioCarrito.update("MontoProducto", monto, "Usuario_idUsuario", usuario.getIdUsuario());
            }
        }
    }

    public void cambiarMontoCombo(Usuario usuario) {//metodo para cambiar la cantidad de un producto en el carrito, si el monto es cero, lo remueve del carrito
        System.out.println("Inserte el ID del combo");
        int comboID = Integer.parseInt(input.nextLine());
        System.out.println("Inserte el monto que desea comprar");
        int monto = Integer.parseInt(input.nextLine());
        if (monto == 0) {
            this.servicioCarrito.deleteCombo(usuario.getIdUsuario(), comboID);
        } else {
            Combo combo = servicioCombo.selectCombo("idCombo", comboID);//busca el combo en el DB con el ID escogido por el user
            if (monto > combo.getCantidadOfertaCombo()) {//si escoge una cantidad mayor de lo que hay
                System.out.println("No se puede pedir mas combos de los que hay");
                return;
            } else {
                this.servicioCarrito.updateCombo("MontoCombo", monto, "Usuario_idUsuario", usuario.getIdUsuario(),"Combo_idCombo",comboID);
            }

        }

    }

    /**
     * metodo para agregar un producto al carrito
     *
     * @param usuario para asociar el carrito con el usuario ingresado
     */
    public void agregarProductoCarrito(Usuario usuario) {
        System.out.println("\n\nIngrese un ID para agregarlo al carrito u oprima 's' para ir atras");//primero le pregunta al user que ingrese un ID u oprima 's' para salir
        String idProducto = input.nextLine();
        if (idProducto.equalsIgnoreCase("s")) {//si es 's', se devuelve al menu anterior
            return;
        }
        System.out.println("\n\nIngrese la cantidad que desea agregar al carrito");//pide ingresar la cantidad deseada
        String cantidadProducto = input.nextLine();
        Producto producto = ((Producto) servicioProducto.selectAll("idProducto", idProducto).get(0));//busca el producto en el DB con el ID escogido por el user
        System.out.println("\n\nSeguro que desea agregar " + cantidadProducto + " de " + producto.getNombreProducto() + "?\n1-Si\n2-No");//le pide al user confirmar si lo quiere agregar al carrito
        String confirmacion = input.nextLine();
        if (confirmacion.equals("1")) {
            if (Integer.parseInt(cantidadProducto) > producto.getCantidadActualProducto()) {//si escoge una cantidad mayor de lo que hay
                System.out.println("No se puede pedir mas combos de los que hay");
                return;
            } else {
            servicioCarrito.insertProductCarrito(usuario.getIdUsuario(), idProducto, cantidadProducto);//si dice que si, entonces lo agrega al carrito
            }
        }
    }

    /**
     * metodo para agregar un combo al carrito
     *
     * @param usuario para asociar el carrito con el usuario ingresado
     */
    public void agregarComboCarrito(Usuario usuario) {
        System.out.println("\n\nIngrese un ID para agregarlo al carrito u oprima 's' para ir atras");//primero le pregunta al user que ingrese un ID u oprima 's' para salir
        String idCombo = input.nextLine();
        if (idCombo.equalsIgnoreCase("s")) {//si es 's', se devuelve al menu anterior
            return;
        }
        System.out.println("\n\nIngrese la cantidad que desea agregar al carrito");//pide ingresar la cantidad deseada
        String cantidadCombo = input.nextLine();
        Combo combo = servicioCombo.selectCombo("idCombo", idCombo);//busca el combo en el DB con el ID escogido por el user
        if (Integer.parseInt(cantidadCombo) > combo.getCantidadOfertaCombo()) {
            System.out.println("No se puede pedir mas combos de los que hay");
        } else {
            System.out.println("\n\nSeguro que desea agregar " + cantidadCombo + " de " + combo.getNombreCombo() + "?\n1-Si\n2-No");//le pide al user confirmar si lo quiere agregar al carrito
            String confirmacion = input.nextLine();
            if (confirmacion.equals("1")) {
                servicioCarrito.insertComboCarrito(usuario.getIdUsuario(), idCombo, cantidadCombo);//si dice que si, entonces lo agrega al carrito
            }
        }
    }

    /**
     * @param totalCarrito the totalCarrito to set
     */
    public void setTotalCarrito(double totalCarrito) {
        this.totalCarrito = totalCarrito;
    }

    /**
     * @return the descuentoTotal
     */
    public double getDescuentoTotal() {
        return descuentoTotal;
    }

    /**
     * @param descuentoTotal the descuentoTotal to set
     */
    public void setDescuentoTotal(double descuentoTotal) {
        this.descuentoTotal = descuentoTotal;
    }

    /**
     * @param totalFinal the totalFinal to set
     */
    public void setTotalFinal(double totalFinal) {
        this.totalFinal = totalFinal;
    }

}
