package com.registrationWindow;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Sergei on 27.09.2016.
 * Класс для отправки POST запроса на сайт.
 */
public class HttpRequest {
    private static String type = "application/x-www-form-urlencoded";

    void getKey(String params, String url){

    try {
        URL u = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) u.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        conn.setDoOutput(true);
        conn.setRequestProperty("Content-Type", type);
        conn.setRequestProperty("Content-Length", String.valueOf(params.length()));
    }catch (IOException ex){
        ex.printStackTrace();
    }

    }

    /********************************************************************************/
    // метод возвращает ответ сайта.
    // на вход строка спараметрами и url, на который их отправить надо.
    String excutePost(String params, String url){
        StringBuffer response = new StringBuffer();
        try {
            URL u = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) u.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", type);
            conn.setRequestProperty("Content-Length", String.valueOf(params.length()));

        OutputStream os = conn.getOutputStream();
        os.write(params.getBytes());
        //Get Response
        InputStream is = conn.getInputStream();

        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
        String line;
        while((line = rd.readLine()) != null) {
            response.append(line);
            response.append('\r');
        }
        rd.close();
        } catch (IOException e){
            e.printStackTrace();
        }
        return response.toString();

    }
}
