package sample;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bearden-tellez on 9/1/16.
 */
public class Container {
    public ArrayList<ToDoItem> getToDoItems() {
        return toDoItems;
    }

    public void setToDoItems(ArrayList<ToDoItem> toDoItems) {
        this.toDoItems = toDoItems;
    }

    public ArrayList <ToDoItem> toDoItems = new ArrayList <> ();

//    public Container (List<ToDoItem> listReceived) {
//        for (ToDoItem todoItem : listReceived) {
//            toDoItems.add(todoItem);
//        }
//    }

    public Container() {
    }
}

