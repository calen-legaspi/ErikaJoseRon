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

import com.onb.orderingsystem.domain.Order;
import com.onb.orderingsystem.domain.OrderItem;
import com.onb.orderingsystem.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * Default implementation of OrderDAO interface.
 * 
 * @since Jun-8-2011
 * @see "Core J2EE Patterns - Data Access Object"
 * @see <a href="http://goo.gl/1WAAs">"Don't Repeat The DAO! Naming DAO"</a>
 */
public class OrderImpl implements OrderDAO {

    private DataSource dataSource;

    public OrderImpl(DataSource dataSource) {
        setDataSource(dataSource);
    }

    @Override
    public Order findOrderByID(int orderID) throws SQLException {
        String sql = "SELECT DISTINCT ID, CustomerID, Date, OrderStatus"
                + " FROM Order WHERE Order.ID = " + orderID;
        ResultSet rs = dataSource.executeQuery(sql);
        Order order = new Order(orderID);

        if (rs.next()) {
            int customerID = rs.getInt(2);
            Date date = rs.getDate(3);
            int orderStatus = rs.getInt(4);
            order.setCustomerID(customerID);
            order.setDate(date);

            if (orderStatus == 1) {
                order.setAsPaid();
            }
        }

        return order;
    }

    @Override
    public int[] insertOrder(Order newOrder) throws SQLException {
        String updateOrder = "INSERT INTO Order (ID, CustomerID, Date, "
                + "OrderStatus, OrderItemID) VALUES (?, ?, '?', ?, ?);";
        PreparedStatement orderStatement =
                dataSource.prepareStatement(updateOrder);
        int orderID = newOrder.getId();
        int customerID = newOrder.getCustomerID();
        Date date = newOrder.getDate();
        int orderStatus = newOrder.getOrderStatus();

        for (OrderItem item : newOrder.getOrders()) {
            orderStatement.setInt(1, orderID);
            orderStatement.setInt(2, customerID);
            orderStatement.setDate(3, new java.sql.Date(date.getTime()));
            orderStatement.setInt(4, orderStatus);
            orderStatement.setInt(5, item.getID());
            orderStatement.addBatch();
        }

        return orderStatement.executeBatch();
    }

    private void setDataSource(DataSource dataSource) {
        if (dataSource == null) {
            throw new NullPointerException();
        }
        this.dataSource = dataSource;
    }
}
