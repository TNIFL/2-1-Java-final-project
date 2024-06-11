package dto;

public class CartItemWithSaleProductWithShopDto extends CartItemDto {
    private SaleProductWithShopDto saleProduct;

    public CartItemWithSaleProductWithShopDto(int id, int quantity, SaleProductWithShopDto saleProduct) {
        super(id, quantity, saleProduct.getId());
        this.saleProduct = saleProduct;
    }

    public SaleProductWithShopDto getSaleProduct() {
        return saleProduct;
    }
}
