package at.htlsaalfelden;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InventoryManagerTest {
    private InventoryManager manager;

    @BeforeEach
    void init() {
        manager = new InventoryManager();
    }

    @Test
    void testAddItem() {
        manager.addItem("A", 1);
        assertEquals(1, manager.getQuantity("A"));
    }

    @Test
    void testRemoveItem() {
        manager.addItem("A", 2);
        manager.removeItem("A", 1);
        assertEquals(1, manager.getQuantity("A"));
    }

    @Test
    void testRemoveItem_InsufficientQuantity() {
        manager.addItem("A", 1);
        assertThrows(IllegalArgumentException.class, () -> manager.removeItem("A", 2));
    }

    @Test
    void testRemoveItem_ItemNotFound() {
        assertThrows(IllegalArgumentException.class, () -> manager.removeItem("B", 2));
    }

    @Test
    void testHasItem() {
        manager.addItem("A", 1);
        assertTrue(manager.hasItem("A"));
    }

    @Test
    void testAddAndRemoveMultipleItems() {
        manager.addItem("A", 2);
        manager.removeItem("A", 1);
        manager.addItem("A", 1);
        manager.removeItem("A", 1);
        assertEquals(1, manager.getQuantity("A"));
    }

    @Test
    void testAddItem_DuplicateItems() {
        manager.addItem("A", 2);
        manager.addItem("A", 1);
        assertEquals(3, manager.getQuantity("A"));
    }

    @Test
    void testRemoveItem_AllQuantities() {
        manager.addItem("A", 2);
        manager.removeItem("A", 2);
        assertAll(
                () -> assertFalse(manager.hasItem("A")),
                () -> assertEquals(0, manager.getQuantity("A"))
        );
    }
}