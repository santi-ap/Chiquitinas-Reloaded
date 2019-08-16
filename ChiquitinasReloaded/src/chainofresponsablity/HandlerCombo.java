/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chainofresponsablity;

import items.*;
import servicios.ServicioOrdenHasCombo;

/**
 *
 * @author santialfonso
 */
public class HandlerCombo implements Handler{

    private Handler handler;
    @Override
    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void processItem(Item item, int id) {
        if (item instanceof Combo)
        {
            ServicioOrdenHasCombo sohc = new ServicioOrdenHasCombo();
            //process it
            String temp = Integer.toString(id) + "," + Integer.toString(((Combo) item).getIdCombo()) + "," + Integer.toString(((Combo) item).getCantidadActualProductoCombo());
            //Orden_has_Combo
            sohc.insert(temp);
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
