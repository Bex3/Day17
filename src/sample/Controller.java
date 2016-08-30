package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{
    @FXML
    ListView todoList;

    @FXML
    TextField todoText;

    ObservableList<ToDoItem> todoItems = FXCollections.observableArrayList(); // observable list that allows two way data binding with the UI

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        todoList.setItems(todoItems); //set items in the list view to the the todoitems this links the View and model in the Controller
        //view            //arraylist
    }

    public void addItem(KeyEvent event, MouseEvent e) {
        System.out.println("Adding item ...");
        //todoItems.add(new ToDoItem("Sample")); //adds only sample over & over as add is pressed
        if (event.getCode() == KeyCode.ENTER) {
            todoItems.add(new ToDoItem(todoText.getText()));
        } else if (e.equals("Add")) {
            todoItems.add(new ToDoItem(todoText.getText()));
        }

        todoText.setText(""); //clears it
    }

    public void removeItem() {
        //System.out.println("Removing item ...");
        ToDoItem todoItem = (ToDoItem)todoList.getSelectionModel().getSelectedItem(); //selection model returns selected model - returns both the text and the entire object
        System.out.println("Removing " + todoItem.text + " ...");
        todoItems.remove(todoItem);
    }

    public void toggleItem() {
        System.out.println("Toggling item ...");
        ToDoItem todoItem = (ToDoItem)todoList.getSelectionModel().getSelectedItem();
        if (todoItem != null) {
            todoItem.isDone = !todoItem.isDone;
            todoList.setItems(null);
            todoList.setItems(todoItems);
        }
    }


}
