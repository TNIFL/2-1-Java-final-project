package ui.customer;

import ui.base.SelectableUiBase;
import ui.base.SelectableUiListItem;

import java.util.ArrayList;
import java.util.List;

public class CustomerEntryUi extends SelectableUiBase {

    public CustomerEntryUi(Runnable before) {
        super(before);
    }

    @Override
    public String getTitle() {
        return "메인 화면";
    }

    @Override
    public List<SelectableUiListItem> getListItems() {
        List<SelectableUiListItem> list = new ArrayList<>();
        list.add(new SelectableUiListItem(1, "주문하기", new OrderUi(this)));
        list.add(new SelectableUiListItem(2, "장바구니", new DisplayCartUi(this)));
        list.add(new SelectableUiListItem(3, "주문내역", new DisplayCustomerOrderListUi(this)));
        list.add(new SelectableUiListItem(0, "이전 화면으로", this.getBefore()));
        return list;
    }
}
