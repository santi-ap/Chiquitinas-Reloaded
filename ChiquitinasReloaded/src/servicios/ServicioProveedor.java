/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import objetos.Usuario;
import java.util.ArrayList;
import objetos.Proveedor;

/**
 *
 * @author santialfonso
 */
public class ServicioProveedor extends Servicio implements InterfaceDAO{

    public ServicioProveedor() {
    }

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
            sql = "SELECT " + queBuscamos + " FROM Proveedor WHERE " + queColumna + " = ?;";

            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, queValor.toString());
            rs = preparedStatement.executeQuery();

            //STEP 3.1: Extract data from result set
            if (rs.next()) {
                //Retrieve by column name
                returnSelect = rs.getString(queBuscamos.toString());
            } else {//si no encuentra a un usuario con los parametros especificados, va a retornar un un String avisando que no se encontro el usuario
                return "noUserFound";
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

    @Override
    public void insert(Object object) {
        try {
            //STEP 3: Execute a querey
            super.conectar();

            String sql;
            sql = "INSERT INTO Proveedor (idProveedor, nombreProveedor, telefonoProveedor, correoProveedor) values (?,?,?,?);";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, ((Proveedor) object).getIdProveedor());//idPrpveedor
            preparedStatement.setString(2, ((Proveedor) object).getNombreProveedor());//nombreProvedor
            preparedStatement.setInt(3, ((Proveedor) object).getNumeroTelProveedor());//Numero Telefono
            preparedStatement.setString(4, ((Proveedor) object).getCorreoProveedor());//Correo Proveedor
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
       try {
            super.conectar();
            String sql = "UPDATE Proveedor SET " + queColumnaActualizamos + " = ? WHERE " + queColuma + " = ?;";
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
        try {
            super.conectar();
            String sql = "DELETE FROM Proveedor WHERE " + queColumna + " = ?;";
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

    @Override
    public ArrayList<Object> selectAll(Object queColumna, Object queValor) {
        ArrayList<Object> listaDatosProveedor = new ArrayList<>();
        ResultSet rs = null;
        Statement stmt = null;
        try {
            //STEP 3: Execute a query
            super.conectar();
            stmt = conn.createStatement();
            String sql;

            //hacemos el select con lo que buscamos, de cual columna y cual valor de la columna
            sql = "SELECT * FROM Proveedor WHERE " + queColumna + " = ?;";

            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, queValor.toString());
            rs = preparedStatement.executeQuery();

            //STEP 3.1: Extract data from result set
            if (rs.next()) {
                //Retrieve by column name
                listaDatosProveedor.add(rs.getString("idProveedor"));
                listaDatosProveedor.add(rs.getString("nombreProveedor"));
                listaDatosProveedor.add(rs.getString("telefonoProveedor"));
                listaDatosProveedor.add(rs.getString("correoProveedor"));
            } else {//si no encuentra a un proveedor con los parametros especificados, va a retornar un un String avisando que no se encontro el proveedor
                System.out.println("noProvidorFound");
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
        return listaDatosProveedor;
    }
    
    public ArrayList<String> selecAllNombresProveedor(){
        String temp="";
        ArrayList<String> listaProveedores = new ArrayList<>();
        ResultSet rs = null;
        Statement stmt=null;
        try{
            //STEP 3: Execute a querey
            super.conectar();
            stmt=conn.createStatement();
            String sql;
            sql="SELECT idProveedor, nombreProveedor FROM Proveedor;";
            rs=stmt.executeQuery(sql);
            //STEP 3.1: Extract data from result set
            while (rs.next()){
                //Retrieve by column name
                temp=temp+(rs.getString("idProveedor"))+" ";
                temp=temp+(rs.getString("nombreProveedor"));
                listaProveedores.add(temp);
                temp="";
                //System.out.println(nombreProveedor);
               // listaNombresProveedores.add(nombreProveedor);
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
        return listaProveedores;
    } 
    
     public Proveedor selectProveedor(Object queColumna, Object queValor) {
        Proveedor proveedor = new Proveedor();
        ResultSet rs = null;
        Statement stmt = null;
        try {
            //STEP 3: Execute a query
            super.conectar();
            stmt = conn.createStatement();
            String sql;

            //hacemos el select con lo que buscamos, de cual columna y cual valor de la columna
            sql = "SELECT * FROM Proveedor WHERE " + queColumna + " = ?;";

            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, queValor.toString());
            rs = preparedStatement.executeQuery();

            //STEP 3.1: Extract data from result set
            if (rs.next()) {
                //Retrieve by column name
                proveedor.setIdProveedor(rs.getInt("idProveedor"));
                proveedor.setNombreProveedor(rs.getString("nombreProveedor"));
                proveedor.setNumeroTelProveedor(rs.getInt("telefonoProveedor"));
                proveedor.setCorreoProveedor(rs.getString("correoProveedor"));
            } else {//si no encuentra a un proveedor con los parametros especificados, va a retornar un un String avisando que no se encontro el proveedor
                System.out.println("No se encontro el proveedor");
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
        return proveedor;
    }
}
