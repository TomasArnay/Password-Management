package clases;

import javax.swing.*;
import java.sql.*;

public class MyConnection {
    public MyConnection(String user, String password, String url) throws SQLException{
        conn = null;
        this.user = user;
        this.password = password;
        this.url = url;

        try{
            conn = DriverManager.getConnection(url, user, password);

            if (conn != null){
                System.out.println("Conexión exitosa");
            }
        }catch (SQLException ignored){
        }
    }

    public MyConnection(){ };

    //Devuelve el error
    public String getError(){
        return this.error;
    }

    //Retorna la conexion
    public Connection getConnection(){
        return conn;
    }

    //devuelve el resulset
    public ResultSet getResultado(){
        return this.rs;
    }

    //Finaliza la conexion
    public void desconectar(){
        conn = null;
        if (conn == null){
            System.out.println("Conexion finalizada");
        }
    }

    //variables
    private static Connection conn;
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private String user;
    private String password;
    private String url;
    private ResultSet rs; //Contenedor
    private String error;
}
