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
public interface InterfaceDAO {
    /**
     * SELECT queBuscamos FROM TABLA WHERE queColumna = queValor;
     * el metodo select toma los siguientes parametros para especificar que va a seleccionar
     * @param queBuscamos
     * @param queColumna
     * @param queValor
     * @return 
     */
    public String select(Object queBuscamos, Object queColumna, Object queValor);
    
    /**
     * Toma un objeto tipo TABLA para tener todos los datos completos para hacer el insert
     * @param usuario 
     */
    public void insert(Object objecto);//Se toma una un objeto como parametro
    
    /**
     * UPDATE TABLA SET queColumnaActualizamos = queInsertamos WHERE queColuma = queValor
     * @param queColumnaActualizamos
     * @param queInsertamos
     * @param queColuma
     * @param queValor 
     */
    public void update(Object queColumnaActualizamos, Object queInsertamos, Object queColuma, Object queValor);
    
    /**
     * DELETE FROM TABLA WHERE queColumna = queValor
     * @param queColumna
     * @param queValor 
     */
    public void delete(Object queColumna, Object queValor);
    
    /**
     * SELECT * FROM TABLA WHERE queColumna = queValor;
     * @param queColumna
     * @param queValor
     * @return 
     */
    public ArrayList<Object> selectAll(Object queColumna, Object queValor);
}
