package de.zappler2k.api.storage.mysql;

import de.zappler2k.api.storage.json.ModuleManager;
import de.zappler2k.api.storage.mysql.impl.MySQLModule;
import lombok.Getter;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@Getter
public class MySQLConnnector {

    private MySQLModule mySQLModule;
    private Connection connection;
    private Logger logger;

    public MySQLConnnector(Logger logger, ModuleManager moduleManager) {
        new MySQLModule(moduleManager);
        mySQLModule = (MySQLModule) moduleManager.getIModule(MySQLModule.class);
        this.logger = logger;
        connect();
    }

    public void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://" + mySQLModule.getHOST() + ":" + mySQLModule.getPORT() + "/" + mySQLModule.getDATABASE() + "?autoReconnect=true", mySQLModule.getUSER(), mySQLModule.getPASSWORD());
            logger.log(Level.INFO, "Connected to " + mySQLModule.getHOST() + ":" + mySQLModule.getPORT() + "/" + mySQLModule.getDATABASE());
        } catch (SQLException | ClassNotFoundException e) {
            logger.log(Level.WARNING, "Error while connecting to " + mySQLModule.getHOST() + ":" + mySQLModule.getPORT() + "/" + mySQLModule.getDATABASE() + "Error: Wrong host, port, database or user/password");
        }
    }

    public void close() {
        try {
            if (connection != null) {
                connection.close();
                logger.log(Level.INFO, "Disconnected from " + mySQLModule.getHOST() + ":" + mySQLModule.getPORT() + "/" + mySQLModule.getDATABASE());
            }
        } catch (SQLException e) {
            logger.log(Level.WARNING, "Error while disconnecting from " + mySQLModule.getHOST() + ":" + mySQLModule.getPORT() + "/" + mySQLModule.getDATABASE() + "Error: " + e.getMessage());
        }
    }

    public void update(String qry, Object... objects) {
        try {
            PreparedStatement ps = connection.prepareStatement(qry);
            for (int i = 1; i <= objects.length; i++) {
                ps.setObject(i, objects[i - 1]);
            }
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            connect();
            System.err.println(e);
        }
    }

    public ResultSet query(String qry, Object... objects) {
        ResultSet rs = null;

        try {
            PreparedStatement ps = connection.prepareStatement(qry);
            for (int i = 1; i <= objects.length; i++) {
                ps.setObject(i, objects[i - 1]);
            }
            rs = ps.executeQuery();
        } catch (SQLException e) {
            connect();
            System.err.println(e);
        }
        return rs;
    }

    public boolean isConnected() {
        try {
            return connection.isValid(1);
        } catch (SQLException e) {
            return false;
        }
    }
}
