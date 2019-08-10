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
import items.*;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.util.*;
import java.text.SimpleDateFormat;

/**
 *
 * @author santialfonso
 */
public class ServicioCombo extends Servicio implements InterfaceDAO {

    java.util.Date utilDate = new java.util.Date();
    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

    public ServicioCombo() {
    }

    /**
     * SELECT queBuscamos FROM Combo WHERE queColumna = queValor;
     *
     * @param queBuscamos
     * @param queColumna
     * @param queValor
     * @return el valor que estabamos buscando
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
            sql = "SELECT " + "*" + " FROM Combo WHERE " + queColumna + " = ?;";

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

    public String selectDate(Object queBuscamos, Object queColumna, Object queValor) {
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
            sql = "SELECT " + queBuscamos + " FROM Combo WHERE " + queColumna + " = ?;";

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

    /**
     *
     * @param usuario
     */
    @Override
    public void insert(Object object) {
        try {
            //STEP 3: Execute a querey
            super.conectar();

            System.out.println("Insertando valores...");
            String sql;
            sql = "INSERT INTO Combo (idCombo, nombreCombo, precioClienteCombo, contadorOfertaCombo, contadorProductoCombo, descuentoCombo, fechaInicioCombo, fechaFinCombo) values (?,?,?,?,?,?,?,?);";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, ((Combo) object).getIdCombo());//idCombo
            preparedStatement.setString(2, ((Combo) object).getNombreCombo());//nombreCombo
            preparedStatement.setDouble(3, ((Combo) object).getPrecioComboCliente());//precioCombos
            preparedStatement.setInt(4, ((Combo) object).getCantidadOfertaCombo());//cantidadDeCombos
            preparedStatement.setInt(5, ((Combo) object).getCantidadActualProductoCombo());//contadorActualDeProductosEnElCombo
            preparedStatement.setDouble(6, ((Combo) object).getDescuentoCombo());//descuentoCombo
            preparedStatement.setDate(7, ((Combo) object).getSqlDate());//Fecha de inicio de combo
            preparedStatement.setDate(8, ((Combo) object).getSqlDate());//Fecha de fin de combo
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
            //System.out.println("Actualizando valores...");
            String sql = "UPDATE Combo SET " + queColumnaActualizamos + " = ? WHERE " + queColuma + " = ?;";
            //UPDATE Combo SET precioClienteCombo = 5000 WHERE idCombo = 1;
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

    public ArrayList<Combo> selectTodosLosCombos() {
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
//    }
//    
//=======
//        ArrayList<Object> listaDeCombo = new ArrayList<>();
//        Combo combo;
//        ResultSet rs = null;
//        Statement stmt = null;
//        try {
//            //STEP 3: Execute a query
//            super.conectar();
//            System.out.println("Creando statement...");
//            stmt = conn.createStatement();
//            String sql;
//
//            //hacemos el select con lo que buscamos, de cual columna y cual valor de la columna
//            sql = "SELECT * FROM Combo WHERE " + queColumna + " = ?;";
//
//            PreparedStatement preparedStatement = conn.prepareStatement(sql);
//            preparedStatement.setString(1, queValor.toString());
//            rs = preparedStatement.executeQuery();
//
//            //STEP 3.1: Extract data from result set
//            while(rs.next()) {
//                //Retrieve by column name
//                 combo = new Combo();
//                    //Retrieve by column name
//                    combo.setIdCombo(Integer.parseInt(rs.getString("idCombo")));
//                    combo.setNombreCombo(rs.getString("nombreCombo"));
//                    combo.setPrecioComboCliente(Double.parseDouble(rs.getString("precioClienteCombo")));
//                    combo.setCantidadOfertaCombo(Integer.parseInt(rs.getString("contadorOfertaCombo")));
//                    combo.setCantidadActualProductoCombo(Integer.parseInt(rs.getString("contadorProductoCombo")));
//                    combo.setDescuentoCombo(Double.parseDouble(rs.getString("descuentoCombo")));
//                        //long millis=System.currentTimeMillis();  
//                       // java.sql.Date date=new java.sql.Date(millis);  
//                    /*String returnStringMySQL = this.select("fechaInicioCombo", "idCombo", queValor);
//                    java.sql.Date date = java.sql.Date.valueOf(returnStringMySQL);
//                    java.util.Date utilDate = new java.util.Date();
//                    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
//                    combo.setSqlDate(date);*/
//                    
//                    String string_date = "12-December-2012";
//
//                    SimpleDateFormat f = new SimpleDateFormat("dd-MMM-yyyy");
//                    try {
//                        java.util.Date d = f.parse(string_date);
//                        long milliseconds = d.getTime();
//                        
//                        combo.setFechaFinCombo(new java.sql.Date(milliseconds));
//                    } catch (ParseException e) {
//                        e.printStackTrace();
//>>>>>>> origin/master
//}
//                   
//                    //combo.setDescuentoProductoPromo(Double.parseDouble(rs.getString("descuentoPromo")));
//                    //combo.setDescuentoCombo(Date.parse(rs.getString("fechaInicioCombo")));
//                    //combo.setDescuentoCombo(Date.parse(rs.getString("fechaFinCombo")));
//                   // Date.parse(rs.getString("descuentoPromo"));
//                    //combo.setIdProveedorProducto(Integer.parseInt(rs.getString("Proveedor_idProveedor")));
//                   // combo.setCategoriaProducto(rs.getString("categoria"));
//                   System.out.println("Identificador de combo: " + combo.getIdCombo());
//                   System.out.println("Nombre de combo: " + combo.getNombreCombo());
//                   System.out.println("Precio combo: "+Math.round(combo.getPrecioComboCliente())+" colones");
//                   System.out.println("Cantidad disponible de combos: "+combo.getCantidadOfertaCombo()+" unidades");
//                   System.out.println("Cantidad de productos en el combo: "+combo.getCantidadActualProductoCombo()+" unidades");
//                   System.out.println("Descuento del combo: "+ Math.round(combo.getDescuentoCombo())+"%");
//                   System.out.println("Fecha de inicio de combo: "+combo.getFechaInicioCombo());
//                   System.out.println("Fecha de fin de combo: "+combo.getFechaFinCombo());
//                    
//                    listaDeCombo.add(combo);
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                rs.close();
//                stmt.close();
//                super.desconectar();
//            } catch (SQLException ex) {
//                ex.printStackTrace();
//            }
//        }
//        //retorna lo que se selecciono
//        return listaDeCombo;
    }
    
    /**
     * SELECT * FROM Combo WHERE queColumna = queValor;
     * @param queColumna
     * @param queValor
     * @return un Combo con toda su info
     */
    public Combo selectCombo(Object queColumna, Object queValor){
        Combo combo = new Combo();
        ResultSet rs = null;
        Statement stmt = null;
        try {
            //STEP 3: Execute a query
            super.conectar();
            System.out.println("Creando statement...");
            stmt = conn.createStatement();
            String sql;

            //hacemos el select con lo que buscamos, de cual columna y cual valor de la columna
            sql="SELECT * FROM Combo WHERE "+queColumna+" = ?;";
            
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, queValor.toString());
            rs=preparedStatement.executeQuery(); 

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
        return combo;
    }
    
    public String selectAllInfoCombo(Object queColumna, Object queValor){
        Combo combo = new Combo();
        String infoCombo = "";
        ResultSet rs = null;
        Statement stmt = null;
        try {
            //STEP 3: Execute a query
            super.conectar();
           // System.out.println("Creando statement...");
            stmt = conn.createStatement();
            String sql;

            //hacemos el select con lo que buscamos, de cual columna y cual valor de la columna
            sql="SELECT * FROM Combo WHERE "+queColumna+" = ?;";
            
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, queValor.toString());
            rs=preparedStatement.executeQuery(); 

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
                
                infoCombo = "Nombre del Combo: "+combo.getNombreCombo()+"\nIdentificador del combo: "+combo.getIdCombo()
                        +"\nPrecio del combo: "+Math.round(combo.getPrecioComboCliente())+" colones"+"\nCantidad disponible de combos: "+combo.getCantidadOfertaCombo()+" unidades"
                        +"\nProductos del combo: "+combo.getCantidadActualProductoCombo()+" productos"+"\nDescuento combo: "+Math.round(combo.getDescuentoCombo())+"%"+"\nFecha de inicio del combo: "+combo.getFechaInicioCombo()+"\nFecha fin el combo: "+combo.getFechaFinCombo();
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
        return infoCombo;
    }
    
    public String selectCombosPasados() {
        String nombreCombo = "";
        String precioClienteCombo = "";
        String fechaInicioCombo = "";
        String fechaFinCombo = "";
        ResultSet rs = null;
        Statement stmt = null;
        try {
            //STEP 3: Execute a query
            super.conectar();
            System.out.println("Buscando combos pasados...");
            stmt = conn.createStatement();
            String sql;

            //hacemos el select con lo que buscamos, de cual columna y cual valor de la columna
            sql = "SELECT nombreCombo, precioClienteCombo,idCombo, fechaInicioCombo, fechaFinCombo FROM Combo WHERE idCombo is not null AND fechaFinCombo < NOW();";
            
            //SELECT nombreCombo, precioClienteCombo,idCombo, fechaInicioCombo FROM Combo WHERE idCombo is not null AND fechaFinCombo < NOW();

            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            
            rs = preparedStatement.executeQuery();
            System.out.println("Fecha de inicio del combo\t    Fecha de fin del combo\tPrecio del combo\tNombre del combo");
            //STEP 3.1: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                nombreCombo = rs.getString("nombreCombo");
                precioClienteCombo = rs.getString("precioClienteCombo");
                fechaInicioCombo = rs.getString("fechaInicioCombo");
                fechaFinCombo = rs.getString("fechaFinCombo");
               
               // System.out.println(nombreCombo+"\t\t"+precioClienteCombo+"\t\t"+fechaInicioCombo+"\t\t\t"+fechaFinCombo);
                
                System.out.printf("%s", fechaInicioCombo);
                System.out.printf("%40s", fechaFinCombo);System.out.print("\t");
                System.out.printf("%s", precioClienteCombo);System.out.print("\t\t\t");
                System.out.printf("%s", nombreCombo);System.out.println("");
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
        return "";

    
    
    
}

}