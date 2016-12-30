package com.controller;


import com.Run;
import com.settingPart.SettingMain;
import javafx.stage.Stage;

/**
 * Created by olexiy86 on 10/31/16.
 */
public class Controller {

    private Browser browser;

    public Controller(){

         browser = new Browser();

    }

    public void mouseClickedEvent(String button, String id) {

        if(button.equals("PRIMARY")){

            gotowebsite(id); // функция отправляет на сайт
        }
        else if(button.equals("SECONDARY")){

            System.out.println("SECONDARY" + id);
            markMailAsReaded(id); //функция очищает папку непрочитанные
        }

    }

    // функция отправляет на сайт
    private void gotowebsite(String id) {

        switch (id){

            case "yandex" :
                browser.openBrowser("https://mail.yandex.ua"); // Открываем браузер по умолчанию
                break;

            case "mail" :
                browser.openBrowser("https://mail.ru");
                break;

            case "yahoo" :
              browser.openBrowser("https://ua-mg42.mail.yahoo.com");
                break;

            case "gmail" :
              browser.openBrowser("https://mail.google.com");
                break;

            case "setting":
                openSettingWindow();
                break;

            case "close":
                System.exit(0);
                break;
        }
    }



    // Открываем SettingWindow и closim основное окно
    public void openSettingWindow(){

        Run.primaryStage.close();

        SettingMain settingMain = new SettingMain();
        try {
            settingMain.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //функция очищает папку непрочитанные
    private void markMailAsReaded(String id) {

        Run.makeAllMessageAsReader(id);
    }
}
