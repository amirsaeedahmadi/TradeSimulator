package ir.mas.tradesim.server.controller;


import org.json.JSONException;
import org.json.JSONObject;

import ir.mas.tradesim.server.Server;
import ir.mas.tradesim.server.ServerResponse;
import ir.mas.tradesim.server.enums.Views;
import ir.mas.tradesim.server.model.User;


public class ServerMainController {
    public void processCommand(JSONObject request, ServerResponse response) throws JSONException {

        String view = request.getString("view");
        String token = null;
        User user = null;

        if (request.has("token")) {
            token = request.getString("token");
            user = Server.getUser(token);
        }

        if (view.equals(Views.REGISTER_VIEW.getLabel())) {
            ServerRegistrationController.processCommand(request, response);
        } else if (view.equals(Views.MAIN_VIEW.getLabel())) {
            Server.logout(token);
        } else if (view.equals(Views.TRANSACTION_VIEW.getLabel())) {
            ServerTransactionController.processCommand(request, response, user);
        } else if (view.equals(Views.SCOREBOARD_VIEW.getLabel())) {
            ServerScoreBoardController.processCommand(request, response);
        }

    }
}
