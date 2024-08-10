package edu.sdccd.cisc191;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TaskApp extends Application {
    private TaskManager taskManager;
    private ToDoList todoList;
    private TextField taskInput;
    private ComboBox<String> taskList;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        taskManager = new TaskManager(5, 2);
        todoList = new ToDoList();

        taskInput = new TextField();
        Button addButton = new Button("Add Task");
        addButton.setOnAction(e -> {
            String newTask = taskInput.getText();
            if (!newTask.isEmpty()) {
                taskManager.addTask(0, 0, newTask);
                todoList.addTask(newTask);
                taskList.getItems().add(newTask);
                System.out.println("Task added: " + newTask);
            }
        });

        taskList = new ComboBox<>();
        taskList.setPromptText("Select Task");

        Button deleteButton = new Button("Delete Task");
        deleteButton.setOnAction(e -> {
            String selectedTask = taskList.getValue();
            if (selectedTask != null) {
                taskManager.deleteTask(0, 0);
                todoList.completeTask(todoList.getTaskIndex(selectedTask));
                taskList.getItems().remove(selectedTask);
                System.out.println("Task deleted: " + selectedTask);
            }
        });

        Button updateButton = new Button("Update Task");
        updateButton.setOnAction(e -> {
            String selectedTask = taskList.getValue();
            String updatedTask = taskInput.getText();
            if (selectedTask != null && !updatedTask.isEmpty()) {
                taskManager.updateTask(0, 0, updatedTask);
                System.out.println("Task updated: " + updatedTask);
            }
        });

        VBox root = new VBox(10);
        root.getChildren().addAll(taskInput, addButton, taskList, deleteButton, updateButton);

        Scene scene = new Scene(root, 200, 200);

        primaryStage.setTitle("Task List GUI");
        primaryStage.setScene(scene);
        primaryStage.show();

        performAction();
    }

    private void performAction() {
        System.out.println("Tasks currently stored:");
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 2; j++) {
                String task = taskManager.getTask(i, j);
                if (task != null) {
                    System.out.println("Task at row " + i + ", column " + j + ": " + task);
                }
            }
        }

        System.out.println("Tasks in the to-do list:");
        todoList.displayTasks();
    }
}