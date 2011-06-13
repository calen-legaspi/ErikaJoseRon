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

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * To test DAOFactory.
 * 
 * @since Jun-8-2011
 */
public class DAOFactoryTest {

    private DAOFactory daoFactory;

    @Before
    public void testGetFactory() throws InstantiationException {
        daoFactory = DAOFactory.getFactory();
        assertTrue(daoFactory instanceof OrderSystemDAOFactory);
    }

    @Test
    public void testGetCustomerDAO() {
        CustomerDAO dao = daoFactory.getCustomerDAO();
        assertTrue(dao instanceof CustomerImpl);
    }

    @Test
    public void testGetOrderDAO() {
        OrderDAO dao = daoFactory.getOrderDAO();
        assertTrue(dao instanceof OrderImpl);
    }

    @Test
    public void testGetProductDAO() {
        ProductDAO dao = daoFactory.getProductDAO();
        assertTrue(dao instanceof ProductImpl);
    }

    @Test
    public void testGetInventoryItemDAO() {
        InventoryItemDAO dao = daoFactory.getInventoryItemDAO();
        assertTrue(dao instanceof InventoryItemImpl);
    }
}
