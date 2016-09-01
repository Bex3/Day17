package sample;

import jodd.json.JsonParser;
import jodd.json.JsonSerializer;

import java.io.*;

/**
 * Created by bearden-tellez on 8/31/16.
 */
public class SerializationTester {

    final String DATA_FILE_NAME = "todo.dat";

    public static void main(String[] args) {
        System.out.println("Running serialization tester ...");

        new SerializationTester().test();
    }

    public void test() {
        try {
            System.out.println("test()");

            ToDoItem testTD = new ToDoItem("Test Serialization", false);
            System.out.println("test TD = " + testTD);
            saveTD(testTD);

            ToDoItem retrievedTD = restoreTD();
            System.out.println("retrieved TD = " + retrievedTD);

            String jsonTD = jsonSave(testTD);
            System.out.println("JSON ToDo = " + jsonTD);

            ToDoItem restoredFromJSON = jsonRestore(jsonTD);
            System.out.println("Restored from JSON = " + restoredFromJSON);

        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        }

    }

    public void saveTD(ToDoItem todoToSave) throws IOException {
        FileOutputStream fos = new FileOutputStream(DATA_FILE_NAME);
        ObjectOutput objectOut = new ObjectOutputStream(fos);
        objectOut.writeObject(todoToSave);
        objectOut.flush();
    }

    public ToDoItem restoreTD() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(DATA_FILE_NAME);
        ObjectInputStream objectIn = new ObjectInputStream(fis);
        ToDoItem restoredTD = (ToDoItem)objectIn.readObject();

        return restoredTD;
    }
    public String jsonSave(ToDoItem todoToSave) {
        JsonSerializer jsonSerializer = new JsonSerializer().deep(true);
        String jsonString = jsonSerializer.serialize(todoToSave);

        return jsonString;
    }

    public ToDoItem jsonRestore(String jsonTD) {
        JsonParser toDoItemParser = new JsonParser();
        ToDoItem item = toDoItemParser.parse(jsonTD, ToDoItem.class);

        return item;
    }

}