package ir.mas.tradesim.server;

import java.net.Socket;
import java.util.ArrayList;

public class ThreadPool {

    private ArrayList<TaskBlock> tasks;

    public ThreadPool(int numberOfThreads) {
        tasks = new ArrayList<>();
        for (int i = 0; i < numberOfThreads; i++) {
            System.out.println("creating thread " + i);
            new Core(tasks).start();
        }
    }

    public void process(Socket socket) {
        tasks.add(new TaskBlock(socket));
    }
}
