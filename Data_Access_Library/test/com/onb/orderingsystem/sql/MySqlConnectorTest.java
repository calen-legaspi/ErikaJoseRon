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
import java.sql.ResultSet;
import java.sql.SQLException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Test MySqlConnector class.
 * 
 * @since Jun-8-2011
 */
public class MySqlConnectorTest {

    private DataSource ds;

    @Before
    public void testGetInstance() throws SQLException {
        ds = MySqlConnector.getInstance();
    }

    @Test
    public void testExecuteQuery() throws SQLException {
        String sql = "SELECT Name, CreditLimit, PaidAmount FROM Customer"
                + " WHERE Customer.ID = 1;";
        ResultSet rs = ds.executeQuery(sql);
        int rowCount = 0;

        while (rs.next()) {
            ++rowCount;
        }

        Assert.assertEquals(rowCount, 1);
    }

    @Test
    public void testExecuteUpdate() throws Exception {
        String sql =
                "INSERT INTO Customer (Name, CreditLimit, PaidAmount) "
                + "VALUES ('Ronillo Ang', 0, 0)";
        int result = ds.executeUpdate(sql);

        Assert.assertEquals(1, result);
    }

    @After
    public void testClose() throws IOException {
        ds.close();
    }
}
