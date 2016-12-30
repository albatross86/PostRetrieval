package com.settingPart;

import com.RestartProgramm;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by Алексей on 26.11.2016.
 */
public class AboutMain extends Application {

    public static Stage aboutStage;
    @Override
    public void start(Stage primaryStage) throws Exception {

        aboutStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("about.fxml"));
        aboutStage.setTitle("О программе");
        aboutStage.setScene(new Scene(root, 315, 350));
        aboutStage.show();

        aboutStage.setOnCloseRequest( (e) ->{System.out.println("Return Main Window");
            RestartProgramm.restart();});



    }
}
