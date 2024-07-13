package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final  String DB_DRIVER = "com.mysql.jdbc.Driver";
    private static final  String DB_URL = "jdbc:mysql://localhost:3306/mydbtest";
    private static final  String DB_USER = "root";
    private static final  String DB_PASSWORD = "123456789Zxc";

    public static Connection getConnection()  {
        Connection connection = null;
        try {
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("Sussed");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("ERROR");
        }
        return connection;
    }
}
