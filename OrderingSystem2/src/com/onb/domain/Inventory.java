package com.onb.domain;

import java.util.LinkedHashSet;
import java.util.Set;

public class Inventory {
	private Set<InventoryItem> inventoryItems;
	
	public Inventory(){
		this.inventoryItems = new LinkedHashSet<InventoryItem>();
	}
	
	public Set<InventoryItem> getInventory(){
		return this.inventoryItems;
	}
	
	public void add(InventoryItem item){
		this.inventoryItems.add(item);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((inventoryItems == null) ? 0 : inventoryItems.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Inventory other = (Inventory) obj;
		if (inventoryItems == null) {
			if (other.inventoryItems != null)
				return false;
		} else if (!inventoryItems.equals(other.inventoryItems))
			return false;
		return true;
	}

}
