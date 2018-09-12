package dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class jdbcUtil {
    private static String url = null;
    private static String username = null;
    private static String password = null;

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnect() {
        setConfigData();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void closeConnect(Statement st, Connection con){
        closeConnect(st,con,null);
    }

    public static void closeConnect(Statement st, Connection con, ResultSet res) {
        try {
            if (res != null) {
                res.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (st != null) {
                st.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (con != null) {
                con.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void setConfigData (){
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("jdbc/src/main/resources/jdbcConfig.properties"));
            url = properties.getProperty("url");
            System.out.println(url);
            username = properties.getProperty("username");
            password = properties.getProperty("password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
