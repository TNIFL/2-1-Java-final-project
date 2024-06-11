package ui.shop;

import service.order.UpdateOrderStatusService;
import ui.base.SelectableUiBase;
import ui.base.SelectableUiListItem;

import java.util.ArrayList;
import java.util.List;

public class UpdateOrderStatusUi extends SelectableUiBase {
    private int orderId;
    public UpdateOrderStatusUi(Runnable before, int orderId) {
        super(before);
        this.orderId = orderId;
    }

    @Override
    public String getTitle() {
        return "변경할 상태를 선택해주세요";
    }

    @Override
    public List<SelectableUiListItem> getListItems() {
        List<SelectableUiListItem> list = new ArrayList<>();
        list.add(new SelectableUiListItem(1, "조리 중", new UpdateOrderStatusService(this, orderId, "조리 중")));
        list.add(new SelectableUiListItem(2, "배달 중", new UpdateOrderStatusService(this, orderId, "배달 중")));
        list.add(new SelectableUiListItem(3, "배달 완료", new UpdateOrderStatusService(this, orderId, "배달 완료")));
        list.add(new SelectableUiListItem(0, "이전 화면으로", getBefore()));
        return list;
    }
}
