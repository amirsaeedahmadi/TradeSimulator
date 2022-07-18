package ir.mas.tradesim.enums;

import java.util.Locale;

public enum CommandTags {

    SHOW_SCOREBOARD("show scoreboard"),
    LOGIN("login"),
    REGISTER("register"),
    LOGOUT("logout");

    public final String label;

    CommandTags(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public static CommandTags fromValue(String givenName) {
        for (CommandTags tag : values()){
            if (tag.label.toLowerCase(Locale.ROOT).equals(givenName.toLowerCase(Locale.ROOT)))
                return tag;
        }
        return null;
    }
}
