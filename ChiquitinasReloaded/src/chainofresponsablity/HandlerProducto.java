/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chainofresponsablity;

import items.Item;
import items.Producto;
import servicios.ServicioOrdenHasProducto;

/**
 *
 * @author santialfonso
 */
public class HandlerProducto implements Handler{

    private Handler handler;
    @Override
    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void processItem(Item item, int id) {
        if (item instanceof Producto)
        {
            ServicioOrdenHasProducto sohp = new ServicioOrdenHasProducto();
            //process it
            String temp = Integer.toString(id) + "," + Integer.toString(((Producto) item).getIdProducto()) + "," + Integer.toString(((Producto) item).getCantidadActualProducto());
            //inserting into Orden_has_Producto
            sohp.insert(temp);
        } else if (this.handler != null)
        {
            //send it to next handler
            System.out.println("Forwarding request...");
            this.handler.processItem(item, id);
        } else 
        {
            //there's no more handlers!  :((
            System.out.println("Item no pudo ser procesado.");
        }
        
    
   
    }
    
    
}
