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
package com.onb.orderingsystem.dao;

import com.onb.orderingsystem.domain.Customer;
import com.onb.orderingsystem.sql.DataSource;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Default implementation of CustomerDAO interface.
 * 
 * @since Jun-8-2011
 * @see "Core J2EE Patterns - Data Access Object"
 * @see <a href="http://goo.gl/1WAAs">"Don't Repeat The DAO! Naming DAO"</a>
 */
class CustomerImpl implements CustomerDAO {

    private DataSource dataSource;

    CustomerImpl(DataSource dataSource) {
        setDataSource(dataSource);
    }

    @Override
    public Customer findCustomerByID(int id) throws SQLException {
        Customer customer = null;
        String sql = "SELECT ID, Name FROM Customer WHERE ID = " + id;
        ResultSet rs = dataSource.executeQuery(sql);

        if (rs.next()) {
            customer = new Customer(id, rs.getString(1));
        }

        return customer;
    }

    @Override
    public int updateCreditLimit(int customerID, BigDecimal newCreditLimit)
            throws SQLException {
        StringBuilder sql = new StringBuilder();

        sql.append("UPDATE Customer SET CreditLimit = ");
        sql.append(newCreditLimit);
        sql.append(" WHERE ID = ");
        sql.append(customerID);

        return dataSource.executeUpdate(sql.toString());
    }

    @Override
    public int updatePaidAmount(int customerID, BigDecimal newPaidAmount)
            throws SQLException {
        StringBuilder sql = new StringBuilder();

        sql.append("UPDATE Customer SET PaidAmount = ");
        sql.append(newPaidAmount);
        sql.append(" WHERE ID = ");
        sql.append(customerID);

        return dataSource.executeUpdate(sql.toString());
    }

    @Override
    public List<Customer> listAllValidCustomer() throws SQLException {
        List<Customer> validCustomer = new ArrayList<Customer>();
        String sql = "SELECT ID, Name, CreditLimit, PaidAmount FROM Customer";
        ResultSet rs = dataSource.executeQuery(sql);

        while (rs.next()) {
            int id = rs.getInt(1);
            String name = rs.getString(2);
            BigDecimal creditLimit = rs.getBigDecimal(3);
            BigDecimal paidAmount = rs.getBigDecimal(4);

            if (paidAmount.compareTo(creditLimit) > 0) {
                continue;
            }

            validCustomer.add(new Customer(id, name));
        }

        return validCustomer;
    }

    private void setDataSource(DataSource dataSource) {
        if (dataSource == null) {
            throw new NullPointerException();
        }
        this.dataSource = dataSource;
    }
}
