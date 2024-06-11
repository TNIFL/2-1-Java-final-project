package ui.base;

public class SelectableUiListItem {
    private int number;
    private String content;
    private Runnable runnable;

    public SelectableUiListItem(int number, String content, Runnable runnable) {
        this.number = number;
        this.content = content;
        this.runnable = runnable;
    }

    public int getNumber() {
        return number;
    }

    public String getContent() {
        return content;
    }

    public Runnable getRunnable() {
        return runnable;
    }
}
