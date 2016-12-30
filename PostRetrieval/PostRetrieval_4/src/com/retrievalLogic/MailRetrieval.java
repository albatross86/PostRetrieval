package com.retrievalLogic;

import javax.mail.*;
import java.util.Properties;
import java.util.concurrent.Callable;

/**
 * Created by Alex on 22.05.2016.
 */

public class MailRetrieval implements Callable {

    private String host;
    private String username;
    private String password;
    private Folder folder;
    private Integer error = -1;

    public MailRetrieval(String host, String username, String password) {

        this.host = host;
        this.username = username;
        this.password = password;

    }


    @Override
    public Integer call() throws Exception {

        Integer unreadMessageCount = 0;

        if (!(username.equals("null") || password.equals("null"))) {

            //  Выводит сообщение в зависимости от используемого почтового сервера
            switch (host) {
                case "imap.gmail.com":
                    System.out.println("Connecting to the Gmail server...");
                    break;
                case "imap.mail.ru":
                    System.out.println("Connecting to the Mail server...");
                    break;
                case "imap.mail.yahoo.com":
                    System.out.println("Connecting to the Yahoo server...");
                    break;
                case "imap.yandex.ru":
                    System.out.println("Connecting to the Yandex server...");
                    break;

            }

            Properties properties = System.getProperties();
            Session session = Session.getInstance(properties);
            Store store = session.getStore("imaps"); // Не знаю почему IMAPS а не IMAP

            //Возвращает -1 если коннекция не удалась.
          try {
              store.connect(host, username, password);
          }catch (Exception ex){

              return  error;
          }

            folder = store.getFolder("Inbox"); // Просматриваем папку ВХОДЯЩИЕ
            folder.open(Folder.READ_WRITE);  // Можно и читать и удалять

            unreadMessageCount = folder.getUnreadMessageCount();

        }
        return unreadMessageCount;
    }

}


