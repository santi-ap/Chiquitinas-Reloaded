/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package items;

/**
 *
 * @author santialfonso
 */
public abstract class Item {
        protected String recibo;
        /**
        * 
        * @return the accumulated price of the item(component), plus the cost of the product(decorator)
        */
        public abstract double getPrecio(int tipoUsuario);
        public abstract String getRecibo(int tipoUsuario);
}
