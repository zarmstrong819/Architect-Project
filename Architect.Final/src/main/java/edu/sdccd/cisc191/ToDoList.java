package edu.sdccd.cisc191;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class ToDoList {
    private ArrayList<String> tasks;
    private Scanner scanner;

    public ToDoList() {
        tasks = new ArrayList<>();
        scanner = new Scanner(System.in);
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

    public void addTaskFromInput() {
        System.out.print("Enter a task: ");
        String task = scanner.nextLine();
        addTask(task);
    }

    public static void main(String[] args) {
        ToDoList todoList = new ToDoList();

        todoList.addTask("Buy groceries");
        todoList.addTask("Finish homework");
        todoList.addTask("Call mom");

        todoList.displayTasks();

        todoList.addTaskFromInput();

        todoList.displayTasks();

        System.out.print("Enter a task to search: ");
        String searchTask = new Scanner(System.in).nextLine();
        int searchIndex = todoList.searchTask(searchTask);
        if (searchIndex >= 0) {
            System.out.println("Task found at index: " + searchIndex);
        } else {
            System.out.println("Task not found.");
        }
    }


    public int getTaskIndex(String selectedTask) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).equals(selectedTask)) {
                return i;
            }
        }
        return -1;
    }

    public void completeTask(int taskIndex) {
    }
}