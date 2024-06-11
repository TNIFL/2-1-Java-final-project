package service.shop;

import dao.ShopDao;
import dto.ShopDto;

public class AddShopService implements Runnable {
    private String name;
    private String description;

    public AddShopService(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public void run() {
        ShopDao shopDao = new ShopDao();
        ShopDto shop = new ShopDto(0, getName(), getDescription());
        shopDao.insert(shop);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
