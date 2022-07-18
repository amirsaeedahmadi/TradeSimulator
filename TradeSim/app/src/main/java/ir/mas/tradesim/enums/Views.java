package ir.mas.tradesim.enums;

public enum Views {
    REGISTER_VIEW("Register View"),
    MAIN_VIEW("Main View"),
    TRANSACTION_VIEW("Transaction View"),
    SCOREBOARD_VIEW("Scoreboard View");

    public final String label;

    Views(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
