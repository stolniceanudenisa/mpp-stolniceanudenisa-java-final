package org.example.utils;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.util.Properties;
import java.sql.DriverManager;
import java.sql.SQLException;


public class JdbcUtils {
    private final Properties properties;
    private static final Logger logger = LogManager.getLogger();

    public JdbcUtils(Properties properties) {
        this.properties = properties;
    }

    private Connection instance=null;
    private Connection getNewConnection(){
        logger.traceEntry();
        String driver=properties.getProperty("mtasks.jdbc.driver");
        String url=properties.getProperty("mtasks.jdbc.url");
//        String user=jdbcProps.getProperty("tasks.jdbc.user");
//        String pass=jdbcProps.getProperty("tasks.jdbc.pass");
        logger.info("trying to connect to database ... {}",url);
//        logger.info("user: {}",user);
//        logger.info("pass: {}", pass);
        Connection con=null;
        try {
            Class.forName(driver);
            logger.info("Loaded driver ...{}",driver);
            con= DriverManager.getConnection(url);
        } catch (ClassNotFoundException e) {
            logger.error(e);
            System.out.println("Error loading driver "+e);
        } catch (SQLException e) {
            logger.error(e);
            System.out.println("Error getting connection "+e);
        }
        return con;
    }

    public Connection getConnection(){
        logger.traceEntry();
        try {
            if (instance==null || instance.isClosed())
                instance=getNewConnection();

        } catch (SQLException e) {
            logger.error(e);
            System.out.println("Error DB "+e);
        }
        logger.traceExit(instance);
        return instance;
    }



//    private Connection connection = null;
//
//    private Connection getNewConnection() {
//        logger.traceEntry();
//
//        String url = properties.getProperty("sql.url");
//        String username = properties.getProperty("sql.username");
//        String password = properties.getProperty("sql.password");
//
//        logger.info("Trying to connect to url...{}", url);
//        logger.info("Username...{}", username);
//        logger.info("Password...{}", password);
//
//        Connection con = null;
//        try {
//            if (username == null || password == null) {
//                con = DriverManager.getConnection(url);
//            } else {
//                con = DriverManager.getConnection(url, username, password);
//            }
//        } catch (SQLException sqlException) {
//            logger.error(sqlException);
//            System.out.println("Error getting connection "+sqlException);
//            sqlException.printStackTrace();
//        }
//        return con;
//    }
//
//    public Connection getConnection() {
//        logger.traceEntry();
//        try {
//            if (connection == null || connection.isClosed()) {
//                connection = getNewConnection();
//            }
//        } catch (SQLException e) {
//            logger.error(e);
//            System.out.println("Eroare la DB "+e);
//            e.printStackTrace();
//        }
//        logger.traceExit();
//        return connection;
//    }
}