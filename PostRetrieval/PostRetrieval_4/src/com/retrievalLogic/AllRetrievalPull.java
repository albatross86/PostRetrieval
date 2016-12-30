package com.retrievalLogic;


import com.SaveLoadSequrity.DataLoader;
import com.SaveLoadSequrity.JsonClass;
import com.VisualClasses.WorkingWindow;
import com.sound.NotificationSound;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by Alex on 30.07.2016.
 */
public class AllRetrievalPull {



    int[] unreadMessage = new int[4];       // Массивы для сравнения кол -ва непрочитанных сообщений
    int[] currentUnreadMessage = new int[4];//

    boolean flagForSaund = false; //

    WorkingWindow parent;

    private Timeline timeline_start;
    private Timeline timeline;

    // для проверки писем и воспроизведения звука о входящих.
    private boolean flag = false;

    private List<String> readyData;

    NotificationSound notification = new NotificationSound();

    ExecutorService service;

    MailRetrieval mailRetrievalGmail;
    MailRetrieval mailRetrievalMail ;
    MailRetrieval mailRetrievalYahoo ;
    MailRetrieval mailRetrievalYandex;

    MarkAsReader markAsReaderGmail;
    MarkAsReader markAsReaderMail;
    MarkAsReader markAsReaderYahoo;
    MarkAsReader markAsReaderYandex;


    public AllRetrievalPull(WorkingWindow workingWindow) {

        parent = workingWindow;
    }

    // Этод метод самый главный
    public void startRetrieve() {

        // Вызываю сборщик мусора чтоб поудалять результаты и обьекты с прошедших проверок
        Runtime.getRuntime().gc();

        //Создаю экземпляры классов которые будут проверять каждый свою почту
        initialize_instance();

        //Задаю количество одновременно выполняемых потоков
        service = Executors.newFixedThreadPool(2);

        //Это планировщик задач который проверяет почту раз в 2 минуты
        KeyFrame keyFrame = new KeyFrame(Duration.millis(20000), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event)
            {
                retrieve();

            }
        });

        //Это планировщик который проверяет почту только один - первый раз, чтоб не ждать 2 минуты до первой проверки
        KeyFrame keyFrame_for_start = new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            //    System.out.println("Start retrieve");
                retrieve();

                // После проверки почты один раз сам себя закрывает и больше не повторяется
                timeline_start.stop();
            }
        });

        timeline_start = new Timeline(keyFrame_for_start);
        timeline_start.setCycleCount(1);
        timeline_start.play();

        // Это задание для проверки почт каждые 2 минуты, использую timeline я могу динамически изменять данные GUI
        timeline = new Timeline(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    // Метод который фактически проверяет почты, а главное получает результаты проверок
    private void retrieve(){

        //Вот прям здесь всё и происходит

                            // Вот этот service фактически являеться Thread-ом для проверки каждой почты
        Future resultGmail = service.submit(mailRetrievalGmail);
        Future resultMail = service.submit(mailRetrievalMail);
        Future resultYahoo = service.submit(mailRetrievalYahoo);
        Future resultYandex = service.submit(mailRetrievalYandex);

        try {
            // Проверяю есть ли вообще непрочитанные письма
            if ((Integer) resultGmail.get() > 0) {

                // Заполняем массив кол-ом новых сообщений
                currentUnreadMessage[0] = (int) resultGmail.get();

                //Если есть непрочитанные передаём в Интерфейс программы картинку с названием newMessage,
                //Оно там само через css подставляет нужную картинку.
                parent.setGmailNewMessage("newMessage");

                // Если проверка почты вернула (-1) - код ошибки
            } else if ((Integer) resultGmail.get() == -1) {

                // Передаём в интерфейс картинку с ошибкой
                parent.setGmailNewMessage("error");

                //Если нет ни ошибки ни непрочитанных, то просто передаём в интерфейс стандартную картинку с логотипом почты.
            } else  parent.setGmailNewMessage("notNewMessage");


        }  catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        try{
            // Проверяю есть ли вообще непрочитанные письма
            if ((Integer) resultMail.get() > 0) {

                // Заполняем массив кол-ом новых сообщений
                currentUnreadMessage[1] = (int) resultMail.get();

                //Если есть непрочитанные передаём в Интерфейс программы картинку с названием newMessage,
                //Оно там само через css подставляет нужную картинку.
                parent.setMailNewMessage("newMessage");

                // Если проверка почты вернула (-1) - код ошибки
            }else if((Integer)resultMail.get() == -1){

                // Передаём в интерфейс картинку с ошибкой
            parent.setMailNewMessage("error");

                //Если нет ни ошибки ни непрочитанных, то просто передаём в интерфейс стандартную картинку с логотипом почты.
            } else parent.setMailNewMessage("notNewMessage");

        }  catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

         try{
             // Проверяю есть ли вообще непрочитанные письма
            if ((Integer) resultYahoo.get() > 0) {

                // Заполняем массив кол-ом новых сообщений
                currentUnreadMessage[2] = (int) resultYahoo.get();

                //Если есть непрочитанные передаём в Интерфейс программы картинку с названием newMessage,
                //Оно там само через css подставляет нужную картинку.
                parent.setYahooNewMessage("newMessage");

                // Если проверка почты вернула (-1) - код ошибки
            }else if((Integer)resultYahoo.get() == -1){

                // Передаём в интерфейс картинку с ошибкой
            parent.setYahooNewMessage("error");

                //Если нет ни ошибки ни непрочитанных, то просто передаём в интерфейс стандартную картинку с логотипом почты.
           } else parent.setYahooNewMessage("notNewMessage");

         }  catch (ExecutionException | InterruptedException e) {
             e.printStackTrace();
         }

         try{
             // Проверяю есть ли вообще непрочитанные письма
            if ((Integer) resultYandex.get() > 0) {

                // Заполняем массив кол-ом новых сообщений
                currentUnreadMessage[3] = (int) resultYandex.get();

                //Если есть непрочитанные передаём в Интерфейс программы картинку с названием newMessage,
                //Оно там само через css подставляет нужную картинку.
                parent.setYandexNewMessage("newMessage");

                // Если проверка почты вернула (-1) - код ошибки
            }else if((Integer)resultYandex.get() == -1){

                // Передаём в интерфейс картинку с ошибкой
            parent.setYandexNewMessage("error");

                //Если нет ни ошибки ни непрочитанных, то просто передаём в интерфейс стандартную картинку с логотипом почты.
            }  else parent.setYandexNewMessage("notNewMessage");
        }
        catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        // Проводим сравнение кол -ва непрочитанных сообщений, и устанавливаем флаг проигрывания музыки
        for(int index = 0; index < 4; index++){

            countMessageComparator(unreadMessage[index], currentUnreadMessage[index], index);
        }

        // Перезапишем файл сохранения
        reWriteSaveFile();

        // Метод для музыки
        playMusic();
    }

    // Для перезаписи файла сохранения
    private void reWriteSaveFile() {
        JsonClass jsonClass = new JsonClass();

        jsonClass.setUnreadMessageCount(unreadMessage[0], unreadMessage[1], unreadMessage[2], unreadMessage[3]);
        jsonClass.createJson();
    }

    // В этом методе инициализируем обьекты для проверки почты
    private void initialize_instance() {

        // Сначала загрузим все данные для работы прогаммы из файла сохранения
        DataLoader dataLoader = new DataLoader();

        // Получили все данные уже расшифрованые, и готовые для передачи в конструктор
        readyData = dataLoader.getReadyData();

        //  Передали все данные в экземпляры классов, можно стартовать проверку.
        mailRetrievalGmail = new MailRetrieval("imap.gmail.com", readyData.get(0), readyData.get(1));
        mailRetrievalMail = new MailRetrieval("imap.mail.ru", readyData.get(3), readyData.get(4));
        mailRetrievalYahoo = new MailRetrieval("imap.mail.yahoo.com", readyData.get(6), readyData.get(7));
        mailRetrievalYandex = new MailRetrieval("imap.yandex.ru", readyData.get(9), readyData.get(10));

        unreadMessage[0] = Integer.parseInt(readyData.get(18));
        unreadMessage[1] = Integer.parseInt(readyData.get(18));
        unreadMessage[2] = Integer.parseInt(readyData.get(18));
        unreadMessage[3] = Integer.parseInt(readyData.get(18));




    }

    // Этот метод завершает всек проверки и потоки
    public void stopRetrieve(){

        timeline_start.stop();
        timeline.stop();
        service.shutdown();
    }

    // Отмечает все письма "прочитанными"
    public  void markAllMessageReader(String string){

        markAsReaderGmail = new MarkAsReader("imap.gmail.com", readyData.get(0), readyData.get(1));
        markAsReaderMail = new MarkAsReader("imap.mail.ru", readyData.get(3), readyData.get(4));
        markAsReaderYahoo = new MarkAsReader("imap.mail.yahoo.com", readyData.get(6), readyData.get(7));
        markAsReaderYandex = new MarkAsReader("imap.yandex.ru", readyData.get(9), readyData.get(10));

        switch (string){
            case "gmail" : markAsReaderGmail.start(); parent.setGmailNewMessage("notNewMessage"); break;
            case "mail"  : markAsReaderMail.start(); parent.setMailNewMessage("notNewMessage"); break;
            case "yahoo" : markAsReaderYahoo.start(); parent.setYahooNewMessage("notNewMessage");break;
            case "yandex": markAsReaderYandex.start(); parent.setYandexNewMessage("notNewMessage"); break;
        }

    }


    void playMusic(){
        notification.flagStatusCheck(flagForSaund); //
        flagForSaund = false;
    }

    // Сравнивает кол-во непрочитанных сообщений, и устанавливает флаг для проигрывания музыки
    private void countMessageComparator(int x, int y, int index){

        if(x > y){

            unreadMessage[index] = y;

        } else if(x < y){

            unreadMessage[index] = y;
            flagForSaund = true;
        }


    }
}




