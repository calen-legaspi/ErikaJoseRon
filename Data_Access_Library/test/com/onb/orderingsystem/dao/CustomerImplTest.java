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
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author rondroid
 */
public class CustomerImplTest {

    private CustomerDAO customerDAO;

    @Before
    public void setUP() throws InstantiationException {
        DAOFactory factory = DAOFactory.getFactory();
        customerDAO = factory.getCustomerDAO();
        Assert.assertTrue(customerDAO instanceof CustomerImpl);
    }

    @Test
    public void testFindCustomerByID() throws Exception {
        Customer customer = customerDAO.findCustomerByID(1);
        Assert.assertNotNull(customer);
    }

    @Test
    public void testUpdateCreditLimit() throws Exception {
        BigDecimal newCreditLimit = new BigDecimal("10000.00");
        int affectedRow = customerDAO.updateCreditLimit(1, newCreditLimit);
        Assert.assertTrue(affectedRow == 1);
    }

    @Test
    public void testUpdatePaidAmount() throws Exception {
        BigDecimal newPaidAmount = new BigDecimal("5000.00");
        int affectedRow = customerDAO.updateCreditLimit(1, newPaidAmount);
        Assert.assertTrue(affectedRow == 1);
    }

    @Test
    public void testListAllCustomer() throws Exception {
        List<Customer> customerList = customerDAO.listAllCustomer();
        Assert.assertTrue(!customerList.isEmpty());
    }
}
