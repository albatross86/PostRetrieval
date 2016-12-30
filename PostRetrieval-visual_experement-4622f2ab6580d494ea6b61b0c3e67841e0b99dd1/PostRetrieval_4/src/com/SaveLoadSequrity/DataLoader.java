package com.SaveLoadSequrity;

import com.GeneralInformationData;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * Created by User on 06.06.2016.
 */
public class DataLoader {
    private static File file;

    private List<String> readyData;

    /********************************************************************************/
    // Возвращает список со строкими в файле.
    public static String getDataFromFile() throws IOException {
        String line;
        // для хранения строк из файла
        String lines = "";

            BufferedReader reader = new BufferedReader(new FileReader(GeneralInformationData.pathToFileSave));

            while ((line = reader.readLine()) != null) {
                lines = lines + line;
            }

        return lines;
    }

    public List<String> getReadyData() {

        // Здесь сначала считываем файл, а если его нету, то создаём и снова считываем
        JsonClass jsonClass = new JsonClass();
        String loadedData = null;
        try {
            loadedData = getDataFromFile();
        } catch (Exception e) {

            //  я сначала создам файл с полями null
            jsonClass.createStandartJsonFile(); // Создаём и записываем Json файл с полями null

            try {
                loadedData = getDataFromFile();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        // Расшифруем
        SecuritySettings securitySettings = new SecuritySettings();
        loadedData = securitySettings.decrypt(loadedData);

        readyData = jsonClass.readJson(loadedData);

        return readyData;
    }
}