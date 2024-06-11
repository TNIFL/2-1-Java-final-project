package ui.shop;

import ui.base.SelectableUiBase;
import ui.base.SelectableUiListItem;
import ui.shop.DisplayShopListUi;
import ui.shop.ManagementShopUi;

import java.util.ArrayList;
import java.util.List;

public class ShopEntryUi extends SelectableUiBase {

    public ShopEntryUi(Runnable before) {
        super(before);
    }

    @Override
    public String getTitle() {
        return "가게 모드";
    }

    @Override
    public List<SelectableUiListItem> getListItems() {
        List<SelectableUiListItem> list = new ArrayList<>();
        list.add(new SelectableUiListItem(1, "가게 리스트 출력", new DisplayShopListUi(this)));
        list.add(new SelectableUiListItem(2, "가게 관리", new ManagementShopUi(this)));
        list.add(new SelectableUiListItem(0, "이전 화면으로", this.getBefore()));
        return list;
    }
}
