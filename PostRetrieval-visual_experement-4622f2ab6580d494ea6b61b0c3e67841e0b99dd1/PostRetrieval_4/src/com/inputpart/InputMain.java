package com.inputpart;

import com.RestartProgramm;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class InputMain extends Application {
    
    public static Stage inputStage;

    @Override
    public void start(Stage inputStage) throws Exception {
        this.inputStage = inputStage;
        Parent root = FXMLLoader.load(getClass().getResource("inputSample.fxml"));
        inputStage.setTitle("Ввод данных");
        inputStage.setScene(new Scene(root, 405, 400));
        inputStage.show();

        inputStage.setOnCloseRequest( (e) ->{System.out.println("Return Main Window");
                                                RestartProgramm.restart();});
    }
}

