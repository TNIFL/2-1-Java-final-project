package ui.shop;

import dao.ShopDao;
import dto.ShopDto;
import ui.base.SelectableUiBase;
import ui.base.SelectableUiListItem;

import java.util.ArrayList;
import java.util.List;

public class DisplayShopDetailUi extends SelectableUiBase {
    private int shopId;

    public DisplayShopDetailUi(Runnable before, int shopId) {
        super(before);
        this.shopId = shopId;
    }

    @Override
    public String getTitle() {
        ShopDao shopDao = new ShopDao();
        ShopDto shop = shopDao.findOne(shopId);
        return "[" + shop.getName() + "] 관리";
    }

    @Override
    public List<SelectableUiListItem> getListItems() {
        List<SelectableUiListItem> list = new ArrayList<>();
        list.add(new SelectableUiListItem(1, "주문 리스트 출력", new DisplayShopOrderListUi(this, shopId)));
        list.add(new SelectableUiListItem(2, "상품 관리", new DisplayProductUi(this, shopId)));
        list.add(new SelectableUiListItem(0, "이전 화면으로", getBefore()));
        return list;
    }
}
