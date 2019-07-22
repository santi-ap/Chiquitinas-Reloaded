/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Object queColumnaActualizamos, Object queInsertamos, Object queColuma, Object queValor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Object queColumna, Object queValor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Object> selectAll(Object queColumna, Object queValor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
