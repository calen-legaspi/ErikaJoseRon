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

import com.onb.orderingsystem.sql.DataSource;

/**
 * Default implementation of DAOFactory.
 * 
 * @since Jun-8-2011
 * @see "Core J2EE Patterns - Data Access Object"
 */
class OrderSystemDAOFactory extends DAOFactory {

    private CustomerDAO customerDAO;
    private ProductDAO productDAO;
    private OrderDAO orderDAO;
    private InventoryItemDAO inventoryDAO;
    private DataSource dataSource;

    OrderSystemDAOFactory(DataSource dataSource) {
        setDataSource(dataSource);
        initializeDataAccess();
    }

    @Override
    public CustomerDAO getCustomerDAO() {
        return customerDAO;
    }

    @Override
    public OrderDAO getOrderDAO() {
        return orderDAO;
    }

    @Override
    public ProductDAO getProductDAO() {
        return productDAO;
    }

    @Override
    public InventoryItemDAO getInventoryItemDAO() {
        return inventoryDAO;
    }

    private void setDataSource(DataSource dataSource) {
        if (dataSource == null) {
            throw new NullPointerException();
        }
        this.dataSource = dataSource;
    }

    private DataSource getDataSource() {
        return dataSource;
    }

    private void initializeDataAccess() {
        customerDAO = new CustomerImpl(getDataSource());
        orderDAO = new OrderImpl(getDataSource());
        productDAO = new ProductImpl(getDataSource());
        inventoryDAO = new InventoryItemImpl(getDataSource());
    }
}
