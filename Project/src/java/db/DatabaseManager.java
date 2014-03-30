/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author william
 */
public class DatabaseManager {
    private Connection connection;
    private static final String url = "jdbc:mysql://localhost:3306/project";
    private static final String user = "root";
    private static final String pwd = "";
    
    public DatabaseManager() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        this.connection = DriverManager.getConnection(url , user, pwd);
    }
    
    public Connection getConnection() {
        return this.connection;
    }
}

