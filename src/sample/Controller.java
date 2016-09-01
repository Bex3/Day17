package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import jodd.json.JsonParser;
import jodd.json.JsonSerializer;

import java.io.File;
import java.io.FileWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    ListView todoList;

    @FXML
    TextField todoText;

    ObservableList<ToDoItem> todoItems = FXCollections.observableArrayList(); // observable list that allows two way data binding with the UI

    // myController = new Controller();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        todoList.setItems(todoItems); //set items in the list view to the the todoitems this links the View and model in the Controller
        //view            //arraylist
    }

    public void addItem() {
        System.out.println("Adding item ...");
        //todoItems.add(new ToDoItem("Sample")); //adds only sample over & over as add is pressed
        todoItems.add(new ToDoItem(todoText.getText()));
        todoText.setText(""); //clears it
    }

    public void handleEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            addItem();
        }
    }

    public void removeItem() {
        //System.out.println("Removing item ...");
        ToDoItem todoItem = (ToDoItem) todoList.getSelectionModel().getSelectedItem(); //selection model returns selected model - returns both the text and the entire object
        System.out.println("Removing " + todoItem.text + " ...");
        todoItems.remove(todoItem);
    }

    public void toggleItem() {
        System.out.println("Toggling item ...");
        ToDoItem todoItem = (ToDoItem) todoList.getSelectionModel().getSelectedItem();
        if (todoItem != null) {
            todoItem.isDone = !todoItem.isDone;
            todoList.setItems(null);
            todoList.setItems(todoItems);
        }
    }

    public void toggleAllItemsDone() {
        System.out.println("Toggling all items to done ...");
        for (ToDoItem currentItem : todoItems) {
            //System.out.println("Items " + currentItem);
            currentItem.isDone = true;
            todoList.setItems(null);
            todoList.setItems(todoItems);
        }
        System.out.println("selected all done");
    }

    public void toggleAllItemsNotDone() {
        System.out.println("Toggling all items to not done ...");
        ToDoItem todoItem = (ToDoItem) todoList.getSelectionModel().getSelectedItem();
        for (ToDoItem currentItem : todoItems) {
            //System.out.println("Items " + currentItem);
            currentItem.isDone = false;
            todoList.setItems(null);
            todoList.setItems(todoItems);
        }
        System.out.println("selected all done");
    }

    public void toggleAllOpposite() {
        System.out.println("We are gonna swap your items");
        ToDoItem todoItem = (ToDoItem) todoList.getSelectionModel().getSelectedItem();
        for (ToDoItem currentItem : todoItems) {
            //System.out.println("Items " + currentItem);
            if (currentItem.isDone = !currentItem.isDone) {
                //!currentItem.isDone = currentItem.isDone;
                todoList.setItems(null);
                todoList.setItems(todoItems);
                //}else if (!currentItem.isDone = currentItem.isDone) {
                //  todoList.setItems(null);
                //todoList.setItems(todoItems);
            }

        }
        System.out.println("The great switch up");

    }

    public String jsonSave(ToDoItem todoToSave) { //this generates the json string - must save that string to file
        JsonSerializer jsonSerializer = new JsonSerializer().deep(true);
        String jsonString = jsonSerializer.serialize(todoToSave);

        return jsonString;
    }
    public void saveFile(String jsonString){
        try {
            File jsonStringFile = new File("json test.json");
            FileWriter jsonStringFileWriter = new FileWriter(jsonStringFile);
            jsonStringFileWriter.write(jsonString);
            jsonStringFileWriter.close();
        } catch (Exception exception) {
            System.out.println("Exception while writing to file ...");
        }
    }

    public ToDoItem jsonRestore(String jsonTD) {
        JsonParser toDoItemParser = new JsonParser();
        ToDoItem item = toDoItemParser.parse(jsonTD, ToDoItem.class);
        return item;
    }

    public void writeJsonItem(){

    }











}