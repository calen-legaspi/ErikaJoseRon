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

import com.onb.orderingsystem.sql.MySqlConnector;
import java.sql.SQLException;

/**
 * A abstract factory to get concrete implementation of data-access
 * interfaces.
 * 
 * @since Jun-8-2011
 * @see "Core J2EE Patterns - Data Access Object"
 */
public abstract class DAOFactory {

    /**
     * @return the default concrete class of this abstract class.
     * @throws InstantiationException if concrete implementation can't
     * be instantiated.
     */
    public static DAOFactory getFactory() throws InstantiationException {
        DAOFactory instance = null;
        try {
            instance = new OrderSystemDAOFactory(MySqlConnector.getInstance());
        } catch (SQLException ex) {
            throw new InstantiationException(ex.getMessage());
        }
        return instance;
    }

    /**
     * @return a concrete implementation of CustomerDAO interface.
     */
    public abstract CustomerDAO getCustomerDAO();

    /**
     * @return a concrete implementation of OrderDAO interface.
     */
    public abstract OrderDAO getOrderDAO();

    /**
     * @return a concrete implementation of ProductDAO interface.
     */
    public abstract ProductDAO getProductDAO();

    /**
     * @return a concrete implementation of CustomerDAO interface.
     */
    public abstract InventoryItemDAO getInventoryItemDAO();
}
