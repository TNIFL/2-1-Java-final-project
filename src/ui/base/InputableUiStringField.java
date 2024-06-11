package ui.base;

import ui.base.InputableUiField;

import java.util.Scanner;

public class InputableUiStringField extends InputableUiField<String> {
    public InputableUiStringField(String label, String content) {
        super(label, content);
    }

    @Override
    public void scan(Scanner scanner) {
        setContent(scanner.nextLine());
    }
}
