package at.htlsaalfelden;

public class CartItem {
    private int quantity;
    private Product product;

    public CartItem(Product product, int quantity) {
        this.quantity = quantity;
        this.product = product;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int i) {
        this.quantity = i;
    }

    public Product getProduct() {
        return this.product;
    }
}
