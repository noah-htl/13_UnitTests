package at.htlsaalfelden;

import java.util.HashMap;
import java.util.Map;

/*
The InventoryManager class manages an inventory of items with their quantities. 
Write test cases to ensure that the inventory is being managed correctly.

Generate the following test cases
- testAddItem
- testRemoveItem
- testRemoveItem_InsufficientQuantity
- testRemoveItem_ItemNotFound
- testHasItem
- testAddAndRemoveMultipleItems
- testAddItem_DuplicateItems
- testRemoveItem_AllQuantities
*/
public class InventoryManager {
    private Map<String, Integer> inventory;

    public InventoryManager() {
        inventory = new HashMap<>();
    }

    public void addItem(String item, int quantity) {
        int currentQuantity = inventory.getOrDefault(item, 0);
        inventory.put(item, currentQuantity + quantity);
    }

    public void removeItem(String item, int quantity) {
        if (inventory.containsKey(item)) {
            int currentQuantity = inventory.get(item);
            if (currentQuantity > quantity) {
                inventory.put(item, currentQuantity - quantity);
            } else if (currentQuantity == quantity) {
                inventory.remove(item);
            } else {
                throw new IllegalArgumentException("Insufficient quantity in inventory");
            }
        } else {
            throw new IllegalArgumentException("Item not found in inventory");
        }
    }

    public int getQuantity(String item) {
        return inventory.getOrDefault(item, 0);
    }

    public boolean hasItem(String item) {
        return inventory.containsKey(item);
    }
}
