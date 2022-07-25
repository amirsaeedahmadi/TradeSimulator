package ir.mas.tradesim.enums;

public enum Views {
    REGISTER_VIEW("Register Menu"),
    PROFILE_VIEW("Profile Menu"),
    MAIN_VIEW("Main Menu"),
    SOME_VIEW("Some View"),
    SCOREBOARD_VIEW("Scoreboard Menu");
    public final String label;

    Views(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
