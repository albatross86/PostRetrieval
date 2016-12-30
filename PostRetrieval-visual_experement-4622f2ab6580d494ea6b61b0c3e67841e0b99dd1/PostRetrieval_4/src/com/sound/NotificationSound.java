package com.sound;

import javafx.application.Platform;


import java.awt.*;


/**
 * Created by Sergei on 27.08.2016.
 * Класс воспроизводит файл в формате .mp3 с помощью бибилиотеки JLayer.
 */
public class NotificationSound {
    // переместил в этот класс, т.к. он отвечает за уведомления.
   public static java.awt.TrayIcon trayIcon;


    /********************************************************************************/
    // Воспроизведение звука. Пока отключил так как хватает системного звукового уведомления
//    private void play() {
//
//
//        try {
//            FileInputStream fileInputStream = new FileInputStream("src/com/sound/WavLibraryNet_AlertTone14.mp3");
//            Player player = new Player(fileInputStream);
//            player.play();
//        } catch (FileNotFoundException | JavaLayerException e) {
//            e.printStackTrace();
//        }
//    }


    /********************************************************************************/
    // проверка статуса флага. Если тру - звук и уведомление в трее.
    public void flagStatusCheck(boolean flag){
        if (flag){
            showInfoMessage("У Вас есть новые сообщения!");
           // play();
        }
    }


    /********************************************************************************/
    // показывает информационное сообщение в трее.
    public void showInfoMessage(String message){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                trayIcon.displayMessage("Post Retrieval", message, TrayIcon.MessageType.INFO);
            }
        });
    }
}
