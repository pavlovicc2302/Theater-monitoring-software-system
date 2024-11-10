/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.io.FileInputStream;
import java.sql.DriverManager;
import java.sql.Connection;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.Settings;

/**
 *
 * @author Ana
 */
public class DBConnection {

    private Connection connection;
    private static DBConnection instance;

    public DBConnection() {
    }

    public static DBConnection getInstance() {
        if (instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }

    public Connection getConnection() throws Exception {

        if (connection == null || connection.isClosed()) {
            String url = Settings.getInstance().getProperties().getProperty("url");
            String username = Settings.getInstance().getProperties().getProperty("username");
            String password = Settings.getInstance().getProperties().getProperty("password");

            connection = DriverManager.getConnection(url, username, password);
            connection.setAutoCommit(false);

        }

        return connection;
    }

}
