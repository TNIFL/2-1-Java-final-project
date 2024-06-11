package dto;

import java.time.LocalDateTime;

public class OrderDto {
    private int id;
    private String status;
    private LocalDateTime createAt;
    private int quantity;
    private int saleProductId;

    public OrderDto(int id, String status, LocalDateTime createAt, int quantity, int saleProductId) {
        this.id = id;
        this.status = status;
        this.createAt = createAt;
        this.quantity = quantity;
        this.saleProductId = saleProductId;
    }

    public int getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getSaleProductId() {
        return saleProductId;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
