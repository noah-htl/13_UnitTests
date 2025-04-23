package at.htlsaalfelden;

import java.util.ArrayList;
import java.util.List;
/*
The ShoppingCart class represents a shopping cart with a list of CartItem objects. 
It provides methods to add items to the cart, remove items from the cart, 
calculate the total price of the cart, and apply discounts.

The CartItem class is a helper class that represents an item in the shopping cart, 
consisting of a Product object and a quantity.
Generate them first to make the program complete!

Then code the following test cases
- testAddItem
- testAddItemWithInvalidProduct
- testAddItemWithInvalidQuantity
- testRemoveItem
- testRemoveItemWithInvalidProduct
- testRemoveItemWithInvalidQuantity
- testRemoveNonExistingItem
- testGetTotal
- testApplyDiscount
- testApplyDiscountWithInvalidPercentage
- testGetItemCount
*/
public class ShoppingCart {
    private List<CartItem> cartItems;
    private double total;

    public ShoppingCart() {
        cartItems = new ArrayList<>();
        total = 0.0;
    }

    public void addItem(Product product, int quantity) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }

        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }

        CartItem cartItem = findCartItem(product);

        if (cartItem != null) {
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        } else {
            cartItem = new CartItem(product, quantity);
            cartItems.add(cartItem);
        }

        total += product.getPrice() * quantity;
    }

    public void removeItem(Product product, int quantity) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }

        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }

        CartItem cartItem = findCartItem(product);

        if (cartItem != null) {
            if (quantity >= cartItem.getQuantity()) {
                cartItems.remove(cartItem);
            } else {
                cartItem.setQuantity(cartItem.getQuantity() - quantity);
            }

            total -= product.getPrice() * quantity;
        }
    }

    public double getTotal() {
        return total;
    }

    public void applyDiscount(double discountPercentage) {
        if (discountPercentage < 0 || discountPercentage > 100) {
            throw new IllegalArgumentException("Discount percentage must be between 0 and 100");
        }

        double discountAmount = total * (discountPercentage / 100);
        total -= discountAmount;
    }

    public int getItemCount() {
        int count = 0;

        for (CartItem cartItem : cartItems) {
            count += cartItem.getQuantity();
        }

        return count;
    }

    private CartItem findCartItem(Product product) {
        for (CartItem cartItem : cartItems) {
            if (cartItem.getProduct().equals(product)) {
                return cartItem;
            }
        }

        return null;
    }
}
