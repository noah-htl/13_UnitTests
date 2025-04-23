package at.htlsaalfelden;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.*;

class ShoppingCartTest {
    private ShoppingCart cart;

    private static final Product P1 = new Product(1.2);
    private static final Product P2 = new Product(7.5);

    @BeforeEach
    void init() {
        cart = new ShoppingCart();
    }

    @Test
    void testAddItem() {
        cart.addItem(P1, 2);
        assertEquals(2, cart.getItemCount());
        assertEquals(P1.getPrice()*2, cart.getTotal());
    }

    @Test
    void testAddItemWithInvalidProduct() {
        assertThrows(IllegalArgumentException.class, () -> cart.addItem(null,1));
    }

    @Test
    void testAddItemWithInvalidQuantity() {
        assertThrows(IllegalArgumentException.class, () -> cart.addItem(P1,0));
    }

    @Test
    void testRemoveItem() {
        cart.addItem(P1, 2);

        cart.removeItem(P1, 1);
        assertEquals(1, cart.getItemCount());
        assertEquals(P1.getPrice(), cart.getTotal());
    }

    @Test
    void testRemoveItemWithInvalidProduct() {
        assertThrows(IllegalArgumentException.class, () -> cart.removeItem(null,1));
    }

    @Test
    void testRemoveItemWithInvalidQuantity() {
        assertThrows(IllegalArgumentException.class, () -> cart.removeItem(P1,0));
    }

    @Test
    void testRemoveNonExistingItem() {
        cart.removeItem(P2, 1);
        assertEquals(0, cart.getItemCount());
    }

    @Test
    void testGetTotal() {
        cart.addItem(P1, 2);
        assertEquals(P1.getPrice()*2, cart.getTotal());
    }

    @Test
    void testApplyDiscount() {
        cart.addItem(P1, 2);
        cart.applyDiscount(50);
        assertEquals(P1.getPrice(), cart.getTotal());
        assertEquals(2, cart.getItemCount());
    }

    @Test
    void testApplyDiscountWithInvalidPercentage() {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> cart.applyDiscount(-1)),
                () -> assertThrows(IllegalArgumentException.class, () -> cart.applyDiscount(101))
        );
    }

    @Test
    void testGetItemCount() {
        cart.addItem(P1, 2);
        assertEquals(2, cart.getItemCount());
    }
}