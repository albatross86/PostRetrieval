package com.SaveLoadSequrity;


import com.GeneralInformationData;

import java.io.*;

/**
 * Created by User on 03.06.2016.
 */
public class DataSaver {


    private static File file;

    /********************************************************************************/
    // метод для создания файла в домашней папке пользователя
    private static File createFile(){
        String filePath = GeneralInformationData.pathToFileSave;
        file = new File(filePath);
        // если файла не существует - создаем.
        if (!file.exists()) {
            // создаем вместе с папками.
            file.getParentFile().mkdirs();
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Somthigs is wrong..." + e.getMessage());
            }
        }
        return file;
    }

    /********************************************************************************/
    // метод для вненсении информации в файл
    public  void setDataToFile(String info){

        SecuritySettings securitySettings = new SecuritySettings();

        // Здесь зашифруем
      //  info = securitySettings.encrypt(info);
        // создается файл
       file = createFile();
        // PrintWriter для внесения новой запси с новой строки.
        PrintWriter writer = null;
        try {
            // BufferedWriter для того, чтобы постоянно не обращаться к жесткому диску
            writer = new PrintWriter(new BufferedWriter((new FileWriter(file, false))));
            // Запись в файл
            writer.println(info);
            // очистка буфера
            writer.flush();
        } catch (IOException e) {
            System.out.println("Somthigs is wrong..." + e.getMessage());
        } finally {
            // закрывам поток
            writer.close();
        }
    }
}
