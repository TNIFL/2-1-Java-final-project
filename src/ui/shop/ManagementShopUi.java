package ui.shop;

import ui.base.SelectableUiBase;
import ui.base.SelectableUiListItem;

import java.util.ArrayList;
import java.util.List;

public class ManagementShopUi extends SelectableUiBase {

    public ManagementShopUi(Runnable before) {
        super(before);
    }

    @Override
    public String getTitle() {
        return "가게 관리 화면";
    }

    @Override
    public List<SelectableUiListItem> getListItems() {
        List<SelectableUiListItem> list = new ArrayList<>();
        list.add(new SelectableUiListItem(1, "가게 추가", new AddShopUi(this)));
        list.add(new SelectableUiListItem(0, "이전 화면으로", this.getBefore()));
        return list;
    }
}
