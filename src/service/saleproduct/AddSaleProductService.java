package service.saleproduct;

import dao.SaleProductDao;
import dto.SaleProductDto;

public class AddSaleProductService implements Runnable {
    private String name;
    private int price;
    private String description;
    private int shopId;

    @Override
    public void run() {
        SaleProductDao saleProductDao = new SaleProductDao();
        saleProductDao.insert(new SaleProductDto(0, getName(), getDescription(), getPrice(), getShopId()));
    }

    public AddSaleProductService(String name, int price, String description, int shopId) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.shopId = shopId;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public int getShopId() {
        return shopId;
    }
}
