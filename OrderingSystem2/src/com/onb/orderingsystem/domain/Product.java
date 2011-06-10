package com.onb.orderingsystem.domain;

import java.math.BigDecimal;

public class Product {

    private final String sku;
    private String name;
    private BigDecimal price;

    public Product(String sku) {
        this.sku = sku.trim();
    }

    public Product(String sku, String name, BigDecimal price) {
        this(sku);
        this.name = name.trim();
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getName() {
        return this.name;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public String getSkuNumber() {
        return this.sku;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + (this.sku != null ? this.sku.hashCode() : 0);
        hash = 97 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 97 * hash + (this.price != null ? this.price.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Product other = (Product) obj;
        if ((this.sku == null) ? (other.sku != null) : !this.sku.equals(other.sku)) {
            return false;
        }
        return true;
    }
}
