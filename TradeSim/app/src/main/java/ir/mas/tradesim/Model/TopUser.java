package ir.mas.tradesim.Model;

import java.util.ArrayList;

import ir.mas.tradesim.Exceptions.TooMuchTopUsersException;

public class TopUser {
    String nickname;
    double totalEquivalentRial;
    int rank;
    boolean isTheUser = false;

    static ArrayList<TopUser> topUsers = new ArrayList<TopUser>();

    public TopUser(String nickname, double totalEquivalentRial, int rank) throws TooMuchTopUsersException {
        this.nickname = nickname;
        this.totalEquivalentRial = totalEquivalentRial;
        this.rank = rank;
        if (topUsers.size() >= 10) {
            throw new TooMuchTopUsersException();
        }
        topUsers.add(this);
    }

    public TopUser(String nickname, double totalEquivalentRial, int rank, boolean isTheUser) throws TooMuchTopUsersException {
        this.nickname = nickname;
        this.totalEquivalentRial = totalEquivalentRial;
        this.rank = rank;
        this.isTheUser = isTheUser;
        if (topUsers.size() >= 10) {
            throw new TooMuchTopUsersException();
        }
        topUsers.add(this);
    }

    public static void addTheUser(int rank) throws TooMuchTopUsersException {
        User user = User.getInstance();
        new TopUser(user.getNickname(), user.rialEquivalent, rank, true);
    }

    public static void deleteAllTopUsers() {
        topUsers = new ArrayList<TopUser>();
    }

    /**
     * It should get the data from the server, but now it is just for test
     * TODO: to modify ...*/
    public static void initialize(){
        deleteAllTopUsers();
        try {
            new TopUser("ali", 1_000_000_000, 1);
            new TopUser("mahdi", 600_000_000, 2);
            new TopUser("hadi", 570_300_000, 3);
            addTheUser(4);
            new TopUser("hasan", 570_000_000, 5);
            new TopUser("hashem", 500_000_000, 6);
            new TopUser("mohsen", 480_534_342,7);
        } catch (TooMuchTopUsersException e) {
            e.printStackTrace();
        }
    }
    // getters
    public String getNickname() {
        return nickname;
    }

    public double getTotalEquivalentRial() {
        return totalEquivalentRial;
    }

    public int getRank() {
        return rank;
    }

    public static ArrayList<TopUser> getTopUsers() {
        return topUsers;
    }
}
