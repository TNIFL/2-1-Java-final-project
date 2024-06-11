package ui.shop;

import service.saleproduct.AddSaleProductService;
import ui.base.InputableUiBase;
import ui.base.InputableUiField;
import ui.base.InputableUiIntegerField;
import ui.base.InputableUiStringField;

import java.util.ArrayList;
import java.util.List;

public class AddProductUi extends InputableUiBase {

    private int shopId;
    public AddProductUi(Runnable before, int shopId) {
        super(before);
        this.shopId = shopId;
    }

    @Override
    public Runnable getRunnable() {
        return new AddSaleProductService(
                (String)getFields().get(0).getContent(),
                (int)getFields().get(1).getContent(),
                (String)getFields().get(2).getContent(),
                getShopId()
        );
    }

    @Override
    public String getTitle() {
        return "상품 추가하기";
    }

    public int getShopId() {
        return shopId;
    }

    @Override
    public List<InputableUiField> initializeFields() {
        List<InputableUiField> fields = new ArrayList<>();
        fields.add(new InputableUiStringField("상품 이름", ""));
        fields.add(new InputableUiIntegerField("상품 가격", 0));
        fields.add(new InputableUiStringField("상품 설명", ""));
        return fields;
    }
}
