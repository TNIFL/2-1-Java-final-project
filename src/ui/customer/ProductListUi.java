package ui.customer;

import dao.SaleProductDao;
import dao.ShopDao;
import dto.SaleProductDto;
import dto.ShopDto;
import ui.base.SelectableUiBase;
import ui.base.SelectableUiListItem;

import java.util.ArrayList;
import java.util.List;

public class ProductListUi extends SelectableUiBase {
    private int shopId;
    public ProductListUi(Runnable before, int shopId) {
        super(before);
        this.shopId = shopId;
    }

    @Override
    public String getTitle() {
        ShopDto shop = new ShopDao().findOne(shopId);
        return "[" + shop.getName() + "] 의 메뉴 목록";
    }

    @Override
    public List<SelectableUiListItem> getListItems() {
        List<SelectableUiListItem> list = new ArrayList<>();
        for (SaleProductDto product : new SaleProductDao().findByShopId(shopId)) {
            String content = product.getName() + " | " + product.getPrice() + "원 | " + product.getDescription();
            list.add(new SelectableUiListItem(product.getId(), content, new ProductDetailUi(this, product.getId())));
        }
        list.add(new SelectableUiListItem(0, "이전 화면으로", getBefore()));
        return list;
    }
}
