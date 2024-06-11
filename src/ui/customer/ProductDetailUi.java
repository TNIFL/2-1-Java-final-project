package ui.customer;

import dao.SaleProductDao;
import dto.SaleProductDto;
import ui.base.SelectableUiBase;
import ui.base.SelectableUiListItem;
import ui.customer.AddCartItemUi;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailUi extends SelectableUiBase {
    private int saleProductId;
    public ProductDetailUi(Runnable before, int saleProductId) {
        super(before);
        this.saleProductId = saleProductId;
    }

    @Override
    public String getTitle() {
        return "메뉴 세부사항";
    }

    @Override
    public String getDescription() {
        SaleProductDto saleProduct = new SaleProductDao().findOne(saleProductId);
        return "" +
                "메뉴 이름 : " + saleProduct.getName() + "\n" +
                "가격 : " + saleProduct.getPrice() + "원\n" +
                "메뉴 설명 : " + saleProduct.getDescription();
    }

    @Override
    public List<SelectableUiListItem> getListItems() {
        List<SelectableUiListItem> list = new ArrayList<>();
        list.add(new SelectableUiListItem(1, "메뉴 담기", new AddCartItemUi(this, getSaleProductId())));
        list.add(new SelectableUiListItem(0, "이전 화면으로", getBefore()));
        return list;
    }

    public int getSaleProductId() {
        return saleProductId;
    }
}