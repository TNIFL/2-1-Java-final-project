package ui.base;

import ui.base.InputableUiField;

import java.util.Scanner;

public class InputableUiIntegerField extends InputableUiField<Integer> {
    public InputableUiIntegerField(String label, int content) {
        super(label, content);
    }

    @Override
    public void scan(Scanner scanner) {
        setContent(scanner.nextInt());
        scanner.nextLine();
    }
}
