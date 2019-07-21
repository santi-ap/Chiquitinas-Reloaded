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
    public abstract void processItem(Item item);
}
