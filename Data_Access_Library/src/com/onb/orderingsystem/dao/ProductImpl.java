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

import com.onb.orderingsystem.domain.Product;
import com.onb.orderingsystem.sql.DataSource;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Default implementation of ProductDAO interface.
 * 
 * @since Jun-8-2011
 * @see "Core J2EE Patterns - Data Access Object"
 * @see <a href="http://goo.gl/1WAAs">"Don't Repeat The DAO! Naming DAO"</a>
 */
class ProductImpl implements ProductDAO {

    private DataSource dataSource;

    ProductImpl(DataSource dataSource) {
        setDataSource(dataSource);
    }

    @Override
    public Product findProductBySKU(String sku) throws SQLException {
        String sql = "SELECT Name, Price FROM Product WHERE SKU = '"
                + sku + "'";
        ResultSet rs = dataSource.executeQuery(sql);
        Product product = null;

        if (rs.next()) {
            String name = rs.getString(1);
            BigDecimal price = rs.getBigDecimal(2);
            product = new Product(sku, name, price);
        }

        return product;
    }

    private void setDataSource(DataSource dataSource) {
        if (dataSource == null) {
            throw new NullPointerException();
        }
        this.dataSource = dataSource;
    }
}
