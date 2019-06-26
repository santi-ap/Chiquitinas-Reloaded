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
    public void insert(Object queInsertamos, Object queColumna, Object queValor);
    public void update(Object queActualizamos, Object queColumna, Object queValor);
    public void delete(Object queBorramos, Object queColumna, Object queValor);
    public ArrayList<Object> selectAll(Object queColumna, Object queValor);
}
