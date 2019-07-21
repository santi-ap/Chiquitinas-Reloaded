/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import objetos.Usuario;
import servicios.Servicio;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author santialfonso
 */
public class ServicioUsuario extends Servicio implements InterfaceDAO{

    /**
     * santiago do 
     * REMEMBER TO ALWAYS TOSTRING THE RETURN TO SHOW THE OBJECT AS A VISIBLE THING TO THE USER
     * @param queBuscamos 
     * @param queColumna
     * @param queValor
     * @return the selected value if it finds it, the string "noUserFound" will be returned
     */
    @Override//select, toma como parametros lo que estmos buscando, la columna donde especificamos el valor de condicion y que valor debe tener la columna 
    public Object select(Object queBuscamos, Object queColumna, Object queValor) {
        String returnSelect="";
        ResultSet rs = null;
        Statement stmt = null;
        try{
            //STEP 3: Execute a query
            super.conectar();
            System.out.println("Creando statement...");
            stmt=conn.createStatement();
            String sql;
            
            //hacemos el select con lo que buscamos, de cual columna y cual valor de la columna
            sql="SELECT "+queBuscamos+" FROM Usuario WHERE "+queColumna+" = ?;";
            
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, queValor.toString());
            rs=preparedStatement.executeQuery(); 
            
            //STEP 3.1: Extract data from result set
            if(rs.next()){
                //Retrieve by column name
                returnSelect = rs.getString(queBuscamos.toString());
            }else{//si no encuentra a un usuario con los parametros especificados, va a retornar un un String avisando que no se encontro el usuario
            return "noUserFound";
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
            //STEP 3: Execute a querey
            super.conectar();
            
            System.out.println("Insertando valores...");
            String sql;
            sql="INSERT INTO Usuario (idUsuario, nombreUsuario, contrasennaUsuario, tipoUsuario) values (?,?,?,?);";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, ((Usuario)object).getIdUsuario());//idUsuario
            preparedStatement.setString(2, ((Usuario)object).getNombreUsuario());//nombreUsuario
            preparedStatement.setString(3, ((Usuario)object).getContrasennaUsuario());//contrasennaUsuario
            preparedStatement.setInt(4, ((Usuario)object).getTipoUsuario());//tipoUsuario
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
            System.out.println("Actualizando valores...");
            String sql = "UPDATE Usuario SET "+queColumnaActualizamos+" = ? WHERE "+queColuma+" = ?";
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
            System.out.println("Borrando valores...");
            String sql = "DELETE FROM Usuario WHERE "+queColumna+" = ?";
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
        ArrayList<Object> listaDatosUsuario= new ArrayList<>();
        ResultSet rs = null;
        Statement stmt=null;
        try{
            //STEP 3: Execute a query
            super.conectar();
            System.out.println("Creando statement...");
            stmt=conn.createStatement();
            String sql;
            
            //hacemos el select con lo que buscamos, de cual columna y cual valor de la columna
            sql="SELECT * FROM Usuario WHERE "+queColumna+" = ?;";
            
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, queValor.toString());
            rs=preparedStatement.executeQuery(); 
            
            //STEP 3.1: Extract data from result set
            if(rs.next()){
                //Retrieve by column name
                listaDatosUsuario.add(rs.getString("idUsuario"));
                listaDatosUsuario.add(rs.getString("nombreUsuario"));
                listaDatosUsuario.add(rs.getString("contrasennaUsuario"));
                listaDatosUsuario.add(rs.getString("tipoUsuario"));
            }else{//si no encuentra a un usuario con los parametros especificados, va a retornar un un String avisando que no se encontro el usuario
                listaDatosUsuario.add("noUserFound");
            return listaDatosUsuario;
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
        return listaDatosUsuario;
    }

    
}
