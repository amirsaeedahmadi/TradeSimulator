package ir.mas.tradesim.Model;

import java.util.ArrayList;

import ir.mas.tradesim.Exceptions.NotEnoughValueException;

public class User {
    String username = "<USERNAME>";
    String nickname = "<NICKNAME>";
    ArrayList<Currency> currencies = new ArrayList<Currency>();
    double rialCredit = 0;
    double rialEquivalent = 0;

//    public void User(String username, ArrayList<Currency> currencies, rial) {}

    private static User instance;
    private User(){}//TODO: to modify and get it from database/sharedPrefs or the server

    public void increaseRialCredit(Double amount) {
        rialCredit += amount;
    }
    public void decreaseRialCredit(Double amount) throws NotEnoughValueException {
        if (rialCredit >= amount)
            rialCredit -= amount;
        else throw new NotEnoughValueException();
    }

    public static User getInstance() {
        if (instance == null) {
            instance = new User();
        }
        return instance;
    }
    public static void deleteInstance() {
        instance = null;
    }

    //getters and setters

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ArrayList<Currency> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(ArrayList<Currency> currencies) {
        this.currencies = currencies;
    }

    public double getRialCredit() {
        return rialCredit;
    }

    public void setRialCredit(double rialCredit) {
        this.rialCredit = rialCredit;
    }

    public double getRialEquivalent() {
        return rialEquivalent;
    }

    public void setRialEquivalent(double rialEquivalent) {
        this.rialEquivalent = rialEquivalent;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
