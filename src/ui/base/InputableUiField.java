package ui.base;

import java.util.Scanner;

public abstract class InputableUiField<T> {
    private String label;

    private T content;

    public abstract void scan(Scanner scanner);

    public InputableUiField(String label, T content) {
        this.label = label;
        this.content = content;
    }

    public String getLabel() {
        return label;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }
}
