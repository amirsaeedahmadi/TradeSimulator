package ir.mas.tradesim.enums;

public enum Strings {
//    USERNAME("Register Menu"),
//    PASSWORD("Profile Menu"),
//    NICKNAME("Main Menu");
    PRIVATE_KEY("token"),
    NICKNAME("Nickname"),
    RIAL_CREDIT("RialCredit"),
    CURRENCY_AMOUNT("currency_amount"),
    RIAL_AMOUNT("rial_amount"),
    TRANSACTION_ID("transaction_id"),
    TRANSACTION("transaction"),
    CURRENCY_CODE("CurrencyCode");


    public final String label;

    Strings(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
