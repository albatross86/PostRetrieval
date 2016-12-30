package com;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Created by Алексей on 18.12.2016.
 * Класс перезапускает программу. Это решение для того чтоб сохранить прозрачность Главного окна.
 * Работает только для JAR файла, в Idea НЕ РАБОТАЕТ !!!!
 */
public class RestartProgramm {

    public RestartProgramm(){
        restart();
    }


    public static void restart() {

        try {
       //     Runtime.getRuntime().exec("java -jar " + new File(".").getAbsolutePath() + "\\PostRetrieval_4.jar");

            Runtime.getRuntime().exec("CommaN.exe");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }
}
