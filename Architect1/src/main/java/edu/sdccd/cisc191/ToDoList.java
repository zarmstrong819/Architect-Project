package edu.sdccd.cisc191;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;
import javafx.scene.Scene;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ToDoList extends Application {

    //Creating a button
    Button button;

    private ObservableList<String> ToDoList;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage){
        stage.setTitle("To Do List");
        //Setting the button to say "Add"
        button = new Button();
        button.setText("Add");

        StackPane layout = new StackPane();
        layout.getChildren().add(button);


        ObservableList<String> ToDoList = FXCollections.observableArrayList();

        ListView<String> listView = new ListView<>(ToDoList);

        //Making a textfield so whenever ObservableList<String> ToDoList is updated it is reflected
        TextField inputField = new TextField();
        button.setOnAction(e -> {
            ToDoList.add(inputField.getText());
            inputField.setText("");
            inputField.requestFocus();
        });


        //creating our HBox
        HBox entryBox = new HBox();
        entryBox.getChildren().addAll(inputField, button);

        //Creating vBox
        VBox vBox = new VBox();
        vBox.getChildren().addAll(listView, entryBox);

        //Shows our scene
        Scene scene = new Scene(vBox);
        stage.setScene(scene);
        stage.show();
    }
}
