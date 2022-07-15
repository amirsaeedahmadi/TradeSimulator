package ir.mas.tradesim.enums;

public enum Responses {
    MENU_ENTER_NOT_ALLOWED("please login first"),
    INVALID_COMMAND("invalid command"), SUCCESS("Successful");


    public final String label;

    Responses(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
