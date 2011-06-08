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

import com.onb.orderingsystem.domain.Customer;
import java.math.BigDecimal;
import java.sql.SQLException;

/**
 * Data access interface for updating and search
 * for customer records.
 * 
 * @since Jun-7-2011
 * @see "Core J2EE Patterns - Data Access Object"
 */
public interface CustomerDAO {

    /**
     * Fetch the information of a customer stored
     * on the data-stores.
     * 
     * @param id the id of the customer to be look-up.
     * @return a value-object containing the information.
     */
    Customer findCustomerByID(int id) throws SQLException;

    /**
     * Update the customer credit limit to the specified new credit limit.
     * NOTE: This method is made public in the hope that it will be useful
     * although performance wise CMP is much suitable for updating
     * the data-store.
     * 
     * @param customerID the customer to be updated.
     * @param newCreditLimit the new credit limit.
     * @return the number of rows affected.
     */
    int updateCreditLimit(int customerID, BigDecimal newCreditLimit);

    /**
     * Update the customer paid to the specified new paid amount.
     * NOTE: This method is made public in the hope that it will be useful
     * although performance wise CMP is much suitable for updating
     * the data-store.
     * 
     * @param customerID the customer to be updated.
     * @param newPaidAmount the new paid amount.
     * @return the number of rows affected.
     */
    int updatePaidAmount(int customerID, BigDecimal newPaidAmount);
}
