package ui;

import ui.base.SelectableUiBase;
import ui.base.SelectableUiListItem;
import ui.customer.CustomerEntryUi;
import ui.shop.ShopEntryUi;

import java.util.ArrayList;
import java.util.List;

public class EntryUi extends SelectableUiBase {

    public EntryUi(Runnable before) {
        super(before);
    }

    @Override
    public String getTitle() {
        return "프로그램 모드 선택";
    }

    @Override
    public List<SelectableUiListItem> getListItems() {
        List<SelectableUiListItem> list = new ArrayList<>();
        list.add(new SelectableUiListItem(1, "고객", new CustomerEntryUi(this)));
        list.add(new SelectableUiListItem(2, "가게", new ShopEntryUi(this)));
        list.add(new SelectableUiListItem(0, "나가기", getBefore()));
        return list;
    }
}
