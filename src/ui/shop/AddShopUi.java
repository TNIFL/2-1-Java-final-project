package ui.shop;

import service.shop.AddShopService;
import ui.base.InputableUiBase;
import ui.base.InputableUiField;
import ui.base.InputableUiStringField;
import ui.base.NullRunnable;

import java.util.ArrayList;
import java.util.List;

public class AddShopUi extends InputableUiBase {

    public AddShopUi(Runnable before) {
        super(before);
    }

    @Override
    public Runnable getRunnable() {
        return new AddShopService(
                (String) getFields().get(0).getContent(),
                (String) getFields().get(1).getContent()
        );
    }

    @Override
    public String getTitle() {
        return "가게 추가";
    }

    @Override
    public List<InputableUiField> initializeFields() {
        List<InputableUiField> fields = new ArrayList<>();
        fields.add(new InputableUiStringField("가게 이름", ""));
        fields.add(new InputableUiStringField("가게 설명", ""));
        return fields;
    }
}
