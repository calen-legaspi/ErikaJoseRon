package com.onb.orderingsystem.domain;

public class InventoryItem {

    private int id;
    private int quantity;
    private Product product;

    public InventoryItem(int id, Product item, int qty) {
        this(item, qty);
        this.id = id;
    }

    public InventoryItem(Product product, int quantity) {
        if (product == null) {
            throw new NullPointerException("cannot set a null product");
        }
        this.product = product;
        this.quantity = quantity;
    }

    public final int getID() {
        return id;
    }
    
    public Product getProduct() {
        return this.product;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void updateQuantity(int quantity) {
        this.quantity = this.quantity - quantity;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((product == null) ? 0 : product.hashCode());
        result = prime * result + quantity;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        InventoryItem other = (InventoryItem) obj;
        if (product == null) {
            if (other.product != null) {
                return false;
            }
        } else if (!product.equals(other.product)) {
            return false;
        }
        if (quantity != other.quantity) {
            return false;
        }
        return true;
    }
}
