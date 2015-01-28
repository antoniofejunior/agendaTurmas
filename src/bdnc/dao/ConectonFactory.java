/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bdnc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.OracleConnection;

/**
 *
 * @author Junior
 */
public class ConectonFactory {

    public static Connection getOracleConnection() {
        String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
        String user = "bdoo";
        String password = "bdoo";

        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            OracleConnection connection = (OracleConnection) DriverManager.getConnection(url, user, password);
            return connection;
        } catch (SQLException ex) {
            Logger.getLogger(ConectonFactory.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("impossivel se conectar ao banco de dados!!/n"
                    + " Tente novamente mais tarde.");
        }
        return null;
    }

    public static void close(Connection connection) throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    public static void close(Statement pstmt) throws SQLException {
        if (pstmt != null) {
            pstmt.close();
        }
    }
}
