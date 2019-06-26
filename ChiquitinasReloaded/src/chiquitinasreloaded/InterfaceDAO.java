/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chiquitinasreloaded;

import java.util.ArrayList;

/**
 *
 * @author santialfonso
 */
public interface InterfaceDAO {
    public Object select(Object queBuscamos, Object queColumna, Object queValor);
    public void insert(ArrayList<Object> datosQueInsertamos);//Se toma una lista como parametro porque hay que insertar todos los datos de un objeto al mismo tiempo
    public void update(Object queInsertamos, Object queColumnaActualizamos, Object queColuma, Object queValor);
    public void delete(Object queBorramos, Object queColumna, Object queValor);
    public ArrayList<Object> selectAll(Object queColumna, Object queValor);
}
