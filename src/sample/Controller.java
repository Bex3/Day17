package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import jodd.json.JsonParser;
import jodd.json.JsonSerializer;

import java.io.File;
import java.io.FileWriter;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Controller implements Initializable {
    @FXML
    ListView todoList;

    @FXML
    TextField todoText;

    ObservableList<ToDoItem> todoItems = FXCollections.observableArrayList(); // observable list that allows two way data binding with the UI

    public String username;
    String filename = "todo.json";


    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        todoList.setItems(todoItems); //set items in the list view to the the todoitems this links the View and model in the Controller
        //view            //arraylist
        Scanner inputScanner = new Scanner(System.in);

        System.out.println("Please enter your name");
        username = inputScanner.nextLine();
        System.out.println("UserName " + username);
        if (username != null) {
            filename = username + ".json";
        }
        System.out.println("Checking for your list");
        Container userExistingList = getList();

        if (userExistingList != null) {
            for (ToDoItem item : userExistingList.toDoItems) {
                todoItems.add(item);
            }
        }
        todoList.setItems(todoItems);
    }

    public void addItem() {
        System.out.println("Adding item ...");
        //todoItems.add(new ToDoItem("Sample")); //adds only sample over & over as add is pressed
        todoItems.add(new ToDoItem(todoText.getText()));
        todoText.setText(""); //clears it

        serializeAndsaveFile();

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

//    public String jsonSave(ToDoItem todoToSave) { //this generates the json string - must save that string to file
//        JsonSerializer jsonSerializer = new JsonSerializer().deep(true);
//        String jsonString = jsonSerializer.serialize(todoToSave);
//
//        return jsonString;
//    }

    public void serializeAndsaveFile() {
        try {
            JsonSerializer jsonSerializer = new JsonSerializer().deep(true);
            Container tempContainer = new Container(todoItems);
            String jsonString = jsonSerializer.serialize(tempContainer);

            File jsonStringFile = new File(filename);
            FileWriter jsonStringFileWriter = new FileWriter(jsonStringFile);
            jsonStringFileWriter.write(jsonString);
            System.out.println(jsonString);
            jsonStringFileWriter.close();
        } catch (Exception exception) {
            System.out.println("Exception while writing to file ...");
        }
    }


    public Container getList() {
        try {
            Scanner fileScanner = new Scanner(new File(filename));
            while (fileScanner.hasNext()) {
                String currentLine = fileScanner.nextLine();
                JsonParser toDoItemParser = new JsonParser();
                Container pExistingToDo = toDoItemParser.parse(currentLine, Container.class);
                return pExistingToDo;
            }

        } catch (Exception exception) {
            exception.printStackTrace();

        }
        return null;
    }

/*
    public ToDoItem jsonRestore(String jsonTD) {
        JsonParser toDoItemParser = new JsonParser();
        ToDoItem item = toDoItemParser.parse(jsonTD, ToDoItem.class);
        return item;
    }*/

/*    public void readJsonItem(){
            try {
                File jsonStringFile = new File("test.json");
                Scanner fileScanner = new Scanner(jsonStringFile);
                while (fileScanner.hasNext()) {
                    String currentLine = fileScanner.nextLine();
                    currentLine.add
                }

            }catch (Exception exception) {
                exception.printStackTrace();
            }
        return ...........;
    }*/
}