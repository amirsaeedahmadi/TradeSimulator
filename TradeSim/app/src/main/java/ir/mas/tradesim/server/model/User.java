package ir.mas.tradesim.server.model;



import java.util.ArrayList;
import java.util.HashMap;

import ir.mas.tradesim.Exceptions.NotEnoughValueException;

public class User {

    private static final HashMap<String, User> users;
    private static final ArrayList<String> nicknames;

    static {
        users = new HashMap<>();
        nicknames = new ArrayList<>();
    }

    private ArrayList<Transaction> auctions;
    private Wallet wallet;
    private String activeDeck;
    private String password;
    private String username;
    private String nickname;
    private int score;
    private int rank;

    public User(String username, String password) {
        // TODO: CREATE USERS BASED ON DATABASE INFORMATION
    }

    double rialCredit = 0;
    double rialEquivalent = 0;


    public void increaseRialCredit(Double amount) {
        rialCredit += amount;
    }
    public void decreaseRialCredit(Double amount) throws NotEnoughValueException {
        if (rialCredit >= amount)
            rialCredit -= amount;
        else throw new NotEnoughValueException();
    }



    //getters and setters

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
