package dto;

import java.time.LocalDateTime;

public class OrderWithSaleProductAndShopDto extends OrderDto {
    private SaleProductWithShopDto saleProduct;

    public OrderWithSaleProductAndShopDto(int id,
                                          String status,
                                          LocalDateTime createAt,
                                          int quantity,
                                          SaleProductWithShopDto saleProduct) {
        super(id, status, createAt, quantity, saleProduct.getId());
        this.saleProduct = saleProduct;
    }

    public SaleProductWithShopDto getSaleProduct() {
        return saleProduct;
    }
}
