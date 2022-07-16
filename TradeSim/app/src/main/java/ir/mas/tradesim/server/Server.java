package ir.mas.tradesim.server;


import ir.mas.tradesim.server.model.User;

import java.io.IOException;
import java.net.ServerSocket;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;


public class Server {

    private static final int PORT = 7755;

    private static ThreadPool threadPool;
    private static HashMap<String, User> onlineUsers;
    private static int numberOfOnlineUsers;
    private ServerSocket serverSocket;

    public static void main(String[] args) {
        numberOfOnlineUsers = 0;
//        Database.prepareDatabase();
        onlineUsers = new HashMap<>();
        runServer();
    }

    public static int getNumberOfOnlineUsers() {
        return numberOfOnlineUsers;
    }

    public static ArrayList<String> getOnlineUsers() {
        ArrayList<String> usernames = new ArrayList<>();
        onlineUsers.values().forEach(u -> usernames.add(u.getUsername()));
        return usernames;
    }

    private static void runServer() {
        threadPool = new ThreadPool(2);
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            log("listening on port " + PORT);
            while (true){
                threadPool.process(serverSocket.accept());
                log("accepted");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void log(String string) {
        System.out.println("\u001B[31m" + LocalDate.now() + " | " + System.currentTimeMillis() + "_" + numberOfOnlineUsers + "\u001B[0m : " + string);
    }

    public static void addUser(String token, User user){
        onlineUsers.put(token, user);
        numberOfOnlineUsers++;
    }

    public static User getUser(String token){
        return onlineUsers.get(token);
    }

    public static void logout(String token) {
        onlineUsers.remove(token);
        numberOfOnlineUsers--;
    }
}