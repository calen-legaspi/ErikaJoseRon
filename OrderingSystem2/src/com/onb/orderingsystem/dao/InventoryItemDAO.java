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

import com.onb.orderingsystem.domain.InventoryItem;
import java.sql.SQLException;

/**
 * A data-access interface for updating and fetching of
 * product information on the inventory.
 * 
 * @since Jun-8-2011
 * @see "Core J2EE Patterns - Data Access Object"
 */
public interface InventoryItemDAO {

    /**
     * Updates product quantity to new quantity.
     * 
     * NOTE: This method is made public in the hope that it will be useful
     * although performance wise CMP is much suitable for updating
     * the data-store.
     * 
     * @param sku the stock-keeping unit of a product to be updated.
     * @param newQuantity the remaining product quantity.
     * @return the number of rows affected.
     * @throws SQLException if an SQL error occurs.
     */
    int updateProductQuantity(String sku, int newQuantity) throws SQLException;

    /**
     * Fetch inventory item information. The information consist
     * of the remaining product quantity and product information itself.
     * 
     * @param sku the stock-keeping unit of the inventory item.
     * @return a value-object with inventory item information.
     * @throws SQLException if an SQL error occurs.
     */
    InventoryItem findItemBySKU(String sku) throws SQLException;
}
