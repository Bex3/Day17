package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.util.Scanner;

import static java.lang.String.valueOf;

import jodd.json.JsonParser;
import jodd.json.JsonSerializer;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Scanner inputScanner = new Scanner(System.in);
        System.out.println("Please enter your name");
        String userName = String.valueOf(inputScanner.nextLine());
        System.out.println("UserName " + userName);
        //if (userName == )



        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
