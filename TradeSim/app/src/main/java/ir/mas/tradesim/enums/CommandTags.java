package ir.mas.tradesim.enums;

import java.util.Locale;

public enum CommandTags {

    SHOW_SCOREBOARD("show_scoreboard"),
    LOGIN("login"),
    GET_CURRENCIES("get_currencies"),
    REGISTER("register"),
    LOGOUT("logout"),
    GET_PRICE("get_price"),
    GET_CREDIT("get_credit");

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
