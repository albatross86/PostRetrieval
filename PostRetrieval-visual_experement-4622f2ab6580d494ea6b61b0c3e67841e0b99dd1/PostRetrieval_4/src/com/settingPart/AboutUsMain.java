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
public class AboutUsMain extends Application {

    public static Stage aboutUsStage;
    @Override
    public void start(Stage primaryStage) throws Exception {

        aboutUsStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("aboutUs.fxml"));
        aboutUsStage.setTitle("О нас");
        aboutUsStage.setScene(new Scene(root, 335, 200));
        aboutUsStage.show();

        aboutUsStage.setOnCloseRequest( (e) ->{System.out.println("Return Main Window");
            RestartProgramm.restart();});


    }
}
