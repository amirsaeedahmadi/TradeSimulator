package ir.mas.tradesim.server.controller;


import org.json.JSONException;
import org.json.JSONObject;

import ir.mas.tradesim.server.ServerResponse;


public class ServerScoreBoardController {

    private static ServerResponse response;

    public static void processCommand(JSONObject request, ServerResponse response) throws JSONException {
        ServerScoreBoardController.response = response;

        String commandTag = request.getString("command");

//        if (commandTag.equals(CommandTags.SHOW_SCOREBOARD.getLabel()))
//            response.addMessage(new Gson().toJson(getScoreboard()));
//        else if (commandTag.equals(CommandTags.GET_ONLINE_USERS.getLabel()))
//            response.addMessage(new Gson().toJson(Server.getOnlineUsers()));
    }




//    private static ArrayList<User> getScoreboard() {
//        response.success();
//        ArrayList<User> users = rankUsers(sortUsers(User.getUsers()));
//
//        return users;
//    }


//    public static String stringify(ArrayList<User> users) {
//        return users.toString().substring(1, users.toString().length() - 1).replace(", ", "\n");
//    }
}