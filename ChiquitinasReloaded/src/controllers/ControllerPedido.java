/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import items.Pedido;
import items.Producto;
import java.io.Serializable;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import mediador.*;
import objetos.Proveedor;
import observer.*;
import servicios.*;
import org.apache.commons.mail.*;
import servicios.ServicioProveedor;

/**
 *
 * @author santialfonso
 */
public class ControllerPedido extends ControllerFactory implements Observer, Colleague,Serializable {

    // ---------------------------------- HAY QUE HACER UN CASTING AQUI PARA PODER IMPLEMENTAR EL PATRON FACTORY
    private ServicioPedido servicioPedido = ((ServicioPedido) this.CrearServicio());//CASTING DE Servcio A ServicioPedido
    private ServicioPedidoHasProducto servicioPedidoHasProducto = new ServicioPedidoHasProducto();
    private ServicioPedidoHasProveedor servicioPedidoHasProveedor = new ServicioPedidoHasProveedor();
    private Mediador mediador;
    private Producto productoPedido;
    private Pedido pedidoByMediador;
    private Proveedor proveedorPedido;
    private final Subject subject;
    private Pedido nuevoPedido;
    private String idProveedorPedido;
    private int montoCompra;

    public ControllerPedido() {
        this.subject=null;
    }
    
    //constructor para aplicar el observer pattern
    public ControllerPedido(Subject subject){
        this.subject=subject;
    }

    public ServicioPedido getServicioPedido() {
        return servicioPedido;
    }

    public void setServicioPedido(ServicioPedido servicioPedido) {
        this.servicioPedido = servicioPedido;
    }

    /**
     * METODO NECESARIO PARA IMPLEMENTAR FACTORY
     *
     * @return un nuevo ServicioPedido
     */
    @Override
    public Servicio CrearServicio() {
        return new ServicioPedido();
    }
    
    /**
     * para pedir un producto existente de forma automatica a un proveedor
     * parte del observer pattern
     * @param producto es el producto del cual deberiamos pedirle mas al proveedor
     */
    @Override
    public void updateObserver(Producto producto) {
        ControllerProducto controllerProducto = new ControllerProducto();
        ControllerProveedor controllerProveedor = new ControllerProveedor();
        PedidoMediador pedidoMediador = new PedidoMediador(this,controllerProveedor,controllerProducto);
        this.setMediador(pedidoMediador);
        controllerProducto.setMediador(pedidoMediador);
        controllerProveedor.setMediador(pedidoMediador);
        controllerProducto.setIdProveedorForMediador(String.valueOf(producto.getIdProveedorProducto()));
        controllerProducto.setProductoPedido(producto);
        pedidoMediador.sendProductoToControllerPedido(producto);
        pedidoMediador.sumarleLoPedidoAlStock(0);
    }
    
    /**
     * metodo para vincular al observer con el subject
     */
    @Override
    public void suscribeObserver(){
        this.subject.registrarObserver(this);
    }
    
    /**
     * metodo para romper vinculo entre observer y subject
     */
    @Override
    public void unSubscribeObserver(){
        this.subject.removerObserver(this);
    }

    @Override
    public void setMediador(Mediador mediador) {
        this.mediador = mediador;
    }

    public void setProductoPedidoByMediador(Producto productoPedido) {
        this.setProductoPedido(productoPedido);
        ((PedidoMediador)mediador).takeProveedorFromControllerProveedor();
    }

    public void setIdProveedorPedidoByMediador(String idProveedor) {
        this.setIdProveedorPedido(idProveedor);
        ((PedidoMediador) mediador).createPedidoWithAmmount();
    }

    public void createPedido() {
        this.nuevoPedido = new Pedido();
        this.nuevoPedido.setIdPedido(this.servicioPedido.selectMaxId());
        this.getProductoPedido().setItemDecorado(this.nuevoPedido);
        this.nuevoPedido.setTotalPedido(this.getProductoPedido().getPrecio());
        this.nuevoPedido.setFechaPedido(new Date(System.currentTimeMillis()));
        ((PedidoMediador)mediador).insertIntoPedido();
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
     * @return the pedidoByMediador
     */
    public Pedido getPedidoByMediador() {
        return nuevoPedido;
    }


    /**
     * @return the proveedorPedido
     */
    public String getIdProveedorPedido() {
        return idProveedorPedido;
    }

    /**
     * @param idProveedorPedido the proveedorPedido to set
     */
    public void setProveedorPedido(String idProveedorPedido) {
        this.setIdProveedorPedido(idProveedorPedido);
    }

    /**
     * @return the servicioPedidoHasProducto
     */
    public ServicioPedidoHasProducto getServicioPedidoHasProducto() {
        return servicioPedidoHasProducto;
    }

    /**
     * @param servicioPedidoHasProducto the servicioPedidoHasProducto to set
     */
    public void setServicioPedidoHasProducto(ServicioPedidoHasProducto servicioPedidoHasProducto) {
        this.servicioPedidoHasProducto = servicioPedidoHasProducto;
    }

    /**
     * @return the servicioPedidoHasProveedor
     */
    public ServicioPedidoHasProveedor getServicioPedidoHasProveedor() {
        return servicioPedidoHasProveedor;
    }

    /**
     * @param servicioPedidoHasProveedor the servicioPedidoHasProveedor to set
     */
    public void setServicioPedidoHasProveedor(ServicioPedidoHasProveedor servicioPedidoHasProveedor) {
        this.servicioPedidoHasProveedor = servicioPedidoHasProveedor;
    }

    /**
     * @param idProveedorPedido the idProveedorPedido to set
     */
    public void setIdProveedorPedido(String idProveedorPedido) {
        this.idProveedorPedido = idProveedorPedido;
    }
    /**
     * this method sends the email, once everything has been set
     */
    public void enviarCorreo() {
        try {
            this.sendSimpleEmail();
        } catch (Exception ex) {
            Logger.getLogger(ControllerPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
       }

    /**
     * this method is used to insert the new pedido in the intermediate table PedidoHasProducto
     */
    public void insertIntoPedidoHasProducto(){
        this.getServicioPedidoHasProducto().insert(this.getNuevoPedido().getIdPedido() + "," + this.getProductoPedido().getIdProducto());
        ((PedidoMediador)this.mediador).insertIntoPedidoHasProveedor();
    }

    /**
     * this method is used to insert the new pedido in the intermediate table PedidoHasProveedor
     */
    public void insertIntoPedidoHasProveedor(){
        this.getServicioPedidoHasProveedor().insert(this.getNuevoPedido().getIdPedido() + "," + this.getIdProveedorPedido());
        ((PedidoMediador)this.mediador).enviarCorreo();
    }

    /**
     * @return the nuevoPedido
     */
    public Pedido getNuevoPedido() {
        return nuevoPedido;
    }

    /**
     * @param nuevoPedido the nuevoPedido to set
     */
    public void setNuevoPedido(Pedido nuevoPedido) {
        this.nuevoPedido = nuevoPedido;
    }
    
    /**   
	 * 
	 */ 
	private static final long serialVersionUID = 1L;

	private static void setConfigSettings(Email email) throws Exception {
		
		email.setSmtpPort(587);
		email.setCharset("UTF-8");
		email.setDebug(false);
		email.setHostName("smtp.gmail.com");
		email.getMailSession().getProperties().put("mail.smtps.auth", "true");
		email.getMailSession().getProperties().put("mail.debug", "true");
		email.getMailSession().getProperties().put("mail.smtps.port", "587");
		email.getMailSession().getProperties().put("mail.smtps.socketFactory.port", "587");
		email.getMailSession()
				.getProperties()
				.put("mail.smtps.socketFactory.class",
						"javax.net.ssl.SSLSocketFactory");
		email.getMailSession().getProperties()
				.put("mail.smtps.socketFactory.fallback", "false");
		email.getMailSession().getProperties()
				.put("mail.smtp.starttls.enable", "true");
	}

	private static void setAuthSettings(Email email) {
		String authuser = "diariofacil5@gmail.com";
		String authpwd = "d1s2t3w4";
		email.setAuthenticator(new DefaultAuthenticator(authuser, authpwd));
	}
        
         
	private static void setEmailInfo(Email email, String subject, String body)
			throws Exception {
		email.setFrom("diariofacil5@gmail.com", subject);
		email.setSubject(subject);
		email.setMsg(body);
	}

	public void sendSimpleEmail() throws Exception,InterruptedException {
		Email email = new SimpleEmail();
		setAuthSettings(email);
		setConfigSettings(email);
                String subject = "Order #" + this.getNuevoPedido().getIdPedido();
		setEmailInfo(email, subject, "Notificacion automatica - NO responder este correo.\n\n Estimado(a), \n\nEste correo detalla la siguiente orden: \n"+this.getProductoPedido().getRecibo());
		ServicioProveedor sp = new ServicioProveedor();
                
                email.addTo(sp.select("correoProveedor", "idProveedor", this.idProveedorPedido), sp.select("nombreProveedor", "idProveedor", this.idProveedorPedido)); /*I know I'm sorry, I am very lazy*/
		email.send();
	}

	public static void sendSimpleEmail(String[] receivers, String subject, String body)
			throws Exception, InterruptedException {
		Email email = new SimpleEmail();
		setAuthSettings(email);
		setConfigSettings(email);
		setEmailInfo(email, subject, body);
		email.addTo(receivers);
		
		email.send();
	}

	public static EmailAttachment createAttachment(String path, String description, String name) {
		EmailAttachment attachment = new EmailAttachment();
		
		attachment.setPath(path);
		attachment.setDisposition(EmailAttachment.ATTACHMENT);
		attachment.setDescription(description);
		attachment.setName(name);
		
		return attachment;
	}
	
	public static void sendEmailWithAttachment(String subject, String body, EmailAttachment attachment, String... receivers) throws Exception {

		MultiPartEmail email = new MultiPartEmail();
		setAuthSettings(email);
		setConfigSettings(email);
		setEmailInfo(email, subject, body);
		email.addTo(receivers);

		email.attach(attachment);

		email.send();
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

}



