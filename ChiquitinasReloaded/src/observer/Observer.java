/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package observer;

import items.Producto;

/**
 *
 * @author santialfonso
 */
public interface Observer {
    public abstract void updateObserver(Producto producto);
    
    public abstract void suscribeObserver();
    
    public abstract void unSubscribeObserver();
}
