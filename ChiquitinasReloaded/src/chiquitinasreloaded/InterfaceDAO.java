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
    public Object select(Object object);
    public void insert(Object object);
    public void update(Object object);
    public void delete(Object object);
    public ArrayList<Object> selectAll(Object object);
}
