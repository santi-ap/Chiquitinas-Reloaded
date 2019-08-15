/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import items.Pedido;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import objetos.Usuario;
import java.util.ArrayList;
import java.sql.Date;

/**
 *
 * @author santialfonso
 */
public class ServicioPedido extends Servicio implements InterfaceDAO {

    public ServicioPedido() {
    }

 
    /**
     * 
     * REMEMBER TO ALWAYS TOSTRING THE RETURN TO SHOW THE OBJECT AS A VISIBLE THING TO THE USER
     * @param queBuscamos 
     * @param queColumna
     * @param queValor
     * @return the selected value if it finds it, the string "noPedidoFound" will be returned
     */
    @Override//select, toma como parametros lo que estmos buscando, la columna donde especificamos el valor de condicion y que valor debe tener la columna 
    public String select(Object queBuscamos, Object queColumna, Object queValor) {
        String returnSelect="";
        ResultSet rs = null;
        Statement stmt = null;
        try{
            //STEP 3: Execute a query
            super.conectar();
            stmt=conn.createStatement();
            String sql;
            
            //hacemos el select con lo que buscamos, de cual columna y cual valor de la columna
            sql="SELECT "+queBuscamos+" FROM Pedido WHERE "+queColumna+" = ?;";
            
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, queValor.toString());
            rs=preparedStatement.executeQuery(); 
            
            //STEP 3.1: Extract data from result set
            if(rs.next()){
                //Retrieve by column name
                returnSelect = rs.getString(queBuscamos.toString());
            }else{//si no encuentra a un usuario con los parametros especificados, va a retornar un un String avisando que no se encontro el usuario
            return "noPedidoFound";
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
        return returnSelect;
    }

    @Override
    public void insert(Object object) {
        try{
            //sending info to table Pedido
            super.conectar();
            
            String sql;
            sql="INSERT INTO Pedido (idPedido, totalPedido, fechaPedido) values (?,?,?);";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, Integer.toString(((Pedido)object).getIdPedido()));//idPedido
            preparedStatement.setString(2, Double.toString(((Pedido)object).getTotalPedido()));//nombreUsuario
            
            Date date = ((Pedido)object).getFechaPedido();
            
            preparedStatement.setDate(3, date);//fecha
            preparedStatement.executeUpdate();  
            
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try {
                super.desconectar();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void update(Object queColumnaActualizamos, Object queInsertamos, Object queColuma, Object queValor) {
        try{
            super.conectar();
            String sql = "UPDATE Pedido SET "+queColumnaActualizamos+" = ? WHERE "+queColuma+" = ?;";
            PreparedStatement preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setString(1, queInsertamos.toString());
            preparedStmt.setString(2, queValor.toString());
            preparedStmt.executeUpdate(); 
           
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try {
                super.desconectar();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void delete(Object queColumna, Object queValor) {
        try{
            super.conectar();
            String sql = "DELETE FROM Pedido WHERE "+queColumna+" = ?;";
            PreparedStatement preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setString(1, queValor.toString());
            preparedStmt.execute(); 
           
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try {
                super.desconectar();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

    }

    @Override
    public ArrayList<Object> selectAll(Object queColumna, Object queValor) {
        ArrayList<Object> listaDatosPedido= new ArrayList<>();
        ResultSet rs = null;
        Statement stmt=null;
        try{
            //STEP 3: Execute a query
            super.conectar();
            stmt=conn.createStatement();
            String sql;
            
            //hacemos el select con lo que buscamos, de cual columna y cual valor de la columna
            sql="SELECT * FROM Pedido WHERE "+queColumna+" = ?;";
            
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, queValor.toString());
            rs=preparedStatement.executeQuery(); 
            
            //STEP 3.1: Extract data from result set
            if(rs.next()){
                //Retrieve by column name
                listaDatosPedido.add(rs.getString("idPedido"));
                listaDatosPedido.add(rs.getDouble("totalPedido"));
                listaDatosPedido.add(rs.getDate("fechaPedido"));
            }else{//si no encuentra a un usuario con los parametros especificados, va a retornar un un String avisando que no se encontro el usuario
                listaDatosPedido.add("noPedidoFound");
            return listaDatosPedido;
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
        return listaDatosPedido;
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
            sql="SELECT max(idPedido) FROM Pedido";
            
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            rs=preparedStatement.executeQuery(); 
            
            //STEP 3.1: Extract data from result set
            if(rs.next()){
                //Retrieve by column name
                maxId = Integer.parseInt(rs.getString("max(idPedido)"))+1;
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
