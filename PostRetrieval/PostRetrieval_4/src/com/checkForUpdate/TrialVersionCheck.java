package com.checkForUpdate;



import com.SaveLoadSequrity.JsonClass;
import com.registrationWindow.WindowForRegistration;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.Date;
import java.util.List;

/**
 * Created by Sergei on 03.08.2016.
 */
public class TrialVersionCheck {

    private static long FIVE_DAYS = 432000000L; //пять суток в милисекундах
    private static List<String> list;

    public TrialVersionCheck(List<String> list) {

        this.list = list;

        for (String x: list
             ) {
            System.out.println(x);
        }
    }


    /*************************************************************************/
    public static long readDate(){

        return Long.parseLong(list.get(14)); // дата первого запуска
    }

    public static boolean readFlag(){

        return Boolean.parseBoolean(list.get(15));
    }

    public static boolean readLicenseFlag(){

        return Boolean.parseBoolean(list.get(16));
    }


    /*************************************************************************/
    // возвращает текущую дату системы при запуске программы.
    private static long currentDate(){
        long currentDate;

        Date date = new Date();
        currentDate = date.getTime();

        return currentDate;
    }


    /*************************************************************************/
    // проверка закончился ли триал период (5 суток).
    private static boolean isTrialFinished(){

        long currentDate = currentDate();

        long installationDate = readDate();

        boolean flag;

        flag = readFlag();

        if(flag == true) {

            if ((currentDate - installationDate) > FIVE_DAYS)
                flag = false;

            else flag = true;

            // Перезапись флага.
            JsonClass jsonClass = new JsonClass();
            jsonClass.setTrial_bool(flag);
            jsonClass.createJson();

        }

        return flag;

    }


    /*************************************************************************/
    // информационное окно для пользователя, после чего програма не запускается.(Окно посылает и обрабатывает серверерные запросы)
    private static void showDialog() throws Exception {
        WindowForRegistration windowForRegistration = new WindowForRegistration();
        windowForRegistration.start(new Stage());

   //     Application.launch(WindowForRegistration.class);
    }

    /*************************************************************************/
    public static boolean check() {
        boolean flag = true;
        if(!checkLicenseFlag()) {// Если Главный Флаг true, то всё остальное уже не проверяем, иначе....

            if (isTrialFinished())
                // надо вызвать программу!!!)
                flag = true;
            else {
                try {
                    showDialog();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                flag = false;
            }
        }
        return flag;
    }

    // Узнаем значение главного флага
    private static boolean checkLicenseFlag() {

        boolean licenseFlag = readLicenseFlag();

        return licenseFlag;
    }
}

