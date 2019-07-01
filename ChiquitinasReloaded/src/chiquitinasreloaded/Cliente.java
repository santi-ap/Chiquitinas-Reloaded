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
public class Cliente extends Usuario{
    public Cliente ()
    {
        super.setTipoUsuario(1); //client will always be 1 in the db
    }
    
    public Cliente(String idUsuario, String nombreUsuario, String contrasennaUsuario) {
        super.idUsuario = idUsuario;
        super.nombreUsuario = nombreUsuario;
        super.contrasennaUsuario = contrasennaUsuario;
        super.tipoUsuario = 1;
    }
}
