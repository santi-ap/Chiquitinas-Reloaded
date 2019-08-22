/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chainofresponsablity;

import items.Item;

/**
 *
 * @author santialfonso
 */
public interface Handler {
    public abstract void setHandler(Handler handler);
    /**
     * inserts the item into the right table
     * @param item the product or combo to be processed
     * @param id the id of the orden 
     */
    public abstract void processItem(Item item, int id);
}
