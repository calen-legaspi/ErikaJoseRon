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

import java.sql.SQLException;
import com.onb.orderingsystem.domain.InventoryItem;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 */
public class InventoryItemImplTest {

    private InventoryItemDAO dataAccess;

    @Before
    public void setUp() throws InstantiationException {
        DAOFactory factory = DAOFactory.getFactory();
        dataAccess = factory.getInventoryItemDAO();
        assertTrue(dataAccess instanceof InventoryItemImpl);
    }

    @Test
    public void testUpdateProductQuantity() throws SQLException {
        int updateCount = dataAccess.updateProductQuantity("ABC1", 99);
        int updateCount2 = dataAccess.updateProductQuantity("X", 0);
        assertTrue(updateCount == 1 && updateCount2 == 0);
    }

    @Test
    public void testFindItemBySKU() throws SQLException {
        InventoryItem inventoryItem = dataAccess.findItemBySKU("ABC0");
        assertNotNull(inventoryItem);
    }

    @Test
    public void testListAllItem() throws SQLException {
        List<InventoryItem> list = dataAccess.listAllItem();
        assertTrue(!list.isEmpty());
    }
}
