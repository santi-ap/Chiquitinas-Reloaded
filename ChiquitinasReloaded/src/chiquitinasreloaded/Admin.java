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
public class Admin extends Usuario{
    public Admin ()
    {
        super.setTipoUsuario(0); //admins will always be 0
    }
    public Admin(String idUsuario, String nombreUsuario, String contrasennaUsuario) {
        super.idUsuario = idUsuario;
        super.nombreUsuario = nombreUsuario;
        super.contrasennaUsuario = contrasennaUsuario;
        super.tipoUsuario = 0;
    }
}
