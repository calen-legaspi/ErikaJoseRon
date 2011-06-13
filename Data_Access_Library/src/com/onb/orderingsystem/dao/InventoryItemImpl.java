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
import com.onb.orderingsystem.domain.Product;
import com.onb.orderingsystem.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Default implementation of InventoryItemDAO interface.
 * 
 * @since Jun-8-2011
 * @see "Core J2EE Patterns - Data Access Object"
 * @see <a href="http://goo.gl/1WAAs">"Don't Repeat The DAO! Naming DAO"</a>
 */
public class InventoryItemImpl implements InventoryItemDAO {

    private DataSource dataSource;

    public InventoryItemImpl(DataSource dataSource) {
        setDataSource(dataSource);
    }

    @Override
    public int updateProductQuantity(String sku, int newQuantity)
            throws SQLException {
        String sql = "UPDATE InventoryItem SET QTY=? WHERE SKU=?";
        PreparedStatement stmnt = dataSource.prepareStatement(sql);

        stmnt.setInt(1, newQuantity);
        stmnt.setString(2, sku);

        return stmnt.executeUpdate();
    }

    @Override
    public InventoryItem findItemBySKU(String sku) throws SQLException {
        InventoryItem inventory = null;
        String sql = "SELECT ID, QTY FROM InventoryItem WHERE SKU=?";
        PreparedStatement stmnt = dataSource.prepareStatement(sql);

        stmnt.setString(1, sku);
        ResultSet rs = stmnt.executeQuery();

        if (rs.next()) {
            Product item = findProduct(sku);
            int id = rs.getInt(1);
            int qty = rs.getInt(2);
            inventory = new InventoryItem(id, item, qty);
        }

        return inventory;
    }

    @Override
    public List<InventoryItem> listAllItem() throws SQLException {
        List<InventoryItem> inventoryItemList = new ArrayList<InventoryItem>();
        ResultSet rs = dataSource.executeQuery("SELECT SKU FROM InventoryItem");

        while (rs.next()) {
            inventoryItemList.add(findItemBySKU(rs.getString(1)));
        }

        return inventoryItemList;
    }

    private void setDataSource(DataSource dataSource) {
        if (dataSource == null) {
            throw new NullPointerException();
        }
        this.dataSource = dataSource;
    }

    private Product findProduct(String sku) throws SQLException {
        Product product = null;
        try {
            DAOFactory factory = DAOFactory.getFactory();
            ProductDAO productAccess = factory.getProductDAO();
            product = productAccess.findProductBySKU(sku);
        } catch (InstantiationException ex) {
            throw new SQLException(ex);
        }
        return product;
    }
}
