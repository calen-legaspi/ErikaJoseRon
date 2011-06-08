/*
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package com.onb.orderingsystem.sql;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * A simple connection for sending and executing
 * SQL statements to a MySQL data-source using
 * Connector/J driver.
 * 
 * @since Jun-7-2011
 */
public class MySqlConnector implements DataSource {

    private static final String CONNECTOR_J_DRIVER;
    private static final String CONNECTION_URL;
    private static final String CONNECTION_PROP;
    private static MySqlConnector singletonInstance;
    private Connection conn;

    static {
        CONNECTOR_J_DRIVER = "com.mysql.jdbc.Driver";
        CONNECTION_PROP = "user=root&password=admin";
        CONNECTION_URL =
                "jdbc:mysql://localhost/Ordering_System?" + CONNECTION_PROP;
    }

    /**
     * Constructs a new MySqlConnector. This will load
     * the MySql JDBC driver and will try to connect to
     * //localhost/Ordering_System? as root user.
     * 
     * @throws SQLException if an SQL error occurs.
     */
    public MySqlConnector() throws SQLException {
        try {
            Class.forName(MySqlConnector.CONNECTOR_J_DRIVER);
        } catch (ClassNotFoundException ex) {
            throw new SQLException(ex);
        }
        conn = DriverManager.getConnection(MySqlConnector.CONNECTION_URL);
    }

    /**
     * Create a singleton instance of this class.
     * 
     * @return the singleton instance of this class.
     * @throws SQLException 
     */
    public static MySqlConnector getInstance() throws SQLException {
        if (singletonInstance == null) {
            // lazily initialiaze our single instance
            singletonInstance = new MySqlConnector();
        }

        return singletonInstance;
    }

    /**
     * 
     * @param sql the select statement to be executed.
     * @return a result-set object containing the query result.
     * @throws SQLException if an SQL error occurs
     */
    @Override
    public ResultSet executeQuery(String sql) throws SQLException {
        return conn.createStatement().executeQuery(sql);
    }

    /**
     * 
     * @param sql the SQL-DML statement.
     * @return the number of rows affected.
     * @throws SQLException if an SQL error occurs.
     */
    @Override
    public int executeUpdate(String sql) throws SQLException {
        return conn.createStatement().executeUpdate(sql);
    }

    /**
     * Closes this active connection.
     * 
     * @throws IOException if an SQL error occur.
     */
    @Override
    public void close() throws IOException {
        try {
            conn.close();
            MySqlConnector.singletonInstance = null;
        } catch (SQLException ex) {
            throw new IOException(ex);
        }
    }
}
