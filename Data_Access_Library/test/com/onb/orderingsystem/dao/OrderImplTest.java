/*
 * Copyright (C) 2011 rondroid
 *
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
import java.math.BigDecimal;
import java.sql.SQLException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 */
public class OrderImplTest {

    private OrderDAO orderDAO;

    @Before
    public void setUP() throws InstantiationException {
        orderDAO = DAOFactory.getFactory().getOrderDAO();
        assertTrue(orderDAO instanceof OrderImpl);
    }

    @Test
    public void testFindOrderByID() throws SQLException {
        Order order = orderDAO.findOrderByID(2);
        assertTrue(order.getOrders().size() == 2);
    }

    @Test
    public void testInsertOrder() throws SQLException {
        Order newOrder = new Order(2);
        newOrder.setCustomerID(1);
        newOrder.add(new OrderItem(1, new Product("ABC0", "", new BigDecimal("22000.50")), 2));
        newOrder.add(new OrderItem(2, new Product("ABC1", "", new BigDecimal("35000.50")), 2));
        //orderDAO.insertOrder(newOrder);
    }
}
