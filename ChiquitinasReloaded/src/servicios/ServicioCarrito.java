/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import items.Combo;
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
public class ServicioCarrito extends Servicio implements InterfaceDAO {

    public ServicioCarrito()
    {}
    
    @Override
    public String select(Object queBuscamos, Object queColumna, Object queValor) {
        String returnSelect = "";
        ResultSet rs = null;
        Statement stmt = null;
        try {
            //STEP 3: Execute a query
            super.conectar();
            stmt = conn.createStatement();
            String sql;

            //hacemos el select con lo que buscamos, de cual columna y cual valor de la columna
            sql = "SELECT " + queBuscamos + " FROM CarritoProducto WHERE " + queColumna + " = ?;";

            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, queValor.toString());
            rs = preparedStatement.executeQuery();

            //STEP 3.1: Extract data from result set
            if (rs.next()) {
                //Retrieve by column name
                returnSelect = rs.getString(queBuscamos.toString());
            } else {//si no encuentra a un usuario con los parametros especificados, va a retornar un un String avisando que no se encontro el usuario
                return null;
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
        return returnSelect;    }

    @Override
    public void insert(Object objecto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Object queColumnaActualizamos, Object queInsertamos, Object queColuma, Object queValor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Object queColumna, Object queValor) {
        try {
            super.conectar();
            System.out.println("Borrando valores...");
            String sql = "DELETE FROM CarritoProducto WHERE " + queColumna + " = ?;";
            PreparedStatement preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setString(1, queValor.toString());
            preparedStmt.execute();
            
            System.out.println("Borrando valores...");
            sql = "DELETE FROM CarritoCombo WHERE " + queColumna + " = ?;";
            preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setString(1, queValor.toString());
            preparedStmt.execute();

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
    public ArrayList<Object> selectAll(Object queColumna, Object queValor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
  
      
/**
     * select * from Producto where idProducto in (select Producto_idProducto from Combo_has_Producto where Combo_idCombo = QUECOMBO) 
     *
     * @param queCombo el id del combo que se quiere buscar
     * @return una lista con todos los datos del Combo
     */
    public ArrayList<Object> selectAllProductsOfCarrito(Object queUserId) {
        ArrayList<Object> listaDeProductos = new ArrayList<>();
        Producto producto;
        ResultSet rs = null;
        Statement stmt = null;
        try {
            //STEP 3: Execute a query
            super.conectar();
//            System.out.println("Creando statement...");
            stmt = conn.createStatement();
            String sql;

            //hacemos el select con lo que buscamos, de cual columna y cual valor de la columna
            sql = "select * from Producto where idProducto in (select Producto_idProducto from CarritoProducto where Usuario_idUsuario = ?) ";

            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, queUserId.toString());
            rs = preparedStatement.executeQuery();

            //STEP 3.1: Extract data from result set
            while(rs.next()) {
                //Retrieve by column name
                 producto = new Producto();
                    //Retrieve by column name
                    producto.setIdProducto(Integer.parseInt(rs.getString("idProducto")));
                    producto.setNombreProducto(rs.getString("nombreProducto"));
                    producto.setPrecioProductoCliente(rs.getDouble("precioClienteProducto"));
                    /*                                                  I am calling a new select to get the actual ammount the user bought*/
                    producto.setCantidadActualProducto(Integer.parseInt(this.select("MontoProducto", "Usuario_idUsuario", queUserId )));
                    listaDeProductos.add(producto);
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
        return listaDeProductos;    }    
      
/**
     * select * from Producto where idProducto in (select Producto_idProducto from Combo_has_Producto where Combo_idCombo = QUECOMBO) 
     *
     * @param queCombo el id del combo que se quiere buscar
     * @return una lista con todos los datos del Combo
     */
    public ArrayList<Object> selectAllCombosOfCarrito(Object queUserId) {
        ArrayList<Object> listaDeCombos = new ArrayList<>();
        Producto producto;
        ResultSet rs = null;
        Statement stmt = null;
        try {
            //STEP 3: Execute a query
            super.conectar();
//            System.out.println("Creando statement...");
            stmt = conn.createStatement();
            String sql;

            //hacemos el select con lo que buscamos, de cual columna y cual valor de la columna
            sql = "select * from Combo where idCombo in (select Combo_idCombo from CarritoCombo where Usuario_idUsuario = ?) ";

            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, queUserId.toString());
            rs = preparedStatement.executeQuery();

            //STEP 3.1: Extract data from result set
            while(rs.next()) {
                //Retrieve by column name
                 Combo combo= new Combo();
                    //Retrieve by column name
                    combo.setIdCombo(Integer.parseInt(rs.getString("idCombo")));
                    combo.setPrecioComboCliente(rs.getDouble("precioClienteCombo"));
                    combo.setCantidadActualProductoCombo(Integer.parseInt(this.selectCombo("MontoCombo", "Usuario_idUsuario", queUserId )));
                    listaDeCombos.add(combo);
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
        return listaDeCombos;    
    }  
    
    public String selectCombo(Object queBuscamos, Object queColumna, Object queValor) {
        String returnSelect = "";
        ResultSet rs = null;
        Statement stmt = null;
        try {
            //STEP 3: Execute a query
            super.conectar();
            stmt = conn.createStatement();
            String sql;

            //hacemos el select con lo que buscamos, de cual columna y cual valor de la columna
            sql = "SELECT " + queBuscamos + " FROM CarritoCombo WHERE " + queColumna + " = ?;";

            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, queValor.toString());
            rs = preparedStatement.executeQuery();

            //STEP 3.1: Extract data from result set
            if (rs.next()) {
                //Retrieve by column name
                returnSelect = rs.getString(queBuscamos.toString());
            } else {//si no encuentra a un usuario con los parametros especificados, va a retornar un un String avisando que no se encontro el usuario
                return null;
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
        return returnSelect;    }

}
