package dto;

public class SaleProductDto {
    private int id;
    private String name;
    private String description;
    private int price;
    private int shopId;

    public SaleProductDto(int id) {
        this.id = id;
    }

    public SaleProductDto(int id, String name, String description, int price, int shopId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.shopId = shopId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public int getShopId() {
        return shopId;
    }
}
