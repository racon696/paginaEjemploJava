/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import java.sql.*;

/**
 *
 * @author alumnossur
 */
public class Conectar {

    private Connection conn;
    private String driver;
    private String user;
    private String password;
    private String host;
    private String puerto;
    private String nombreBDD;
    private String url;

    public Conectar() {
        user = "root";
        password = "";
        host = "localhost";
        puerto = "3306"; /*oracle ocupa el 1507*/
        nombreBDD = "jpa";
        driver = "com.mysql.jdbc.Driver";
        url = "jdbc:mysql://" + host + ":" + puerto + "/" + nombreBDD;
        conn = null;

        try {
            Class.forName(driver);

        } catch (ClassNotFoundException e) {
            System.out.println(">>>Error con ClassForName...problemas con el driver");
        }

        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println(">>>error con DriveManager");
        }
    }
    
    public Connection getconnection(){
        return conn;
    }

}
