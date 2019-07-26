/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import items.Producto;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author santialfonso
 */
public class ServicioComboHasProducto extends Servicio implements InterfaceDAO{

    public ServicioComboHasProducto() {
    }

     /**
     * SELECT queBuscamos FROM Combo_has_Producto WHERE queColumna queValor;
     *
     * @param queBuscamos
     * @param queColumna
     * @param queValor
     * @return lo que buscamos
     */
    @Override
    public String select(Object queBuscamos, Object queColumna, Object queValor) {
        String returnSelect = "";
        ResultSet rs = null;
        Statement stmt = null;
        try {
            //STEP 3: Execute a query
            super.conectar();
            System.out.println("Creando statement...");
            stmt = conn.createStatement();
            String sql;

            //hacemos el select con lo que buscamos, de cual columna y cual valor de la columna
            sql = "SELECT " + queBuscamos + " FROM Combo_has_Producto WHERE " + queColumna + " = ?;";

            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, queValor.toString());
            rs = preparedStatement.executeQuery();

            //STEP 3.1: Extract data from result set
            if (rs.next()) {
                //Retrieve by column name
                returnSelect = rs.getString(queBuscamos.toString());
            } else {//si no encuentra a un usuario con los parametros especificados, va a retornar un un String avisando que no se encontro el usuario
                return "No se encontro el combo";
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
        return returnSelect;
    }

    /**
     * INSERT INTO Combo_has_Producto (Combo_idCombo, Producto_idProducto) values (?,?);
     *
     * @param object este objeto debe ser un String de la siguiente forma: "a,b" donde 'a' es el idCombo y 'b' es el 
     */
    @Override
    public void insert(Object object) {
        try {
            //vamos a tomar lo que hay que insertar como un String done los IDs se separan por comas. Por ejemplo "12,5"
            String[] combo_idComboYProducto_idProducto = ((String)object).split(","); //aqui se separan los dos IDs por la coma en un array y queda ["12","5"]
            int combo_idCombo = Integer.parseInt(combo_idComboYProducto_idProducto[0]);//aqui se agarra el primer ID
            int producto_idProducto = Integer.parseInt(combo_idComboYProducto_idProducto[1]);//aqui se agarra el segundo ID
            //STEP 3: Execute a querey
            super.conectar();

            System.out.println("Insertando valores...");
            String sql;
            sql = "INSERT INTO Combo_has_Producto (Combo_idCombo, Producto_idProducto) values (?,?);";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, combo_idCombo);//combo_idCombo
            preparedStatement.setInt(2, producto_idProducto);//producto_idProduct
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
     * UPDATE Combo_has_Producto SET queColumnaActualizamos = queInsertamos WHERE
     * queColuma = queValor;
     *
     * @param queColumnaActualizamos
     * @param queInsertamos
     * @param queColuma
     * @param queValor
     */
    @Override
    public void update(Object queColumnaActualizamos, Object queInsertamos, Object queColuma, Object queValor) {
        try {
            super.conectar();
            System.out.println("Actualizando valores...");
            String sql = "UPDATE Combo_has_Producto SET " + queColumnaActualizamos + " = ? WHERE " + queColuma + " = ?;";
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

    /**
     * DELETE FROM Combo_has_Producto WHERE queColumna = queValor;
     *
     * @param queColumna
     * @param queValor
     */
    @Override
    public void delete(Object queColumna, Object queValor) {
        try {
            super.conectar();
            System.out.println("Borrando valores...");
            String sql = "DELETE FROM Combo_has_Producto WHERE " + queColumna + " = ?;";
            PreparedStatement preparedStmt = conn.prepareStatement(sql);
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

    /**
     * SELECT * FROM Combo_has_Producto WHERE queColumna = queValor;
     *
     * @param queColumna
     * @param queValor
     * @return una lista con todos los datos del Combo
     */
    @Override
    public ArrayList<Object> selectAll(Object queColumna, Object queValor) {
        ArrayList<Object> listaDeProductos = new ArrayList<>();
        Producto producto;
        ResultSet rs = null;
        Statement stmt = null;
        try {
            //STEP 3: Execute a query
            super.conectar();
            System.out.println("Creando statement...");
            stmt = conn.createStatement();
            String sql;

            //hacemos el select con lo que buscamos, de cual columna y cual valor de la columna
            sql = "SELECT * FROM Combo_has_Producto WHERE " + queColumna + " = ?;";

            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, queValor.toString());
            rs = preparedStatement.executeQuery();

            //STEP 3.1: Extract data from result set
            while(rs.next()) {
                //Retrieve by column name
                 producto = new Producto();
                    //Retrieve by column name
                    producto.setIdProducto(Integer.parseInt(rs.getString("Combo_idCombo")));
                    producto.setNombreProducto(rs.getString("Producto_idProducto"));
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
        return listaDeProductos;
    }
}
