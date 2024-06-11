package dto;

public class SaleProductWithShopDto extends SaleProductDto {
    private ShopDto shop;

    public SaleProductWithShopDto(int id, String name, String description, int price, ShopDto shop) {
        super(id, name, description, price, shop.getId());
        this.shop = shop;
    }

    public ShopDto getShop() {
        return shop;
    }
}
