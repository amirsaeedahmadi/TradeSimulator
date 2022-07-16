package ir.mas.tradesim.server;


import org.json.JSONException;
import org.json.JSONObject;

import ir.mas.tradesim.server.controller.ServerMainController;



import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Core extends Thread{
    private static ArrayList<TaskBlock> tasks;


    public Core(ArrayList<TaskBlock> tasks){
         Core.tasks = tasks;
    }

    @Override
    public void run() {
        while (true) {
            TaskBlock taskBlock;

            if ((taskBlock = getBlock()) != null) {
                try {
                    DataInputStream dataInputStream = new DataInputStream(taskBlock.getSocket().getInputStream());
                    DataOutputStream dataOutputStream = new DataOutputStream(taskBlock.getSocket().getOutputStream());
                    ServerResponse response = new ServerResponse();
                    Server.log("processing...");
                    new ServerMainController().processCommand(new JSONObject(dataInputStream.readUTF()), response);
                    dataOutputStream.writeUTF(response.getResponse().toString());
                    dataOutputStream.flush();
                    Server.log("-> " + response.getResponse().toString());
                    dataInputStream.close();
                    dataOutputStream.close();
                    taskBlock.getSocket().close();
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }else{
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static synchronized TaskBlock getBlock(){
        if (tasks.size() == 0) return null;
        return tasks.remove(0);
    }
}
