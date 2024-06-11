package ui.shop;

import dao.SaleProductDao;
import dto.SaleProductDto;
import ui.base.SelectableUiBase;
import ui.base.SelectableUiListItem;

import java.util.ArrayList;
import java.util.List;

public class DisplayProductUi extends SelectableUiBase {
    private int shopId;
    public DisplayProductUi(Runnable before, int shopId) {
        super(before);
        this.shopId = shopId;
    }

    @Override
    public String getTitle() {
        return "상품 목록";
    }

    @Override
    public String getDescription() {
        String content = "";
        SaleProductDao saleProductDao = new SaleProductDao();

        for (SaleProductDto saleProduct : saleProductDao.findByShopId(shopId)) {
            content += " - " + saleProduct.getName() + "\n";
        }
        return content;
    }

    @Override
    public List<SelectableUiListItem> getListItems() {
        List<SelectableUiListItem> list = new ArrayList<>();
        list.add(new SelectableUiListItem(1, "상품 추가", new AddProductUi(this, shopId)));
        list.add(new SelectableUiListItem(0, "이전 화면으로", getBefore()));
        return list;
    }
}
