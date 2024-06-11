package dto;

public class CartItemDto {
    private int id;
    private int quantity;
    private int saleProductId;

    public CartItemDto(int id, int quantity, int saleProductId) {
        this.id = id;
        this.quantity = quantity;
        this.saleProductId = saleProductId;
    }

    public int getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getSaleProductId() {
        return saleProductId;
    }
}
