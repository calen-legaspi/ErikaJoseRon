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
import java.sql.SQLException;
import java.util.Date;
import java.util.Set;

/**
 * Data access interface for inserting new order
 * and order retrieval.
 * 
 * @since Jun-7-2011
 * @see "Core J2EE Patterns - Data Access Object"
 */
public interface OrderDAO {

    /**
     * Retrieve order information.
     * 
     * @param orderID
     * @return a value-objects with order information.
     */
    Order findOrderByID(int orderID) throws SQLException;

    /**
     * Insert new order to the data-store.
     * 
     * NOTE: This method is made public in the hope that it will be useful
     * although performance wise CMP is much suitable for updating
     * the data-store.
     * 
     * @param newOrder
     */
    void insertOrder(Order newOrder) throws SQLException;
    
    /**
     * Retrieve order information of the specified customer on
     * the specified date.
     * 
     * @param customerID
     * @param orderDate
     * @return the order value-object containing the order-info.
     * @throws SQLException 
     */
    Order findOrderByCustomer(int customerID, Date orderDate)
            throws SQLException;
    
    /**
     * Retrieve all order information of the specified customer.
     * 
     * @param customerID
     * @return a set of order value-object containing the order-info.
     * @throws SQLException 
     */
    Set<Order> findAllOrderByCustomer(int customerID) throws SQLException;
}
