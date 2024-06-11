package ui.base;

import java.util.List;
import java.util.Scanner;

public abstract class SelectableUiBase implements Runnable {
    private Runnable before;

    public abstract String getTitle();
    public abstract List<SelectableUiListItem> getListItems();

    public SelectableUiBase(Runnable before) {
        this.before = before;
    }

    @Override
    public void run() {
        displayMenu();
        int selected = selectNumber();
        System.out.println();
        runSelected(selected);
    }

    void displayMenu() {
        System.out.println("===========================");
        System.out.println(getTitle());
        System.out.println("---------------------------");
        if (null != getDescription()) {
            System.out.println(getDescription());
            System.out.println("---------------------------");
        }
        for (SelectableUiListItem listItem : this.getListItems()) {
            System.out.println(listItem.getNumber() + ". " + listItem.getContent());
        }
    }

    int selectNumber() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("---------------------------");
        System.out.printf("번호 선택 : ");
        return scanner.nextInt();
    }

    void runSelected(int selectedNumber) {
        for (SelectableUiListItem listItem : this.getListItems()) {
            if (listItem.getNumber() == selectedNumber) {
                listItem.getRunnable().run();
                return;
            }
        }
        this.run();
    }

    public Runnable getBefore() {
        return before;
    }

    public String getDescription() {
        return null;
    }
}
