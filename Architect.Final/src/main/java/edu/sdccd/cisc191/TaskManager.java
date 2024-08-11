package edu.sdccd.cisc191;

public class TaskManager {
    private String[][] tasks;

    public TaskManager(int rows, int columns) {
        this.tasks = new String[rows][columns];
    }

    public void addTask(int row, int column, String task) {
        tasks[row][column] = task;
    }

    public String getTask(int row, int column) {
        return tasks[row][column];
    }

    public void deleteTask(int row, int column) {
        tasks[row][column] = null;
    }

    public void updateTask(int row, int column, String updatedTask) {
        if (row >= 0 && row < tasks.length && column >= 0 && column < tasks[0].length) {
            tasks[row][column] = updatedTask;
            System.out.println("Task updated at row " + row + ", column " + column + ": " + updatedTask);
        } else {
            System.out.println("Invalid row or column index provided for updating task.");
        }
    }
}