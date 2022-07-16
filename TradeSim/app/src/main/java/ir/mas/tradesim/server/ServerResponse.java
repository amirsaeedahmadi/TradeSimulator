package ir.mas.tradesim.server;


import org.json.JSONException;
import org.json.JSONObject;

import ir.mas.tradesim.server.enums.Responses;


public class ServerResponse {
    private  JSONObject response = new JSONObject();
    private  String token;

    public  void addMessage(String message) throws JSONException {
        response.put("message", message);
    }

    public  JSONObject getResponse() {
        return response;
    }

    public boolean isSuccess() throws JSONException {
        return response.get("type").equals(Responses.SUCCESS.getLabel());
    }


    public  void success() throws JSONException {
        response.put("type", Responses.SUCCESS.getLabel());
    }

    public  void add(String key, String value) throws JSONException {
        response.put(key, value);
    }

    public  void error() throws JSONException {
        response.put("type", Responses.ERROR.getLabel());
    }

    public  void addObject(String key, JSONObject jsonObject) throws JSONException {
        response.put(key, jsonObject);
    }

    public  void addToken(String generatedToken) throws JSONException {
        token = generatedToken;
        response.put("token", generatedToken);
    }
}
