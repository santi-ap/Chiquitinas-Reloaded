/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import items.Orden;
import items.Producto;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Asus
 */
public class ServicioOrdenHasProducto extends Servicio implements InterfaceDAO {

    @Override
    public String select(Object queBuscamos, Object queColumna, Object queValor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * INSERT INTO Pedido_has_Producto (Pedido_id, Producto_idProducto) values (?,?);
     *
     * @param object este objeto debe ser un String de la siguiente forma: "a,b,c" donde 'a' es el idOrden, 'b' es el idProducto, y 'c' es el numero de productos comprados
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

            String sql;
            sql = "INSERT INTO Orden_has_Producto values (?,?,?);";
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
        ArrayList<Object> listaDatos = new ArrayList<>();
        ResultSet rs = null;
        Statement stmt = null;
        try {
            //STEP 3: Execute a query
            super.conectar();
            stmt = conn.createStatement();
            String sql;

            //hacemos el select con lo que buscamos, de cual columna y cual valor de la columna
            sql = "SELECT * FROM Orden_has_Producto WHERE " + queColumna + " = ?;";

            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, queValor.toString());
            rs = preparedStatement.executeQuery();


            //STEP 3.1: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                String idProducto;
                String monto;
                idProducto = rs.getString("Pruducto_idProducto");
                monto = rs.getString("MontoCompra");
                listaDatos.add(idProducto);
                listaDatos.add(monto);
//            } else {//si no encuentra a un usuario con los parametros especificados, va a retornar un un String avisando que no se encontro el usuario
//                System.out.println("noPedidoFound");
//                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                stmt.close();
                super.desconectar();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        //retorna lo que se selecciono
        return listaDatos;
    }
    
}
