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
import objetos.Usuario;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author santialfonso
 */
public class ServicioCombo extends Servicio implements InterfaceDAO {
    
    public ServicioCombo() {
    }
    
    
    /**
     * SELECT queBuscamos FROM Combo WHERE queColumna = queValor;
     * @param queBuscamos
     * @param queColumna
     * @param queValor
     * @return el valor que estabamos buscando
     */
    @Override
    public String select(Object queBuscamos, Object queColumna, Object queValor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    /**
     * 
     * @param usuario 
     */
    @Override
    public void insert(Object object) {
        
    }

    @Override
    public void update(Object queColumnaActualizamos, Object queInsertamos, Object queColuma, Object queValor) {
        
        try {
            super.conectar();
            System.out.println("Actualizando valores...");
            String sql = "UPDATE Combo SET " + queColumnaActualizamos + " = ? WHERE " + queColuma + " = ?;";
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

    //servicioProducto.update("precioClienteProducto", precioClienteProductoNuevo, "idProducto", idProducto);
    
    
    @Override
    public void delete(Object queColumna, Object queValor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Object> selectAll(Object queColumna, Object queValor) {
        ArrayList<Object> listaDeCombos = new ArrayList<>();
        Combo combo;
        ResultSet rs = null;
        Statement stmt = null;
        try {
            //STEP 3: Execute a query
            super.conectar();
            System.out.println("Creando statement...");
            stmt = conn.createStatement();
            String sql;

            //hacemos el select con lo que buscamos, de cual columna y cual valor de la columna
            sql = "SELECT * FROM Combo;";

            rs = stmt.executeQuery(sql);

            //STEP 3.1: Extract data from result set
//            if (rs.next()) {
                while (rs.next()) {
                    combo = new Combo();
                    //Retrieve by column name
                    combo.setIdCombo(Integer.parseInt(rs.getString("idCombo")));
                    combo.setNombreCombo(rs.getString("nombreCombo"));
                    combo.setPrecioComboCliente(Double.parseDouble(rs.getString("precioClienteCombo")));
                    combo.setCantidadOfertaCombo(Integer.parseInt(rs.getString("contadorOfertaCombo")));
                    combo.setCantidadActualProductoCombo(Integer.parseInt(rs.getString("contadorProductoCombo")));
                    combo.setDescuentoCombo(Double.parseDouble(rs.getString("descuentoCombo")));
                    combo.setFechaInicioCombo(rs.getDate("fechaInicioCombo"));
                    combo.setFechaFinCombo(rs.getDate("fechaFinCombo"));
                    listaDeCombos.add(combo);
                }
//            } else {//si no encuentra productos, avisa que no se encontro y retorna null
//                System.out.println("No existen productos");
//                return null;
//            }

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
    
    public ArrayList<Combo> selectTodosLosCombos(){
        ArrayList<Combo> listaDeCombos = new ArrayList<>();
        Combo combo;
        ResultSet rs = null;
        Statement stmt = null;
        try {
            //STEP 3: Execute a query
            super.conectar();
            System.out.println("Creando statement...");
            stmt = conn.createStatement();
            String sql;

            //hacemos el select con lo que buscamos, de cual columna y cual valor de la columna
            sql = "SELECT * FROM Combo;";

            rs = stmt.executeQuery(sql);

            //STEP 3.1: Extract data from result set
//            if (rs.next()) {
                while (rs.next()) {
                    combo = new Combo();
                    //Retrieve by column name
                    combo.setIdCombo(Integer.parseInt(rs.getString("idCombo")));
                    combo.setNombreCombo(rs.getString("nombreCombo"));
                    combo.setPrecioComboCliente(Double.parseDouble(rs.getString("precioClienteCombo")));
                    combo.setCantidadOfertaCombo(Integer.parseInt(rs.getString("contadorOfertaCombo")));
                    combo.setCantidadActualProductoCombo(Integer.parseInt(rs.getString("contadorProductoCombo")));
                    combo.setDescuentoCombo(Double.parseDouble(rs.getString("descuentoCombo")));
                    combo.setFechaInicioCombo(rs.getDate("fechaInicioCombo"));
                    combo.setFechaFinCombo(rs.getDate("fechaFinCombo"));
                    listaDeCombos.add(combo);
                }
//            } else {//si no encuentra productos, avisa que no se encontro y retorna null
//                System.out.println("No existen productos");
//                return null;
//            }

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
    
}
