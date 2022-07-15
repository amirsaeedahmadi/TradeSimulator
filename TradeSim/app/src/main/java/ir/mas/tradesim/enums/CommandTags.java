package ir.mas.tradesim.enums;

public enum CommandTags {
    LOGIN("login"),
    REGISTER("register"),
    CHANGE_PASSWORD("change-password"),
    CHANGE_NICKNAME("change-nickname");
    public final String label;

    CommandTags(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
