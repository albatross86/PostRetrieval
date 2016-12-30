package com.controller;

import com.GeneralInformationData;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by olexiy86 on 11/2/16.
 */
public class Browser {

    public void openBrowser(String link){

        choiseBrowser(link);
    }

    // Открывает браузер в зависимости от платформы

    private void choiseBrowser(String link) {



            if(GeneralInformationData.getOs().contains("linux"))  openLinuxBrowser(link);
            if(GeneralInformationData.getOs().contains("windows")) openWindowsBrowser(link);
            if(GeneralInformationData.getOs().contains("mac")) openMacBrowser(link);




    }

    private void openLinuxBrowser(String link) {

        Runtime rt = Runtime.getRuntime();
        String[] browsers = {"epiphany", "firefox", "mozilla", "konqueror",
                "netscape","opera","links","lynx"};

        StringBuffer cmd = new StringBuffer();
        for (int i=0; i<browsers.length; i++)
            cmd.append( (i==0  ? "" : " || " ) + browsers[i] +" \"" + link + "\" ");

        try {
            rt.exec(new String[] { "sh", "-c", cmd.toString() });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openWindowsBrowser(String link) {

        System.out.println("Open browser");
        try {
            Desktop.getDesktop().browse(new URI(link));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

    }

    private void openMacBrowser(String link) {
        Runtime rt = Runtime.getRuntime();
        try {
            rt.exec( "open" + link);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
