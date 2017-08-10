package dao;

import org.apache.log4j.Logger;
import org.apache.tomcat.jdbc.pool.DataSource;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Ник on 24.04.2016.
 */
public class DBConnection {

    private static final Logger LOGGER = Logger.getLogger(DBConnection.class.getName());
    private static DataSource ds = null;


    static {
        LOGGER.info("Try get data source");
        Context initContext = null;
        try {
            initContext = new InitialContext();

            Context envContext = (Context) initContext.lookup("java:/comp/env");
            ds = (DataSource) envContext.lookup("jdbc/studentsDB");

        } catch (NamingException e) {
            e.printStackTrace();
            LOGGER.info("Could not get data source");
        }
    }

    public static Connection getConnection() throws SQLException{
        LOGGER.info("Try get connection");
        return ds.getConnection();
    }
}
