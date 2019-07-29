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
public class ServicioPedidoHasProveedor extends Servicio implements InterfaceDAO {

    @Override
    public String select(Object queBuscamos, Object queColumna, Object queValor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insert(Object object) {
        try {
            
            //vamos a tomar lo que hay que insertar como un String donde los IDs se separan por comas. Por ejemplo "12,5"
/*quitenifty*/String[] Pedido_idPedidoYProveedor_idProveedor = ((String)object).split(","); //aqui se separan los dos IDs por la coma en un array y queda ["12","5"]
            int pedido_idPedido = Integer.parseInt(Pedido_idPedidoYProveedor_idProveedor[0]);//aqui se agarra el primer ID
            int proveedor_idProveedor = Integer.parseInt(Pedido_idPedidoYProveedor_idProveedor[1]);//aqui se agarra el segundo ID
            //STEP 3: Execute a querey
            super.conectar();

            System.out.println("Insertando valores...");
            String sql;
            sql = "INSERT INTO Pedido_has_Proveedor (Pedido_id, Proveedor_id) values (?,?);";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, pedido_idPedido);//Pedido_idPedido
            preparedStatement.setInt(2, proveedor_idProveedor);//producto_idProduct
            preparedStatement.executeUpdate();
            } catch (Exception e) {
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
