package ui.shop;

import dao.OrderDao;
import dto.OrderWithSaleProductAndShopDto;
import ui.base.SelectableUiBase;
import ui.base.SelectableUiListItem;

import java.util.ArrayList;
import java.util.List;

public class DisplayShopOrderListUi extends SelectableUiBase {
    private int shopId;

    public DisplayShopOrderListUi(Runnable before, int shopId) {
        super(before);
        this.shopId = shopId;
    }

    @Override
    public String getTitle() {
        return "들어온 주문 목록";
    }

    @Override
    public List<SelectableUiListItem> getListItems() {
        List<SelectableUiListItem> list = new ArrayList<>();
        for (OrderWithSaleProductAndShopDto dto : new OrderDao().findByShopIdWithSaleProductAndShop(shopId)) {
            String content = dto.getSaleProduct().getName() + " :: " + dto.getQuantity() + "개 :: " + dto.getStatus();
            list.add(new SelectableUiListItem(dto.getId(), content, new UpdateOrderStatusUi(this, dto.getId())));
        }
        list.add(new SelectableUiListItem(0, "이전 화면으로", getBefore()));
        return list;
    }
}
