package com.checkForUpdate;


import com.GeneralInformationData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Alex on 24.06.2016.
 */
public class CheckForUpdates {

    double current_version = GeneralInformationData.currentVersion;

    double version_available;

    public void checkUpdate()  {

        download_check_version_file();

        //parse_number_version();

    }

    private void parse_number_version(String number_version) {

        String str = number_version.substring(9, 12);

         version_available = Double.parseDouble(str);

    }

    private void download_check_version_file() {

        URL url = null;
        URLConnection urlConn = null;

        try
        {
            url  = new URL(GeneralInformationData.urlToCheckVersion);

            urlConn = url.openConnection();

        } catch( IOException e){
            System.out.println("Can't connect to the provided URL:" + e.toString() );
        }

        try( InputStreamReader inStream =
                     new InputStreamReader (urlConn.getInputStream(), "UTF8");
             BufferedReader buff  = new BufferedReader(inStream)){

            String currentLine;

            while ((currentLine = buff.readLine())!= null ){

                if (currentLine.contains("[version]")) {

                    parse_number_version(currentLine);

                    version_comparison();

                }
                break;
                //  System.out.println(currentLine);
            }
        } catch(MalformedURLException ex){
            System.out.println ("Check the spelling of the URL" + ex.getMessage());
        }
        catch(IOException  ioe){
            System.out.println("Can't read from the Internet: "+
                    ioe.toString());
        }
    }

    public void version_comparison(){

        if(version_available > current_version){

            // Предлагаем скачать новую версию
            try {
                Runtime.getRuntime().exec("java -jar " + GeneralInformationData.getCurrentPath());

            } catch (IOException e) {
                e.printStackTrace();
            }


            System.exit(0);

        }
    }
}

