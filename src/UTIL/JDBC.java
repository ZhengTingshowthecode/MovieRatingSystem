package UTIL;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JDBC {
    static String driver;
    static String url;
    static String username;
    static String pwd;
    static Connection cn=null;
    static PreparedStatement pst=null;
    static ResultSet rs=null;
    static {
        InputStream resourceAsStream= JDBC.class.getClassLoader().getResourceAsStream("config.properties");
        Properties properties = new Properties();
        try {
            properties.load(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        driver = properties.getProperty("driver");
        url = properties.getProperty("url");
        username = properties.getProperty("username");
        pwd = properties.getProperty("pwd");
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            if(resourceAsStream != null)
                resourceAsStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Connection getCn() throws SQLException {
        return DriverManager.getConnection(url,username,pwd);
    }
    public static void closeCn(){
        try{
            if (rs!=null){
                rs.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try{
                if (pst!=null){
                    pst.close();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }finally {
                try{
                    if (cn!=null){
                        cn.close();
                    }
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }
}