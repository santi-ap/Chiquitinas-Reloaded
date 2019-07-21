/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

/**
 *
 * @author santialfonso
 */
public class Proveedor {
    
    private int idProveedor;
    private String nombreProveedor;
    private int numeroTelProveedor;
    private String correoProveedor;

    public Proveedor() {
    }

    public Proveedor(int idProveedor, String nombreProveedor, int numeroTelProveedor, String correoProveedor) {
        this.idProveedor = idProveedor;
        this.nombreProveedor = nombreProveedor;
        this.numeroTelProveedor = numeroTelProveedor;
        this.correoProveedor = correoProveedor;
    }
    
    

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public int getNumeroTelProveedor() {
        return numeroTelProveedor;
    }

    public void setNumeroTelProveedor(int numeroTelProveedor) {
        this.numeroTelProveedor = numeroTelProveedor;
    }

    public String getCorreoProveedor() {
        return correoProveedor;
    }

    public void setCorreoProveedor(String correoProveedor) {
        this.correoProveedor = correoProveedor;
    }
    
}
