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
import objetos.Usuario;
import java.util.ArrayList;

/**
 *
 * @author santialfonso
 */
public class ServicioOrden extends Servicio implements InterfaceDAO{

    public ServicioOrden() {
    }

    @Override
    public String select(Object queBuscamos, Object queColumna, Object queValor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insert(Object object) {
        try {
            //STEP 3: Execute a querey
            super.conectar();

            String sql;
            sql = "INSERT INTO Orden (idOrden, totalOrden, fechaOrden, User_idUsuario) values (?,?,?,?);";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, ((Orden) object).getIdOrden());//idProducto
            preparedStatement.setDouble(2, ((Orden) object).getTotalOrden());//
            preparedStatement.setDate(3, ((Orden) object).getFechaOrden());//precioClienteProducto
            preparedStatement.setInt(4, Integer.parseInt(((Orden) object).getClienteOrden().getIdUsuario()));//stockMinProducto
            
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

    /**
     * UPDATE Orden SET queColumnaActualizamos = queInsertamos WHERE queColuma = queValor;
     * @param queColumnaActualizamos
     * @param queInsertamos
     * @param queColuma
     * @param queValor 
     */
    @Override
    public void update(Object queColumnaActualizamos, Object queInsertamos, Object queColuma, Object queValor) {
        try {
            super.conectar();
            String sql = "UPDATE Orden SET " + queColumnaActualizamos + " = ? WHERE " + queColuma + " = ?;";
            PreparedStatement preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setString(1, queInsertamos.toString());
            preparedStmt.setString(2, queValor.toString());
            preparedStmt.executeUpdate();

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
    public void delete(Object queColumna, Object queValor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Object> selectAll(Object queColumna, Object queValor) {
        ArrayList<Object> listaOrdenes = new ArrayList<>();
        ResultSet rs = null;
        Statement stmt = null;
        try {
            //STEP 3: Execute a query
            super.conectar();
            stmt = conn.createStatement();
            String sql;

            //hacemos el select con lo que buscamos, de cual columna y cual valor de la columna
            sql = "SELECT * FROM Orden WHERE " + queColumna + " = ?;";

            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, queValor.toString());
            rs = preparedStatement.executeQuery();


            //STEP 3.1: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                Orden orden = new Orden();
                orden.setIdOrden(rs.getInt("idOrden"));
                orden.setTotalOrden(rs.getDouble("totalOrden"));
                orden.setFechaOrden(rs.getDate("fechaOrden"));
                listaOrdenes.add(orden);
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
        return listaOrdenes;
    }
    
    
    public int selectMaxId() {
        int maxId=1;    
        ResultSet rs = null;
        Statement stmt=null;
        try{
            //STEP 3: Execute a query
            super.conectar();
            stmt=conn.createStatement();
            String sql;
            //select max(idProducto) from ChiquitinasReloaded.Producto where idProducto>(-1);
            sql="SELECT max(idOrden) FROM Orden";
            
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            rs=preparedStatement.executeQuery(); 
            System.out.println("...");
            //STEP 3.1: Extract data from result set
            if(rs.next()){
                //Retrieve by column name
                maxId = Integer.parseInt(rs.getString("max(idOrden)"))+1;
            }else{//si no encuentra a un pedido con los parametros especificados, va a retornar un uno
                System.out.println("No Id Found. ID is 1.");
            return 1;
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try {
                rs.close();
                stmt.close();
                super.desconectar();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        //retorna lo que se selecciono
        return maxId;
    }
   
}
