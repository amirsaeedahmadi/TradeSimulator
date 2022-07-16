package ir.mas.tradesim.server.enums;

public enum Responses {

    SUCCESS("Successful"),
    ERROR("Error"),
    REGISTER_SUCCESSFUL("You registered successfully!"),
    LOGIN_SUCCESSFUL("user logged in successfully!"),
    SUCCESSFUL("It's been successful!");


    public final String label;

    Responses(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
