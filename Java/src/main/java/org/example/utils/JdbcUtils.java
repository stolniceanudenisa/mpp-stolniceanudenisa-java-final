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


// folosita in tema lab 3
    private Connection getNewConnection(){
//        logger.traceEntry();

        String url=properties.getProperty("jdbc.url");
        String user=properties.getProperty("jdbc.user");
        String pass=properties.getProperty("jdbc.pass");
//        logger.info("trying to connect to database ... {}",url);
//        logger.info("user: {}",user);
//        logger.info("pass: {}", pass);
        Connection con=null;
        try {

            if (user!=null && pass!=null)
                con= DriverManager.getConnection(url,user,pass);
            else
                con=DriverManager.getConnection(url);
        } catch (SQLException e) {
//            logger.error(e);
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

    // metoda 2 cu mtasks
    //    private Connection getNewConnection()
    ////    {
    ////
    ////        logger.traceEntry();
    ////        String driver=properties.getProperty("mtasks.jdbc.driver");
    ////        String url=properties.getProperty("mtasks.jdbc.url");
    //////        String user=jdbcProps.getProperty("tasks.jdbc.user");
    //////        String pass=jdbcProps.getProperty("tasks.jdbc.pass");
    ////        logger.info("trying to connect to database ... {}",url);
    //////        logger.info("user: {}",user);
    //////        logger.info("pass: {}", pass);
    ////
    ////
    ////        Connection con=null;
    ////        try {
    ////            Class.forName(driver);
    ////            logger.info("Loaded driver ...{}",driver);
    ////            con= DriverManager.getConnection(url);
    ////        } catch (ClassNotFoundException e) {
    ////            logger.error(e);
    ////            System.out.println("Error loading driver "+e);
    ////        } catch (SQLException e) {
    ////            logger.error(e);
    ////            System.out.println("Error getting connection "+e);
    ////        }
    ////        return con;
    ////    }

    // sau
//    public Connection getConnection() {
//        logger.traceEntry();
//        try {
//            if (connection == null || connection.isClosed()) {
//                connection = getNewConnection();
//            }
//        } catch (SQLException e) {
//            logger.error(e);
//            System.out.println("Error DB "+e);
//            e.printStackTrace();
//        }
//        logger.traceExit();
//        return connection;
//    }

}