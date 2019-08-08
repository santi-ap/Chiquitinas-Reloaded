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

    public ServicioCarrito() {
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
        return returnSelect;
    }

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
            String sql = "DELETE FROM CarritoProducto WHERE " + queColumna + " = ?;";
            PreparedStatement preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setString(1, queValor.toString());
            preparedStmt.execute();
            
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
     * select * from Producto where idProducto in (select Producto_idProducto
     * from Combo_has_Producto where Combo_idCombo = QUECOMBO)
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
            while (rs.next()) {
                
                producto = new Producto();
                //Retrieve by column name
                producto.setIdProducto(Integer.parseInt(rs.getString("idProducto")));
                producto.setNombreProducto(rs.getString("nombreProducto"));
                producto.setPrecioProductoCliente(rs.getDouble("precioClienteProducto"));
                /*                                                  I am calling a new select to get the actual ammount the user bought*/
                producto.setCantidadActualProducto(Integer.parseInt(this.select("MontoProducto", "Usuario_idUsuario", queUserId)));
                producto.setDescuentoProductoPromo(rs.getDouble("descuentoPromo"));
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
     * select * from Producto where idProducto in (select Producto_idProducto
     * from Combo_has_Producto where Combo_idCombo = QUECOMBO)
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
            while (rs.next()) {
                //Retrieve by column name

                Combo combo = new Combo();
                //Retrieve by column name
                combo.setNombreCombo(rs.getString("nombreCombo"));
                combo.setIdCombo(Integer.parseInt(rs.getString("idCombo")));
                combo.setPrecioComboCliente(rs.getDouble("precioClienteCombo"));
                combo.setCantidadActualProductoCombo(Integer.parseInt(this.selectCombo("MontoCombo", "Usuario_idUsuario", queUserId)));
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
        return returnSelect;
    }

    public String selectMontoProducto(Object userID, Object productID) {
        String returnSelect = "";
        ResultSet rs = null;
        Statement stmt = null;
        try {
            //STEP 3: Execute a query
            super.conectar();
            stmt = conn.createStatement();
            String sql;

            //hacemos el select con lo que buscamos, de cual columna y cual valor de la columna
            sql = "SELECT MontoProducto FROM CarritoProducto WHERE Usuario_idUsuario = ? AND Producto_idProducto = ?;";

            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, userID.toString());
            preparedStatement.setString(2, productID.toString());
            rs = preparedStatement.executeQuery();

            //STEP 3.1: Extract data from result set
            if (rs.next()) {
                //Retrieve by column name
                returnSelect = rs.getString("MontoProducto");
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
        return returnSelect;
    }

    public void deleteProducto(Object userID, Object productID) {
        try {
            super.conectar();
            String sql = "DELETE FROM CarritoProducto WHERE Usuario_idUsuario = ? AND Producto_idProducto = ?;";
            PreparedStatement preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setString(1, userID.toString());
            preparedStmt.setString(2, productID.toString());
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

    public void deleteCombo(Object userID, Object comboID) {
        try {
            super.conectar();
            String sql = "DELETE FROM CarritoCombo WHERE Usuario_idUsuario = ? AND Combo_idCombo = ?;";
            PreparedStatement preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setString(1, userID.toString());
            preparedStmt.setString(2, comboID.toString());
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
     * INSERT INTO CarritoProducto (Usuario_idUsuario, Producto_idProducto,
     * MontoProducto) values (idUsuario,idProducto,cantidadProducto);
     *
     * @param idUsuario el id del usuario ingresado
     * @param idProducto el id del producto escogido por el usuario
     * @param cantidadProducto la cantidad de producto escogido por el usuario
     */
    public void insertProductCarrito(String idUsuario, String idProducto, String cantidadProducto) {
        try {
            //STEP 3: Execute a querey
            super.conectar();

            String sql;
            sql = "INSERT INTO CarritoProducto (Usuario_idUsuario, Producto_idProducto, MontoProducto) values (?,?,?);";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, idUsuario);//id del Usuario logeado
            preparedStatement.setString(2, idProducto);//id del producto que se va a agregar al carrito
            preparedStatement.setString(3, cantidadProducto);//la cantidad de ese producto que se va a agregar al carrito
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
     * INSERT INTO CarritoProducto (Combo_idCombo, Usuario_idUsuario,
     * MontoCombo) values (idCombo,idUsuario,cantidadCombo);
     *
     * @param idUsuario el id del usuario ingresado
     * @param idCombo el id del combo escogido por el usuario
     * @param cantidadCombo la cantidad de combo escogido por el usuario
     */
    public void insertComboCarrito(String idUsuario, String idCombo, String cantidadCombo) {
        try {
            //STEP 3: Execute a querey
            super.conectar();

            String sql;
            sql = "INSERT INTO CarritoCombo (Combo_idCombo, Usuario_idUsuario, MontoCombo) values (?,?,?);";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, idCombo);//id del combo que se va a agregar al carrito
            preparedStatement.setString(2, idUsuario);//id del Usuario logeado
            preparedStatement.setString(3, cantidadCombo);//la cantidad de ese combo que se va a agregar al carrito
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

}
