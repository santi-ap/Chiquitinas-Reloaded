/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chiquitinasreloaded;

/**
 *
 * @author santialfonso
 */
public class Controller {
    protected long idInput;
    protected String contrasennaInput;
    protected ServicioUsuario servivioUsuario;

    public long getIdInput() {
        return idInput;
    }

    public void setIdInput(long idInput) {
        this.idInput = idInput;
    }

    public String getContrasennaInput() {
        return contrasennaInput;
    }

    public void setContrasennaInput(String contrasennaInput) {
        this.contrasennaInput = contrasennaInput;
    }

    public ServicioUsuario getServivioUsuario() {
        return servivioUsuario;
    }

    public void setServivioUsuario(ServicioUsuario servivioUsuario) {
        this.servivioUsuario = servivioUsuario;
    }
    
    //metodo que verifica si ya existe un usuario con el ID especificado
    public boolean existeId(long idInput){
        return true;
    }
    
    //metodo que verifica si las contrasennas ingresadas son iguales
    public boolean verificaContrasenna(String passInput, String passDosInput){
        return true;
    }
}
