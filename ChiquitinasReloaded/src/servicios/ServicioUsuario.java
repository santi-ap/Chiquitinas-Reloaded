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
import objetos.Cliente;

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
    public String select(Object queBuscamos, Object queColumna, Object queValor) {
        String returnSelect="";
        ResultSet rs = null;
        Statement stmt = null;
        try{
            //STEP 3: Execute a query
            super.conectar();
           // System.out.println("Creando statement...");
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
            String sql = "UPDATE Usuario SET "+queColumnaActualizamos+" = ? WHERE "+queColuma+" = ?;";
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
            String sql = "DELETE FROM Usuario WHERE "+queColumna+" = ?;";
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
    
    public Usuario selectUsuario(int idUsuario){
        Usuario usuario = new Usuario();
        ResultSet rs = null;
        Statement stmt = null;
        try{
            //STEP 3: Execute a query
            super.conectar();
            stmt=conn.createStatement();
            String sql;
            
            //hacemos el select con lo que buscamos, de cual columna y cual valor de la columna
            sql="SELECT * FROM Usuario WHERE idUsuario = ?;";
            
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, idUsuario);
            rs=preparedStatement.executeQuery(); 
            
            //STEP 3.1: Extract data from result set
            if(rs.next()){
                //Retrieve by column name
                usuario.setIdUsuario(rs.getString("idUsuario"));
                usuario.setNombreUsuario(rs.getString("nombreUsuario"));
                usuario.setContrasennaUsuario(rs.getString("contrasennaUsuario"));
                usuario.setTipoUsuario(rs.getInt("tipoUsuario"));
            }else{//si no encuentra a un usuario con los parametros especificados, va a retornar un un String avisando que no se encontro el usuario
                System.out.println("No se encontro el usuario");
                return null;
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
        return usuario;
    }

    
    
    
    
    public int selectCount(Object queContamos, Object queColumna, Object queValor) {
        int returnSelect=0;
        ResultSet rs = null;
        Statement stmt = null;
        try{
            //STEP 3: Execute a query
            super.conectar();
            stmt=conn.createStatement();
            String sql;
            
            //hacemos el select con lo que buscamos, de cual columna y cual valor de la columna
           //"SELECT COUNT(idOrden) FROM ChiquitinasReloaded.Orden WHERE User_idUsuario = "
            sql="SELECT COUNT("+queContamos+") FROM Orden WHERE " +queColumna+" = ?;";
            //
            
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, queValor.toString());
            rs=preparedStatement.executeQuery(); 
            
            //STEP 3.1: Extract data from result set
            if(rs.next()){
                //Retrieve by column name
                returnSelect = rs.getInt("COUNT(idOrden)");
            }else{//si no encuentra pedido, retorna 0
            return 0;
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
    
    /**
     * 
     * @param queValor
     * @return 
     * @marco
     * Muestra los 10 productos más consumidos
     * Sino ha comprado tantos productos, simplemente utilizará los que haya
     */
    
    public ArrayList<Object> selectProductosMasConsumidos(Object queValor) {
        ArrayList<Object> countProducto = new ArrayList<Object>();
        ArrayList<Object> nombreProducto = new ArrayList<Object>();
        int returnSelect=0;
        String returnSelectIdProducto = "";
        ResultSet rs = null;
        Statement stmt = null;
        try{
            //STEP 3: Execute a query
            super.conectar();
            stmt=conn.createStatement();
            String sql;
            
            //hacemos el select con lo que buscamos, de cual columna y cual valor de la columna
            sql= "SELECT Producto.nombreProducto, COUNT(Orden_has_Producto.Pruducto_idProducto) FROM Orden_has_Producto INNER JOIN Orden ON Orden_has_Producto.Orden_idOrdon = Orden.idOrden INNER JOIN Producto ON Orden_has_Producto.Pruducto_idProducto = Producto.idProducto WHERE Orden_has_Producto.Pruducto_idProducto is NOT nULL AND Orden.User_idUsuario = ? GROUP BY Pruducto_idProducto ORDER BY COUNT(Orden_has_Producto.Pruducto_idProducto) DESC LIMIT 10;";

            
            //sql = 
            
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, queValor.toString());
            rs=preparedStatement.executeQuery(); 
            
            //STEP 3.1: Extract data from result set
            while(rs.next()){
                //Retrieve by column name
                returnSelect = rs.getInt("COUNT(Orden_has_Producto.Pruducto_idProducto)");
                returnSelectIdProducto = rs.getString("nombreProducto");
            countProducto.add(returnSelect);
            nombreProducto.add(returnSelectIdProducto);
            }
                
            //si no encuentra a un usuario con los parametros especificados, va a retornar un un String avisando que no se encontro el usuario
            
            
            
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
        System.out.println(nombreProducto);
        return countProducto;
    }
    
    public ArrayList<Object> selectNombreProductosUltimaOrden(Object queValor) {
        ArrayList<Object> sumProducto = new ArrayList<Object>();
        ArrayList<Object> dummy = new ArrayList<Object>();
        ArrayList<Object> nombreProducto = new ArrayList<Object>();
        String formattedString = "";
        int returnSelect=0;
        String returnSelectIdProducto = "";
        ResultSet rs = null;
        Statement stmt = null;
        try{
            //STEP 3: Execute a query
            super.conectar();
            //System.out.println("Creando statement...");
            stmt=conn.createStatement();
            String sql;
            
            //hacemos el select con lo que buscamos, de cual columna y cual valor de la columna
            sql= "SELECT nombreProducto  FROM Orden_has_Producto INNER JOIN Orden ON Orden.idOrden = Orden_has_Producto.Orden_idOrden INNER JOIN Producto ON Orden_has_Producto.Pruducto_idProducto = Producto.idProducto WHERE Orden.User_idUsuario = ? AND Orden_idOrden = ?;";

            
            
            
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, queValor.toString());
            preparedStatement.setString(2, queValor.toString());
            rs=preparedStatement.executeQuery(); 
            
            //STEP 3.1: Extract data from result set
            while(rs.next()){
                //Retrieve by column name
                //returnSelect = rs.getInt("SUM(precioClienteProducto)");
                returnSelectIdProducto = rs.getString("nombreProducto");
            sumProducto.add(returnSelect);
             formattedString = nombreProducto.toString()
                    .replace("[", "")  
                    .replace("]", "");  
            nombreProducto.add(returnSelectIdProducto);
            }
                
            //si no encuentra a un usuario con los parametros especificados, va a retornar un un String avisando que no se encontro el usuario
            
            
            
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
        System.out.print("Productos comprados: "+formattedString);
        return dummy;
    }
    
    /**
     * 
     * @marco
     * Parte del método de arriba arroja la suma de la compra, y la fecha de la compra 
     */
    
    public int selectSumaUltimaOrden(Object queValor) {
        int returnSelect=0;
        String returnDate = "";
        ResultSet rs = null;
        Statement stmt = null;
        try{
            //STEP 3: Execute a query
            super.conectar();
            //System.out.println("Creando statement...");
            stmt=conn.createStatement();
            String sql;
            
            //hacemos el select con lo que buscamos, de cual columna y cual valor de la columna
            sql="SELECT SUM(precioClienteProducto), fechaOrden  FROM Orden_has_Producto INNER JOIN Orden ON Orden.idOrden = Orden_has_Producto.Orden_idOrden INNER JOIN Producto ON Orden_has_Producto.Pruducto_idProducto = Producto.idProducto WHERE Orden.User_idUsuario = ? AND Orden_idOrden = ?;";
            
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, queValor.toString());
            preparedStatement.setString(2, queValor.toString());
            rs=preparedStatement.executeQuery(); 
            
            //STEP 3.1: Extract data from result set
            if(rs.next()){
                //Retrieve by column name
                returnSelect = rs.getInt("SUM(precioClienteProducto)");
                returnDate = rs.getString("fechaOrden");
            }else{//si no encuentra a un usuario con los parametros especificados, va a retornar un un String avisando que no se encontro el usuario
            return 0;
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
        System.out.println("Fecha de la compra: "+returnDate);
        System.out.print("Total de la compra: ");return returnSelect;
    
}
    
    public String desplegarProductosConPromocion() {
        String returnPromo="";
        int precioOriginal=0;
        int precioActual = 0;
        double descuentoPromo = 0;
        ResultSet rs = null;
        Statement stmt = null;
        try{
            //STEP 3: Execute a query
            super.conectar();
            
            stmt=conn.createStatement();
            String sql;
            
            //hacemos el select con lo que buscamos, de cual columna y cual valor de la columna
            sql="SELECT nombreProducto, precioClienteProducto AS precioOriginal,precioClienteProducto - (precioClienteProducto * descuentoPromo) AS precioActual, descuentoPromo FROM Producto WHERE descuentoPromo > 0;";
            
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            rs=preparedStatement.executeQuery(); 
            
            //STEP 3.1: Extract data from result set
            System.out.println("Producto\tPrecio antes\tPrecio ahora\tDescuento");
            while(rs.next()){
                //Retrieve by column name
                returnPromo = rs.getString("nombreProducto");
                precioOriginal = rs.getInt("precioOriginal");
                precioActual = rs.getInt("precioActual");
                descuentoPromo = rs.getDouble("descuentoPromo");
                System.out.println(returnPromo+"\t\t"+precioOriginal+"\t\t"+precioActual+"\t\t"+Math.round(descuentoPromo*100)+"%");
                
  
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
        return "";
    
}
    

    public ArrayList<Usuario> selectAllUsers() {
        ArrayList<Usuario> listaDeUsuarios = new ArrayList<>();
        Usuario usuario;
        ResultSet rs = null;
        Statement stmt = null;
        try {
            //STEP 3: Execute a query
            super.conectar();
            stmt = conn.createStatement();
            String sql;

            //hacemos el select con lo que buscamos, de cual columna y cual valor de la columna
            sql = "SELECT * FROM Usuario;";

            rs = stmt.executeQuery(sql);

            //STEP 3.1: Extract data from result set
//            if (rs.next()) {
            while (rs.next()) {
                usuario = new Usuario();
                //Retrieve by column name
                usuario.setIdUsuario(rs.getString("idUsuario"));
                usuario.setNombreUsuario(rs.getString("nombreUsuario"));
                usuario.setContrasennaUsuario(rs.getString("contrasennaUsuario"));
                usuario.setTipoUsuario(Integer.parseInt(rs.getString("tipoUsuario")));
                listaDeUsuarios.add(usuario);
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
        return listaDeUsuarios;
    }
    
    public String selectAllUsuarios() {
        String listaDatosUsuario="";
        ResultSet rs = null;
        Statement stmt=null;
        try{
            //STEP 3: Execute a query
            super.conectar();
            stmt=conn.createStatement();
            String sql;
            
            //hacemos el select con lo que buscamos, de cual columna y cual valor de la columna
            sql="SELECT * FROM Usuario WHERE idUsuario is not null;";
            
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            
            rs=preparedStatement.executeQuery(); 
            
            //STEP 3.1: Extract data from result set
            System.out.println("Nombre del usuario\tIdentificación del usuario\t   Contraseña del usuario\tTipo de usuario");
            while(rs.next()){
                //Retrieve by column name
               String userId = rs.getString("idUsuario");
               String userName =  rs.getString("nombreUsuario");
               String userPass = rs.getString("contrasennaUsuario");
               String userType = rs.getString("tipoUsuario");

                String userNamePadding = userName+"        ";
                String userIdPadding = userId + "        ";
                
                System.out.printf("%s %22s %30s %30s", userNamePadding.substring(0, 10), userIdPadding, userPass, userType);System.out.println("");
                
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
