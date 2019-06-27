/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chiquitinasreloaded;

import java.sql.SQLException;

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
    
/**
 * metodo chequea en la base de datos si el id ingresado ya existe
 * @param idInput es el id que se envia a la base de datos para verificar unicidad
 * @return true si el id ya existe en la base de datos
 */
    public boolean existeId(long idInput) throws SQLException{
        Object o;
        try
        {
            this.getServivioUsuario().conectar();
            o = this.getServivioUsuario().select("id", "id", idInput);
            if (o==null)
                return false;
            else
                return true;
        } catch(Exception e) {
            System.out.println(e + "\n\nError in method existeId in the controller class, method defaulting to true...");
            return true;
        } finally {
            this.getServivioUsuario().desconectar();
        }      
    }
    
    /**
     * metodo que verifica si dos strings son iguales.
     * @param s es el primer input ingresado por el usuario.
     * @param p es el segundo input ingresado por el usuario.
     * @return true si son iguales, false si no.
     */
    public boolean verificaString (String s, String p){
        return s.equals(p);
    }
}
