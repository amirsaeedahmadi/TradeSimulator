package ir.mas.tradesim.server.controller;


import org.json.JSONException;
import org.json.JSONObject;

import ir.mas.tradesim.server.ServerResponse;
import ir.mas.tradesim.server.model.User;


public class ServerTransactionController {



    private static ServerResponse response;

    public static void processCommand(JSONObject request, ServerResponse response, User user) throws JSONException {
        ServerTransactionController.response = response;

        String commandTag = request.getString("command");


//        if (commandTag.equals(CommandTags.BUY.getLabel()))
//            response.addMessage(buy(request.getString("currency"), user));
//        else if (commandTag.equals(CommandTags.SELL.getLabel()))
//            response.addMessage(sell(...));
    }

//    private static String buy(String currency, User user) {
//
//
//    }
}