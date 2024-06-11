package ui.customer;

import dao.ShopDao;
import dto.ShopDto;
import ui.base.SelectableUiBase;
import ui.base.SelectableUiListItem;

import java.util.ArrayList;
import java.util.List;

public class OrderUi extends SelectableUiBase {
    public OrderUi(Runnable before) {
        super(before);
    }

    @Override
    public String getTitle() {
        return "주문하실 가게를 선택해주세요.";
    }

    @Override
    public List<SelectableUiListItem> getListItems() {
        List<SelectableUiListItem> list = new ArrayList<>();
        ShopDao shopDao = new ShopDao();
        List<ShopDto> shopList = shopDao.findAll();
        for (ShopDto shop : shopList) {
            list.add(new SelectableUiListItem(shop.getId(), shop.getName(), new ProductListUi(this, shop.getId())));
        }
        list.add(new SelectableUiListItem(0, "이전 화면으로", this.getBefore()));
        return list;
    }
}
