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
public class ServicioProducto extends Servicio implements InterfaceDAO {

    public ServicioProducto() {
    }

    /**
     * SELECT queBuscamos FROM Producto WHERE queColumna queValor;
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
            sql = "SELECT " + queBuscamos + " FROM Producto WHERE " + queColumna + " = ?;";

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
     * INSERT INTO Producto (idProducto, nombreProducto, precioClienteProducto,
     * stockMinProducto, contadorProducto, descuentoPromo,
     * Proveedor_idProveedor) values (?,?,?,?,?,?,?);
     *
     * @param object
     */
    @Override
    public void insert(Object object) {
        try {
            //STEP 3: Execute a querey
            super.conectar();

            System.out.println("Insertando valores...");
            String sql;
            sql = "INSERT INTO Producto (idProducto, nombreProducto, precioClienteProducto, stockMinProducto, contadorProducto, descuentoPromo, Proveedor_idProveedor, categoria, precioProveedorProducto) values (?,?,?,?,?,?,?,?,?);";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, ((Producto) object).getIdProducto());//idProducto
            preparedStatement.setString(2, ((Producto) object).getNombreProducto());//nombreProducto
            preparedStatement.setDouble(3, ((Producto) object).getPrecioProductoCliente());//precioClienteProducto
            preparedStatement.setInt(4, ((Producto) object).getStockMinimoProducto());//stockMinProducto
            preparedStatement.setInt(5, ((Producto) object).getCantidadActualProducto());//contadorProducto
            preparedStatement.setDouble(6, ((Producto) object).getDescuentoProductoPromo());//descuentoPromo
            preparedStatement.setInt(7, ((Producto) object).getIdProveedorProducto());//Proveedor_idProveedor
            preparedStatement.setString(8, ((Producto) object).getCategoriaProducto());//Proveedor_idProveedor
            preparedStatement.setDouble(9, ((Producto) object).getPrecioProductoProveedor());//precioClienteProducto
            
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
     * UPDATE Producto SET queColumnaActualizamos = queInsertamos WHERE
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
            String sql = "UPDATE Producto SET " + queColumnaActualizamos + " = ? WHERE " + queColuma + " = ?;";
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
     * DELETE FROM Producto WHERE queColumna = queValor;
     *
     * @param queColumna
     * @param queValor
     */
    @Override
    public void delete(Object queColumna, Object queValor) {
        try {
            super.conectar();
            System.out.println("Borrando valores...");
            String sql = "DELETE FROM Producto WHERE " + queColumna + " = ?;";
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
     * SELECT * FROM Producto WHERE queColumna = queValor;
     *
     * @param queColumna
     * @param queValor
     * @return una lista con todos los datos del producto
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
//            System.out.println("Creando statement...");
            stmt = conn.createStatement();
            String sql;

            //hacemos el select con lo que buscamos, de cual columna y cual valor de la columna
            sql = "SELECT * FROM Producto WHERE " + queColumna + " = ?;";

            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, queValor.toString());
            rs = preparedStatement.executeQuery();

            //STEP 3.1: Extract data from result set
            while(rs.next()) {
                //Retrieve by column name
                 producto = new Producto();
                    //Retrieve by column name
                    producto.setIdProducto(Integer.parseInt(rs.getString("idProducto")));
                    producto.setNombreProducto(rs.getString("nombreProducto"));
                    producto.setPrecioProductoCliente(Double.parseDouble(rs.getString("precioClienteProducto")));
                    producto.setStockMinimoProducto(Integer.parseInt(rs.getString("stockMinProducto")));
                    producto.setCantidadActualProducto(Integer.parseInt(rs.getString("contadorProducto")));
                    producto.setPrecioProductoProveedor(Double.parseDouble(rs.getString("precioProveedorProducto")));
                    producto.setDescuentoProductoPromo(Double.parseDouble(rs.getString("descuentoPromo")));
                    producto.setIdProveedorProducto(Integer.parseInt(rs.getString("Proveedor_idProveedor")));
                    producto.setCategoriaProducto(rs.getString("categoria"));
                    
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

    /**
     * SELECT * FROM Producto; Metodo para retornas todos los Productos de la
     * base de datos con toda su info en una lista de Productos
     *
     * @return lista de Productos
     */
    public ArrayList<Producto> selectTodosLosProductos() {
        ArrayList<Producto> listaDeProductos = new ArrayList<>();
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
            sql = "SELECT * FROM Producto;";

            rs = stmt.executeQuery(sql);

            //STEP 3.1: Extract data from result set
//            if (rs.next()) {
                while (rs.next()) {
                    producto = new Producto();
                    //Retrieve by column name
                    producto.setIdProducto(Integer.parseInt(rs.getString("idProducto")));
                    producto.setNombreProducto(rs.getString("nombreProducto"));
                    producto.setPrecioProductoCliente(Double.parseDouble(rs.getString("precioClienteProducto")));
                    producto.setStockMinimoProducto(Integer.parseInt(rs.getString("stockMinProducto")));
                    producto.setCantidadActualProducto(Integer.parseInt(rs.getString("contadorProducto")));
                    producto.setPrecioProductoProveedor(Double.parseDouble(rs.getString("precioProveedorProducto")));
                    producto.setDescuentoProductoPromo(Double.parseDouble(rs.getString("descuentoPromo")));
                    producto.setIdProveedorProducto(Integer.parseInt(rs.getString("Proveedor_idProveedor")));
                    listaDeProductos.add(producto);
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
        return listaDeProductos;
    }
    
    public ArrayList<Producto> buscarProductoConDescuento() {
        ArrayList<Producto> listaDeProductos = new ArrayList<>();
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
            sql = "SELECT * FROM Producto WHERE descuentoPromo > 0;";

            rs = stmt.executeQuery(sql);

            //STEP 3.1: Extract data from result set
//            if (rs.next()) {
                while (rs.next()) {
                    producto = new Producto();
                    //Retrieve by column name
                    producto.setIdProducto(Integer.parseInt(rs.getString("idProducto")));
                    producto.setNombreProducto(rs.getString("nombreProducto"));
                    producto.setPrecioProductoCliente(Double.parseDouble(rs.getString("precioClienteProducto")));
                    producto.setStockMinimoProducto(Integer.parseInt(rs.getString("stockMinProducto")));
                    producto.setCantidadActualProducto(Integer.parseInt(rs.getString("contadorProducto")));
                    producto.setPrecioProductoProveedor(Double.parseDouble(rs.getString("precioProveedorProducto")));
                    producto.setDescuentoProductoPromo(Double.parseDouble(rs.getString("descuentoPromo")));
                    producto.setIdProveedorProducto(Integer.parseInt(rs.getString("Proveedor_idProveedor")));
                    listaDeProductos.add(producto);
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
        return listaDeProductos;
    }


    public String selectAllInfoProducto(Object queColumna, Object queValor) {
        //ArrayList<Object> listaDeProductos = new ArrayList<>();
        String returnSelect = "";
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
            sql = "SELECT * FROM Producto WHERE " + queColumna + " = ?;";

            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, queValor.toString());
            rs = preparedStatement.executeQuery();

            //STEP 3.1: Extract data from result set
            while(rs.next()) {
                //Retrieve by column name
                 producto = new Producto();
                    //Retrieve by column name
                    producto.setIdProducto(Integer.parseInt(rs.getString("idProducto")));
                    producto.setNombreProducto(rs.getString("nombreProducto"));
                    producto.setPrecioProductoCliente(Double.parseDouble(rs.getString("precioClienteProducto")));
                    producto.setStockMinimoProducto(Integer.parseInt(rs.getString("stockMinProducto")));
                    producto.setCantidadActualProducto(Integer.parseInt(rs.getString("contadorProducto")));
                    producto.setPrecioProductoProveedor(Double.parseDouble(rs.getString("precioProveedorProducto")));
                    producto.setDescuentoProductoPromo(Double.parseDouble(rs.getString("descuentoPromo")));
                    producto.setIdProveedorProducto(Integer.parseInt(rs.getString("Proveedor_idProveedor")));
                    producto.setCategoriaProducto(rs.getString("categoria"));
                    
                    returnSelect = "Nombre del producto: "+producto.getNombreProducto()+"\nPrecio del producto: "+Math.round(producto.getPrecioProductoCliente())+"\nIdentificador del producto: "+producto.getIdProducto() +""
                            +"\nUnidades disponibles: "+producto.getCantidadActualProducto()+" unidades"+"\nPrecio del proveedor: "+Math.round(producto.getPrecioProductoProveedor())+" colones"
                            +"\nDescuento promocional: "+Math.round(producto.getDescuentoProductoPromo()*100)+"%"+"\nID del proveedor: "+producto.getIdProveedorProducto()+"\nCategoria del producto: "+producto.getCategoriaProducto();
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

}