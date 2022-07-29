package ir.mas.tradesim.model;



import androidx.annotation.NonNull;

import java.util.LinkedList;

import ir.mas.tradesim.exceptions.NotEnoughValueException;


/*StartActivity.userDao.deleteUsers();
        StartActivity.userDao.insert(new UserDb(User.getInstance().getAuthToken(),
        User.getInstance().getNickname(), User.getInstance().getRialCredit(),
        User.getInstance().getRialEquivalent(),
        new Gson().toJson(User.getInstance().throughTime)));
        StartActivity.userList = StartActivity.userDao.getAllUsers();*/
public class User {

    private static User instance;
    private String authToken;
    String nickname;
    double rialCredit;
    double rialEquivalent;
    boolean darkMode;
    public LinkedList<String> throughTime;



    public User( String authToken, String nickname, double rialCredit, double rialEquivalent) {
        this.authToken = authToken;
        this.nickname = nickname;
        this.rialCredit = rialCredit;
        this.rialEquivalent = rialEquivalent;
        this.darkMode = false;
        this.throughTime = new LinkedList<>();
        this.throughTime.add(rialCredit + "");
    }

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
            instance = new User ("AUTH TOKEN","NICKNAME", 0, 0);
        }
        return instance;
    }

    public static void deleteInstance() {
        instance = null;
    }

    //getters and setters

    public boolean isDarkMode() {
        return darkMode;
    }

    public void setDarkMode(boolean darkMode) {
        this.darkMode = darkMode;
    }

    @NonNull
    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(@NonNull String authToken) {
        this.authToken = authToken;
    }

    public static void setInstance(User instance) {
        User.instance = instance;
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

    public void setNickname(@NonNull String nickname) {
        this.nickname = nickname;
    }
}
