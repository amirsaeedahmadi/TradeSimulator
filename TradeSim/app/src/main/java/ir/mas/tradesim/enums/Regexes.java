package ir.mas.tradesim.enums;

public enum Regexes {
    MENU_EXIT("menu exit"),
    MENU_CURRENT("show current menu"),
    MENU_ENTER("menu enter (.*)"),
    CREATE_USER("^user create (?=.*(--(username) (\\w+)))(?=.*(--(password) (\\w+)))(?=.*(--(nickname) (\\w+))).*$"),
    LOGIN_USER("^user login (?=.*(--(username) (\\w+)))(?=.*(--(password) (\\w+))).*$"),
    CHANGE_PROFILE_NICKNAME("^profile change (?=.*(--(nickname) (\\w+))).*$"),
    CHANGE_PROFILE_PASSWORD("^profile change --password (?=.*(--(current) (\\w+)))(?=.*(--(new) (\\w+))).*$"),
    DATA("--(\\w+) (\\w+)");

    public final String label;

    Regexes(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}

