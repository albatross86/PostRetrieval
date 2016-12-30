package com.checkForUpdate;

import com.GeneralInformationData;
import com.RestartProgramm;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.io.IOException;
import java.util.Optional;

/**
 * Created by Alex on 30.06.2016.
 */
public class OfferDownloadNewVersion {

    public void showDialog() {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        alert.setTitle("Ready to update");

        alert.setHeaderText("Update Program?");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK){

            // Здесь будем вызывать программу для обновления.


            try {
                Runtime.getRuntime().exec("java -jar " + GeneralInformationData.getCurrentPath());

            } catch (IOException e) {
                e.printStackTrace();
            }


            System.exit(0);
        } else {

            RestartProgramm.restart();


        }

    }

}
