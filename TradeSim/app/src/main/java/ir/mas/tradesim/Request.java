package ir.mas.tradesim;

import android.net.wifi.WifiManager;

import ir.mas.tradesim.enums.CommandTags;
import ir.mas.tradesim.enums.Views;
import ir.mas.tradesim.enums.Regexes;
import ir.mas.tradesim.enums.Responses;

import org.json.JSONException;
import org.json.JSONObject;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import android.annotation.SuppressLint;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class Request {
    public static JSONObject request = new JSONObject();
    public static JSONObject response;
    public static String token = null;
    public static Thread Thread1 = null;

    public static void getToken() throws JSONException {
        token = response.getString("token");
    }

    public static JSONObject getRequest() {
        return request;
    }

    public static String giveToken() {
        return token;
    }

    public static void addData(String key, String value) throws JSONException { // adding data with key and value
        request.put(key, value);
    }

    public static void setCommandTag(CommandTags commandTag) throws JSONException { // set the request main command
        request.put("command", commandTag.getLabel());
    }

    public static void setCurrentMenu(Views currentMenu) throws JSONException {
        request.put("view", currentMenu.getLabel());
    }

    public static void setOption(String command, String option) throws JSONException { // extract option if available
        addBooleanData(option, command.contains("--" + option));
    }

    public static String getMessage() throws JSONException {
        return response.getString("message");
    }

    public static void extractData(String command) throws JSONException { // extract data from the input with the "--key value" format
        Pattern pattern = Pattern.compile(Regexes.DATA.getLabel());
        Matcher matcher = pattern.matcher(command);
        while (matcher.find())
            request.put(matcher.group(1), matcher.group(2).trim());
    }


    public static void sendToServer() {

        try {
            // TODO: after preparing registration uncomment next line
//            Request.setToken();

            Socket socket = null;

            socket = new Socket("192.168.1.52", 7755);

//            Socket socket = new Socket("0.tcp.ngrok.io", 15169);
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream.writeUTF(request.toString());
            dataOutputStream.flush();
            String result = dataInputStream.readUTF();
            System.out.println(result);
            response = new JSONObject(result);
            dataOutputStream.close();
            System.out.println("server job finished");
            socket.close();
            clear();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

    }


    static class SendToServer implements Runnable {
        public void run() {
            try {
                // TODO: after preparing registration uncomment next line
//            Request.setToken();

                Socket socket = null;

                socket = new Socket("192.168.1.52", 7755);

//            Socket socket = new Socket("0.tcp.ngrok.io", 15169);
                DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                dataOutputStream.writeUTF(request.toString());
                dataOutputStream.flush();
                String result = dataInputStream.readUTF();
                System.out.println(result);
                response = new JSONObject(result);
                dataOutputStream.close();
                System.out.println("server job finished");
                socket.close();
                clear();
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
        }
    }


    public static JSONObject getResponse() {
        return response;
    }

    public static void clear() {
        request = new JSONObject();
    }

    private static void setToken() throws JSONException {
        request.put("token", token);
    }

    public static boolean isSuccessful() throws JSONException { // check whether the command was successful or not
        return response.getString("type").equals(Responses.SUCCESS.getLabel());
    }

    public static void addDataToRequest(String regex, String command, String key) throws JSONException {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(command);
        if (matcher.find()) {
            Request.addData(key, matcher.group(1));
        }
    }


    public static boolean doesHaveKey(String key) {
        return response.has(key);
    }

    public static void addBooleanData(String option, boolean bool) throws JSONException {
        request.put(option, bool);
    }
}
