package ir.mas.tradesim.model;

import java.util.ArrayList;

import ir.mas.tradesim.exceptions.TooMuchTopUsersException;


public class TopUser {

    String nickname;
    double totalEquivalentRial;
    int rank;
    boolean isTheUser = false;
    static ArrayList<TopUser> topUsers = new ArrayList<TopUser>();


    public TopUser(String nickname, double totalEquivalentRial, int rank, boolean isTheUser) {
        this.nickname = nickname;
        this.totalEquivalentRial = totalEquivalentRial;
        this.rank = rank;
        this.isTheUser = isTheUser;
        topUsers.add(this);
    }


    public static void deleteAllTopUsers() {
        topUsers = new ArrayList<TopUser>();
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

    public static void setTopUsers(ArrayList<TopUser> topUsers) {
        TopUser.topUsers = topUsers;
    }
}