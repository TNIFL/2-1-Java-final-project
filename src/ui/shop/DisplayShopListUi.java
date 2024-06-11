package ui.shop;

import dao.ShopDao;
import dto.ShopDto;
import ui.base.SelectableUiBase;
import ui.base.SelectableUiListItem;

import java.util.ArrayList;
import java.util.List;

public class DisplayShopListUi extends SelectableUiBase {

    public DisplayShopListUi(Runnable before) {
        super(before);
    }

    @Override
    public String getTitle() {
        return "주문 가능 가게 목록";
    }

    @Override
    public List<SelectableUiListItem> getListItems() {
        List<SelectableUiListItem> list = new ArrayList<>();
        ShopDao shopDao = new ShopDao();
        List<ShopDto> shopList = shopDao.findAll();
        for (ShopDto shop : shopList) {
            list.add(new SelectableUiListItem(shop.getId(), shop.getName(), new DisplayShopDetailUi(this, shop.getId())));
        }

        list.add(new SelectableUiListItem(0, "이전 화면으로", this.getBefore()));
        return list;
    }
}
