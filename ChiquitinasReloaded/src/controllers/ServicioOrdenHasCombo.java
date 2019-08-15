/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Asus
 */
public class ServicioOrdenHasCombo extends Servicio implements InterfaceDAO {

    @Override
    public String select(Object queBuscamos, Object queColumna, Object queValor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    /**
     * INSERT INTO Pedido_has_Producto (Pedido_id, Producto_idProducto) values (?,?);
     *
     * @param object este objeto debe ser un String de la siguiente forma: "a,b,c" donde 'a' es el idOren, 'b' es el idCombo, y 'c' es el numero de productos comprados
     */
    @Override
    public void insert(Object object) {
        try {
            //vamos a tomar lo que hay que insertar como un String donde los IDs se separan por comas. Por ejemplo "12,5"
/*quitenifty*/String[] Pedido_idPedidoYProducto_idProducto = ((String)object).split(","); //aqui se separan los dos IDs por la coma en un array y queda ["12","5"]
            int Pedido_idPedido = Integer.parseInt(Pedido_idPedidoYProducto_idProducto[0]);//aqui se agarra el primer ID
            int producto_idProducto = Integer.parseInt(Pedido_idPedidoYProducto_idProducto[1]);//aqui se agarra el segundo ID
            int montoCompra = Integer.parseInt(Pedido_idPedidoYProducto_idProducto[2]);
            //STEP 3: Execute a querey
            super.conectar();

            System.out.println("Insertando valores...");
            String sql;
            sql = "INSERT INTO Orden_has_Combo (Orden_idOrden, Combo_idCombo, MontoCompra) values (?,?,?);";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, Pedido_idPedido);//Pedido_idPedido
            preparedStatement.setInt(2, producto_idProducto);//producto_idProduct
            preparedStatement.setInt(3, montoCompra);//cantidad de productos comprados
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                super.desconectar();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }


    @Override
    public void update(Object queColumnaActualizamos, Object queInsertamos, Object queColuma, Object queValor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Object queColumna, Object queValor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Object> selectAll(Object queColumna, Object queValor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
