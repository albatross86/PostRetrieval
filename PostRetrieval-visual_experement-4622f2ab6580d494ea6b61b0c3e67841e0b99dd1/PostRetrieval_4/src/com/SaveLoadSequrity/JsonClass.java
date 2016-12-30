package com.SaveLoadSequrity;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Alex on 10.06.2016.
 */
public class JsonClass {

    private static String gmail_log;
    private static String gmail_password;
    private static String gmail_checkbox;

    private static String mail_log;
    private static String mail_password;
    private static String mail_checkbox;

    private static String yahoo_log;
    private static String yahoo_password;
    private static String yahoo_checkbox;

    private static String yandex_log;
    private static String yandex_password;
    private static String yandex_checkbox;

    private static double coordinatesX = 500;
    private static double coordinatesY = 250;

    private static long firstLaunchDate;// Записываеться только при первом запуске, и потом не изменяеться.
    private static boolean trial_bool = true; // Флаг для проверки 5 дней;
    private static boolean license_bool = false; // флаг для проверки была ли регистрация

    private static int unreadMessageCount0; // Сохраняет массив с кол-ом непрочитанных сообщений.
    private static int unreadMessageCount1;
    private static int unreadMessageCount2;
    private static int unreadMessageCount3;

    SecuritySettings securitySettings = new SecuritySettings();
    DataSaver dataSaver = new DataSaver();


    // Cоздаёт Json
    // Мне нужно сделать независимыми функции сохранения данных отвечающие за вход в почту и координаты появления главного окна,
    // А еще нужно сделать это сложное простым

    public void createJson() {

        JSONArray jsonArrayGmail = new JSONArray();
        JSONArray jsonArrayMail = new JSONArray();
        JSONArray jsonArrayYahoo = new JSONArray();
        JSONArray jsonArrayYandex = new JSONArray();
        JSONArray jsonArrayCoordinates = new JSONArray();
        JSONArray jsonArrayFirstLaunchDate = new JSONArray();
        JSONArray jsonArrayUnreadMessageCount = new JSONArray();

        JSONObject jsonObject = new JSONObject();

        jsonArrayGmail.add(gmail_log);
        jsonArrayGmail.add(gmail_password);
        jsonArrayGmail.add(gmail_checkbox);
        jsonObject.put("gmail", jsonArrayGmail);

        jsonArrayMail.add(mail_log);
        jsonArrayMail.add(mail_password);
        jsonArrayMail.add(mail_checkbox);
        jsonObject.put("mail", jsonArrayMail);

        jsonArrayYahoo.add(yahoo_log);
        jsonArrayYahoo.add(yahoo_password);
        jsonArrayYahoo.add(yahoo_checkbox);
        jsonObject.put("yahoo", jsonArrayYahoo);

        jsonArrayYandex.add(yandex_log);
        jsonArrayYandex.add(yandex_password);
        jsonArrayYandex.add(yandex_checkbox);
        jsonObject.put("yandex", jsonArrayYandex);

        jsonArrayCoordinates.add(coordinatesX);
        jsonArrayCoordinates.add(coordinatesY);
        jsonObject.put("coordinates", jsonArrayCoordinates);

        jsonArrayFirstLaunchDate.add(firstLaunchDate);
        jsonArrayFirstLaunchDate.add(trial_bool);
        jsonArrayFirstLaunchDate.add(license_bool);
        jsonObject.put("firstLaunchDate", jsonArrayFirstLaunchDate);

        jsonArrayUnreadMessageCount.add(unreadMessageCount0);
        jsonArrayUnreadMessageCount.add(unreadMessageCount1);
        jsonArrayUnreadMessageCount.add(unreadMessageCount2);
        jsonArrayUnreadMessageCount.add(unreadMessageCount3);
        jsonObject.put("unreadMessageCaunt", jsonArrayUnreadMessageCount);


        // Здесь мы вызовим метод для кодировки и сохранения данных в файле

        dataSaver.setDataToFile(securitySettings.encrypt(jsonObject.toString())); // Здесь всё кодируеться и записываеться в файл

     //   dataSaver.setDataToFile(jsonObject.toString()); // Здесь всё записываеться но не kодируется

    }

    public void createStandartJsonFile(){

        JSONArray jsonArrayGmail = new JSONArray();
        JSONArray jsonArrayMail = new JSONArray();
        JSONArray jsonArrayYahoo = new JSONArray();
        JSONArray jsonArrayYandex = new JSONArray();
        JSONArray jsonArrayCoordinates = new JSONArray();
        JSONArray jsonArrayFirstLaunchDate = new JSONArray();
        JSONArray jsonArrayUnreadMessageCount = new JSONArray();

        JSONObject jsonObject = new JSONObject();

        jsonArrayGmail.add("null");
        jsonArrayGmail.add("null");
        jsonArrayGmail.add("true");
        jsonObject.put("gmail", jsonArrayGmail);

        jsonArrayMail.add("null");
        jsonArrayMail.add("null");
        jsonArrayMail.add("true");
        jsonObject.put("mail", jsonArrayMail);

        jsonArrayYahoo.add("null");
        jsonArrayYahoo.add("null");
        jsonArrayYahoo.add("true");
        jsonObject.put("yahoo", jsonArrayYahoo);

        jsonArrayYandex.add("null");
        jsonArrayYandex.add("null");
        jsonArrayYandex.add("true");
        jsonObject.put("yandex", jsonArrayYandex);

        jsonArrayCoordinates.add(coordinatesX);
        jsonArrayCoordinates.add(coordinatesY);
        jsonObject.put("coordinates", jsonArrayCoordinates);

        jsonArrayFirstLaunchDate.add(new Date().getTime());// Дата первого запуска программы
        jsonArrayFirstLaunchDate.add(trial_bool);
        jsonArrayFirstLaunchDate.add(license_bool);
        jsonObject.put("firstLaunchDate", jsonArrayFirstLaunchDate);

        jsonArrayUnreadMessageCount.add(unreadMessageCount0);
        jsonArrayUnreadMessageCount.add(unreadMessageCount1);
        jsonArrayUnreadMessageCount.add(unreadMessageCount2);
        jsonArrayUnreadMessageCount.add(unreadMessageCount3);
        jsonObject.put("unreadMessageCaunt", jsonArrayUnreadMessageCount);


        dataSaver.setDataToFile(securitySettings.encrypt(jsonObject.toString())); // Здесь всё кодируеться и записываеться в файл

    }



    // Распарсивает Json
    public List<String> readJson(String str) {

        org.json.simple.parser.JSONParser jsonParser = new org.json.simple.parser.JSONParser();

        Object object = null;
        try {
            object = jsonParser.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        JSONObject jsonObject = (JSONObject) object;

        JSONArray jsonObjectGmail = (JSONArray) jsonObject.get("gmail"); // Сначала отбираем по параметру mail or yandex
        JSONArray jsonObjectMail = (JSONArray) jsonObject.get("mail");
        JSONArray jsonObjectYahoo = (JSONArray) jsonObject.get("yahoo");
        JSONArray jsonObjectYandex = (JSONArray) jsonObject.get("yandex");
        JSONArray jsonArrayCoordinates = (JSONArray) jsonObject.get("coordinates");
        JSONArray jsonArrayFirstLaunchDate = (JSONArray) jsonObject.get("firstLaunchDate");
        JSONArray jsonArrayUnreadMessageCount = (JSONArray) jsonObject.get("unreadMessageCaunt");



        gmail_log = jsonObjectGmail.get(0).toString();
        gmail_password = jsonObjectGmail.get(1).toString();
        gmail_checkbox = String.valueOf(jsonObjectGmail.get(2));

        mail_log = jsonObjectMail.get(0).toString();
        mail_password = jsonObjectMail.get(1).toString();
        mail_checkbox = String.valueOf(jsonObjectMail.get(2));

        yahoo_log = jsonObjectYahoo.get(0).toString();
        yahoo_password = jsonObjectYahoo.get(1).toString();
        yahoo_checkbox = String.valueOf(jsonObjectYahoo.get(2));

        yandex_log = jsonObjectYandex.get(0).toString();
        yandex_password = jsonObjectYandex.get(1).toString();
        yandex_checkbox = String.valueOf(jsonObjectYandex.get(2));

        coordinatesX = (double) jsonArrayCoordinates.get(0);
        coordinatesY = (double) jsonArrayCoordinates.get(1);

        firstLaunchDate = (long) jsonArrayFirstLaunchDate.get(0);
        trial_bool = (boolean) jsonArrayFirstLaunchDate.get(1);
        license_bool = (boolean) jsonArrayFirstLaunchDate.get(2);

        unreadMessageCount0 = Integer.parseInt(jsonArrayUnreadMessageCount.get(0).toString());
        unreadMessageCount1 = Integer.parseInt(jsonArrayUnreadMessageCount.get(1).toString());
        unreadMessageCount2 = Integer.parseInt(jsonArrayUnreadMessageCount.get(2).toString());
        unreadMessageCount3 = Integer.parseInt(jsonArrayUnreadMessageCount.get(3).toString());

        // Это для возврата всех расшифрованых и распарсеных данных, для прямого использования в конструкторах классов
        // Проверки почты и ВК сообщений, List потом можно поменять на что то другое
        List<String> dataAlredyReadAndParsed = new ArrayList<String>();

        dataAlredyReadAndParsed.add(gmail_log);
        dataAlredyReadAndParsed.add(gmail_password);
        dataAlredyReadAndParsed.add(String.valueOf(gmail_checkbox));

        dataAlredyReadAndParsed.add(mail_log);
        dataAlredyReadAndParsed.add(mail_password);
        dataAlredyReadAndParsed.add(String.valueOf(mail_checkbox));

        dataAlredyReadAndParsed.add(yahoo_log);
        dataAlredyReadAndParsed.add(yahoo_password);
        dataAlredyReadAndParsed.add(String.valueOf(yahoo_checkbox));

        dataAlredyReadAndParsed.add(yandex_log);
        dataAlredyReadAndParsed.add(yandex_password);
        dataAlredyReadAndParsed.add(String.valueOf(yandex_checkbox));

        dataAlredyReadAndParsed.add(String.valueOf(coordinatesX));
        dataAlredyReadAndParsed.add(String.valueOf(coordinatesY));

        dataAlredyReadAndParsed.add(String.valueOf(firstLaunchDate));
        dataAlredyReadAndParsed.add(String.valueOf(trial_bool));
        dataAlredyReadAndParsed.add(String.valueOf(license_bool));

        dataAlredyReadAndParsed.add(String.valueOf(unreadMessageCount0));
        dataAlredyReadAndParsed.add(String.valueOf(unreadMessageCount1));
        dataAlredyReadAndParsed.add(String.valueOf(unreadMessageCount2));
        dataAlredyReadAndParsed.add(String.valueOf(unreadMessageCount3));

        return dataAlredyReadAndParsed;
    }

    public static void setGmail_log(String string) {
        gmail_log = string;
    }

    public static void setGmail_password(String string) {
        gmail_password = string;
    }

    public static void setMail_log(String string) {
        mail_log = string;
    }

    public static void setMail_password(String string) {
        mail_password = string;
    }

    public static void setYahoo_log(String string) {
        yahoo_log = string;
    }

    public static void setYahoo_password(String string) {
        yahoo_password = string;
    }

    public static void setYandex_log(String string) {
        yandex_log = string;
    }

    public static void setYandex_password(String string) {
        yandex_password = string;
    }

    public static void setCoordinatesX(Double x){
        coordinatesX = x;
    }

    public static void setCoordinatesY(Double y){
        coordinatesY = y;
    }

    public static void setTrial_bool(boolean bool){ trial_bool = bool; }

    public static void setLicense_bool(boolean bool){ license_bool = bool; }

    public void setGmail_checkbox(boolean bool) { gmail_checkbox = String.valueOf(bool); }

    public void setMail_checkbox(boolean bool) { mail_checkbox = String.valueOf(bool); }

    public void setYahoo_checkbox(boolean bool) { yahoo_checkbox = String.valueOf(bool); }

    public void setYandex_checkbox(boolean bool) { yandex_checkbox = String.valueOf(bool); }

    public static void setUnreadMessageCount(int x, int y, int z, int v){
        unreadMessageCount0 = x;
        unreadMessageCount1 = y;
        unreadMessageCount2 = z;
        unreadMessageCount3 = v;
    }
}