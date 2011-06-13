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

import java.io.Closeable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * For sending and executing an SQL statements.
 * 
 * @since Jun-7-2011
 */
public interface DataSource extends Closeable {

    /**
     * Executes the given SQL statement, which returns
     * a single ResultSet object.
     * 
     * @param sql an SQL statement to be sent to the database,
     *  typically a static SQL SELECT statement.
     * @return a ResultSet object that contains the data produced
     *  by the given query; never null.
     * @throws java.sql.SQLException if a database access error occurs.
     */
    ResultSet executeQuery(String sql) throws java.sql.SQLException;

    /**
     * Executes the given SQL statement, which may be an INSERT, UPDATE,
     * or DELETE statement or an SQL statement that returns nothing,
     * such as an SQL DDL statement.
     * 
     * @param sql
     * @return
     * @throws SQLException 
     */
    int executeUpdate(String sql) throws SQLException;

    /**
     * Creates a PreparedStatement object for sending parameterized SQL
     * statements to the database.
     * 
     * @param sql an SQL statement that may contain one or more '?'
     *  IN parameter place-holders.
     * @return a new default PreparedStatement object containing
     *  the pre-compiled SQL statement
     * @throws SQLException if a database access error occurs or this method
     *  is called when this data-source is closed.
     */
    PreparedStatement prepareStatement(String sql) throws SQLException;

    /**
     * Makes all changes made since the previous commit/rollback permanent
     * and releases any database locks currently held by this DataSource
     * object.
     * 
     * @throws SQLException if a database access error occurs.
     */
    void commit() throws SQLException;

    /**
     * Undoes all changes made in the current transaction and releases
     * any database locks currently held by this DataSource object.
     * 
     * @throws SQLException if a database access error occurs.
     */
    void rollback() throws SQLException;
}
