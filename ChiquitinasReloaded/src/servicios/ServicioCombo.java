/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import objetos.Usuario;
import java.util.ArrayList;

/**
 *
 * @author santialfonso
 */
public class ServicioCombo extends Servicio implements InterfaceDAO {
    
    public ServicioCombo() {
    }
    
    
    /**
     * SELECT queBuscamos FROM Combo WHERE queColumna = queValor;
     * @param queBuscamos
     * @param queColumna
     * @param queValor
     * @return el valor que estabamos buscando
     */
    @Override
    public String select(Object queBuscamos, Object queColumna, Object queValor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    /**
     * 
     * @param usuario 
     */
    @Override
    public void insert(Object object) {
        
    }

    @Override
    public void update(Object queColumnaActualizamos, Object queInsertamos, Object queColuma, Object queValor) {
        
        try {
            super.conectar();
            System.out.println("Actualizando valores...");
            String sql = "UPDATE Combo SET " + queColumnaActualizamos + " = ? WHERE " + queColuma + " = ?;";
            PreparedStatement preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setString(1, queInsertamos.toString());
            preparedStmt.setString(2, queValor.toString());
            preparedStmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                super.desconectar();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    
        
        
    }

    //servicioProducto.update("precioClienteProducto", precioClienteProductoNuevo, "idProducto", idProducto);
    
    
    @Override
    public void delete(Object queColumna, Object queValor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Object> selectAll(Object queColumna, Object queValor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
