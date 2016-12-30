package com.retrievalLogic;

import javax.mail.*;
import java.util.Properties;

/**
 * Created by Алексей on 24.11.2016.
 */
public class MarkAsReader extends  Thread {

    private String host;
    private String username;
    private String password;
    private Folder folder;


    public MarkAsReader(String host, String username, String password) {

        this.host = host;
        this.username = username;
        this.password = password;

    }

    public  void run() {

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
        }

            Properties properties = System.getProperties();
            Session session = Session.getInstance(properties);
        Store store = null; // Не знаю почему IMAPS а не IMAP
        try {
            store = session.getStore("imaps");
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }

        //Возвращает -1 если коннекция не удалась.

        try {
            store.connect(host, username, password);
        } catch (MessagingException e) {
            e.printStackTrace();
        }


        try {
            folder = store.getFolder("Inbox"); // Просматриваем папку ВХОДЯЩИЕ
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        try {
            folder.open(Folder.READ_WRITE);  // Можно и читать и удалять
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        try {
            for (Message mess : folder.getMessages()) {


                mess.setFlag(Flags.Flag.SEEN, true);


            }
        } catch (MessagingException e) {
            e.printStackTrace();
        }


    }

    }

