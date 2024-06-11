package ui.customer;

import service.cartitem.AddCartItemService;
import ui.base.InputableUiBase;
import ui.base.InputableUiField;
import ui.base.InputableUiIntegerField;

import java.util.ArrayList;
import java.util.List;

public class AddCartItemUi extends InputableUiBase {
    private int saleProductId;

    public AddCartItemUi(Runnable before, int saleProductId) {
        super(before);
        this.saleProductId = saleProductId;
    }

    @Override
    public Runnable getRunnable() {
        return new AddCartItemService(getSaleProductId(), (int) getFields().get(0).getContent());
    }

    @Override
    public String getTitle() {
        return "장바구니에 담을 수량을 입력해주세요.";
    }

    @Override
    public List<InputableUiField> initializeFields() {
        List<InputableUiField> fields = new ArrayList<>();
        fields.add(new InputableUiIntegerField("수량", 0));
        return fields;
    }

    public int getSaleProductId() {
        return saleProductId;
    }
}
