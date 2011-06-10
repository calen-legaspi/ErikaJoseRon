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
import com.onb.orderingsystem.domain.Product;
import com.onb.orderingsystem.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.Set;

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
        String sql = "SELECT ID, CustomerID, Date, OrderStatus"
                + " FROM `Order` WHERE ID = " + orderID + ";";
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

            order = getOrderItem(order);
        }

        return order;
    }

    @Override
    public void insertOrder(Order newOrder) throws SQLException {
        String updateOrder = "INSERT INTO `Order` (ID, CustomerID, Date, "
                + "OrderStatus) VALUES (?, ?, ?, ?);";
        PreparedStatement orderStatement =
                dataSource.prepareStatement(updateOrder);

        orderStatement.setInt(1, newOrder.getId());
        orderStatement.setInt(2, newOrder.getCustomerID());
        orderStatement.setDate(3, new Date(newOrder.getDate().getTime()));
        orderStatement.setInt(4, newOrder.getOrderStatus());
        orderStatement.executeUpdate();

        insertToOrderItem(newOrder.getId(), newOrder.getOrders());
        dataSource.commit();
    }

    private void setDataSource(DataSource dataSource) {
        if (dataSource == null) {
            throw new NullPointerException();
        }
        this.dataSource = dataSource;
    }

    private void insertToOrderItem(int id, Set<OrderItem> orders)
            throws SQLException {
        String updateItem = "INSERT INTO OrderItem (OrderItemID, "
                + "ProdSKU, QTY, Amount, OrderID) VALUES (?, ?, ?, ?, ?);";
        PreparedStatement itemStatement =
                dataSource.prepareStatement(updateItem);
        for (OrderItem item : orders) {
            itemStatement.setInt(1, item.getID());
            itemStatement.setString(2, item.getProduct().getSkuNumber());
            itemStatement.setInt(3, item.getQuantity());
            itemStatement.setBigDecimal(4, item.computeAmount());
            itemStatement.setInt(5, id);
            itemStatement.addBatch();
        }
        itemStatement.executeBatch();
    }

    private Order getOrderItem(Order order) throws SQLException {
        String sql = "SELECT OrderItemID, ProdSKU, QTY "
                + "FROM OrderItem WHERE OrderID = " + order.getId();
        ResultSet rs = dataSource.executeQuery(sql);

        while (rs.next()) {
            int orderItemID = rs.getInt(1);
            String sku = rs.getString(2);
            int qty = rs.getInt(3);
            Product product = findProduct(sku);
            order.add(new OrderItem(orderItemID, product, qty));
        }

        return order;
    }

    private Product findProduct(String sku) throws SQLException {
        DAOFactory daoFactory = null;
        try {
            daoFactory = DAOFactory.getFactory();
        } catch (InstantiationException ex) {
            throw new SQLException(ex);
        }
        return daoFactory.getProductDAO().findProductBySKU(sku);
    }
}
