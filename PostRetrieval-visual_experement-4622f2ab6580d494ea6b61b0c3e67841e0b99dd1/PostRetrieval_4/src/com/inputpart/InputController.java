package com.inputpart;

import com.RestartProgramm;
import com.SaveLoadSequrity.DataLoader;
import com.SaveLoadSequrity.JsonClass;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class InputController {

    @FXML
    TextField yandexLogin;
    @FXML
    PasswordField yandexPassword;
    @FXML
    CheckBox yandexCheckBox;
    @FXML
    TextField mailLogin;
    @FXML
    PasswordField mailPassword;
    @FXML
    CheckBox mailCheckBox;
    @FXML
    TextField yahooLogin;
    @FXML
    PasswordField yahooPassword;
    @FXML
    CheckBox yahooCheckBox;
    @FXML
    TextField gmailLogin;
    @FXML
    PasswordField gmailPassword;
    @FXML
    CheckBox gmailCheckBox;

    private JsonClass jsonClass = new JsonClass();

    @FXML private void initialize() {



        DataLoader dataLoader = new DataLoader();
        List<String> data_for_fields_FXML = dataLoader.getReadyData();

        gmailLogin.setText(data_for_fields_FXML.get(0));
        gmailPassword.setText(data_for_fields_FXML.get(1));
        gmailCheckBox.setSelected(Boolean.parseBoolean(data_for_fields_FXML.get(2)));

        mailLogin.setText(data_for_fields_FXML.get(3));
        mailPassword.setText(data_for_fields_FXML.get(4));
        mailCheckBox.setSelected(Boolean.parseBoolean(data_for_fields_FXML.get(5)));

        yahooLogin.setText(data_for_fields_FXML.get(6));
        yahooPassword.setText(data_for_fields_FXML.get(7));
        yahooCheckBox.setSelected(Boolean.parseBoolean(data_for_fields_FXML.get(8)));

        yandexLogin.setText(data_for_fields_FXML.get(9));
        yandexPassword.setText(data_for_fields_FXML.get(10));
        yandexCheckBox.setSelected(Boolean.parseBoolean(data_for_fields_FXML.get(11)));
    }


    public void done(){

        readDataFromFields_and_WritingFile();
        restart();

    }

    public void reset(){

        resetData();
    }

    public void discard(){

        restart();

    }


    public void readDataFromFields_and_WritingFile() {
        System.out.println("accept");

        jsonClass.setGmail_log(gmailLogin.getText());
        jsonClass.setGmail_password(gmailPassword.getText());
        jsonClass.setGmail_checkbox(gmailCheckBox.isSelected());

        jsonClass.setMail_log(mailLogin.getText());
        jsonClass.setMail_password(mailPassword.getText());
        jsonClass.setMail_checkbox(mailCheckBox.isSelected());

        jsonClass.setYahoo_log(yahooLogin.getText());
        jsonClass.setYahoo_password(yahooPassword.getText());
        jsonClass.setYahoo_checkbox(yahooCheckBox.isSelected());

        jsonClass.setYandex_log(yandexLogin.getText());
        jsonClass.setYandex_password(yandexPassword.getText());
        jsonClass.setYandex_checkbox(yandexCheckBox.isSelected());

        jsonClass.createJson();

    }

    // Очень непонятный кусок кода который перезапускает программу, но работает только как JAR, в Idea не работает. Прикрутил Сергей.
    private void restart(){

        RestartProgramm.restart();
    }

    public void resetData() {

        gmailLogin.setText("null");
        gmailPassword.setText("null");
        gmailCheckBox.setSelected(true);

        mailLogin.setText("null");
        mailPassword.setText("null");
        mailCheckBox.setSelected(true);

        yahooLogin.setText("null");
        yahooPassword.setText("null");
        yahooCheckBox.setSelected(true);

        yandexLogin.setText("null");
        yandexPassword.setText("null");
        yandexCheckBox.setSelected(true);

        jsonClass.setGmail_log(gmailLogin.getText());
        jsonClass.setGmail_password(gmailPassword.getText());
        jsonClass.setGmail_checkbox(gmailCheckBox.isSelected());

        jsonClass.setMail_log(mailLogin.getText());
        jsonClass.setMail_password(mailPassword.getText());
        jsonClass.setMail_checkbox(mailCheckBox.isSelected());

        jsonClass.setYahoo_log(yahooLogin.getText());
        jsonClass.setYahoo_password(yahooPassword.getText());
        jsonClass.setYahoo_checkbox(yahooCheckBox.isSelected());

        jsonClass.setYandex_log(yandexLogin.getText());
        jsonClass.setYandex_password(yandexPassword.getText());
        jsonClass.setYandex_checkbox(yandexCheckBox.isSelected());

        jsonClass.createJson();

        System.out.println("reset");

    }


}
