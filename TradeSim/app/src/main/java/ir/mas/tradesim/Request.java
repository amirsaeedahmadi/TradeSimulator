package ir.mas.tradesim;

//import Controller.MainController;
import ir.mas.tradesim.enums.CommandTags;
import ir.mas.tradesim.enums.Regexes;
import ir.mas.tradesim.enums.Responses;

import org.json.JSONException;
import org.json.JSONObject;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Request {
//    private static JSONObject request = new JSONObject();
//    private static JSONObject response;
//
//    public static void addData(String key, String value) { // adding data with key and value
//        request.put(key, value);
//    }
//
//    public static void setCommandTag(CommandTags commandTag) { // set the request main command
//        request.put("command", commandTag.getLabel());
//    }
//
//    public static void extractData(String command) { // extract data from the input with the "--key value" format
//
//        Pattern pattern = Pattern.compile(Regexes.DATA.getLabel());
//        Matcher matcher = pattern.matcher(command);
//        while (matcher.find()) {
//
//            if (matcher.group(1).equals("username")) {
//                new CurrentUser(matcher.group(2));
//            }
//            request.put(matcher.group(1), matcher.group(2));
//        }
//    }
//
//    public static void send() { // sending the request to the main controller
//        response = new JSONObject(MainController.processCommand(request.toString()));
//        clear();
//    }
//
//    public static String getResponse() {
//        return response.getString("message");
//    }
//
//    public static void clear() {
//        request = new JSONObject();
//    }
//
//    public static boolean isSuccessful() throws JSONException { // check whether the command was successful or not
//        return response.getString("type").equals(Responses.SUCCESS.getLabel());
//    }
}
