/*
 * Author:Ajay Sharma
 * Date : 30-11-2022
 *Created With : Intellij IDEA Community Edition
 */

package com.niit.jdp.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseService {

    private static final String URL = "jdbc:mysql://localhost:3306/songdatabase ";
    private static final String USERNAME = "root ";
    private static final String PASSWORD = "96169616 ";

    Connection connection;

    public void Connect() throws ClassNotFoundException, SQLException {

        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

    }

    public Connection getConnection() throws SQLException, ClassNotFoundException {
        Connect();
        return connection;
    }


}
