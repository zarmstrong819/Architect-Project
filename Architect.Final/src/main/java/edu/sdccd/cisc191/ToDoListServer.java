package edu.sdccd.cisc191;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class ToDoListServer {
    private ArrayList<String> tasks;

    public ToDoListServer() {
        tasks = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
    }

    public void addTask(String task) {
        tasks.add(task);
        Collections.sort(tasks);
        System.out.println("Task added: " + task);
    }

    public void displayTasks() {
        System.out.println("Tasks in the to-do list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    public int searchTask(String task) {
        int index = Collections.binarySearch(tasks, task);
        return index;
    }

    public void startServer() {
        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Server started. Waiting for clients...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket);

                ClientHandler clientHandler = new ClientHandler(clientSocket, this);
                Thread clientThread = new Thread(clientHandler);
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ToDoListServer todoListServer = new ToDoListServer();
        todoListServer.startServer();
    }
}

class ClientHandler implements Runnable {
    private Socket clientSocket;
    private ToDoListServer todoListServer;

    public ClientHandler(Socket clientSocket, ToDoListServer todoListServer) {
        this.clientSocket = clientSocket;
        this.todoListServer = todoListServer;
    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Client message: " + inputLine);

                // Add task logic
                if (inputLine.startsWith("ADD:")) {
                    String task = inputLine.substring(4);
                    todoListServer.addTask(task);
                    out.println("Task added: " + task);
                }
                // Display tasks logic
                else if (inputLine.equals("DISPLAY")) {
                    todoListServer.displayTasks();
                    out.println("Tasks displayed in the to-do list.");
                }
                // Search task logic
                else if (inputLine.startsWith("SEARCH:")) {
                    String task = inputLine.substring(7);
                    int searchIndex = todoListServer.searchTask(task);
                    if (searchIndex >= 0) {
                        out.println("Task found at index: " + searchIndex);
                    } else {
                        out.println("Task not found.");
                    }
                }
            }

            in.close();
            out.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}