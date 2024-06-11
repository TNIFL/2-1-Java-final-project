package ui.base;

import java.util.List;
import java.util.Scanner;

public abstract class InputableUiBase implements Runnable {
    private List<InputableUiField> fields;
    private Runnable before;

    public abstract Runnable getRunnable();

    public abstract String getTitle();

    public abstract List<InputableUiField> initializeFields();

    public InputableUiBase(Runnable before) {
        this.before = before;
    }

    @Override
    public void run() {
        setFields(initializeFields());
        processFields();
        System.out.println();
        runWithFields();
        getBefore().run();
    }

    private void processFields() {
        System.out.println("===========================");
        System.out.println(getTitle());
        System.out.println("---------------------------");
        Scanner scanner = new Scanner(System.in);
        for (InputableUiField field : getFields()) {
            System.out.printf(field.getLabel() + " : ");
            field.scan(scanner);
        }
    }

    private void runWithFields() {
        getRunnable().run();
    }

    public Runnable getBefore() {
        return before;
    }

    public List<InputableUiField> getFields() {
        return fields;
    }

    public void setFields(List<InputableUiField> fields) {
        this.fields = fields;
    }
}
