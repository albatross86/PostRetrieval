package com.VisualClasses;


import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;



import com.retrievalLogic.AllRetrievalPull;

import java.util.List;


/**
 * Created by Alex on 23.10.2016.
 */
public class WorkingWindow extends GridPane {


    private final int weidth  = 45;
    private int height = 205;

    private GeneralPane yandexPane;
    private GeneralPane mailPane;
    private GeneralPane yahooPane;
    private GeneralPane gmailPane;
    private GeneralPane settingPane;

    private Stage stage = new Stage();// Для MyTooltip

    AllRetrievalPull allRetrievalPull = new AllRetrievalPull(this);

    public WorkingWindow(List<String> list){

        yandexPane = new GeneralPane(45, 40, "yandexPane", "yandex", "hint");
        mailPane = new GeneralPane(45, 40, "mailPane", "mail", "hint");
        yahooPane = new GeneralPane(45, 40, "yahooPane", "yahoo", "hint");
        gmailPane = new GeneralPane(45, 40, "gmailPane", "gmail", "hint");
        settingPane = new GeneralPane(45, 45, "settingPane", "setting");

        addChildren( list);

        getStylesheets().add("com/css/style.css");
        getStyleClass().add("working_window");

        getScaleX();

    }

    private void addChildren(List<String> list){

        int height = 45; // Cтандартная высота панели для Setting окна.

        if (list.get(11).equals("true")) {add(yandexPane, 2, 1); height += 40;}
        if (list.get(5).equals("true")) {add(mailPane, 2, 2); height += 40;}
        if (list.get(8).equals("true")) {add(yahooPane, 2, 3); height += 40;}
        if (list.get(2).equals("true")) {add(gmailPane, 2, 4); height += 40;}

        add(settingPane, 2, 5);

        setPrefSize(40, height);
    }

//*********************** Меняет картинку с обычной, на картинку со значком NEW или Warning в зависимости от состояния процесса
    public void setYandexNewMessage(String state){

        switch (state) {

            case "newMessage" :
                yandexPane.getStyleClass().add("yandexPaneNew") ; break;

            case "notNewMessage" :
                yandexPane.getStyleClass().add("yandexPaneNotNewMessage"); break;
            case "error" :
                yandexPane.getStyleClass().add("yandexPaneWarning"); break;
        }



    }

    public void setMailNewMessage(String state){

        switch (state) {

            case "newMessage":
                mailPane.getStyleClass().add("mailPaneNew"); break;

            case "notNewMessage":
                mailPane.getStyleClass().add("mailPaneNotNewMessage"); break;

            case "error" :
                mailPane.getStyleClass().add("mailPaneWarning"); break;
        }

    }


    public void setYahooNewMessage(String state){

        switch (state) {

            case "newMessage":
                yahooPane.getStyleClass().add("yahooPaneNew"); break;

            case "notNewMessage" :
                yahooPane.getStyleClass().add("yahooPaneNotNewMessage"); break;

            case "error" :
                yahooPane.getStyleClass().add("yahooPaneWarning"); break;
        }

    }

    public void setGmailNewMessage(String state){

        switch (state) {

            case "newMessage":
                gmailPane.getStyleClass().add("gmailPaneNew"); break;

            case "notNewMessage" :
                gmailPane.getStyleClass().add("gmailPaneNotNewMessage"); break;

            case "error" :
               gmailPane.getStyleClass().add("gmailPaneWarning"); break;
    }
    }

    public void startRetrieve(){

        allRetrievalPull.startRetrieve();
    }

    public void stopRetrieve(){

        allRetrievalPull.stopRetrieve();
    }

    public void readMessage(String string){

        allRetrievalPull.markAllMessageReader(string);
    }





}
