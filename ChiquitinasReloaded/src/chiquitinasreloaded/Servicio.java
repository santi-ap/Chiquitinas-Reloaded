/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chiquitinasreloaded;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author santialfonso
 */
public abstract class Servicio {
    String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    String DB_URL="jdbc:mysql://ulatina3.ceuol7o4t0su.us-east-2.rds.amazonaws.com:3306/ChiquitinasReloaded";
    String USER="chic";
    String PASS="123456";
    Connection conn=null;
    
    protected void conectar(){
        try{
            //STEP 1: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 2: Open a connecNon
            System.out.println("Connecting to database...");
            conn=DriverManager.getConnection(DB_URL,USER,PASS);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    protected void desconectar() throws SQLException{
        try{
            if(!conn.isClosed()){
                conn.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
