package ui.customer;

import dao.OrderDao;
import dto.OrderWithSaleProductAndShopDto;
import ui.base.SelectableUiBase;
import ui.base.SelectableUiListItem;

import java.util.ArrayList;
import java.util.List;

public class DisplayCustomerOrderListUi extends SelectableUiBase {
    public DisplayCustomerOrderListUi(Runnable before) {
        super(before);
    }

    @Override
    public String getTitle() {
        return "주문 내역";
    }

    @Override
    public String getDescription() {
        String content = "";
        OrderDao orderDao = new OrderDao();
        for (OrderWithSaleProductAndShopDto order : orderDao.findAllWithSaleProductAndShop()) {
            content += order.getCreateAt().toString() + " || " + order.getSaleProduct().getShop().getName() + " > " + order.getSaleProduct().getName() + " | " + order.getSaleProduct().getPrice() + "원 X " + order.getQuantity() + "개 [" + order.getStatus() + "]\n";
        }
        return content;
    }

    @Override
    public List<SelectableUiListItem> getListItems() {
        List<SelectableUiListItem> list = new ArrayList<>();
        list.add(new SelectableUiListItem(0, "이전 화면으로", getBefore()));
        return list;
    }
}
