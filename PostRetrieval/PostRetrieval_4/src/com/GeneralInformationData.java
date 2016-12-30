package com;

import java.io.File;
import java.io.IOException;

/**
 * Created by olexiy86 on 11/2/16.
 *
 * Сюда программа обращаеться для получения всех необходимых переменных - пароли и тд..
 */
public class GeneralInformationData {

    public static final double currentVersion = 0.5; // Текущая версия программы

    public static final String pathToFileSave = new File(".").getAbsolutePath()+"\\Save.txt";   //"C:\\Programming\\Save.txt";

    public static final String urlToCheckVersion = "http://spacelore.dp.ua/programming/check_version.txt";

    // адреса для отправки POST запросов для регистрации в классе HttpRequest.
    public static String urlToGetKey = "http://www.spacelore.dp.ua/paymentsystem/postretrieval_order/get_key.php";
    public static String urlToCheckKey = "http://www.spacelore.dp.ua/paymentsystem/postretrieval_order/key_varification.php";

    // Задаеться классом MainWindow при загрузке, или перемещении окна программы.
    public static Double currentXPosition = 0.0; // Используеться классом GeneralPane для определения текущей позиции окна программы на мониторе


    public static String getOs(){

        String os;

        os = System.getProperty("os.name").toLowerCase();

        return os;
    }

    // Return path to current directory, where was being install this programm.
    public static String getCurrentPath(){

        String path = null;

        try {
            path = new File(".").getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return path + "/Updater.jar";

//        System.out.println(path + "");

    }
}
