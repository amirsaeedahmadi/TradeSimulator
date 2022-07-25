package ir.mas.tradesim.enums;

public enum Views {
    REGISTER_VIEW("Register View"),
    PROFILE_VIEW("Profile View"),
    MAIN_VIEW("Main View"),
    SCOREBOARD_VIEW("Scoreboard View");
    public final String label;

    Views(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
