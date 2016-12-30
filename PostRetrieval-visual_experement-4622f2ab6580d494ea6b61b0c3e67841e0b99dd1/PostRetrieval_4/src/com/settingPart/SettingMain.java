package com.settingPart;

import com.RestartProgramm;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SettingMain extends Application {

    public static Stage settingStage;

    @Override
    public void start(Stage settingStage) throws Exception {
        this.settingStage = settingStage;
        Parent root = FXMLLoader.load(getClass().getResource("settingSample.fxml"));
        settingStage.setTitle("Настройки");
        settingStage.setScene(new Scene(root, 160, 150));
        settingStage.show();

        settingStage.setOnCloseRequest( (e) ->{System.out.println("Return Main Window");
            RestartProgramm.restart();});
    }
}




