package ui.customer;

import dao.CartItemDao;
import dto.CartItemWithSaleProductWithShopDto;
import service.order.AddOrderService;
import ui.base.SelectableUiBase;
import ui.base.SelectableUiListItem;

import java.util.ArrayList;
import java.util.List;

public class DisplayCartUi extends SelectableUiBase {

    public DisplayCartUi(Runnable before) {
        super(before);
    }

    @Override
    public String getTitle() {
        return "장바구니";
    }

    @Override
    public String getDescription() {
        String content = "";
        int totalPrice = 0;
        CartItemDao cartItemDao = new CartItemDao();
        for (CartItemWithSaleProductWithShopDto cartItemDto : cartItemDao.findAllWithSaleProductAndShop()) {
            content += cartItemDto.getSaleProduct().getShop().getName() + " > " + cartItemDto.getSaleProduct().getName() + " | " + cartItemDto.getSaleProduct().getPrice() + "원 X " + cartItemDto.getQuantity() + "개\n";
            totalPrice += cartItemDto.getSaleProduct().getPrice() * cartItemDto.getQuantity();
        }
        content += "총 결재금액 : " + totalPrice + "원";
        return content;
    }

    @Override
    public List<SelectableUiListItem> getListItems() {
        List<SelectableUiListItem> list = new ArrayList<>();
        list.add(new SelectableUiListItem(1, "결제하기", new AddOrderService(this)));
        list.add(new SelectableUiListItem(0, "이전 화면으로", getBefore()));
        return list;
    }
}
